package com.softvan.hospitalManagement.repository;

import com.softvan.hospitalManagement.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    Optional<UserEntity> findByEmailIgnoreCase(String email);


    @Query("select u from UserEntity u where  u.status=true and (lower(u.firstName) like lower(?1) or lower(u.lastName) like lower(?1) or lower(u.Id) like lower(?1) )")
    Page<UserEntity> getAll(String searchValue, Pageable pageable);
}
