package com.nisum.demotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.demotest.data.ZipCodeRangeRequest;
import com.nisum.demotest.data.ZipCodeRangeResponse;
import com.nisum.demotest.service.ZipCodeRangeService;

@RestController
public class ZipCodeRangeController {

	@Autowired
	private ZipCodeRangeService zipCodeRangeService;

	@RequestMapping(value = "/zipcode", method = RequestMethod.POST)
	public ResponseEntity<ZipCodeRangeResponse> getZipCodeRangeList(
			@RequestBody ZipCodeRangeRequest zipCodeRangeRequest) {
		ZipCodeRangeResponse zipCodeRangeResponse = new ZipCodeRangeResponse();
		zipCodeRangeService.getZipCodeRangeList(zipCodeRangeRequest, zipCodeRangeResponse);
		return ResponseEntity.ok().body(zipCodeRangeResponse);
	}
}
