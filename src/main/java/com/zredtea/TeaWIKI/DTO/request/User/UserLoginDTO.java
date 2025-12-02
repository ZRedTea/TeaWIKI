package com.zredtea.TeaWIKI.DTO.request.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "用户登录表单对象")
@Data
public class UserLoginDTO {
    @Schema(description = "用户名", example = "user1")
    @NotBlank(message = "用户名不能为空")
    @Size(max = 16, message = "用户名必须小于16个字符")
    private String username;

    @Schema(description = "密码", example = "123456")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码必须在6-20个字符之间")
    private String password;
}
