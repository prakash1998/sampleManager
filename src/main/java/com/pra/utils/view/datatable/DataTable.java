package com.pra.utils.view.datatable;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public abstract class DataTable<T> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RowDblClickHandler<T> dblClickhandler;
	private List<T> availableData;
	private Class<T> clazz;
	private JScrollPane scrollPane;
	private JTable table;
	private ObjectTableModel<T> tableModel;
	@SuppressWarnings("rawtypes")
	private TableRowSorter<ObjectTableModel> tableSorter;

	public DataTable(Class<T> clazz, RowDblClickHandler<T> handler) {
		this();
		this.clazz = clazz;
		this.dblClickhandler = handler;
		this.initializeTable();
//		LocalDate now = LocalDate.now();
//		this.availableData = this.dataGetter.getBetweem(now, now);
		this.availableData = new ArrayList<>();
		this.refreshTable();
	}
	
	@SuppressWarnings("unchecked")
	public DataTable(RowDblClickHandler<T> handler) {
		this();
		Type sooper = getClass().getGenericSuperclass();
		Type t = ((ParameterizedType)sooper).getActualTypeArguments()[0];
		this.clazz = (Class<T>)t;
		this.dblClickhandler = handler;
		this.initializeTable();
//		LocalDate now = LocalDate.now();
//		this.availableData = this.dataGetter.getBetweem(now, now);
		this.availableData = new ArrayList<>();
		this.refreshTable();
	}

	public void setTableData(List<T> data) {
		this.availableData = data;
		this.refreshTable();
	}
	
	public void setRowFilter(RowFilter<Object, Object> filter) {
		this.tableSorter.setRowFilter(filter);
	}
	
	public List<T> getDisplayedData() {
		int visibleRowCount = this.table.getRowCount();
		List<T> visibleData = new ArrayList<T>();
		for (int i = 0; i < visibleRowCount; i++)
			visibleData.add(this.availableData.get(this.table.convertRowIndexToModel(i)));
		return visibleData;
	}
	
	public ObjectTableModel<T> getTableModel() {
		return this.tableModel;
	}

	@SuppressWarnings("rawtypes")
	private void initializeTable() {
		this.tableModel = new DisplayableObjectTableModel<>(this.clazz);
		this.table = new JTable(this.tableModel);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					int index = table.getSelectedRow();
					if (index != -1) {
						dblClickhandler.handle(availableData.get(table.convertRowIndexToModel(index)));
					}
				}
			}
		});
		List<Integer> tempList = this.tableModel.getColumnWidths();
		for (int i = 0; i < tempList.size(); i++) {
			this.table.getColumnModel().getColumn(i).setPreferredWidth(tempList.get(i));
		}
		this.scrollPane.setViewportView(this.table);
		this.tableSorter = new TableRowSorter<ObjectTableModel>(this.tableModel);
		this.table.setRowSorter(this.tableSorter);
		this.table.setRowHeight(30);
		// this.table.setAutoCreateRowSorter(true);
	}

	private void refreshTable() {
		this.tableModel.setObjectRows(this.availableData);
		this.tableModel.fireTableDataChanged();
	}

	private DataTable() {

		setLayout(new BorderLayout(0, 0));

		this.scrollPane = new JScrollPane();
		add(this.scrollPane, BorderLayout.CENTER);

	}
	
	public void resetDataTable() {
		this.availableData = new ArrayList<>();
	}

}
