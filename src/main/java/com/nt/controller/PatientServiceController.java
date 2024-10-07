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

import com.nt.binding.PatientDTO;
import com.nt.exception.EntityNotFoundException;
import com.nt.service.IPatientService;

//Handles Patients related operations
@RestController
@RequestMapping("/patient-api")
public class PatientServiceController {
	
	@Autowired
	private IPatientService patientService;

	@PostMapping("/create-patient")
	public ResponseEntity<String> createPatient(@RequestBody PatientDTO patient) throws IllegalArgumentException{
		String resultMsg = patientService.createPatient(patient);
		return new ResponseEntity<>(resultMsg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-patient")
	public ResponseEntity<String> updatePatient(@RequestBody PatientDTO patient)throws EntityNotFoundException{
		String resultMsg = patientService.updatePatient(patient);
		return new ResponseEntity<>(resultMsg,HttpStatus.OK);
	}
	
	@GetMapping("/patient/{pid}")
	public ResponseEntity<PatientDTO> getPatientById(@PathVariable("pid") Long patientId)throws EntityNotFoundException{
		PatientDTO pdetails = patientService.getPatientById(patientId);
		return new ResponseEntity<>(pdetails,HttpStatus.OK);
	}
	
	@GetMapping("/all-patients")
	public ResponseEntity<List<PatientDTO>> getAllPatients()throws Exception{
		List<PatientDTO> patientList = patientService.getAllPatients();
		return new ResponseEntity<>(patientList,HttpStatus.OK);
	}
}
