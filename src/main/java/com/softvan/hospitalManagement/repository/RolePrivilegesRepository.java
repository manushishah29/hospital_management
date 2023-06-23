package com.softvan.hospitalManagement.repository;

import com.softvan.hospitalManagement.entity.PrivilegesEntity;
import com.softvan.hospitalManagement.entity.RoleEntity;
import com.softvan.hospitalManagement.entity.RolePrivilegesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolePrivilegesRepository extends JpaRepository<RolePrivilegesEntity, Integer> {


    Optional<RolePrivilegesEntity> findById(Integer id);


//    Optional<List<RoleRightsMappingEntity>> findByRoleId(RoleEntity roleEntity);

    List<RolePrivilegesEntity> findByRole(RoleEntity roleEntity);

}
