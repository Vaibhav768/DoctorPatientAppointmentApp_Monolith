package com.nt.binding;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppointmentDTO {
	private Long appointmentId;
	private Long doctorId;
	private Long patientId;
	private LocalDateTime appointmentDate;
	private String reason;
}
