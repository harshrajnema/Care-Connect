package com.careconnectapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.careconnectapi.api.model.UserRequestBody;
import com.careconnectapi.api.model.UserIdRequest;
import com.careconnectapi.api.service.UserService;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserRequestBody userRequestBody) throws Exception {
        return ResponseEntity.ok(userService.createUser(userRequestBody));
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody UserRequestBody userRequestBody) throws Exception {
        return ResponseEntity.ok(userService.updateUser(userRequestBody));
    }

    @RequestMapping(value = "/listAllUsers", method = RequestMethod.GET)
    public ResponseEntity<?> listAllUsers(@RequestParam(defaultValue = "0") final Integer pageNumber,
                                          @RequestParam(defaultValue = "10") final Integer size) throws Exception {
        return ResponseEntity.ok(userService.listAllUsers(pageNumber, size));
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@RequestBody UserIdRequest userIdRequest) throws Exception {
        return ResponseEntity.ok(userService.deleteUser(userIdRequest));
    }

    @RequestMapping(value = "/usersCount", method = RequestMethod.GET)
    public ResponseEntity<?> countNumberOfUsers() throws Exception {
        return ResponseEntity.ok(userService.countNumberOfUsers());
    }
}
