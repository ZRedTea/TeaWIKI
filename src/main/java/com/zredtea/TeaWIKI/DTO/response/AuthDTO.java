package com.zredtea.TeaWIKI.DTO.response;

import lombok.Data;

@Data
public class AuthDTO {
    private UserDTO user;
    private String token;
    private String tokenType =  "Bearer";
    private Long expiresIn;

    public AuthDTO(UserDTO user, String token, Long expiresIn) {
        this.user = user;
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
