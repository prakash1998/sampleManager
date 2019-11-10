package com.pra.view;

import org.springframework.stereotype.Component;

import com.pra.controller.PartyOutDataController;
import com.pra.model.PartyOut;
import com.pra.view.basewindows.SimpleBaseDataWindow;

@Component
public class PartyOutDataWindow extends SimpleBaseDataWindow<PartyOut,PartyOutDataController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<PartyOut> getBeanClass() {
		return PartyOut.class;
	}

	@Override
	protected String setTitle() {
		return "Party Outward";
	}

}
