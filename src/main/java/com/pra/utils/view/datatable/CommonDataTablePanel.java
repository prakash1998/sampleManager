package com.pra.utils.view.datatable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.text.JTextComponent;

import com.pra.utils.commons.DateConvertUtils;
import com.pra.utils.commons.RegExUtils;
import com.pra.utils.commons.Searchable;
import com.pra.utils.view.AutoComplete;
import com.pra.utils.view.SwingComponentUtils;
import com.sun.media.jfxmedia.logging.Logger;
import com.toedter.calendar.JDateChooser;

import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("restriction")
public class CommonDataTablePanel<T> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataTable<T> dataTable;
	private TableDataGetter<T> dataGetter;
	private Class<T> clazz;
	private String reportPath;
	private JasperReport jasperReport;
	private AutoComplete filter1AutoComplete;
	private AutoComplete filter2AutoComplete;
	private JPanel panelCriteria;
	private JPanel panelBottom;
	private JDateChooser dateChooserStart;
	private JDateChooser dateChooserEnd;
	private JLabel lblStart;
	private JLabel lblEnd;
	private JButton btnRetrieve;
	private JLabel lblFilter;
	private JTextField textFieldFilter1;
	private JButton btnPrint;
	private JLabel labelArrow;
	private JTextField textFieldFilter2;
	private JLabel labelFilter1Index;
	private JLabel labelFilter2Index;

	public CommonDataTablePanel(String reportPath, Class<T> clazz, TableDataGetter<T> dataGetter,
			RowDblClickHandler<T> handler) {
		this();
		this.clazz = clazz;
		this.dataGetter = dataGetter;
		this.reportPath = reportPath;
		this.dataTable = new DataTable<T>(clazz, handler) {
			private static final long serialVersionUID = 1L;
		};
		add(this.dataTable, BorderLayout.CENTER);
		LocalDate now = LocalDate.now();
		this.dateChooserStart.setDate(DateConvertUtils.asUtilDate(now));
		this.dateChooserEnd.setDate(DateConvertUtils.asUtilDate(now));
		// this.availableData = this.dataGetter.getBetweem(now, now);
		// this.dataTable.setDates(now, now);
		// this.refreshTable();
		this.loadReportFile();
		this.initializeFilters();
	}

	public void setDates(LocalDate startDate, LocalDate endDate) {
		this.dateChooserStart.setDate(DateConvertUtils.asUtilDate(startDate));
		this.dateChooserEnd.setDate(DateConvertUtils.asUtilDate(endDate));
		this.dataTable.setTableData(this.dataGetter.getBetweem(startDate, endDate));
		// this.availableData = this.dataGetter.getBetweem(startDate, endDate);
		// this.refreshTable();
	}

	private void initializeFilters() {
		int filter1Index = 3;
		int filter2Index = 4;
		this.labelFilter1Index.setText("(" + filter1Index + ")");
		this.labelFilter2Index.setText("(" + filter2Index + ")");

		this.resetKeywordsFromIndex(this.textFieldFilter1, this.filter1AutoComplete, filter1Index);
		this.resetKeywordsFromIndex(this.textFieldFilter2, this.filter2AutoComplete, filter2Index);
	}

	private void resetKeywordsFromIndex(JTextComponent textComponent, AutoComplete listener, int index) {
		ObjectTableModel<T> tableModel = this.dataTable.getTableModel();
		int rowCount = tableModel.getRowCount();
		Set<String> keywords = new HashSet<String>();
		for (int i = 0; i < rowCount; i++)
			keywords.add((String) tableModel.getValueAt(i, index - 1));

		if (listener != null)
			textComponent.getDocument().removeDocumentListener(listener);
		textComponent.getDocument().addDocumentListener(
				new AutoComplete(textComponent, Searchable.convert(new ArrayList<String>(keywords))));
	}

	private void loadReportFile() {
		String reportPath = this.reportPath + this.clazz.getSimpleName() + ".jasper";

		try {
			this.jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);
			CompletableFuture.runAsync(() -> {
				Map<String, Object> param = new HashMap<String, Object>();
				JRBeanCollectionDataSource dataJRBean = new JRBeanCollectionDataSource(new ArrayList<T>());
				param.put("objectDataSource", dataJRBean);
				JasperPrint jasperPrint = null;
				try {
					jasperPrint = JasperFillManager.fillReport(this.jasperReport, param, new JREmptyDataSource());
				} catch (JRException e) {
					// TODO Auto-generated catch block
					Logger.logMsg(Logger.ERROR, "error while loading report async");
					e.printStackTrace();
				}
				new JasperViewer(jasperPrint, false);
			});
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// private void initializecomboBoxFilterAttribute() {
	// this.tableModel.getFilterableColumnList().stream().forEach( item ->
	// this.comboBoxFilterAttribute.addItem(item));
	// }

	private JasperViewer printReport() {
		Map<String, Object> param = new HashMap<String, Object>();

		JRBeanCollectionDataSource dataJRBean = new JRBeanCollectionDataSource(this.dataTable.getDisplayedData());
		param.put("objectDataSource", dataJRBean);
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(this.jasperReport, param, new JREmptyDataSource());
			JasperViewer jasperView = new JasperViewer(jasperPrint, false);
			jasperView.setVisible(true);
			return jasperView;
		} catch (JRException e) {
			System.out.println("Error while printing report");
			JOptionPane.showMessageDialog(null, "Report File not Found");
			e.printStackTrace();
		}
		return null;
	}

	private void retrieveDataBetweenSelectedDates() {
		LocalDate startDate = DateConvertUtils.asLocalDate(this.dateChooserStart.getDate());
		LocalDate endDate = DateConvertUtils.asLocalDate(this.dateChooserEnd.getDate());
		this.dataTable.setTableData(this.dataGetter.getBetweem(startDate, endDate));
	}

	private int getIndexFromLabel(JLabel label) {
		return Integer.parseInt(label.getText().replace('(', ' ').replace(')', ' ').trim());
	}

	private void incrementIndexForLabel(JLabel label) {
		int currentIndex = this.getIndexFromLabel(label);
		if (currentIndex >= this.dataTable.getTableModel().getColumnCount())
			currentIndex = 0;
		currentIndex++;
		label.setText("(" + currentIndex + ")");
	}

	private void decrementIndexForLabel(JLabel label) {
		int currentIndex = this.getIndexFromLabel(label);
		if (currentIndex <= 1)
			currentIndex = this.dataTable.getTableModel().getColumnCount() + 1;
		currentIndex--;
		label.setText("(" + currentIndex + ")");
	}

	private void filterTableWithChain() {
		List<RowFilter<Object, Object>> filters = new ArrayList<>();

		filters.add(RowFilter.regexFilter(RegExUtils.regExLiteral(this.textFieldFilter1.getText().trim()),
				this.getIndexFromLabel(this.labelFilter1Index) - 1));
		filters.add(RowFilter.regexFilter(RegExUtils.regExLiteral(this.textFieldFilter2.getText().trim()),
				this.getIndexFromLabel(this.labelFilter2Index) - 1));

		this.dataTable.setRowFilter(RowFilter.andFilter(filters));
	}

	private CommonDataTablePanel() {

		setLayout(new BorderLayout(0, 0));

		this.panelCriteria = new JPanel();
		add(this.panelCriteria, BorderLayout.NORTH);
		this.panelCriteria
				.setLayout(new MigLayout("", "[37px][160px][30px][160px][79px][47px][][170px][][][170px]", "[25px]"));

		this.lblStart = new JLabel("Start :");
		this.panelCriteria.add(this.lblStart, "cell 0 0,growx,aligny center");

		this.dateChooserStart = new JDateChooser();
		this.panelCriteria.add(this.dateChooserStart, "cell 1 0,grow");

		this.lblEnd = new JLabel("End :");
		this.panelCriteria.add(this.lblEnd, "cell 2 0,alignx left,aligny center");

		this.dateChooserEnd = new JDateChooser();
		this.panelCriteria.add(this.dateChooserEnd, "cell 3 0,grow");

		this.btnRetrieve = new JButton("Retrieve");
		this.btnRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CommonDataTablePanel<T> tempHolder =
				// SwingComponentUtils.findParent((Component) e.getSource(),
				// CommonDataTablePanel.class);
				retrieveDataBetweenSelectedDates();
			}
		});
		this.panelCriteria.add(this.btnRetrieve, "cell 4 0,grow");

		this.lblFilter = new JLabel("Filter Chain =>");
		this.panelCriteria.add(this.lblFilter, "cell 5 0,growx,aligny center");

		this.textFieldFilter1 = new JTextField();
		this.textFieldFilter1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				// CommonDataTablePanel<T> tempHolder =
				// SwingComponentUtils.findParent((Component) e.getSource(),
				// CommonDataTablePanel.class);
				if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_RIGHT) {
					incrementIndexForLabel(labelFilter1Index);
				}
				if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT) {
					decrementIndexForLabel(labelFilter1Index);
				}
				filterTableWithChain();
			}
		});

		this.labelFilter1Index = new JLabel("(3)");
		this.panelCriteria.add(this.labelFilter1Index, "cell 6 0,alignx trailing");
		this.panelCriteria.add(this.textFieldFilter1, "cell 7 0,grow");
		this.textFieldFilter1.setColumns(10);

		this.labelArrow = new JLabel("=>");
		this.panelCriteria.add(this.labelArrow, "cell 8 0,alignx trailing");

		this.textFieldFilter2 = new JTextField();
		this.textFieldFilter2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				// CommonDataTablePanel<T> tempHolder =
				// SwingComponentUtils.findParent((Component) e.getSource(),
				// CommonDataTablePanel.class);
				if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_RIGHT) {
					incrementIndexForLabel(labelFilter2Index);
				}
				if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT) {
					decrementIndexForLabel(labelFilter2Index);
				}
				filterTableWithChain();
			}
		});

		this.labelFilter2Index = new JLabel("(4)");
		this.panelCriteria.add(this.labelFilter2Index, "cell 9 0,alignx trailing");
		this.panelCriteria.add(this.textFieldFilter2, "cell 10 0,grow");
		this.textFieldFilter2.setColumns(10);

		this.panelBottom = new JPanel();
		add(this.panelBottom, BorderLayout.SOUTH);
		this.panelBottom.setLayout(new MigLayout("", "[]", "[]"));

		this.btnPrint = new JButton("Print");
		this.btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CommonDataTablePanel<T> tempHolder =
				// SwingComponentUtils.findParent((Component) e.getSource(),
				// CommonDataTablePanel.class);
				printReport();
			}
		});
		this.panelBottom.add(this.btnPrint, "cell 0 0");

	}

	public void resetCommonDataTable() {
		this.textFieldFilter1.getDocument().removeDocumentListener(filter1AutoComplete);
		this.textFieldFilter2.getDocument().removeDocumentListener(filter2AutoComplete);
		this.dataTable.resetDataTable();
		SwingComponentUtils.clearAllText(this.textFieldFilter1,this.textFieldFilter2);
	}

}
