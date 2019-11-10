package com.pra.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseDataController;
import com.pra.model.SampleOut;
import com.pra.reports.beans.SampleOutReportBean;
import com.pra.repositories.SampleOutRepository;
import com.pra.view.SampleOutDataWindow;

@Service
public class SampleOutDataController extends BaseDataController<SampleOut, SampleOutReportBean, Integer, SampleOutRepository, SampleOutController, SampleOutDataWindow>{


	@Override
	public List<SampleOut> getBetweenDesc(LocalDate startDate, LocalDate endDate) {
		return repo.findAllByDateBetweenOrderByRefIdDesc(startDate, endDate);
	}

	@Override
	public List<SampleOutReportBean> convertList(List<SampleOut> list) {
		return list.stream()
				.map( sample -> sample.sampleOutReportBean())
				.collect(Collectors.toList());
	}

}
