package com.softvan.hospitalManagement.util;

import com.softvan.hospitalManagement.entity.UserEntity;
import com.softvan.hospitalManagement.jwt.JwtTokenProvider;
import com.softvan.hospitalManagement.repository.UserRepository;
import com.softvan.hospitalManagement.responseDto.GetTokenClaims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class Utilities {

    private final UserRepository userRepository;

    @javax.annotation.Resource
    private GetTokenClaims getTokenClaims;

    public Date getDate() {
        return new Date();
    }

    public UserEntity currentUser() {
        UserEntity userEntity = userRepository.findById(getTokenClaims.getUserId()).get();
        System.out.println("current user in utilities  " + userEntity);
        return userRepository.findById(getTokenClaims.getUserId()).get();
    }

}
