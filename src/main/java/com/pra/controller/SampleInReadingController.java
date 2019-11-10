package com.pra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseEntityController;
import com.pra.model.SampleIn;
import com.pra.model.SampleInReading;
import com.pra.repositories.SampleInReadingRepository;
import com.pra.view.SampleInReadingWindow;

@Service
public class SampleInReadingController extends BaseEntityController<SampleInReading, Integer, SampleInReadingRepository, SampleInReadingWindow>{
	
	@Autowired 
	private PartyInController partyInControl;
	@Autowired
	private ProductController productControl;

	
	public void openWindow(SampleIn sample) {
		window.showWindow(sample);
	}

	public int getMaxId() {
		try {
			return repo.getMaxId();
		}catch(NullPointerException ex) {
			return 0;
		}
	}

	
	public void navigateToPartyIn() {
		partyInControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToProduct() {
		productControl.openWindow();
		this.closeWindow();
	}
	

}
