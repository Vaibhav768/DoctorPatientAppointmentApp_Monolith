package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

}
