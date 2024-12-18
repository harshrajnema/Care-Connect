
package com.careconnectapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.careconnectapi.api.model.PaymentRequestBody;
import com.careconnectapi.api.model.AppointmentIdRequest;
import com.careconnectapi.api.model.PaymentIdRequest;
import com.careconnectapi.api.service.PaymentService;

@RestController
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService PaymentService;

    @RequestMapping(value = "/createPayment", method = RequestMethod.POST)
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequestBody PaymentRequestBody) {
        return ResponseEntity.ok(PaymentService.createPayment(PaymentRequestBody));
    }

    @RequestMapping(value = "/updatePayment", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePayment(@RequestBody PaymentRequestBody PaymentRequestBody) {
        return ResponseEntity.ok(PaymentService.updatePayment(PaymentRequestBody));
    }

    @RequestMapping(value = "/listAllPayments", method = RequestMethod.GET)
    public ResponseEntity<?> listAllPayments(@RequestParam(defaultValue = "0") Integer pageNumber,
                                             @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(PaymentService.listAllPaymentRecords(pageNumber, size));
    }

    @RequestMapping(value = "/deletePayment", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePayment(@RequestBody PaymentIdRequest PaymentIdRequest) {
        return ResponseEntity.ok(PaymentService.deletePayment(PaymentIdRequest));
    }

    @RequestMapping(value = "/PaymentsCount", method = RequestMethod.GET)
    public ResponseEntity<?> countNumberOfPayments() {
        return ResponseEntity.ok(PaymentService.countNumberOfPaymentRecords());
    }
    @RequestMapping(value = "/billByAppointmentId", method = RequestMethod.GET)
    public ResponseEntity<?> getByAppointmentId(@RequestBody AppointmentIdRequest IdRequest) throws Exception {
        return ResponseEntity.ok(PaymentService.getRecordsByAppointmentId(IdRequest));
    }
}