package com.pra.view;

import org.springframework.stereotype.Component;

import com.pra.controller.SampleOutDataController;
import com.pra.reports.beans.SampleOutReportBean;
import com.pra.view.basewindows.BaseDataWindow;

@Component
public class SampleOutDataWindow extends BaseDataWindow<SampleOutReportBean,SampleOutDataController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<SampleOutReportBean> getBeanClass() {
		return SampleOutReportBean.class;
	}
	
	@Override
	protected String setTitle() {
		return "Sample Outward Book";
	}

}
