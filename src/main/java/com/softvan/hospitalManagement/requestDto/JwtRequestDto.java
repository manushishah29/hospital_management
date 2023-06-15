package com.softvan.hospitalManagement.requestDto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestDto {

    @NotEmpty(message = "username must not be empty")
    @Pattern(regexp = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "email is invalid")
    private String email;

    @NotEmpty(message = "password must not be empty")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,12}$",message = "Password length should be min 8 and max 12 characters and it should contain at least one uppercase, one lowercase, one special character and one digit")
    private String password;

}
