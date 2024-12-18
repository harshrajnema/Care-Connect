package com.careconnectapi.api.service;

import com.careconnectapi.api.entity.AppointmentEntity;
import com.careconnectapi.api.model.AppointmentRequestBody;
import com.careconnectapi.api.model.AppointmentIdRequest;
import com.careconnectapi.api.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository AppointmentRepository;

    
    public AppointmentEntity createAppointment(AppointmentRequestBody appointmentRequestBody) {
    	AppointmentEntity newAppointment = new AppointmentEntity();
    	newAppointment.setDoctorId(appointmentRequestBody.getDoctorId());
    	newAppointment.setPatientId(appointmentRequestBody.getPatientId());
    	 newAppointment.setAppointmentDate(appointmentRequestBody.getAppointmentDate());
    	  newAppointment.setAppointmentTime(appointmentRequestBody.getAppointmentTime());
    	  newAppointment.setStatus(appointmentRequestBody.getStatus());
    	  newAppointment.setReasonForVisit(appointmentRequestBody.getReasonForVisit());
    	  return AppointmentRepository.save(newAppointment);
    }
    
    public AppointmentEntity updateAppointment(AppointmentRequestBody appointmentRequestBody) {
    	AppointmentEntity newAppointment = new AppointmentEntity();
    	newAppointment.setAppointmentId(appointmentRequestBody.getAppointmentId());
    	newAppointment.setDoctorId(appointmentRequestBody.getDoctorId());
    	newAppointment.setPatientId(appointmentRequestBody.getPatientId());
    	 newAppointment.setAppointmentDate(appointmentRequestBody.getAppointmentDate());
    	  newAppointment.setAppointmentTime(appointmentRequestBody.getAppointmentTime());
    	  newAppointment.setStatus(appointmentRequestBody.getStatus());
    	  newAppointment.setReasonForVisit(appointmentRequestBody.getReasonForVisit());
    	  return AppointmentRepository.save(newAppointment);
    }

    public Page<AppointmentEntity> listAllAppointments(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return AppointmentRepository.listAllUsers(pageable);
    }

    public String deleteAppointment(AppointmentIdRequest AppointmentIdRequest) {
        AppointmentRepository.deleteById(AppointmentIdRequest.getAppointmentId());
        return "Appointment Deleted";
    }

    public String countNumberOfAppointment() {
        return AppointmentRepository.countNumberOfAppointments();
    }

    
}
