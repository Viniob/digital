package com.digital.checkpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.checkpoint.model.AlocateTime;
import com.digital.checkpoint.service.AlocateTimeService;

@RestController
@RequestMapping("/alocateTime")
public class AlocateTimeController {

	@Autowired
	private AlocateTimeService alocateTimeService;

	@PostMapping
	public ResponseEntity<?> alocateTime(@RequestBody AlocateTime alocateTime) throws Exception {
		AlocateTime alocatedTime = alocateTimeService.launchHours(alocateTime);
		return new ResponseEntity<>(alocatedTime, HttpStatus.OK);
	}

}
