package com.pra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pra.ApplicationShutdownManager;
import com.pra.controller.interfaces.BaseWindowController;
import com.pra.model.User;
import com.pra.view.LogInWindow;

@Service
public class LogInController extends BaseWindowController<LogInWindow> {

	@Autowired
	private UserController userControl;
	@Autowired
	private AdminHomeController adminHomeControl;
	@Autowired
	private ApplicationShutdownManager shutdownManager;
	
	public void shutDownApp() {
		this.closeWindow();
		shutdownManager.initiateShutdown(0);
	}

	public boolean validateUser(User user) {
		return userControl.validateUser(user);
	}

	public boolean validateAdmin(User user) {
		return userControl.validateAdmin(user);
	}
	
	public void navigateToAdmin() {
		adminHomeControl.openWindow();
		this.closeWindow();
	}

}
