package com.digital.checkpoint.controller;

import java.time.LocalDateTime;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.checkpoint.model.CheckPoint;
import com.digital.checkpoint.service.CheckPointService;

@RestController
@RequestMapping("/checkpoint")
public class CheckPointController {

	@Autowired
	private CheckPointService checkPointService;

	@PostMapping("/checkPointExecuteOneTime")
	public ResponseEntity<?> checkPoint(@RequestBody CheckPoint checkPoint) throws Exception {
		CheckPoint checkPointExecuteOneTime = checkPointService.checkPointExecuteOneTime(checkPoint);
		return new ResponseEntity<>(checkPointExecuteOneTime, HttpStatus.ACCEPTED);
	}

	@PutMapping("/workedTime")
	public ResponseEntity<?> updateWorkedTime(@RequestBody CheckPoint checkPoint) throws Exception {

		CheckPoint timeUpdated = checkPointService.updateWorkedTime(checkPoint);
		return new ResponseEntity<>(timeUpdated, HttpStatus.OK);
	}

	@GetMapping("/workedHours/{registration}/{date}")
	public ResponseEntity<?> workedHours(@PathVariable("registration") String registration,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date) {

		String checkWorkedTimeOnMounth = checkPointService.checkWorkedTimeOnMounth(registration, date);

		return new ResponseEntity<>(checkWorkedTimeOnMounth, HttpStatus.OK);
	}
}
