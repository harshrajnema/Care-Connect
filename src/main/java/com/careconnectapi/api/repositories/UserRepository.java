package com.careconnectapi.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.careconnectapi.api.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query(value = "select * from userss", nativeQuery = true)
    Page<UserEntity> listAllUsers(Pageable pageable);

    @Query(value = "SELECT count(*) from userss", nativeQuery = true)
    String countNumberOfUsers();
}
