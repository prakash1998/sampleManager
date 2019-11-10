package com.pra.view;

import org.springframework.stereotype.Component;

import com.pra.controller.PartyInDataController;
import com.pra.model.PartyIn;
import com.pra.view.basewindows.SimpleBaseDataWindow;

@Component
public class PartyInDataWindow extends SimpleBaseDataWindow<PartyIn,PartyInDataController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<PartyIn> getBeanClass() {
		return PartyIn.class;
	}

	@Override
	protected String setTitle() {
		return "Party Inwards";
	}

}
