package com.pra.view.basewindows;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.annotation.PostConstruct;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;

import com.pra.config.AppConfigProperties;
import com.pra.controller.interfaces.BaseController;

public abstract class ParentWindow<C extends BaseController> extends JFrame {
	
	@Autowired
	protected AppConfigProperties properties;
	
	@Autowired
	protected C control;
	
	
	protected final int windowHeight;
	protected final int windowWidth;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6173121954203109922L;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel centerPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JLabel labelCompanyName;
	private Component horizontalStrutLeft;
	private Component horizontalStrutRight;
	
	
	public JPanel headerPanel;
	public JPanel footerPanel;
	public JPanel contentPanel;
	public JPanel leftSidePanel;
	public JPanel rightSidePanel;

	/**
	 * Create the frame.
	 */
	public ParentWindow() {
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.windowHeight = screenSize.height;
		this.windowWidth = screenSize.width;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screenSize);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		labelCompanyName = new JLabel("Comapny Name");
		labelCompanyName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		topPanel.add(labelCompanyName);
		
		bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		headerPanel = new JPanel();
		centerPanel.add(headerPanel, BorderLayout.NORTH);
		
		footerPanel = new JPanel();
		centerPanel.add(footerPanel, BorderLayout.SOUTH);
		
		contentPanel = new JPanel();
		centerPanel.add(contentPanel, BorderLayout.CENTER);
		
		leftSidePanel = new JPanel();
		centerPanel.add(leftSidePanel, BorderLayout.WEST);
		
		rightSidePanel = new JPanel();
		centerPanel.add(rightSidePanel, BorderLayout.EAST);
		
		leftPanel = new JPanel();
		getContentPane().add(leftPanel, BorderLayout.WEST);
		
		horizontalStrutLeft = Box.createHorizontalStrut(50);
		leftPanel.add(horizontalStrutLeft);
		
		rightPanel = new JPanel();
		getContentPane().add(rightPanel, BorderLayout.EAST);
		
		horizontalStrutRight = Box.createHorizontalStrut(50);
		rightPanel.add(horizontalStrutRight);

	}
	
	@PostConstruct
	private void postConstruct() {
		this.labelCompanyName.setText(properties.getComapanyName());
		this.postConstruction();
	}
	

	protected abstract void postConstruction();
	
	protected abstract void init();
	
	protected abstract void initFromController();
	
	protected abstract void resetWindow();
	
	public void showWindow() {
		this.init();
		this.initFromController();
		this.setVisible(true);
	}
	
	public void closeWindow() {
		this.resetWindow();
		this.dispose();
	}
	

}
