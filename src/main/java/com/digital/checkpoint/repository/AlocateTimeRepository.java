package com.digital.checkpoint.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digital.checkpoint.model.AlocateTime;

@Repository
public interface AlocateTimeRepository extends CrudRepository<AlocateTime, Integer>{

	List<AlocateTime> findByDayWorkedAndRegistration(LocalDateTime dayWorked, String registrarion);
	
	
}
