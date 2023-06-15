package com.softvan.hospitalManagement.responseDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetTokenClaims {
    private String token;
    private String username;
    private Integer userId;

    private String role;

}
