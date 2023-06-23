package com.softvan.hospitalManagement.repository;

import com.softvan.hospitalManagement.entity.RoleEntity;
import com.softvan.hospitalManagement.responseDto.RoleResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRoleNameIgnoreCase(String roleName);

    @Query(value = "select r from RoleEntity  r where r.status=true and lower(r.roleName) like lower(?1) ")
    Page<RoleEntity> findRoleWithPagination(String searchValue, Pageable pageable);
}
