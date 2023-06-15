package com.softvan.hospitalManagement.repository;

import com.softvan.hospitalManagement.entity.PrivilegesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegesRepository extends JpaRepository<PrivilegesEntity, Integer> {
    Optional<PrivilegesEntity> findByPrivilegeNameIgnoreCase(String privilege);
}
