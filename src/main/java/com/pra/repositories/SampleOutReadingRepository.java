package com.pra.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pra.model.SampleOutReading;

public interface SampleOutReadingRepository extends JpaRepository<SampleOutReading, Integer>{
	List<SampleOutReading> findAllByDateBetweenOrderByIdDesc(LocalDate startDate, LocalDate endDate);
	
	@Query("SELECT MAX(t.id) as m FROM SampleOutReading t")
	Integer getMaxId();
}
