package com.pra.view;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.pra.controller.SampleInDataController;
import com.pra.reports.beans.SampleInReportBean;
import com.pra.view.basewindows.BaseDataWindow;

@Configurable(dependencyCheck = true)
@Component
public class SampleInDataWindow extends BaseDataWindow<SampleInReportBean,SampleInDataController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<SampleInReportBean> getBeanClass() {
		return SampleInReportBean.class;
	}

	@Override
	protected String setTitle() {
		return "Sample Inward Book";
	}

}
