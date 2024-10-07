package com.nt.service;

import java.util.List;

import com.nt.binding.DoctorClinicDTO;
import com.nt.exception.EntityNotFoundException;

public interface IDoctorService {
	
	//doctor operations
		public String createDoctor(DoctorClinicDTO input);
		public String updateDoctor( DoctorClinicDTO input)throws EntityNotFoundException;
		public DoctorClinicDTO getDoctorById(Long doctorId)throws EntityNotFoundException;
		public List<DoctorClinicDTO> getAllDoctorsList();
		
}
