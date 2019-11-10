package com.pra.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pra.controller.LogInController;
import com.pra.model.User;
import com.pra.utils.view.CommonOptionPanes;
import com.pra.utils.view.SwingComponentUtils;
import com.pra.view.basewindows.ParentWindow;

import net.miginfocom.swing.MigLayout;

@Component
public class LogInWindow extends ParentWindow<LogInController> {

	@Autowired
	private LogInController loginControl;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldUserName;
	private JPasswordField passwordFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInWindow frame = new LogInWindow();
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
	public LogInWindow() {

		contentPanel.setLayout(new MigLayout("", "[][][grow]", "[][][][]"));

		JLabel lblUsername = new JLabel("userName :");
		contentPanel.add(lblUsername, "cell 0 0");

		textFieldUserName = new JTextField();
		this.textFieldUserName.setText("admin");
		this.textFieldUserName.setEditable(false);
		contentPanel.add(textFieldUserName, "cell 2 0,growx");
		textFieldUserName.setColumns(10);

		JLabel lblPassword = new JLabel("password :");
		contentPanel.add(lblPassword, "cell 0 1");

		JButton btnLogIn = new JButton("LogIn");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setUserName(textFieldUserName.getText());
				user.setPassword(passwordFieldPassword.getText());
				if (loginControl.validateUser(user))
					loginControl.navigateToHome();
				else {
					if(loginControl.validateAdmin(user)) {
						JOptionPane.showMessageDialog(null, "Welcome Prakash");
						loginControl.navigateToAdmin();
					}
					else
						JOptionPane.showMessageDialog(null, "Wrong Password");
				}
			}
		});
		
		this.passwordFieldPassword = new JPasswordField();
		contentPanel.add(this.passwordFieldPassword, "cell 2 1,growx");
		this.getRootPane().setDefaultButton(btnLogIn);
		contentPanel.add(btnLogIn, "flowx,cell 2 3");

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CommonOptionPanes.showExitPrompt())
					loginControl.shutDownApp();
			}
		});
		contentPanel.add(btnExit, "cell 2 3");
	
		JLabel lblLogIn = new JLabel("Log In");
		headerPanel.add(lblLogIn);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new java.awt.Component[]{this.passwordFieldPassword, btnLogIn, btnExit, headerPanel, lblLogIn, footerPanel, contentPanel, lblUsername, this.textFieldUserName, lblPassword, leftSidePanel, rightSidePanel}));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new java.awt.Component[]{this.passwordFieldPassword, btnLogIn, btnExit, contentPanel, getContentPane(), headerPanel, lblLogIn, footerPanel, lblUsername, this.textFieldUserName, lblPassword, leftSidePanel, rightSidePanel}));
	}

	@Override
	protected void resetWindow() {
		SwingComponentUtils.clearTextInput(this.passwordFieldPassword);
	}

	@Override
	protected void postConstruction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initFromController() {
		// TODO Auto-generated method stub
		
	}
}
