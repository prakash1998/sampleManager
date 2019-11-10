package com.pra.view.basewindows;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.pra.controller.interfaces.SimpleBaseDataController;
import com.pra.model.BaseModel;
import com.pra.utils.view.datatable.DataTable;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings({ "rawtypes", "serial" })
public abstract class SimpleBaseDataWindow<T extends BaseModel<?>,C extends SimpleBaseDataController<T,?, ? extends SimpleBaseDataWindow>> extends ParentWindow<C> {
	
	protected JButton btnBack;
	
	protected DataTable<T> dataTablePane;
	private JLabel lblScreentitle;
	private JButton btnAdd;
	
	public SimpleBaseDataWindow() {
		contentPanel.setLayout(new BorderLayout(0, 0));
		headerPanel.setLayout(new MigLayout("", "[100][grow][100]", "[]"));
		
		this.btnBack = new JButton("Back");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToHome();
			}
		});
		headerPanel.add(this.btnBack, "cell 0 0,grow");
		
		this.lblScreentitle = new JLabel(this.setTitle());
		this.lblScreentitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		headerPanel.add(this.lblScreentitle, "cell 1 0,alignx center");
		
		this.btnAdd = new JButton("Add");
		this.btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToEntityWindow();
			}
		});
		headerPanel.add(this.btnAdd, "cell 2 0,grow");
		
	}
	
	protected abstract String setTitle();

	public void showWindow() {
		dataTablePane.setTableData(control.getAll());
		this.setVisible(true);
	}
	
	protected abstract Class<T> getBeanClass();
	
	@Override
	protected void postConstruction() {
		this.dataTablePane = new DataTable<T>(
				this.getBeanClass(),
				obj -> control.navigateToEntityWindow(obj)) {};
		super.contentPanel.add(this.dataTablePane, BorderLayout.CENTER);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initFromController() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void resetWindow() {
		this.dataTablePane.resetDataTable();
	}


	
}
