package com.pra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseEntityController;
import com.pra.model.PartyIn;
import com.pra.model.Product;
import com.pra.model.SampleIn;
import com.pra.reports.beans.SampleInReadingReportBean;
import com.pra.repositories.SampleInRepository;
import com.pra.view.SampleInWindow;

@Service
public class SampleInController extends BaseEntityController<SampleIn, Integer, SampleInRepository, SampleInWindow>{
	
	@Autowired 
	private PartyInController partyInControl;
	@Autowired
	private ProductController productControl;
	@Autowired
	private SampleInReadingController readingControl;

	public List<PartyIn> getParties(){
		return partyInControl.getAll();
	}
	
	public List<Product> getProducts(){
		return productControl.getAll();
	}
	
	public int getMaxRefId() {
		try {
			return repo.getMaxRefId();
		}catch(NullPointerException ex) {
			return 0;
		}
	}
	
	public void navigateToHome() {
		homeControl.openWindow();
		this.closeWindow();
	}
	
	public void navigateToSampleInReading(SampleIn obj) {
		readingControl.openWindow(obj);
		this.closeWindow();
	}
	
	public void navigateToSampleInReading(SampleInReadingReportBean obj) {
		readingControl.openWindow(readingControl.getById(obj.getPrimaryKey()).orElse(null));
		this.closeWindow();
	}

}
