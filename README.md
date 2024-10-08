Due to time constraints, I was unable to write unit tests or incorporate security features.
Additionally, I acknowledge that there is room for improvement, such as adding caching and security.

--Project Title-- DoctorPatientAppointmentApp_Monolith
This one is monolithic model of DoctorPatientAppointmentApp- 
Having four rest controller enables clean seperation between 4 services Doctor, Clinic, Patient, Appointment

--Tools Used for development--
Java 17, Spring Boot 3, Oracle database, Swagger, Postman, Maven, GitHub, GitLab

--API Endpoints--
1) DoctorServiceController -
  POST http://localhost:9090/doctor-api/create-doctor
  PUT  http://localhost:9090/doctor-api/update-doctor
  GET  http://localhost:9090/doctor-api/doctor/{did}
  GET  http://localhost:9090/doctor-api/all-doctors

2) ClinicServiceController -
  POST http://localhost:9090/clinic-api/create-clinic
  PUT  http://localhost:9090/clinic-api/update-clinic
  GET  http://localhost:9090/clinic-api/clinic/{cid}
  GET  http://localhost:9090/clinic-api/all-clinics

3) PatientServiceController -
  POST http://localhost:9090/patient-api/create-patient
  PUT  http://localhost:9090/patient-api/update-patient
  GET  http://localhost:9090/patient-api/patient/{pid}
  GET  http://localhost:9090/patient-api/all-patients

4) AppointmentServiceController -
  POST http://localhost:9090/appointment-api/create-appointment/{doctorId}/{patientId}
  PUT  http://localhost:9090/appointment-api/update-appointment
  GET  http://localhost:9090/appointment-api/appointment/{aid}
  GET  http://localhost:9090/appointment-api/all-appointments
