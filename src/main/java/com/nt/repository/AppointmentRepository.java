package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
//		public List<Appointment> findByDoctorId(Long doctorId);
//	    public List<Appointment> findByPatientId(Long patientId);
}
