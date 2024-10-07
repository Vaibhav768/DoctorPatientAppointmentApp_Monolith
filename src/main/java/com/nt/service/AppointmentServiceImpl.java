package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.AppointmentDTO;
import com.nt.entity.Appointment;
import com.nt.entity.Doctor;
import com.nt.entity.Patient;
import com.nt.exception.EntityNotFoundException;
import com.nt.repository.AppointmentRepository;
import com.nt.repository.DoctorRepository;
import com.nt.repository.PatientRepository;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	// Injecting the repositories
	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Autowired
	private DoctorRepository doctorRepo;
	
	@Autowired
	private PatientRepository patientRepo;

		/*====================================================================
	 		i converted entities to DTOs in this class only but, we can keep mapping logic into a
	 		separate mapper class
	 		=====================================================================*/
	
	//Creates a new appointment for a given doctor and patient.
	@Override
	public String createAppointment(Long doctorId, Long patientId, AppointmentDTO input) throws Exception {
		// Fetch the doctor entity by doctorId, throw exception if not found
		Doctor doctor = doctorRepo.findById(doctorId)
														      .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
		
		// Fetch the patient entity by patientId, throw exception if not found
        Patient patient = patientRepo.findById(patientId) 
        														 .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        
        // Create a new Appointment entity
        Appointment appointment = new Appointment();
        
        // Copy properties from input DTO to the appointment entity
        BeanUtils.copyProperties(input, appointment);
        
        // Set the associated doctor and patient for the appointment
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        
        // Save the new appointment in the database
        Appointment appointment1 = appointmentRepo.save(appointment);
        
        // Return a success message including the doctor and patient names
        return "Appointment is created for Patient :: "+appointment1.getPatient().getPatientName()+
        		" with Doctor :: "+appointment1.getDoctor().getDoctorName()+" Appointment ID is :: "+
        		appointment1.getAppointmentId();
        
	}
	
	// Updates an existing appointment by its ID.
	@Override
	public String updateAppointment(Long appointmentId, AppointmentDTO input) throws EntityNotFoundException {
		// Fetch the existing appointment by appointmentId, throw exception if not found
		Appointment existingAppointment = appointmentRepo.findById(appointmentId)
                											 .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
		
		// Create a new Appointment entity and copy updated details from input DTO
		Appointment updateAppointment = new Appointment();
		BeanUtils.copyProperties(input, updateAppointment);
		
		// Update fields of the existing appointment
        existingAppointment.setAppointmentDate(updateAppointment.getAppointmentDate());
        existingAppointment.setReason(updateAppointment.getReason());
        
        // Save the updated appointment in the database
        Appointment appointment = appointmentRepo.save(existingAppointment);
        
        // Return a success message including the patient and doctor IDs
        return "Appointment is Updated for patient :: "+appointment.getPatient().getPatientId()+
        			"with doctor :: "+appointment.getDoctor().getDoctorId();
	}
	
	// Fetches appointment details by its ID.
	@Override
	public AppointmentDTO getAppointDetailsById(Long appointmentId)throws EntityNotFoundException{
		// Fetch the appointment by appointmentId, throw exception if not found
		Appointment appointmentEntity = appointmentRepo.findById(appointmentId).orElseThrow(()->new IllegalArgumentException("Appointment not found.."));
		
		// Create a new AppointmentDTO and copy properties from entity
		AppointmentDTO appointment = new AppointmentDTO();
		BeanUtils.copyProperties(appointmentEntity,appointment);
		
		// Return the DTO with appointment details
		return appointment;
	}

	//Fetches all appointments from the database.
	@Override
	public List<AppointmentDTO> getAllAppointments() {
		// Fetch all appointments from the repository
		 List<Appointment> appointments = appointmentRepo.findAll();
		 
		// Create an empty list to store appointment DTOs
		 List<AppointmentDTO> appointmentList = new ArrayList<>();
		 
		// Convert each Appointment entity to AppointmentDTO and add it to the list
		 appointments.forEach(appointment->{
			    AppointmentDTO appointmentDTO = new AppointmentDTO();
				BeanUtils.copyProperties(appointment, appointmentDTO);
				appointmentList.add(appointmentDTO);
			});
		 
		// Return the list of appointment DTOs
		 return appointmentList;
	}

	/*
	 //Fetches appointment details by Doctor ID.
	@Override
	public List<AppointmentDTO> getAppointmentByDoctorId(Long doctorId) throws EntityNotFoundException{
		List<Appointment> appointments = appointmentRepo.findByDoctorId(doctorId);
		List<AppointmentDTO> appointmentList = new ArrayList<>();
		 appointments.forEach(appointment->{
			    AppointmentDTO appointmentDTO = new AppointmentDTO();
				BeanUtils.copyProperties(appointment, appointmentDTO);
				appointmentList.add(appointmentDTO);
			});
		 return appointmentList;
	}
	
	//Fetches appointment details by Patient ID.
	@Override
	public List<AppointmentDTO> getAppointmentByPatientId(Long patientId) throws EntityNotFoundException{
		List<Appointment> appointments = appointmentRepo.findByPatientId(patientId);
		List<AppointmentDTO> appointmentList = new ArrayList<>();
		 appointments.forEach(appointment->{
			    AppointmentDTO appointmentDTO = new AppointmentDTO();
				BeanUtils.copyProperties(appointment, appointmentDTO);
				appointmentList.add(appointmentDTO);
			});
		 return appointmentList;
	}
*/
}
