package com.pra.view;

import static com.pra.utils.view.SwingComponentUtils.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;

import org.springframework.stereotype.Component;

import com.pra.controller.SampleOutController;
import com.pra.model.PartyOut;
import com.pra.model.Product;
import com.pra.model.SampleOut;
import com.pra.model.SampleOutReading;
import com.pra.reports.beans.SampleOutReadingReportBean;
import com.pra.utils.commons.DateConvertUtils;
import com.pra.utils.commons.PrimaryKeyConverter;
import com.pra.utils.view.AutoComplete;
import com.pra.utils.view.datatable.DataTable;
import com.pra.view.basewindows.BaseEntityWindow;
import com.toedter.calendar.JDateChooser;

import net.miginfocom.swing.MigLayout;

@Component
public class SampleOutWindow extends BaseEntityWindow<SampleOut, SampleOutController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DocumentListener documentListner;

	private DataTable<SampleOutReadingReportBean> dataTablePane;

	private JLabel labelDate;
	private JLabel labelReferenceNo;
	private JLabel labelParty;
	private JLabel labelProduct;
	private JLabel labelDetailReport;
	private JLabel labelCost;
	private JLabel lblPrice;
	private JTextArea textAreaDetail;
	private JLabel lblSampleOut;
	private JDateChooser dateChooserDate;
	private JFormattedTextField formattedTextFieldRefNo;
	private JComboBox<PartyOut> comboBoxParty;
	private JComboBox<Product> comboBoxProduct;
	private JFormattedTextField formattedTextFieldCost;
	private JFormattedTextField formattedTextFieldPrice;
	private JButton btnAddSampleReadings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SampleOutWindow frame = new SampleOutWindow();
					List<PartyOut> parties = new ArrayList<PartyOut>();
					parties.add(PartyOut.builder().id(1).name("Party1").build());
					List<Product> products = new ArrayList<Product>();
					products.add(Product.builder().id(1).name("Product1").build());
					frame.init();
				} catch (Exception e) { // ex1
					JOptionPane.showMessageDialog(null, "error--c1/ex1" + e.getMessage());
					e.printStackTrace();
				}

			}
		});
	}

	private void initializePartyComboWith(List<PartyOut> parties) {
		parties.forEach(party -> this.comboBoxParty.addItem(party));
	}

	private void initializeProductComboWith(List<Product> products) {
		products.forEach(product -> this.comboBoxProduct.addItem(product));
	}

	private void initializetextAreaWithAutoComplete() {
		this.documentListner = new AutoComplete(this.textAreaDetail, new File(properties.getSuggetionFilePath()));
		this.textAreaDetail.getDocument().addDocumentListener(this.documentListner);
	}

	@Override
	protected boolean validateData() {
		if (anyBlank(this.formattedTextFieldRefNo) ||
				invalidDate(this.dateChooserDate) || this.comboBoxParty.getSelectedIndex() == -1 || this.comboBoxProduct.getSelectedIndex() == -1)
			return false;
		return true;
	}

	@Override
	protected SampleOut getDataFromUI() {
		LocalDate selectedDate = DateConvertUtils.asLocalDate(this.dateChooserDate.getDate());
		return SampleOut.builder()
				.refId(PrimaryKeyConverter.convertFromOrdinary(intVal(this.formattedTextFieldRefNo), selectedDate))
				.date(selectedDate).party((PartyOut) this.comboBoxParty.getSelectedItem())
				.product((Product) this.comboBoxProduct.getSelectedItem()).detailReport(this.textAreaDetail.getText())
				.cost(doubleVal(this.formattedTextFieldCost)).price(doubleVal(this.formattedTextFieldPrice)).build();
	}

	@Override
	protected void setDataToUI(SampleOut obj) {
		this.formattedTextFieldRefNo.setText(String.valueOf(PrimaryKeyConverter.getOrdinary(obj.getRefId())));
		this.dateChooserDate.setDate(DateConvertUtils.asUtilDate(obj.getDate()));
		this.comboBoxParty.setSelectedItem(obj.getParty());
		this.comboBoxProduct.setSelectedItem(obj.getProduct());
		this.textAreaDetail.setText(obj.getDetailReport());
		this.formattedTextFieldCost.setText(String.valueOf(obj.getCost()));
		this.formattedTextFieldPrice.setText(String.valueOf(obj.getPrice()));
	}

	/**
	 * Create the frame.
	 */
	public SampleOutWindow() {
		contentPanel.setLayout(new MigLayout("", "640[][300][grow]", "[][][][][100][][][][][][]"));

		this.labelDate = new JLabel("Date :");
		contentPanel.add(this.labelDate, "cell 0 0,alignx trailing");

		this.dateChooserDate = new JDateChooser();
		contentPanel.add(this.dateChooserDate, "cell 1 0,grow");

		this.labelReferenceNo = new JLabel("Reference No :");
		contentPanel.add(this.labelReferenceNo, "cell 0 1,alignx trailing");

		this.formattedTextFieldRefNo = new JFormattedTextField(NumberFormat.getIntegerInstance());
		contentPanel.add(this.formattedTextFieldRefNo, "cell 1 1,growx");

		this.labelParty = new JLabel("Party :");
		contentPanel.add(this.labelParty, "cell 0 2,alignx trailing");

		this.comboBoxParty = new JComboBox<PartyOut>();
		contentPanel.add(this.comboBoxParty, "cell 1 2,growx");

		this.labelProduct = new JLabel("Product :");
		contentPanel.add(this.labelProduct, "cell 0 3,alignx trailing");

		this.comboBoxProduct = new JComboBox<Product>();
		contentPanel.add(this.comboBoxProduct, "cell 1 3,growx");

		this.labelDetailReport = new JLabel("Details Report :");
		contentPanel.add(this.labelDetailReport, "cell 0 4,alignx trailing,aligny top");

		this.textAreaDetail = new JTextArea();
		contentPanel.add(this.textAreaDetail, "cell 1 4,grow");

		this.labelCost = new JLabel("Cost :");
		contentPanel.add(this.labelCost, "cell 0 5,alignx trailing");

		this.formattedTextFieldCost = new JFormattedTextField(NumberFormat.getNumberInstance());
		contentPanel.add(this.formattedTextFieldCost, "cell 1 5,growx");

		this.lblPrice = new JLabel("Price :");
		contentPanel.add(this.lblPrice, "cell 0 6,alignx trailing");

		this.formattedTextFieldPrice = new JFormattedTextField(NumberFormat.getNumberInstance());
		contentPanel.add(this.formattedTextFieldPrice, "cell 1 6,growx");

		contentPanel.add(this.btnSave, "flowx,cell 1 9,alignx center");
		contentPanel.add(this.btnUpdate, "flowx,cell 1 9,alignx center");
		contentPanel.add(this.btnCancel, "cell 1 9,alignx center");

		this.btnAddSampleReadings = new JButton("Add Sample Readings");
		this.btnAddSampleReadings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.navigateToSampleOutReading(modelObject);
			}
		});
		contentPanel.add(this.btnAddSampleReadings, "cell 1 10");

		this.lblSampleOut = new JLabel("Sample IN");
		headerPanel.add(this.lblSampleOut);
		footerPanel.setLayout(new BorderLayout(0, 0));

	}

	@Override
	protected void postConstruction() {
		this.initializetextAreaWithAutoComplete();
		this.dataTablePane = new DataTable<SampleOutReadingReportBean>(
				obj -> control.navigateToSampleOutReading(obj)) {
			private static final long serialVersionUID = 1L;
		};
		super.footerPanel.add(this.dataTablePane, BorderLayout.CENTER);
	}

	@Override
	public void init() {
		if (this.modelObject == null) {
			LocalDate now = LocalDate.now();
			this.dateChooserDate.setDate(DateConvertUtils.asUtilDate(now));
			this.formattedTextFieldRefNo
					.setText(String.valueOf(PrimaryKeyConverter.getNextId(control.getMaxRefId(), now)));
			this.btnAddSampleReadings.setEnabled(false);
		} else {
			this.btnAddSampleReadings.setEnabled(true);
			List<SampleOutReading> readings = modelObject.getReadings();
			if (readings != null) {
				this.dataTablePane.setTableData(
						readings.stream().map(item -> item.sampleOutReadingReportBean()).collect(Collectors.toList()));
			}
		}
	}

	@Override
	protected void initFromController() {
		this.initializePartyComboWith(control.getParties());
		this.initializeProductComboWith(control.getProducts());
	}

	@Override
	protected void resetWindow() {
		this.comboBoxParty.removeAllItems();
		this.comboBoxProduct.removeAllItems();
		this.dataTablePane.resetDataTable();
		clearAllText(this.textAreaDetail , this.formattedTextFieldPrice , this.formattedTextFieldCost);
	}

}
