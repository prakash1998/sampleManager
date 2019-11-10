package com.pra.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.pra.controller.AdminHomeController;
import com.pra.view.basewindows.ParentWindow;

import net.miginfocom.swing.MigLayout;

@Configurable(dependencyCheck = true)
@Component
public class AdminHomeWindow extends ParentWindow<AdminHomeController> {

	@Autowired
	AdminHomeController adminHomeControl;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblHome;
	private JButton btnExit;
	private JButton btnPasswordReset;
	private JButton btnBackupFolder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHomeWindow frame = new AdminHomeWindow();
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
	public AdminHomeWindow() {

		this.lblHome = new JLabel("Home");
		headerPanel.add(this.lblHome);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][][][]"));

		this.btnPasswordReset = new JButton("Password Reset");
		this.btnPasswordReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminHomeControl.navigateToUser();
			}
		});
		contentPanel.add(this.btnPasswordReset, "cell 0 0,growx");

		this.btnBackupFolder = new JButton("Backup Folder");
		this.btnBackupFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminHomeControl.navigateToBackup();
			}
		});
		contentPanel.add(this.btnBackupFolder, "cell 0 1,growx");

		this.btnExit = new JButton("Back To LogIn");
		this.btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					adminHomeControl.navigateToLogIn();
			}
		});
		contentPanel.add(this.btnExit, "cell 0 2,growx");

	}

	@Override
	protected void resetWindow() {
		// TODO Auto-generated method stub

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
