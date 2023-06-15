package com.softvan.hospitalManagement.requestDto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegesRequestDto {

    @NotEmpty(message = "privilege must not be empty")
    private String privilegeName;
}
