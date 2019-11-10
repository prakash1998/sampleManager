package com.pra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseEntityController;
import com.pra.model.SampleOut;
import com.pra.model.SampleOutReading;
import com.pra.repositories.SampleOutReadingRepository;
import com.pra.view.SampleOutReadingWindow;

@Service
public class SampleOutReadingController extends BaseEntityController<SampleOutReading, Integer, SampleOutReadingRepository, SampleOutReadingWindow>{
	
	@Autowired 
	private PartyOutController partyOutControl;
	@Autowired
	private ProductController productControl;

	
	public void openWindow(SampleOut sample) {
		window.showWindow(sample);
	}

	public int getMaxId() {
		try {
			return repo.getMaxId();
		}catch(NullPointerException ex) {
			return 0;
		}
	}

	
	public void navigateToPartyOut() {
		partyOutControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToProduct() {
		productControl.openWindow();
		this.closeWindow();
	}
	

}
