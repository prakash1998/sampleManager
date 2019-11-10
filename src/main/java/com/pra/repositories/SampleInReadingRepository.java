package com.pra.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pra.model.SampleInReading;

public interface SampleInReadingRepository extends JpaRepository<SampleInReading, Integer>{
	List<SampleInReading> findAllByDateBetweenOrderByIdDesc(LocalDate startDate, LocalDate endDate);
	
	@Query("SELECT MAX(t.id) as m FROM SampleInReading t")
	Integer getMaxId();
}
