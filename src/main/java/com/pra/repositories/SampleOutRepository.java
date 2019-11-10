package com.pra.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pra.model.SampleOut;

public interface SampleOutRepository extends JpaRepository<SampleOut, Integer>{
	List<SampleOut> findAllByDateBetweenOrderByRefIdDesc(LocalDate startDate, LocalDate endDate);
	
	@Query("SELECT MAX(t.refId) as m FROM SampleOut t")
	Integer getMaxRefId();
}
