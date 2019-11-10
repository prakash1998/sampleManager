package com.pra.utils.view.datatable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.table.TableRowSorter;

import com.pra.annotations.TableCol;

public class DisplayableObjectTableModel<T> extends ObjectTableModel<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, ColumnInfo> columnInfoMap;

	public DisplayableObjectTableModel(Class<T> tClass) {
		init(tClass);
	}

	private void init(Class<T> tClass) {
		try {
			this.columnInfoMap = new HashMap<>();
			int index = 0;

			for (Field f : tClass.getDeclaredFields()) {
				f.setAccessible(true);
				TableCol tablecol = f.getAnnotation(TableCol.class);
				if (tablecol == null) {
					continue;
				}
				ColumnInfo columnInfo = new ColumnInfo();
				columnInfo.displayName = tablecol.displayName() + " (" + (index + 1) + ")";
				columnInfo.width = tablecol.width();
				columnInfo.filterable = tablecol.filterable();
				columnInfo.index = index++;
				columnInfo.field = f;
				columnInfo.propertyName = f.getName();
				columnInfoMap.put(columnInfo.index, columnInfo);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object getValueAt(T t, int columnIndex) {
		try {
			return columnInfoMap.get(columnIndex).field.get(t);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getColumnCount() {
		return columnInfoMap.size();
	}

	@Override
	public String getColumnName(int column) {
		ColumnInfo columnInfo = columnInfoMap.get(column);
		if (columnInfo == null) {
			throw new RuntimeException("No column found for index " + column);
		}
		return columnInfo.displayName;
	}

	@Override
	public String getFieldName(int column) {
		ColumnInfo columnInfo = columnInfoMap.get(column);
		return columnInfo.propertyName;
	}

	public Class<?> getColumnClass(int columnIndex) {
		ColumnInfo columnInfo = columnInfoMap.get(columnIndex);
		return columnInfo.field.getClass();
	}

	@Override
	public List<String> getFilterableColumnList() {
		return columnInfoMap.values().stream().filter(col -> col.filterable).map(col -> col.displayName)
				.collect(Collectors.toList());
	}

	@Override
	public List<Integer> getColumnWidths() {
		return columnInfoMap.values().stream().sorted((obj1, obj2) -> obj1.index > obj2.index ? 1 : -1)
				.map(col -> col.width).collect(Collectors.toList());
	}

	private static class ColumnInfo {
		private Field field;
		private int index;
		private String displayName;
		public String propertyName;
		public boolean filterable;
		public int width;
	}
}