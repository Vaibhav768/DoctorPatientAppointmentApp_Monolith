package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.binding.ClinicDTO;
import com.nt.exception.EntityNotFoundException;
import com.nt.service.IClinicService;

//Handles Clinic related operations
@RestController
@RequestMapping("/clinic-api")
public class ClinicServiceController {
	@Autowired
	private IClinicService clinicService;

	@PostMapping("/create-clinic")
	public ResponseEntity<String> createClinic(@RequestBody ClinicDTO clinic) throws IllegalArgumentException{
		String resultMsg = clinicService.createClinic(clinic);
		return new ResponseEntity<>(resultMsg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-clinic")
	public ResponseEntity<String> updateClinic(@RequestBody ClinicDTO clinic)throws EntityNotFoundException{
		String resultMsg = clinicService.updateClinic(clinic);
		return new ResponseEntity<>(resultMsg,HttpStatus.OK);
	}
	
	@GetMapping("/clinic/{cid}")
	public ResponseEntity<ClinicDTO> getClinicById(@PathVariable("cid") Long clinicId)throws EntityNotFoundException{
		ClinicDTO clinicDetails = clinicService.getClinicById(clinicId);
		return new ResponseEntity<>(clinicDetails,HttpStatus.OK);
	}
	
	@GetMapping("/all-clinics")
	public ResponseEntity<List<ClinicDTO>> getAllClinics()throws Exception{
		List<ClinicDTO> clinicList = clinicService.getAllClinic();
		return new ResponseEntity<>(clinicList,HttpStatus.OK);
	}
}
