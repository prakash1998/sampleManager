package com.pra.controller;

import static com.pra.utils.commons.SecurityUtils.getMd5;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseEntityController;
import com.pra.model.User;
import com.pra.repositories.UserRepository;
import com.pra.view.UserWindow;

@Service
public class UserController extends BaseEntityController<User, String, UserRepository, UserWindow> {

	private static final String ADMIN_PASSWORD = "admin";

	@Autowired
	private LogInController logInControl;
	@Autowired
	private AdminHomeController adminControl;

	public void create(User user) {
		user.setPassword(getMd5(user.getPassword()));
		repo.save(user);
	}

	public void update(User user) {
		user.setPassword(getMd5(user.getPassword()));
		repo.save(user);
	}

	public boolean validateUser(User user) {
		Optional<User> u = repo.findById(user.getUserName());
		if (u.isPresent()) {
			return u.get().getPassword().equals(getMd5(user.getPassword()));
		}
		return false;
	}

	public boolean validateAdmin(User user) {
		return user.getPassword().equals(getMd5(getMd5(ADMIN_PASSWORD)));
	}

	public void navigateToLogIn() {
		logInControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToAdmin() {
		adminControl.openWindow();
		this.closeWindow();
	}

}
