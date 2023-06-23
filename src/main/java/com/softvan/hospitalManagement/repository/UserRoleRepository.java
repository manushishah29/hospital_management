package com.softvan.hospitalManagement.repository;

import com.softvan.hospitalManagement.entity.UserEntity;
import com.softvan.hospitalManagement.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    Optional<UserRoleEntity> findByUser(UserEntity userEntity);



}
