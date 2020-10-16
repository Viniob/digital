package com.digital.checkpoint.service;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.checkpoint.model.AlocateTime;
import com.digital.checkpoint.model.CheckPoint;
import com.digital.checkpoint.repository.AlocateTimeRepository;
import com.digital.checkpoint.repository.CheckPointRepository;

@Service
public class AlocateTimeService {

	@Autowired
	private CheckPointRepository checkPointRepository;

	@Autowired
	private AlocateTimeRepository alocateTimeRepository;

	public AlocateTime launchHours(AlocateTime alocateTime) throws Exception {

		if (alocateTime.getDayWorked().getDayOfWeek() == DayOfWeek.SUNDAY
				|| alocateTime.getDayWorked().getDayOfWeek() == DayOfWeek.SUNDAY) {
			throw new Exception("Fail, you can't work on sundays or saturdays");
		}

		CheckPoint compareWorkedDayTime = checkPointRepository.findByDayAndRegistration(alocateTime.getDayWorked(),
				alocateTime.getRegistration());
		if (compareWorkedDayTime != null) {

			Double hoursWorked = (double) (compareWorkedDayTime.getExitTime().getHour()
					- compareWorkedDayTime.getArrivalTime().getHour());

			if (alocateTime.getWorkedTime() > hoursWorked) {
				throw new Exception("Fail, your alocate time on a project can not be greater than your worked time ");
			}
		}

		List<AlocateTime> findByDayWorkedAndRegistration = alocateTimeRepository
				.findByDayWorkedAndRegistration(alocateTime.getDayWorked(), alocateTime.getRegistration());

		int fullWorkedTime = 0;
		for (AlocateTime alocatedTime : findByDayWorkedAndRegistration) {
			fullWorkedTime = fullWorkedTime + alocatedTime.getWorkedTime();
		}

		if ((fullWorkedTime + alocateTime.getWorkedTime()) > 8) {
			throw new Exception("Fail, total sum of hours allocated cannot be greater than your working hours");
		}

		AlocateTime alocateTimeSaved = alocateTimeRepository.save(alocateTime);

		return alocateTimeSaved;
	}
}
