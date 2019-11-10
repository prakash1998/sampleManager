package com.pra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseWindowController;
import com.pra.view.AdminHomeWindow;

@Service
public class AdminHomeController extends BaseWindowController<AdminHomeWindow>{
	
	@Autowired
	private UserController userControl;
	
	@Autowired 
	private BackupController backupControl;
	
	@Autowired
	private LogInController loginControl;
	
	public void navigateToLogIn() {
		loginControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToUser() {
		userControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToBackup() {
		backupControl.openWindow();
		this.closeWindow();
	}
	
}
