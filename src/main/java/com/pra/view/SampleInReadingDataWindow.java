package com.pra.view;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.pra.controller.SampleInReadingDataController;
import com.pra.reports.beans.SampleInReadingReportBean;
import com.pra.view.basewindows.BaseDataWindow;

@Configurable(dependencyCheck = true)
@Component
public class SampleInReadingDataWindow extends BaseDataWindow<SampleInReadingReportBean,SampleInReadingDataController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<SampleInReadingReportBean> getBeanClass() {
		return SampleInReadingReportBean.class;
	}

	@Override
	protected String setTitle() {
		return "Sample Inward Reports";
	}

}
