package com.pra.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseDataController;
import com.pra.model.SampleOutReading;
import com.pra.reports.beans.SampleOutReadingReportBean;
import com.pra.repositories.SampleOutReadingRepository;
import com.pra.view.SampleOutReadingDataWindow;

@Service
public class SampleOutReadingDataController extends BaseDataController<SampleOutReading, SampleOutReadingReportBean, Integer, SampleOutReadingRepository, SampleOutReadingController, SampleOutReadingDataWindow>{

	@Override
	public List<SampleOutReading> getBetweenDesc(LocalDate startDate, LocalDate endDate) {
		return repo.findAllByDateBetweenOrderByDateDesc(startDate, endDate);
	}

	@Override
	public List<SampleOutReadingReportBean> convertList(List<SampleOutReading> list) {
		return list.stream()
				.map( sample -> sample.sampleOutReadingReportBean())
				.collect(Collectors.toList());
	}
	
	
}
