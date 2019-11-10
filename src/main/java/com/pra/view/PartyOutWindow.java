package com.pra.view;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import com.pra.controller.PartyOutController;
import com.pra.model.PartyOut;
import com.pra.utils.view.SwingComponentUtils;
import com.pra.view.basewindows.BaseEntityWindow;

import net.miginfocom.swing.MigLayout;

@Component
public class PartyOutWindow extends BaseEntityWindow<PartyOut,PartyOutController> {

	private JTextField textFieldPartyName;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PartyOutWindow frame = new PartyOutWindow();
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
	public PartyOutWindow() {
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblPartyName = new JLabel("Party Name :");
		contentPanel.add(lblPartyName, "cell 0 0");
		
		textFieldPartyName = new JTextField();
		contentPanel.add(textFieldPartyName, "flowx,cell 1 0,growx");
		textFieldPartyName.setColumns(10);
		
		
		JLabel lblPartyDetails = new JLabel("Party Details");
		headerPanel.add(lblPartyDetails);
		

	}

	@Override
	protected void postConstruction() {
		contentPanel.add(btnSave, "flowx,cell 1 2");
		contentPanel.add(btnUpdate, "cell 1 2");
		contentPanel.add(btnCancel, "cell 1 2");
	}

	@Override
	protected void resetWindow() {
		SwingComponentUtils.clearTextInput(this.textFieldPartyName);
	}




	@Override
	public void initFromController() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean validateData() {
		if (this.textFieldPartyName.getText().trim().isEmpty())
			return false;
		return true;
	}

	@Override
	protected PartyOut getDataFromUI() {
		PartyOut.PartyOutBuilder builder = PartyOut.builder();
		
		if (this.modelObject != null)
			builder = builder.id(this.modelObject.getId());
		return builder
				.name(this.textFieldPartyName.getText())
				.build();
	}


	@Override
	protected void setDataToUI(PartyOut obj) {
		this.textFieldPartyName.setText(obj.getName());
	}

}
