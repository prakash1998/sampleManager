package com.pra.view.basewindows;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;

import com.pra.controller.interfaces.BaseDataController;
import com.pra.reports.beans.ReportBean;
import com.pra.utils.view.datatable.CommonDataTablePanel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings({ "rawtypes", "serial" })
public abstract class BaseDataWindow<B extends ReportBean<?>,C extends BaseDataController<?,B, ?,?,?, ? extends BaseDataWindow>> extends ParentWindow<C> {
	
	protected JButton btnBack;
	
	protected CommonDataTablePanel<B> dataTablePane;
	private JLabel lblScreentitle;
	
	public BaseDataWindow() {
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
		
	}
	
	protected abstract String setTitle();

	public void showWindow(LocalDate initStartDate, LocalDate initEndDate) {
		dataTablePane.setDates(initStartDate, initEndDate);
		this.setVisible(true);
	}
	
	protected abstract Class<B> getBeanClass();
	
	@Override
	protected void postConstruction() {
		this.dataTablePane = new CommonDataTablePanel<B>(
				properties.getReportsPath(),
				this.getBeanClass(),
				control::getBetweenDates,
				obj -> control.navigateToEntityWindow(obj));
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
		this.dataTablePane.resetCommonDataTable();
	}


	
}
