package com.careconnectapi.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.careconnectapi.api.entity.AppointmentEntity;
import com.careconnectapi.api.entity.UserEntity;

@Repository
public interface AppointmentRepository extends CrudRepository<AppointmentEntity, Integer> {

    @Query(value = "select * from harsh_raj_appointments", nativeQuery = true)
    Page<AppointmentEntity> listAllUsers(Pageable pageable);

    @Query(value = "SELECT count(*) from harsh_raj_appointments", nativeQuery = true)
    String countNumberOfAppointments();
}
