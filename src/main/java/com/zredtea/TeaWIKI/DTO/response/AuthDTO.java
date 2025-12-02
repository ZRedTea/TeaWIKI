package com.zredtea.TeaWIKI.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "认证响应对象")
@Data
public class AuthDTO {
    private UserDTO user;

    @Schema(description = "用户token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI3IiwidXNlcm5hbWUiOiJ1c2VyMSIsImlhdCI6MTc2NDU3OTYwOCwiZXhwIjoxNzY0NjY2MDA4fQ.NNwxjw5vjvBAn9Jqrck_SIB8Wx4rtSaXuzWKobC01ec")
    private String token;

    @Schema(description = "用户token类别", example = "Bearer")
    private String tokenType = "Bearer";

    @Schema(description = "用户token时长", example = "86400")
    private Long expiresIn;

    public AuthDTO(UserDTO user, String token, Long expiresIn) {
        this.user = user;
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
