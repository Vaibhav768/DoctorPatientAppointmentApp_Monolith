package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.PatientDTO;
import com.nt.entity.Patient;
import com.nt.exception.EntityNotFoundException;
import com.nt.repository.PatientRepository;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepository patientRepo;
	
	/*====================================================================
	i converted entities to DTOs in this class only but, we can keep mapping logic into a
	separate mapper class
	=====================================================================*/
	
	//Create a new patient in the system.
	@Override
	public String createPatient(PatientDTO input) {
		// Create a new Patient entity and copy properties from the DTO
		Patient entity = new Patient();
		BeanUtils.copyProperties(input, entity);
		
		// Save the patient to the database and return confirmation message
		Patient patientData = patientRepo.save(entity);
		return "Patient Name::"+patientData.getPatientName()+ " is saved with PatientId:: "+patientData.getPatientId();
	}

	//Update existing patient details.
	@Override
	public String updatePatient(PatientDTO patient) throws EntityNotFoundException {
		//Get patientId from DTO
		Long pid = patient.getPatientId();
		
		// Find the patient by ID
		Optional<Patient> patientData = patientRepo.findById(pid);
		
		// If patient exists, update their details
		if(patientData.isPresent()) {
				Patient entity = new Patient();
				
				// Copy updated details to entity
				BeanUtils.copyProperties(patient, entity);
				
				// // Save updated patient and return msg with patientId
				patientRepo.save(entity);
				return "Patient deatails for patientId:: "+patient.getPatientId()+" are updated sccessfully.";
			}
		return "Patient is not found for updation";
	}

	//Retrieve a patient's details by their ID.
	@Override
	public PatientDTO getPatientById(Long patientId) throws EntityNotFoundException{
		// Fetch the patient by ID, throw exception if not found
		PatientDTO patientDetails = new PatientDTO();
		Patient entity = patientRepo.findById(patientId).orElseThrow(()->new EntityNotFoundException("Patient not found.."));
		
		// Convert the entity to DTO and return it
		BeanUtils.copyProperties(entity, patientDetails);
		return patientDetails;
	}

	//Retrieve a list of all patients.
	@Override
	public List<PatientDTO> getAllPatients() {
		// Fetch all patients from the database
		List<Patient> patients = patientRepo.findAll();
		
		// Convert each patient entity to DTO and add to the list
		List<PatientDTO> patientsList = new ArrayList<>();
		 patients.forEach(patient->{
			    PatientDTO patientDTO = new PatientDTO();
				BeanUtils.copyProperties(patient, patientDTO);
				patientsList.add(patientDTO);
			});
		 return patientsList;
	}

}
