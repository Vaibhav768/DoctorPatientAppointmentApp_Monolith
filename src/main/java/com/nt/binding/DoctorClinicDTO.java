package com.nt.binding;

import lombok.Data;

@Data
public class DoctorClinicDTO {
	private Long doctorId;
	private String doctorName;
	private String phoneNumber;
	private String email;
	private String specialty;
	private Long clinicId;
	private String clinicName;
	private String clinicLocation;
	private String contactNumber;
}
