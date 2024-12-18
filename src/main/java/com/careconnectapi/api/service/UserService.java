package com.careconnectapi.api.service;

import com.careconnectapi.api.entity.UserEntity;
import com.careconnectapi.api.model.UserRequestBody;
import com.careconnectapi.api.model.UserIdRequest;
import com.careconnectapi.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserRequestBody userRequestBody) {
        UserEntity newUser = mapToEntity(userRequestBody);
        return userRepository.save(newUser);
    }

    public UserEntity updateUser(UserRequestBody userRequestBody) {
        UserEntity updatedUser = mapToEntity(userRequestBody);
        return userRepository.save(updatedUser);
    }

    public Page<UserEntity> listAllUsers(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return userRepository.listAllUsers(pageable);
    }

    public String deleteUser(UserIdRequest userIdRequest) {
        userRepository.deleteById(userIdRequest.getUserId());
        return "User Deleted";
    }

    public String countNumberOfUsers() {
        return userRepository.countNumberOfUsers();
    }

    private UserEntity mapToEntity(UserRequestBody userRequestBody) {
        UserEntity user = new UserEntity();
        user.setUserId(userRequestBody.getUserId());
        user.setFirstName(userRequestBody.getFirstName());
        user.setLastName(userRequestBody.getLastName());
        user.setEmailId(userRequestBody.getEmailId());
        user.setPhoneNumber(userRequestBody.getPhoneNumber());
        user.setRole(userRequestBody.getRole());
        return user;
    }
}
