package com.digital.checkpoint.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digital.checkpoint.model.CheckPoint;

@Repository
public interface CheckPointRepository extends CrudRepository<CheckPoint, Integer> {

	CheckPoint findByDayAndRegistration(LocalDateTime day, String registration);

	List<CheckPoint> findByDayGreaterThanEqualAndDayLessThanEqual(LocalDateTime startDay, LocalDateTime endDay);
	
}
