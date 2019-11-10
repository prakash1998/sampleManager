package com.pra.utils.view.datatable;

import java.time.LocalDate;
import java.util.List;

@FunctionalInterface
public interface TableDataGetter<T> {
	List<T> getBetweem(LocalDate startDate , LocalDate endDate);
}
