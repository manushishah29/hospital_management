package com.softvan.hospitalManagement.requestDto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotEmpty(message = "email must not be empty")
    @Pattern(regexp = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "email is invalid")
    private String email;

    @NotEmpty(message = "password must not be empty")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,12}$",message = "Password length should be min 8 and max 12 characters and it should contain at least one uppercase, one lowercase, one special character and one digit")
    private String password;

    @NotEmpty(message = "firstname must not be empty")
    private String firstName;

    @NotEmpty(message = "lastname must not be empty")
    private String lastName;

    @NotEmpty(message = "role must not be empty")
    private String role;

}
