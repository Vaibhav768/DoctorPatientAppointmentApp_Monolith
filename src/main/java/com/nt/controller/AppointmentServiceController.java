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

import com.nt.binding.AppointmentDTO;
import com.nt.exception.EntityNotFoundException;
import com.nt.service.IAppointmentService;

//Handles Appointments related operations
@RestController
@RequestMapping("/appointment-api")
public class AppointmentServiceController {
	@Autowired
	private IAppointmentService appointmentService;

	 @PostMapping("/{doctorId}/{patientId}")
	public ResponseEntity<String> createAppointment( @PathVariable Long doctorId,@PathVariable Long patientId,@RequestBody AppointmentDTO appointment) throws Exception{
		String resultMsg = appointmentService.createAppointment(doctorId, patientId,appointment);
		return new ResponseEntity<>(resultMsg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-doctor")
	public ResponseEntity<String> updateAppointment(@PathVariable Long appointmentId,@RequestBody AppointmentDTO appointment)throws EntityNotFoundException{
		String resultMsg = appointmentService.updateAppointment(appointmentId,appointment);
		return new ResponseEntity<>(resultMsg,HttpStatus.OK);
	}
	
	@GetMapping("/appointment/{aid}")
	public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable("aid") Long appointmentId)throws EntityNotFoundException{
		AppointmentDTO appointmentDetails = appointmentService.getAppointDetailsById(appointmentId);
		return new ResponseEntity<>(appointmentDetails,HttpStatus.OK);
	}
	
	@GetMapping("/all-appointments")
	public ResponseEntity<List<AppointmentDTO>> getAllAppointments()throws Exception{
		List<AppointmentDTO> appointmentList = appointmentService.getAllAppointments();
		return new ResponseEntity<>(appointmentList,HttpStatus.OK);
	}
	
	/*
	@GetMapping("/appointment/{doctorId}")
	public ResponseEntity<List<AppointmentDTO>> getAppointmentByDoctorId(@PathVariable Long doctorId)throws EntityNotFoundException{
		List<AppointmentDTO> appointmentList = appointmentService.getAppointmentByDoctorId(doctorId);
		return new ResponseEntity<>(appointmentList,HttpStatus.OK);
	}
	
	@GetMapping("/appointment/{patientId}")
	public ResponseEntity<List<AppointmentDTO>> getAppointmentByPatientId(@PathVariable Long patientId)throws EntityNotFoundException{
		List<AppointmentDTO> appointmentList = appointmentService.getAppointmentByPatientId(patientId);
		return new ResponseEntity<>(appointmentList,HttpStatus.OK);
	}*/
}
