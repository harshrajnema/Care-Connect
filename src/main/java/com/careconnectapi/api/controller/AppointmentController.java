package com.careconnectapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.careconnectapi.api.model.AppointmentRequestBody;
import com.careconnectapi.api.model.AppointmentIdRequest;
import com.careconnectapi.api.service.AppointmentService;

@RestController
@CrossOrigin
public class AppointmentController {

    @Autowired
    private AppointmentService AppointmentService;

    @RequestMapping(value = "/createAppointment", method = RequestMethod.POST)
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequestBody AppointmentRequestBody) throws Exception {
        return ResponseEntity.ok(AppointmentService.createAppointment(AppointmentRequestBody));
    }

    @RequestMapping(value = "/updateAppointment", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAppointment(@RequestBody AppointmentRequestBody AppointmentRequestBody) throws Exception {
        return ResponseEntity.ok(AppointmentService.updateAppointment(AppointmentRequestBody));
    }

    @RequestMapping(value = "/listAllAppointments", method = RequestMethod.GET)
    public ResponseEntity<?> listAllAppointments(@RequestParam(defaultValue = "0") final Integer pageNumber,
                                          @RequestParam(defaultValue = "10") final Integer size) throws Exception {
        return ResponseEntity.ok(AppointmentService.listAllAppointments(pageNumber, size));
    }

    @RequestMapping(value = "/deleteAppointment", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAppointment(@RequestBody AppointmentIdRequest AppointmentIdRequest) throws Exception {
        return ResponseEntity.ok(AppointmentService.deleteAppointment(AppointmentIdRequest));
    }

    @RequestMapping(value = "/AppointmentsCount", method = RequestMethod.GET)
    public ResponseEntity<?> countNumberOfAppointments() throws Exception {
        return ResponseEntity.ok(AppointmentService.countNumberOfAppointment());
    }
}
