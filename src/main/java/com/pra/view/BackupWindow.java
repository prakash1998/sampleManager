package com.pra.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import org.springframework.stereotype.Component;

import com.pra.controller.BackupController;
import com.pra.model.Constants;
import com.pra.view.basewindows.BaseEntityWindow;

import net.miginfocom.swing.MigLayout;

@Component
public class BackupWindow extends BaseEntityWindow<Constants, BackupController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldBackupPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackupWindow frame = new BackupWindow();
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
	public BackupWindow() {

		contentPanel.setLayout(new MigLayout("", "[]20[grow]", "[][][][][]"));

		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				jfc.setDialogTitle("Select Backup Folder");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				// FileNameExtensionFilter filter = new FileNameExtensionFilter("Ms-Access
				// Database", "accdb");
				// jfc.addChoosableFileFilter(filter);

				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					textFieldBackupPath.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		contentPanel.add(btnSelect, "cell 1 0,alignx right");

		JLabel lblBackupPath = new JLabel("Backup Path :");
		contentPanel.add(lblBackupPath, "cell 0 1,alignx trailing");

		this.textFieldBackupPath = new JTextField();
		this.textFieldBackupPath.setEditable(false);
		contentPanel.add(this.textFieldBackupPath, "cell 1 1,growx");
		this.textFieldBackupPath.setColumns(10);

		JButton btnUpdate2 = new JButton("Update");
		btnUpdate2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateAndUpdateData())
					control.navigateToAdmin();
			}
		});
		contentPanel.add(btnUpdate2, "flowx,cell 1 3");

		JButton btnCancel2 = new JButton("Cancel");
		btnCancel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToAdmin();
			}
		});
		contentPanel.add(btnCancel2, "cell 1 3");

		JLabel lblUser = new JLabel("User");
		headerPanel.add(lblUser);

	}

	@Override
	protected void resetWindow() {

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
	protected void setDataToUI(Constants obj) {
		this.textFieldBackupPath.setText(obj.getConstValue());
	}

	@Override
	protected Constants getDataFromUI() {
		return Constants.builder().constName(BackupController.BACKUP_PATH_KEY)
				.constValue(this.textFieldBackupPath.getText()).build();
	}

	@Override
	protected boolean validateData() {
		if (this.textFieldBackupPath.getText().trim().isEmpty())
			return false;
		return true;
	}
}
