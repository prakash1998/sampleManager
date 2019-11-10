package com.pra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseEntityController;
import com.pra.model.PartyOut;
import com.pra.model.Product;
import com.pra.model.SampleOut;
import com.pra.reports.beans.SampleOutReadingReportBean;
import com.pra.repositories.SampleOutRepository;
import com.pra.view.SampleOutWindow;

@Service
public class SampleOutController extends BaseEntityController<SampleOut, Integer, SampleOutRepository, SampleOutWindow>{
	
	@Autowired 
	private PartyOutController partyOutControl;
	@Autowired
	private ProductController productControl;
	@Autowired
	private SampleOutReadingController readingControl;

	public List<PartyOut> getParties(){
		return partyOutControl.getAll();
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
	
	public void navigateToSampleOutReading(SampleOut obj) {
		readingControl.openWindow(obj);
		this.closeWindow();
	}
	
	public void navigateToSampleOutReading(SampleOutReadingReportBean obj) {
		readingControl.openWindow(readingControl.getById(obj.getPrimaryKey()).orElse(null));
		this.closeWindow();
	}

}
