package com.nt.service;

import java.util.List;

import com.nt.binding.AppointmentDTO;
import com.nt.exception.EntityNotFoundException;

public interface IAppointmentService {
	
	//appointment operations
		public String createAppointment(Long doctorId, Long patientId, AppointmentDTO input)throws Exception;
		public String updateAppointment( Long appointmentId, AppointmentDTO input)throws EntityNotFoundException;
		public AppointmentDTO getAppointDetailsById(Long appointmentId)throws EntityNotFoundException;
		public List<AppointmentDTO> getAllAppointments();
//		public List<AppointmentDTO> getAppointmentByDoctorId(Long doctorId)throws EntityNotFoundException;
//		public List<AppointmentDTO> getAppointmentByPatientId(Long patientId)throws EntityNotFoundException;
		
}
