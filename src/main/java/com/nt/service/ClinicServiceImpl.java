package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.ClinicDTO;
import com.nt.entity.Clinic;
import com.nt.exception.EntityNotFoundException;
import com.nt.repository.ClinicRepository;

@Service
public class ClinicServiceImpl implements IClinicService {

	@Autowired
	private ClinicRepository clinicRepo;
		/*=========================================================================
		 We can create or update Clinic separately, without affecting the associated Doctor entity.
		 If you update only clinic attributes like clinicName, clinicLocation, clinicPhone, It will not affect 
		 on Doctor Entity, but if modify the doctors list then this will affect the association
		 ===========================================================================
		 		i converted entities to DTOs in this class only but, we can keep mapping logic into a
		 		separate mapper class
		 ==========================================================================*/
	
	//Create a new clinic entry in the database.
	@Override
	public String createClinic(ClinicDTO input) throws IllegalArgumentException {
		// Create a new Clinic entity and copy properties from the DTO
		Clinic clinic = new Clinic();
		BeanUtils.copyProperties(input, clinic);
		
		// Save the clinic to the database and return a confirmation message
		Long clinicId = clinicRepo.save(clinic).getClinicId();
		return "Clinic Details saved with clinicId:: "+clinicId;
	}

	//Update an existing clinic's details in the database.
	@Override
	public String updateClinic(ClinicDTO input)throws EntityNotFoundException {
		// Find clinic by ID
		Optional<Clinic> optional = clinicRepo.findById(input.getClinicId());
		
		// If clinic is found, update its details
		if(optional.isPresent()) {
			Clinic clinic = new Clinic();
			 // Copy new details to Clinic entity
			BeanUtils.copyProperties(input, clinic);
			
			//// Save the updated clinic & return msg
			clinicRepo.save(clinic);
			return "Clinic details for clinicId:: "+input.getClinicId()+" are updated successfully.";
		}
		return "Clinic Not Found";
	}

	// Retrieve a clinic's details by its ID.
	@Override
	public ClinicDTO getClinicById(Long clinicId)throws EntityNotFoundException {
		ClinicDTO patientDetails = new ClinicDTO();
		
		// Fetch clinic by ID, throw exception if not found
		Clinic clinic = clinicRepo.findById(clinicId).orElseThrow(()->new EntityNotFoundException("Patient not found.."));
		
		// Copy clinic data to DTO and return
		BeanUtils.copyProperties(clinic, patientDetails);
		return patientDetails;
	}

	//Retrieve a list of all clinics from the database.
	@Override
	public List<ClinicDTO> getAllClinic() {
		
		// Fetch all clinics from the database
		List<Clinic> clinics = clinicRepo.findAll();
		
		 // Prepare a DTO list for response
		List<ClinicDTO> clinicList = new ArrayList<>();
		
		// Convert each Clinic entity to ClinicDTO and add to the list
		clinics.forEach(clinic->{
			ClinicDTO clinicDTO = new ClinicDTO();
			BeanUtils.copyProperties(clinic, clinicDTO);
			clinicList.add(clinicDTO);
		});
	 return clinicList;
	}
}
