package com.softvan.hospitalManagement.responseDto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
