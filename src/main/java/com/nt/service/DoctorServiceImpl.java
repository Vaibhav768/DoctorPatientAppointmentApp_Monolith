package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.DoctorClinicDTO;
import com.nt.entity.Clinic;
import com.nt.entity.Doctor;
import com.nt.exception.EntityNotFoundException;
import com.nt.repository.ClinicRepository;
import com.nt.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements IDoctorService {

	// Injecting the repositories
	@Autowired
	private DoctorRepository doctorRepo;
	
	@Autowired
	private ClinicRepository clinicRepo;
	
	/*====================================================================
		i converted entities to DTOs & vice versa in this class only but, we can keep mapping 
		logic into a separate mapper class
		=====================================================================*/
	
	 //Creates a new doctor and associates it with a clinic.
	@Override
	public String createDoctor(DoctorClinicDTO input) {
		// Create a new Clinic entity and set its properties from the DTO
		Clinic clinic = new Clinic();
		clinic.setClinicName(input.getClinicName());
		clinic.setClinicLocation(input.getClinicLocation());
		clinic.setContactNumber(input.getContactNumber());

		// Create a new Doctor entity and set its properties from the DTO
		Doctor doctor = new Doctor();
		doctor.setDoctorName(input.getDoctorName());
		doctor.setPhoneNumber(input.getPhoneNumber());
		doctor.setEmail(input.getEmail());
		doctor.setSpecialty(input.getSpecialty());
		doctor.setClinic(clinic);  // Associate the doctor with the clinic

	    // Clinic will be automatically saved along with Doctor due to CascadeType setting
		// Save the new doctor to the database
		Doctor doctor1 = doctorRepo.save(doctor);
		
		// Return a success message with the doctor's name and ID
		return "Details of Doctor :: "+doctor1.getDoctorName()+" are saved with ID:: "+doctor1.getDoctorId();
	}

	//Updates an existing doctor and associates it with an existing clinic.
	@Override
	public String updateDoctor(DoctorClinicDTO input)throws EntityNotFoundException {
		// Fetch the doctor by doctorId from the input DTO
		Long doctorId = input.getDoctorId();
		Optional<Doctor> doctorData = doctorRepo.findById(doctorId);
		
		 // If the doctor exists, update it, otherwise return a "not found" message
		if(doctorData.isPresent()) {
			 	// Fetch the clinic by clinicId from the input DTO, throw exception if not found
				Clinic existingClinic = clinicRepo.findById(input.getClinicId()).orElseThrow(()->new  EntityNotFoundException("Clinic not found.."));
				
				// Update the doctor's details and associate with the existing clinic
				Doctor doctor = new Doctor();
				doctor.setClinic(existingClinic);
				doctor.setDoctorName(input.getDoctorName());
				
				// Save the updated doctor back to the database
				Doctor updatedDoctor = doctorRepo.save(doctor);
				
				// Return a success message with the doctor's updated ID
				return "Doctor details are updated successfully for ID:: "+updatedDoctor.getDoctorId();
		}
		return "Doctor not found.";
	}

	//Fetches a doctor's details by their ID.
	@Override
	public DoctorClinicDTO getDoctorById(Long doctorId) throws EntityNotFoundException{
		// Fetch the doctor by doctorId, throw exception if not found
		Doctor optional = doctorRepo.findById(doctorId).orElseThrow(()->new EntityNotFoundException("Clinic not found.."));
		
		// Create a new DTO and copy properties from the doctor entity
		DoctorClinicDTO doctorDto = new DoctorClinicDTO();
		BeanUtils.copyProperties(optional,doctorDto);
		
		// Return the DTO with doctor and clinic details
		return doctorDto;
	}
	
	//Fetches a list of all doctors in the database.
	@Override
	public List<DoctorClinicDTO> getAllDoctorsList() {
		// Fetch all doctors from the database
		 List<Doctor> doctors = doctorRepo.findAll();
		 
		// Create a list to store DTOs
		 List<DoctorClinicDTO> doctorList = new ArrayList<>();
		 
		// Convert each Doctor entity to DoctorClinicDTO and add to the list

		 doctors.forEach(doctor->{
			    DoctorClinicDTO doctorDTO = new DoctorClinicDTO();
				BeanUtils.copyProperties(doctor, doctorDTO);
				doctorList.add(doctorDTO);
			});
		 
		// Return the list of doctor DTOs
		 return doctorList;
	}

}
