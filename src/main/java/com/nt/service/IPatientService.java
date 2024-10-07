package com.nt.service;

import java.util.List;

import com.nt.binding.PatientDTO;
import com.nt.exception.EntityNotFoundException;

public interface IPatientService {
	
	//patient operations
	public String createPatient(PatientDTO input);
	public String updatePatient( PatientDTO patient)throws EntityNotFoundException;
	public PatientDTO getPatientById(Long patientId) throws EntityNotFoundException ;
	public List<PatientDTO> getAllPatients();

}
