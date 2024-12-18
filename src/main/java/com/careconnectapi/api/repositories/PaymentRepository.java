package com.careconnectapi.api.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.careconnectapi.api.entity.PaymentEntity;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<PaymentEntity, Integer> {

    // List all Payment records with pagination
    @Query(value = "SELECT * FROM harsh_Payment",nativeQuery = true)
    Page<PaymentEntity> listAllPaymentRecords(Pageable pageable);

    // Count the number of Payment records
    @Query(value = "SELECT COUNT(*) FROM harsh_Payment", nativeQuery = true)
    Long countNumberOfPaymentRecords();
    
    @Query(value = "SELECT * FROM harsh_Payment WHERE appointment_id = :appointment_id", nativeQuery = true)
    Page<PaymentEntity> findByAppointmentId(@Param("appointment_id") int appointment_id, Pageable pageable);
    
    // Update specific fields
    @Modifying
    @Transactional
    @Query(value = "UPDATE harsh_Payment SET paid_amount = :amount, payment_status = :status, payment_date = :PaymentDate, updated_at = :updated WHERE Payment_id = :id", nativeQuery = true)
    void updatePayment(@Param("amount") double amount, 
                       @Param("status") String status, 
                       @Param("PaymentDate") LocalDate PaymentDate, 
                       @Param("updated") LocalDateTime updated, 
                       @Param("id") int id);

}