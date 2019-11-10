package com.pra.view;

import static com.pra.utils.view.SwingComponentUtils.anyBlank;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.stereotype.Component;

import com.pra.controller.UserController;
import com.pra.model.User;
import com.pra.utils.view.SwingComponentUtils;
import com.pra.view.basewindows.BaseEntityWindow;

import net.miginfocom.swing.MigLayout;

@Component
public class UserWindow extends BaseEntityWindow<User, UserController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldUserName;
	private JPasswordField passwordFieldPassword;
	private JPasswordField passwordFieldConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserWindow frame = new UserWindow();
					frame.setVisible(true);
				} catch (Exception e) { // ex1
					JOptionPane.showMessageDialog(null, "error--c1/ex1" + e.getMessage());
					e.printStackTrace();
				}

			}
		});
	}

	public void init() {

	}

	/**
	 * Create the frame.
	 */
	public UserWindow() {

		contentPanel.setLayout(new MigLayout("", "[][][grow]", "[][][][][]"));

		JLabel lblUsername = new JLabel("userName :");
		contentPanel.add(lblUsername, "cell 0 0,alignx right");

		textFieldUserName = new JTextField();
		this.textFieldUserName.setText("admin");
		this.textFieldUserName.setEditable(false);
		contentPanel.add(textFieldUserName, "cell 2 0,growx");
		textFieldUserName.setColumns(10);

		JLabel lblPassword = new JLabel("password :");
		contentPanel.add(lblPassword, "cell 0 1,alignx right");

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (passwordFieldPassword.getText().equals(passwordFieldConfirm.getText())) {
					User user = new User();
					user.setUserName(textFieldUserName.getText());
					user.setPassword(passwordFieldPassword.getText());
					control.update(user);
					JOptionPane.showMessageDialog(null, "User Saved");
					control.navigateToLogIn();
				} else {
					JOptionPane.showMessageDialog(null, "Password Mismatched");
				}
			}
		});

		this.passwordFieldPassword = new JPasswordField();
		contentPanel.add(this.passwordFieldPassword, "cell 2 1,growx");

		JLabel lblConfirm = new JLabel("confirm :");
		contentPanel.add(lblConfirm, "cell 0 2,alignx right");

		this.passwordFieldConfirm = new JPasswordField();
		contentPanel.add(this.passwordFieldConfirm, "cell 2 2,growx");
		this.getRootPane().setDefaultButton(btnUpdate);
		contentPanel.add(btnUpdate, "flowx,cell 2 3");

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					control.navigateToAdmin();
			}
		});
		contentPanel.add(btnExit, "cell 2 3");
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new java.awt.Component[] { this.passwordFieldPassword, this.passwordFieldConfirm, btnUpdate, btnExit,
						lblUsername, this.textFieldUserName, lblPassword, lblConfirm }));

		JLabel lblUser = new JLabel("User");
		headerPanel.add(lblUser);
		getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new java.awt.Component[] { this.passwordFieldPassword,
						this.passwordFieldConfirm, btnUpdate, btnExit, headerPanel, lblUser, footerPanel, contentPanel,
						lblUsername, this.textFieldUserName, lblPassword, lblConfirm, leftSidePanel, rightSidePanel }));

	}

	@Override
	protected void resetWindow() {
		SwingComponentUtils.clearTextInput(this.passwordFieldPassword);
		SwingComponentUtils.clearTextInput(this.passwordFieldConfirm);
	}

	@Override
	protected void postConstruction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initFromController() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean validateData() {
		if (anyBlank(this.textFieldUserName, this.passwordFieldConfirm, this.passwordFieldPassword))
			return false;
		return true;
	}

	@Override
	protected User getDataFromUI() {
		return null;
	}

	@Override
	protected void setDataToUI(User obj) {
		// TODO Auto-generated method stub

	}

}
