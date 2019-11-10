package com.pra.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseDataController;
import com.pra.model.SampleInReading;
import com.pra.reports.beans.SampleInReadingReportBean;
import com.pra.repositories.SampleInReadingRepository;
import com.pra.view.SampleInReadingDataWindow;

@Service
public class SampleInReadingDataController extends BaseDataController<SampleInReading, SampleInReadingReportBean, Integer, SampleInReadingRepository, SampleInReadingController, SampleInReadingDataWindow>{

	@Override
	public List<SampleInReading> getBetweenDesc(LocalDate startDate, LocalDate endDate) {
		return repo.findAllByDateBetweenOrderByIdDesc(startDate, endDate);
	}

	@Override
	public List<SampleInReadingReportBean> convertList(List<SampleInReading> list) {
		return list.stream()
				.map( sample -> sample.sampleInReadingReportBean())
				.collect(Collectors.toList());
	}
	
	
}
