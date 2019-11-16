package com.pra.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

import com.pra.controller.HomeController;
import com.pra.utils.view.CommonOptionPanes;
import com.pra.view.basewindows.ParentWindow;

import net.miginfocom.swing.MigLayout;
import java.awt.Font;

@Component
public class HomeWindow extends ParentWindow<HomeController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblHome;
	private JButton btnSampleIn;
	private JButton btnSampleOut;
	private JButton btnViewSampleIn;
	private JButton btnExit;
	private JButton btnViewSampleOut;
	private JButton btnViewSampleInReading;
	private JButton btnAddPartyIn;
	private JButton btnAddProduct;
	private JButton btnAddPartyout;
	private JButton btnViewSampleoutReadings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeWindow frame = new HomeWindow();
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
	public HomeWindow() {

		this.lblHome = new JLabel("Home");
		this.lblHome.setFont(new Font("Tahoma", Font.PLAIN, 24));
		headerPanel.add(this.lblHome);
		contentPanel.setLayout(new MigLayout("", "["+this.windowWidth/3+"][grow]["+this.windowWidth/3+"]", "[][][][][][][][][][][]"));

		this.btnSampleIn = new JButton("New Sample Inward");
		this.btnSampleIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToSampleIn();
			}
		});
		contentPanel.add(this.btnSampleIn, "cell 1 0,growx");

		this.btnSampleOut = new JButton("New Sample Outward");
		this.btnSampleOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToSampleOut();
			}
		});
		contentPanel.add(this.btnSampleOut, "cell 1 1,growx");

		this.btnViewSampleIn = new JButton("View Sample Inwards");
		this.btnViewSampleIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToSampleInData();
			}
		});
		contentPanel.add(this.btnViewSampleIn, "cell 1 2,growx");

		this.btnExit = new JButton("Exit");
		this.btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CommonOptionPanes.showExitPrompt())
					control.shutDownApp();
			}
		});
		
		this.btnViewSampleOut = new JButton("View Sample Outwards");
		this.btnViewSampleOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToSampleOutData();
			}
		});
		contentPanel.add(this.btnViewSampleOut, "cell 1 3,growx");
		
		this.btnAddPartyIn = new JButton("Manage Inward Partys");
		this.btnAddPartyIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToPartyInData();
			}
		});
		
		this.btnViewSampleInReading = new JButton("View Sample Inward Readings");
		this.btnViewSampleInReading.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToSampleInReadingData();
			}
		});
		contentPanel.add(this.btnViewSampleInReading, "cell 1 4,growx");
		
		this.btnViewSampleoutReadings = new JButton("View Sample Outward Readings");
		this.btnViewSampleoutReadings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToSampleOutReadingData();
			}
		});
		contentPanel.add(this.btnViewSampleoutReadings, "cell 1 5,growx");
		contentPanel.add(this.btnAddPartyIn, "cell 1 6,growx");
		
		this.btnAddProduct = new JButton("Manage Products");
		this.btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToProductData();
			}
		});
		
		this.btnAddPartyout = new JButton("Manage Outward Partys");
		this.btnAddPartyout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToPartyOutData();
			}
		});
		contentPanel.add(this.btnAddPartyout, "cell 1 7,growx");
		contentPanel.add(this.btnAddProduct, "cell 1 8,growx");
		contentPanel.add(this.btnExit, "cell 1 9,growx");

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
