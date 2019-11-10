package com.pra.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseDataController;
import com.pra.model.SampleIn;
import com.pra.reports.beans.SampleInReportBean;
import com.pra.repositories.SampleInRepository;
import com.pra.view.SampleInDataWindow;

@Service
public class SampleInDataController extends BaseDataController<SampleIn, SampleInReportBean, Integer, SampleInRepository, SampleInController, SampleInDataWindow>{

	@Override
	public List<SampleIn> getBetweenDesc(LocalDate startDate, LocalDate endDate) {
		return repo.findAllByDateBetweenOrderByRefIdDesc(startDate, endDate);
	}

	@Override
	public List<SampleInReportBean> convertList(List<SampleIn> list) {
		return list.stream()
				.map( sample -> sample.sampleInReportBean())
				.collect(Collectors.toList());
	}
	
	
}
