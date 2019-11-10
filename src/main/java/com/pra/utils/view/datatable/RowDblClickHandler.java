package com.pra.utils.view.datatable;

@FunctionalInterface
public interface RowDblClickHandler<T> {
	void handle(T obj);
}
