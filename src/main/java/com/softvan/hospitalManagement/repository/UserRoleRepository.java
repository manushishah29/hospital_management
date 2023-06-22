package com.softvan.hospitalManagement.repository;

import com.softvan.hospitalManagement.entity.UserEntity;
import com.softvan.hospitalManagement.entity.UserRoleEntity;
import com.softvan.hospitalManagement.responseDto.PrivilegesResponseDto;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    Optional<UserRoleEntity> findByUser(UserEntity userEntity);


}
