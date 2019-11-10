package com.pra.view.basewindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.springframework.dao.DuplicateKeyException;

import com.pra.controller.interfaces.BaseEntityController;
import com.pra.model.BaseModel;
import com.pra.utils.view.CommonOptionPanes;

@SuppressWarnings({ "rawtypes", "serial" })
public abstract class BaseEntityWindow<T extends BaseModel<?>, C extends BaseEntityController> extends ParentWindow<C> {

	protected JButton btnSave;
	protected JButton btnUpdate;
	protected JButton btnDelete;
	protected JButton btnCancel;

	protected T modelObject;

	public BaseEntityWindow() {

		this.btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateAndCreateData())
					control.navigateToHome();

			}
		});

		this.btnUpdate = new JButton("Update");
		this.btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateAndUpdateData())
					control.navigateToHome();
			}
		});

		this.btnUpdate = new JButton("Delete");
		this.btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CommonOptionPanes.showExitPrompt())
					if (validateAndDeleteData())
						control.navigateToHome();
			}
		});

		this.btnCancel = new JButton("Cancel");
		this.btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToHome();
			}
		});

	}

	@SuppressWarnings("unchecked")
	protected boolean validateAndCreateData() {
		boolean valid = validateData();
		if (valid) {
			try {
				control.createWithBackup(getDataFromUI());
			} catch (DuplicateKeyException ex) {
				JOptionPane.showMessageDialog(null, "Duplicate Data Found");
				return false;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
				return false;
			}
		} else
			JOptionPane.showMessageDialog(null, "Please Enter Apropriate Data");
		return valid;
	}

	@SuppressWarnings("unchecked")
	protected boolean validateAndUpdateData() {
		boolean valid = validateData();
		if (valid) {
			try {
				control.updateWithBackup(getDataFromUI());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please Enter Apropriate Data");
		}
		return valid;
	}

	@SuppressWarnings({ "unchecked" })
	protected boolean validateAndDeleteData() {
		try {
			control.deleteWithBackup(modelObject);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
		return true;
	}

	public void showWindow(T obj) {
		this.modelObject = obj;
		super.showWindow();
		this.btnSave.setEnabled(false);
		this.btnUpdate.setEnabled(true);
		this.setDataToUI(obj);
		this.setVisible(true);
		this.getRootPane().setDefaultButton(this.btnUpdate);
	}

	@Override
	public void showWindow() {
		super.showWindow();
		this.btnSave.setEnabled(true);
		this.btnUpdate.setEnabled(false);
		this.getRootPane().setDefaultButton(this.btnSave);
	}

	@Override
	public void closeWindow() {
		this.modelObject = null;
		super.closeWindow();
	}

	protected abstract boolean validateData();

	protected abstract T getDataFromUI();

	protected abstract void setDataToUI(T obj);

}
