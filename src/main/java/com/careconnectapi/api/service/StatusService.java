package com.careconnectapi.api.service;


import com.careconnectapi.api.entity.StatusEntity;
import com.careconnectapi.api.model.AppointmentIdRequest;
import com.careconnectapi.api.model.StatusIdRequest;
import com.careconnectapi.api.model.StatusRequestBody;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.careconnectapi.api.repositories.StatusRepository;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    // Create a new appointment status
    public StatusEntity createStatus(StatusRequestBody statusRequestBody) {
        StatusEntity newStatus= new StatusEntity();
        newStatus.setAppointmentId(statusRequestBody.getAppointmentId());
        newStatus.setStatus(statusRequestBody.getStatus());
        newStatus.setReason(statusRequestBody.getReason());
        newStatus.setCreatedAt(LocalDateTime.now());
        newStatus.setUpdatedAt(LocalDateTime.now());
        return statusRepository.save(newStatus);
    }

    

    // Update an existing appointment status
    public StatusEntity updateStatus(StatusRequestBody statusRequestBody) {
        StatusEntity updatedStatus = new StatusEntity();
        updatedStatus.setStatusId(statusRequestBody.getStatusId());
        updatedStatus.setAppointmentId(statusRequestBody.getAppointmentId());
       updatedStatus.setStatus(statusRequestBody.getStatus());
       updatedStatus.setReason(statusRequestBody.getReason());
        updatedStatus.setCreatedAt(LocalDateTime.now());
        updatedStatus.setUpdatedAt(LocalDateTime.now());
        return statusRepository.save(updatedStatus);
    }


    // List all appointment statuses with pagination
    public Page<StatusEntity> listAllStatus(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return statusRepository.findAll(pageable);
    }

    // Delete an appointment status by ID
    public String deleteStatus(StatusIdRequest status) {
        statusRepository.deleteById(status.getStatusId());
        return "Appointment Status Deleted Successfully";
    }

    // Get the total number of appointment statuses
    public long countNumberOfStatus() {
        return statusRepository.count();
    }
    
    public Page<StatusEntity> getRecordByAppointmentId(AppointmentIdRequest IdRequest){
    	Pageable pageable = PageRequest.of(0,10);
        return statusRepository.findByAppointmentId(IdRequest.getAppointmentId(), pageable);
    }
}