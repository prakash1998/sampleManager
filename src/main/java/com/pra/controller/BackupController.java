package com.pra.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pra.backup.BackupThread;
import com.pra.controller.interfaces.BaseEntityController;
import com.pra.model.Constants;
import com.pra.repositories.ConstantsRepository;
import com.pra.view.BackupWindow;

@Service
public class BackupController extends BaseEntityController<Constants, String, ConstantsRepository, BackupWindow>{
	
	public static final String BACKUP_PATH_KEY = "backupFolder";
	public static final String BACKUP_FILE_NAME = "\\backup";
	
	public static String BACKUP_PATH = "";
	
	@Autowired
	private AdminHomeController adminControl;
	
	@PostConstruct
	public void loadBackupPath() {
		BACKUP_PATH = repo.findById(BACKUP_PATH_KEY).orElse(new Constants(BACKUP_PATH_KEY,"")).getConstValue()+BackupController.BACKUP_FILE_NAME;
	}

	public void navigateToAdmin() {
		loadBackupPath();
		BackupThread.refreshBackupPath(BackupController.BACKUP_PATH);
		adminControl.openWindow();
		this.closeWindow();
	}
	
}
