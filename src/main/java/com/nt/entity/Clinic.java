package com.nt.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="DPM_CLINIC_DETAILS")
public class Clinic {
	@Id
	@SequenceGenerator(name="gen3",sequenceName="Clinic_Id_Seq",initialValue=3000,allocationSize=1)
	@GeneratedValue(generator="gen3",strategy=GenerationType.AUTO)
	private Long clinicId;
	@NonNull
	private String clinicName;
	@NonNull
	private String clinicLocation;
	@NonNull
	private String contactNumber;
	
	@OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<Doctor> doctors;
}
