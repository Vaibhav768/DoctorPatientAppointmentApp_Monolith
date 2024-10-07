package com.nt.service;

import java.util.List;

import com.nt.binding.ClinicDTO;
import com.nt.exception.EntityNotFoundException;

public interface IClinicService {
	
	//clinic operations
	public String createClinic(ClinicDTO input)throws IllegalArgumentException;
	public String updateClinic( ClinicDTO input)throws EntityNotFoundException;
	public ClinicDTO getClinicById(Long clinicId)throws EntityNotFoundException;
	public List<ClinicDTO> getAllClinic();
	
}
