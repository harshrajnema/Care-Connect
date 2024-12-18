
package com.careconnectapi.api.service;

import com.careconnectapi.api.entity.PaymentEntity;
import com.careconnectapi.api.model.PaymentRequestBody;
import com.careconnectapi.api.model.AppointmentIdRequest;
import com.careconnectapi.api.model.PaymentIdRequest;
import com.careconnectapi.api.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository PaymentRepository;

    // Create a new Payment record
    public PaymentEntity createPayment(PaymentRequestBody PaymentRequestBody) {
        //validatePaymentRequest(PaymentRequestBody);
    	PaymentEntity newPayment = new PaymentEntity();
    	newPayment.setAppointmentId(PaymentRequestBody.getAppointmentId());
    	 newPayment.setPatientId(PaymentRequestBody.getPatientId());
    	 newPayment.setDoctorId(PaymentRequestBody.getDoctorId());
    	 double tot=PaymentRequestBody.getTotalAmount();
    	 double paid=PaymentRequestBody.getPaidAmount();
         newPayment.setTotalAmount(tot);
         newPayment.setPaidAmount(paid);
         if (tot==paid) {
        	 newPayment.setBalanceAmount(0);
        	 newPayment.setPaymentStatus("Paid");
        	 newPayment.setPaymentDate(LocalDate.now());
         }else if(tot>paid && paid>(double)0.0){
        	 newPayment.setBalanceAmount(tot-paid);
        	 newPayment.setPaymentStatus("Partial");
        	 newPayment.setPaymentDate(LocalDate.now());
         }else {
        	 newPayment.setBalanceAmount(tot);
        	 newPayment.setPaymentStatus("Unpaid");
        	 newPayment.setPaymentDate(null);
         }
        newPayment.setCreatedAt(LocalDateTime.now());
        newPayment.setUpdatedAt(LocalDateTime.now());
        return PaymentRepository.save(newPayment);
    }
    
//    public PaymentEntity updatePayment(PaymentRequestBody PaymentRequestBody) {
//        if (PaymentRequestBody == null) {
//            throw new IllegalArgumentException("Request body cannot be null");
//        }
//
//        // Fetch the existing Payment entity from the database
//        PaymentEntity Payment = PaymentRepository.findById(PaymentRequestBody.getPaymentId())
//                .orElseThrow(() -> new IllegalArgumentException("Payment record not found with ID: " + PaymentRequestBody.getPaymentId()));
//
//        // Update fields
//        Payment.setAppointmentId(PaymentRequestBody.getAppointmentId());
//        Payment.setPatientId(PaymentRequestBody.getPatientId());
//        Payment.setDoctorId(PaymentRequestBody.getDoctorId());
//
//        double totalAmount = PaymentRequestBody.getTotalAmount();
//        double paidAmount = PaymentRequestBody.getPaidAmount();
//
//        Payment.setTotalAmount(totalAmount);
//        Payment.setPaidAmount(paidAmount);
//
//        if (totalAmount == paidAmount) {
//            Payment.setBalanceAmount(0);
//            Payment.setPaymentStatus("Paid");
//            Payment.setPaymentDate(LocalDate.now());
//        } else if (totalAmount > paidAmount && paidAmount > 0.0) {
//            Payment.setBalanceAmount(totalAmount - paidAmount);
//            Payment.setPaymentStatus("Partial");
//            Payment.setPaymentDate(LocalDate.now());
//        } else {
//            Payment.setBalanceAmount(totalAmount);
//            Payment.setPaymentStatus("Unpaid");
//            Payment.setPaymentDate(null);
//        }
//
//        Payment.setUpdatedAt(LocalDateTime.now()); // Only update `updatedAt`
//        
//        // Save the updated entity
//        return PaymentRepository.save(Payment);
//    }

    public String updatePayment(PaymentRequestBody PaymentRequestBody) {
      if (PaymentRequestBody == null) {
          throw new IllegalArgumentException("Request body cannot be null");
      }
     int id= PaymentRequestBody.getBillingId();
      // Fetch the existing Payment entity from the database
      PaymentEntity Payment = PaymentRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Payment record not found with ID: " + id));

      double totalAmount = PaymentRequestBody.getTotalAmount();
   double paidAmount = PaymentRequestBody.getPaidAmount();
   String status=null;
   LocalDate payDate=null;
   if (totalAmount == paidAmount) {
//     Payment.setB0);
    status="Paid";
     payDate=LocalDate.now();
 } else if (totalAmount > paidAmount && paidAmount > 0.0) {
//     Payment.setBalanceAmount(totalAmount - paidAmount);
	 status="Partial";
     payDate=LocalDate.now();}
    
    PaymentRepository.updatePayment(paidAmount,status,payDate,LocalDateTime.now(),id);
    return "Bill Successfully Updated";
    }

    // List all Payment records with pagination
    public Page<PaymentEntity> listAllPaymentRecords(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return PaymentRepository.findAll(pageable);
    }

    // Delete a Payment record by ID
    public String deletePayment(PaymentIdRequest PaymentIdRequest) {
        if (PaymentIdRequest == null || PaymentIdRequest.getBillingId() == 0) {
            throw new IllegalArgumentException("Invalid Payment ID for deletion");
        }

        Optional<PaymentEntity> Payment = PaymentRepository.findById(PaymentIdRequest.getBillingId());
        if (!Payment.isPresent()) {
            throw new IllegalArgumentException("Payment record not found for ID: " + PaymentIdRequest.getBillingId());
        }

        PaymentRepository.deleteById(PaymentIdRequest.getBillingId());
        return "Payment record deleted successfully";
    }

    // Get the total number of Payment records
    public Long countNumberOfPaymentRecords() {
        return PaymentRepository.count();
    }
    public Page<PaymentEntity> getRecordsByAppointmentId(AppointmentIdRequest IdRequest){
    	Pageable pageable = PageRequest.of(0,1);
        return PaymentRepository.findByAppointmentId(IdRequest.getAppointmentId(), pageable);
    }
   

}
