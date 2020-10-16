package com.digital.checkpoint.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.checkpoint.model.AlocateTime;
import com.digital.checkpoint.model.CheckPoint;
import com.digital.checkpoint.repository.AlocateTimeRepository;
import com.digital.checkpoint.repository.CheckPointRepository;

@Service
public class CheckPointService {

	@Autowired
	private CheckPointRepository checkPointRepository;

	@Autowired
	private AlocateTimeRepository alocateTimeRepository;

	public CheckPoint checkPointExecuteOneTime(CheckPoint checkPoint) throws Exception {

		validArrivedTimeExitTime(checkPoint);

		validLunchTime(checkPoint);

		validWeekendsDay(checkPoint);

		CheckPoint checkPointSuccess = checkPointRepository.save(checkPoint);

		return checkPointSuccess;
	}

	public CheckPoint updateWorkedTime(CheckPoint checkPoint) throws Exception {
		validArrivedTimeExitTime(checkPoint);

		validLunchTime(checkPoint);

		validWeekendsDay(checkPoint);

		List<AlocateTime> findByDayWorkedAndRegistration = alocateTimeRepository
				.findByDayWorkedAndRegistration(checkPoint.getDay(), checkPoint.getRegistration());
		int fullAlocatedTime = 0;
		for (AlocateTime alocatedTime : findByDayWorkedAndRegistration) {
			fullAlocatedTime = fullAlocatedTime + alocatedTime.getWorkedTime();
		}
		Double hoursWorked = (double) (checkPoint.getExitTime().getHour() - checkPoint.getArrivalTime().getHour());
		if (fullAlocatedTime > hoursWorked) {
			throw new Exception("Allocated time cannot be greater than the new time worked on the day.");
		}
		CheckPoint timeUpdated = checkPointRepository.save(checkPoint);
		return timeUpdated;
	}

	private void validWeekendsDay(CheckPoint checkPoint) throws Exception {
		if (checkPoint.getDay().getDayOfWeek() == DayOfWeek.SUNDAY
				|| checkPoint.getDay().getDayOfWeek() == DayOfWeek.SATURDAY) {
			throw new Exception("Fail, you can't work on sundays or saturdays");
		}
	}

	private void validLunchTime(CheckPoint checkPoint) throws Exception {
		int workedTime = checkPoint.getExitTime().getHour() - checkPoint.getArrivalTime().getHour();
		if (workedTime < 9) {
			throw new Exception("Fail, you need at least one hour of lunch time");
		}
	}

	private void validArrivedTimeExitTime(CheckPoint checkPoint) throws Exception {
		boolean validTime = (checkPoint.getArrivalTime().isAfter(checkPoint.getExitTime())) ? true : false;
		if (validTime) {
			throw new Exception("Fail, arrive time can't be over than exit time");
		}
	}

	public String checkWorkedTimeOnMounth(String registration, LocalDateTime date) {
		LocalDateTime startDay = LocalDateTime.of(date.getYear(), date.getMonth(), 1, 0, 0, 0);
		LocalDateTime endDay = LocalDateTime.of(date.getYear(), date.getMonth(), 31, 0, 0, 0);

		List<CheckPoint> timeWorkedMonth = checkPointRepository.findByDayGreaterThanEqualAndDayLessThanEqual(startDay, endDay);
		int fullWorkedTime = 0;
		for (CheckPoint checkPoint : timeWorkedMonth) {
			fullWorkedTime = fullWorkedTime + (checkPoint.getExitTime().getHour() - checkPoint.getArrivalTime().getHour());
		}
		
		int monthFullTime = 168;
		monthFullTime = monthFullTime - fullWorkedTime;
		String msg = (monthFullTime > 0) ? "hours due " + monthFullTime : "Congratulations for the perfect time";
		return msg;
		
	}

}
