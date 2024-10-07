package com.nt.binding;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PatientDTO {
	private Long patientId;
	private String patientName;
	private String phoneNumber;
	private String email;
	private String disease;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dob;
	private Integer age;
}
