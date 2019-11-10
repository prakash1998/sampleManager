package com.pra.controller.interfaces;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pra.controller.HomeController;
import com.pra.model.BaseModel;
import com.pra.view.basewindows.SimpleBaseDataWindow;

@SuppressWarnings("rawtypes")
public abstract class SimpleBaseDataController<T extends BaseModel<?>, EC extends BaseEntityController<T,?,?,?>, W extends SimpleBaseDataWindow>
		implements BaseController {

	@Autowired
	protected W window;

	@Autowired
	protected EC entityControl;

	@Autowired
	protected HomeController homeControl;

	@Override
	public void openWindow() {
		window.showWindow();
	}

	public List<T> getAll() {
		return entityControl.getAll();
	}
	
	public void navigateToEntityWindow() {
		entityControl.openWindow();
		this.closeWindow();
	}

	public void navigateToEntityWindow(T obj) {
		entityControl.openWindow(obj);
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
