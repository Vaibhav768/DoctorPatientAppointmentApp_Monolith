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

import com.nt.binding.DoctorClinicDTO;
import com.nt.exception.EntityNotFoundException;
import com.nt.service.IDoctorService;

//Handles Doctors related operations
@RestController
@RequestMapping("/doctor-api")
public class DoctorServiceController {
	@Autowired
	private IDoctorService doctorService;

	@PostMapping("/create-doctor")
	public ResponseEntity<String> createDoctor(@RequestBody DoctorClinicDTO doctor) throws IllegalArgumentException{
		String resultMsg = doctorService.createDoctor(doctor);
		return new ResponseEntity<>(resultMsg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-doctor")
	public ResponseEntity<String> updateDoctor(@RequestBody DoctorClinicDTO doctor)throws EntityNotFoundException{
		String resultMsg = doctorService.updateDoctor(doctor);
		return new ResponseEntity<>(resultMsg,HttpStatus.OK);
	}
	
	@GetMapping("/doctor/{did}")
	public ResponseEntity<DoctorClinicDTO> getDoctorById(@PathVariable("did") Long doctorId)throws EntityNotFoundException{
		DoctorClinicDTO doctorDetails = doctorService.getDoctorById(doctorId);
		return new ResponseEntity<>(doctorDetails,HttpStatus.OK);
	}
	
	@GetMapping("/all-doctors")
	public ResponseEntity<List<DoctorClinicDTO>> getAllDoctor()throws Exception{
		List<DoctorClinicDTO> doctorList = doctorService.getAllDoctorsList();
		return new ResponseEntity<>(doctorList,HttpStatus.OK);
	}
}
