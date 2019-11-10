package com.pra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pra.ApplicationShutdownManager;
import com.pra.controller.interfaces.BaseWindowController;
import com.pra.view.HomeWindow;

@Service
public class HomeController extends BaseWindowController<HomeWindow>{
	
	@Autowired
	private SampleInController sampleInControl;
	@Autowired
	private SampleInDataController sampleInDataControl;
	@Autowired
	private SampleOutController sampleOutControl;
	@Autowired
	private SampleOutDataController sampleOutDataControl;
	@Autowired
	private SampleInReadingController sampleInReadingControl;
	@Autowired
	private SampleInReadingDataController sampleInReadingDataControl;
	@Autowired
	private SampleOutReadingController sampleOutReadingControl;
	@Autowired
	private SampleOutReadingDataController sampleOutReadingDataControl;
	@Autowired 
	private PartyInDataController partyInDataControl;
	@Autowired
	private PartyOutDataController partyOutDataControl;
	@Autowired
	private ProductDataController productDataControl;
	@Autowired
	private ApplicationShutdownManager shutdownManager;
	
	public void shutDownApp() {
		this.closeWindow();
		shutdownManager.initiateShutdown(0);
	}
	
	public void navigateToSampleIn() {
		sampleInControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToSampleInData() {
		sampleInDataControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToSampleOut() {
		sampleOutControl.openWindow();
		this.closeWindow();
	}

	public void navigateToSampleOutData() {
		sampleOutDataControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToSampleInReading() {
		sampleInReadingControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToSampleInReadingData() {
		sampleInReadingDataControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToSampleOutReading() {
		sampleOutReadingControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToSampleOutReadingData() {
		sampleOutReadingDataControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToPartyInData() {
		partyInDataControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToPartyOutData() {
		partyOutDataControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToProductData() {
		productDataControl.openWindow();
		this.closeWindow();
	}
	
}
