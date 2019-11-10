package com.pra.controller.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pra.backup.BackupThread;
import com.pra.controller.HomeController;
import com.pra.model.BaseModel;
import com.pra.view.basewindows.BaseEntityWindow;


@SuppressWarnings("rawtypes")
public abstract class BaseEntityController<T extends BaseModel<PK>,PK,R extends JpaRepository<T, PK>,W extends BaseEntityWindow> implements BaseController {
	
	@Autowired
	protected R repo;
	
	@Autowired
	protected W window;
	
	@Autowired
	protected HomeController homeControl;

	@Override
	public void openWindow() {
		window.showWindow();
	}
	
	@SuppressWarnings("unchecked")
	public void openWindow(T obj) {
		window.showWindow(obj);
	}
	
	public List<T> getAll() {
		return repo.findAll();
	}
	
	public Optional<T> getById(PK key) {
		return repo.findById(key);
	}
	
	protected void create(T obj) throws DuplicateKeyException{
		if(obj.primaryKey() != null && repo.findById(obj.primaryKey()).isPresent())
			throw new DuplicateKeyException("Record already Exist in database");
		repo.save(obj);
	}
	
	public final void createWithBackup(T obj) throws DuplicateKeyException{
		this.create(obj);
		BackupThread.backup(obj.getClass(), obj, BackupThread.SAVE);
	}
	
	protected void delete(T obj) {
		repo.deleteById(obj.primaryKey());
	}
	
	public final void deleteWithBackup(T obj) {
		this.delete(obj);
		BackupThread.backup(obj.getClass(), obj, BackupThread.DELETE);
	}
	
	protected void update(T obj) {
		repo.save(obj);
	}
	
	public final void updateWithBackup(T obj) {
		this.update(obj);
		BackupThread.backup(obj.getClass(), obj, BackupThread.UPDATE);
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
