package com.pra.controller.interfaces;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pra.controller.HomeController;
import com.pra.model.BaseModel;
import com.pra.reports.beans.ReportBean;
import com.pra.view.basewindows.BaseDataWindow;

@SuppressWarnings("rawtypes")
public abstract class BaseDataController<T extends BaseModel, B extends ReportBean<PK>, PK, R extends JpaRepository<T, PK>, EC extends BaseEntityController, W extends BaseDataWindow>
		implements BaseController {

	@Autowired
	protected R repo;

	@Autowired
	protected W window;

	@Autowired
	protected EC entityControl;

	@Autowired
	protected HomeController homeControl;

	@Override
	public void openWindow() {
		LocalDate startDate = LocalDate.now().minusDays(30);
		LocalDate endDate = LocalDate.now();
		window.showWindow(startDate, endDate);
	}
	
	public void openWindow(LocalDate startDate, LocalDate endDate) {
		window.showWindow(startDate, endDate);
	}

	public List<B> getBetweenDates(LocalDate startDate, LocalDate endDate) {
		return this.convertList(getBetweenDesc(startDate, endDate));
	}

	public abstract List<T> getBetweenDesc(LocalDate startDate, LocalDate endDate);

	public List<B> getAll() {
		return convertList(repo.findAll());
	}

	public abstract List<B> convertList(List<T> list);

	public T getById(PK id) {
		return repo.findById(id).orElse(null);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void navigateToEntityWindow(B obj) {
		entityControl.openWindow(getById(obj.getPrimaryKey()));
		this.closeWindow();
	}

	@Override
	public void navigateToHome() {
		homeControl.openWindow();
		this.closeWindow();
	}

	@Override
	public void closeWindow() {
		window.closeWindow();
	}

}
