package com.pra.view;

import static com.pra.utils.view.SwingComponentUtils.anyBlank;
import static com.pra.utils.view.SwingComponentUtils.clearAllText;
import static com.pra.utils.view.SwingComponentUtils.doubleVal;
import static com.pra.utils.view.SwingComponentUtils.intVal;
import static com.pra.utils.view.SwingComponentUtils.invalidDate;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.text.Format;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.stereotype.Component;

import com.pra.controller.SampleInReadingController;
import com.pra.model.PartyIn;
import com.pra.model.Product;
import com.pra.model.SampleIn;
import com.pra.model.SampleInReading;
import com.pra.utils.commons.DateConvertUtils;
import com.pra.utils.commons.PrimaryKeyConverter;
import com.pra.utils.view.AutoComplete;
import com.pra.view.basewindows.BaseEntityWindow;
import com.toedter.calendar.JDateChooser;

import net.miginfocom.swing.MigLayout;

@Component
public class SampleInReadingWindow extends BaseEntityWindow<SampleInReading, SampleInReadingController> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DocumentListener documentListner;

	private SampleIn sampleIn;
	private JLabel labelDate;
	private JLabel labelReferenceNo;
	private JLabel labelDetailReport;
	private JTextArea textAreaDetail;
	private JLabel lblSampleIn;
	private JDateChooser dateChooserDate;
	private JFormattedTextField formattedTextFieldRefNo;
	private JFormattedTextField formattedTextFieldStrength1;
	private JLabel labelReading8;
	private JLabel labelReading1;
	private JLabel labelReading6;
	private JLabel labelReading9;
	private JLabel labelReading10;
	private JLabel labelReading4;
	private JLabel labelReading5;
	private JLabel labelReading3;
	private JLabel labelReading2;
	private JFormattedTextField formattedTextFieldStrength2;
	private JFormattedTextField formattedTextFieldDE2;
	private JFormattedTextField formattedTextFieldDA2;
	private JFormattedTextField formattedTextFieldDB2;
	private JFormattedTextField formattedTextFieldDC2;
	private JFormattedTextField formattedTextFieldDE1;
	private JFormattedTextField formattedTextFieldDA1;
	private JFormattedTextField formattedTextFieldDB1;
	private JFormattedTextField formattedTextFieldDC1;
	private JLabel lblId;
	private JFormattedTextField formattedTextFieldId;
	private JLabel lblGroup;
	private JLabel lblGroup2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SampleInReadingWindow frame = new SampleInReadingWindow();
					List<PartyIn> parties = new ArrayList<PartyIn>();
					parties.add(PartyIn.builder().id(1).name("Party1").build());
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

	private void initializetextAreaWithAutoComplete() {
		this.documentListner = new AutoComplete(this.textAreaDetail, new File(properties.getSuggetionFilePath()));
		this.textAreaDetail.getDocument().addDocumentListener(this.documentListner);
	}

	@Override
	protected boolean validateData() {
		if (anyBlank(this.formattedTextFieldRefNo, this.formattedTextFieldStrength1, this.formattedTextFieldStrength2,
				this.formattedTextFieldDA1, this.formattedTextFieldDA2, this.formattedTextFieldDE1,
				this.formattedTextFieldDE2, this.formattedTextFieldDB1, this.formattedTextFieldDB2,
				this.formattedTextFieldDC1, this.formattedTextFieldDC2)|| invalidDate(this.dateChooserDate))
			return false;
		return true;
	}

	@Override
	protected SampleInReading getDataFromUI() {
		LocalDate selectedDate = DateConvertUtils.asLocalDate(this.dateChooserDate.getDate());
		return SampleInReading.builder()
				.id(PrimaryKeyConverter.convertFromOrdinary(intVal(this.formattedTextFieldId),selectedDate))
				.date(selectedDate).sample(this.sampleIn)
				.detailReport(this.textAreaDetail.getText()).strength1(doubleVal(this.formattedTextFieldStrength1))
				.de1(doubleVal(this.formattedTextFieldDE1)).da1(doubleVal(this.formattedTextFieldDA1))
				.db1(doubleVal(this.formattedTextFieldDB1)).dc1(doubleVal(this.formattedTextFieldDC1))
				.strength2(doubleVal(this.formattedTextFieldStrength2)).de2(doubleVal(this.formattedTextFieldDE2))
				.da2(doubleVal(this.formattedTextFieldDA2)).db2(doubleVal(this.formattedTextFieldDB2))
				.dc2(doubleVal(this.formattedTextFieldDC2)).build();
	}

	@Override
	protected void setDataToUI(SampleInReading obj) {
		this.formattedTextFieldId.setText(String.valueOf(PrimaryKeyConverter.getOrdinary(obj.getId())));
		this.formattedTextFieldRefNo.setText(String.valueOf(PrimaryKeyConverter.getOrdinary(obj.getSample().getRefId())));
		this.dateChooserDate.setDate(DateConvertUtils.asUtilDate(obj.getDate()));
		this.textAreaDetail.setText(obj.getDetailReport());
		this.formattedTextFieldStrength1.setText(String.valueOf(obj.getStrength1()));
		this.formattedTextFieldDE1.setText(String.valueOf(obj.getDe1()));
		this.formattedTextFieldDA1.setText(String.valueOf(obj.getDa1()));
		this.formattedTextFieldDB1.setText(String.valueOf(obj.getDb1()));
		this.formattedTextFieldDC1.setText(String.valueOf(obj.getDc1()));
		this.formattedTextFieldStrength2.setText(String.valueOf(obj.getStrength2()));
		this.formattedTextFieldDE2.setText(String.valueOf(obj.getDe2()));
		this.formattedTextFieldDA2.setText(String.valueOf(obj.getDa2()));
		this.formattedTextFieldDB2.setText(String.valueOf(obj.getDb2()));
		this.formattedTextFieldDC2.setText(String.valueOf(obj.getDc2()));
	}

	/**
	 * Create the frame.
	 */
	public SampleInReadingWindow() {
		
		int sideMargin = this.windowWidth/5;
		int inputSizes = this.windowWidth/5;
		
		contentPanel.setLayout(new MigLayout("", sideMargin+"[]["+inputSizes+"][]["+inputSizes+"][]", "[][][100]30[]30[][][][][][][]"));

		this.lblId = new JLabel("ID :");
		contentPanel.add(this.lblId, "cell 0 0,alignx trailing");

		this.formattedTextFieldId = new JFormattedTextField((Format) null);
		this.formattedTextFieldId.setEditable(false);
		contentPanel.add(this.formattedTextFieldId, "cell 1 0,growx");

		this.labelDate = new JLabel("Date :");
		contentPanel.add(this.labelDate, "cell 2 0,alignx trailing");

		this.dateChooserDate = new JDateChooser();
		contentPanel.add(this.dateChooserDate, "cell 3 0,grow");

		this.labelReferenceNo = new JLabel("Reference No :");
		contentPanel.add(this.labelReferenceNo, "cell 0 1,alignx trailing");

		this.formattedTextFieldRefNo = new JFormattedTextField(NumberFormat.getIntegerInstance());
		this.formattedTextFieldRefNo.setEditable(false);
		contentPanel.add(this.formattedTextFieldRefNo, "cell 1 1,growx");

		this.labelDetailReport = new JLabel("Details Report :");
		contentPanel.add(this.labelDetailReport, "cell 0 2,aligny top");

		this.textAreaDetail = new JTextArea();
		contentPanel.add(this.textAreaDetail, "cell 1 2,grow");

		this.lblGroup = new JLabel("Transmission");
		this.lblGroup.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPanel.add(this.lblGroup, "cell 1 3,alignx center");

		this.lblGroup2 = new JLabel("Reflection");
		this.lblGroup2.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPanel.add(this.lblGroup2, "cell 3 3,alignx center");

		this.labelReading1 = new JLabel("Strength (%):");
		contentPanel.add(this.labelReading1, "cell 0 4,alignx trailing");

		this.formattedTextFieldStrength1 = new JFormattedTextField((Format) null);
		contentPanel.add(this.formattedTextFieldStrength1, "cell 1 4,growx");

		this.labelReading2 = new JLabel("Strength (%):");
		contentPanel.add(this.labelReading2, "cell 2 4,alignx trailing");

		this.formattedTextFieldStrength2 = new JFormattedTextField((Format) null);
		contentPanel.add(this.formattedTextFieldStrength2, "cell 3 4,growx");

		this.labelReading3 = new JLabel("DE :");
		contentPanel.add(this.labelReading3, "cell 0 5,alignx trailing");

		this.formattedTextFieldDE1 = new JFormattedTextField((Format) null);
		contentPanel.add(this.formattedTextFieldDE1, "cell 1 5,growx");

		this.labelReading4 = new JLabel("DE :");
		contentPanel.add(this.labelReading4, "cell 2 5,alignx trailing");

		this.formattedTextFieldDE2 = new JFormattedTextField((Format) null);
		contentPanel.add(this.formattedTextFieldDE2, "cell 3 5,growx");

		this.labelReading5 = new JLabel("DA :");
		contentPanel.add(this.labelReading5, "cell 0 6,alignx trailing");

		this.formattedTextFieldDA1 = new JFormattedTextField((Format) null);
		contentPanel.add(this.formattedTextFieldDA1, "cell 1 6,growx");

		this.labelReading6 = new JLabel("DA :");
		contentPanel.add(this.labelReading6, "cell 2 6,alignx trailing");

		this.formattedTextFieldDA2 = new JFormattedTextField((Format) null);
		contentPanel.add(this.formattedTextFieldDA2, "cell 3 6,growx");

		JLabel labelReading7 = new JLabel("DB :");
		contentPanel.add(labelReading7, "cell 0 7,alignx trailing");

		this.formattedTextFieldDB1 = new JFormattedTextField((Format) null);
		contentPanel.add(this.formattedTextFieldDB1, "cell 1 7,growx");

		this.labelReading8 = new JLabel("DB :");
		contentPanel.add(this.labelReading8, "cell 2 7,alignx trailing");

		this.formattedTextFieldDB2 = new JFormattedTextField((Format) null);
		contentPanel.add(this.formattedTextFieldDB2, "cell 3 7,growx");

		this.labelReading9 = new JLabel("DC :");
		contentPanel.add(this.labelReading9, "cell 0 8,alignx trailing");

		this.formattedTextFieldDC1 = new JFormattedTextField((Format) null);
		contentPanel.add(this.formattedTextFieldDC1, "cell 1 8,growx");

		this.labelReading10 = new JLabel("DC :");
		contentPanel.add(this.labelReading10, "cell 2 8,alignx trailing");

		this.formattedTextFieldDC2 = new JFormattedTextField((Format) null);
		contentPanel.add(this.formattedTextFieldDC2, "cell 3 8,growx");
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new java.awt.Component[]{this.textAreaDetail, this.formattedTextFieldStrength1, this.formattedTextFieldDE1, this.formattedTextFieldDA1, this.formattedTextFieldDB1, this.formattedTextFieldDC1, this.formattedTextFieldStrength2, this.formattedTextFieldDE2, this.formattedTextFieldDA2, this.formattedTextFieldDB2, this.formattedTextFieldDC2, this.lblId, this.formattedTextFieldId, this.labelDate, this.dateChooserDate, this.dateChooserDate.getCalendarButton(), this.labelReferenceNo, this.formattedTextFieldRefNo, this.labelDetailReport, this.lblGroup, this.lblGroup2, this.labelReading1, this.labelReading2, this.labelReading3, this.labelReading4, this.labelReading5, this.labelReading6, labelReading7, this.labelReading8, this.labelReading9, this.labelReading10}));

		this.lblSampleIn = new JLabel("Sample IN");
		this.lblSampleIn.setFont(new Font("Tahoma", Font.BOLD, 20));
		headerPanel.add(this.lblSampleIn);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new java.awt.Component[]{this.textAreaDetail, this.formattedTextFieldStrength1, this.formattedTextFieldDE1, this.formattedTextFieldDA1, this.formattedTextFieldDB1, this.formattedTextFieldDC1, this.formattedTextFieldStrength2, this.formattedTextFieldDE2, this.formattedTextFieldDA2, this.formattedTextFieldDB2, this.formattedTextFieldDC2, headerPanel, this.lblSampleIn, footerPanel, contentPanel, this.lblId, this.formattedTextFieldId, this.labelDate, this.dateChooserDate, this.dateChooserDate.getCalendarButton(), this.labelReferenceNo, this.formattedTextFieldRefNo, this.labelDetailReport, this.lblGroup, this.lblGroup2, this.labelReading1, this.labelReading2, this.labelReading3, this.labelReading4, this.labelReading5, this.labelReading6, labelReading7, this.labelReading8, this.labelReading9, this.labelReading10, leftSidePanel, rightSidePanel}));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new java.awt.Component[]{this.textAreaDetail, this.formattedTextFieldStrength1, this.formattedTextFieldDE1, this.formattedTextFieldDA1, this.formattedTextFieldDB1, this.formattedTextFieldDC1, this.formattedTextFieldStrength2, this.formattedTextFieldDE2, this.formattedTextFieldDA2, this.formattedTextFieldDB2, this.formattedTextFieldDC2, getContentPane(), headerPanel, this.lblSampleIn, footerPanel, contentPanel, this.lblId, this.formattedTextFieldId, this.labelDate, this.dateChooserDate, this.dateChooserDate.getCalendarButton(), this.labelReferenceNo, this.formattedTextFieldRefNo, this.labelDetailReport, this.lblGroup, this.lblGroup2, this.labelReading1, this.labelReading2, this.labelReading3, this.labelReading4, this.labelReading5, this.labelReading6, labelReading7, this.labelReading8, this.labelReading9, this.labelReading10, leftSidePanel, rightSidePanel}));

	}

	@Override
	protected void postConstruction() {
		contentPanel.add(super.btnSave, "flowx,cell 1 10 3 1,alignx center");
		contentPanel.add(super.btnUpdate, "flowx,cell 1 10 3 1,alignx center");
		contentPanel.add(this.btnDelete, "flowx,cell 1 10 3 1,alignx center");
		contentPanel.add(super.btnCancel, "cell 1 10 3 1,alignx center");
		this.initializetextAreaWithAutoComplete();
	}

	public void showWindow(SampleIn sample) {
		this.sampleIn = sample;
		this.formattedTextFieldRefNo.setText(String.valueOf(PrimaryKeyConverter.getOrdinary(this.sampleIn.getRefId())));
		super.showWindow();
	}

	@Override
	public void init() {
		if (modelObject == null) {
			this.formattedTextFieldId.setText(String.valueOf(control.getMaxId() + 1));
			this.dateChooserDate.setDate(DateConvertUtils.asUtilDate(LocalDate.now()));
		}else {
			
		}
	}

	@Override
	protected void initFromController() {

	}

	@Override
	protected void resetWindow() {
		clearAllText(this.textAreaDetail,this.formattedTextFieldDA1,this.formattedTextFieldDA2,this.formattedTextFieldDB1,this.formattedTextFieldDB2,
				this.formattedTextFieldDC1,this.formattedTextFieldDC2,this.formattedTextFieldDE1,this.formattedTextFieldDE2,
				this.formattedTextFieldStrength1,this.formattedTextFieldStrength2);
	}

}
