package com.pra.view;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import com.pra.controller.ProductController;
import com.pra.model.Product;
import com.pra.utils.view.SwingComponentUtils;
import com.pra.view.basewindows.BaseEntityWindow;

import net.miginfocom.swing.MigLayout;

@Component
public class ProductWindow extends BaseEntityWindow<Product, ProductController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldProductName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductWindow frame = new ProductWindow();
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
	public ProductWindow() {
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][]"));

		JLabel lblProductName = new JLabel("Product Name :");
		contentPanel.add(lblProductName, "cell 0 0");

		textFieldProductName = new JTextField();
		contentPanel.add(textFieldProductName, "flowx,cell 1 0,growx");
		textFieldProductName.setColumns(10);

		this.getRootPane().setDefaultButton(btnSave);

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
		SwingComponentUtils.clearTextInput(this.textFieldProductName);
	}

	@Override
	public void initFromController() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean validateData() {
		if (this.textFieldProductName.getText().trim().isEmpty())
			return false;
		return true;
	}

	@Override
	protected Product getDataFromUI() {
		Product.ProductBuilder builder = Product.builder();

		if (this.modelObject != null)
			builder = builder.id(this.modelObject.getId());
		return builder.name(this.textFieldProductName.getText()).build();
	}

	@Override
	protected void setDataToUI(Product obj) {
		this.textFieldProductName.setText(obj.getName());
	}

}
