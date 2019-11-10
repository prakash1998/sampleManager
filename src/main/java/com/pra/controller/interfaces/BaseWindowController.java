package com.pra.controller.interfaces;

import org.springframework.beans.factory.annotation.Autowired;

import com.pra.controller.HomeController;
import com.pra.view.basewindows.ParentWindow;


@SuppressWarnings("rawtypes")
public abstract class BaseWindowController<W extends ParentWindow> implements BaseController {
	
	@Autowired
	protected W window;
	
	@Autowired
	protected HomeController homeControl;

	@Override
	public void openWindow() {
		window.showWindow();
	}

	@Override
	public void closeWindow() {
		window.closeWindow();
	}
	
	@Override
	public void navigateToHome() {
		homeControl.openWindow();
		this.closeWindow();
	}

}
