package com.pra.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pra.model.SampleIn;

public interface SampleInRepository extends JpaRepository<SampleIn, Integer>{
	List<SampleIn> findAllByDateBetweenOrderByDateDesc(LocalDate startDate, LocalDate endDate);
	
	@Query("SELECT MAX(t.refId) as m FROM SampleIn t")
	Integer getMaxRefId();
}
