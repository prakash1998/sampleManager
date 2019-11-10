package com.pra.view;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.pra.controller.SampleOutReadingDataController;
import com.pra.reports.beans.SampleOutReadingReportBean;
import com.pra.view.basewindows.BaseDataWindow;

@Configurable(dependencyCheck = true)
@Component
public class SampleOutReadingDataWindow
		extends BaseDataWindow<SampleOutReadingReportBean, SampleOutReadingDataController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<SampleOutReadingReportBean> getBeanClass() {
		return SampleOutReadingReportBean.class;
	}
	
	@Override
	protected String setTitle() {
		return "Sample Outward Reports";
	}

}
