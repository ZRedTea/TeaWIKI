package com.zredtea.TeaWIKI.DTO.request.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "用户修改密码表单对象")
@Data
public class UserPasswordUpdateDTO {
    @Schema(description = "旧密码", example = "123456")
    @NotBlank(message = "旧密码不能为空")
    @Size(min = 6, max = 20, message = "密码必须在6-20个字符之间")
    private String oldPassword;

    @Schema(description = "新密码", example = "114514")
    @NotBlank(message = "新密码不能为空")
    @Size(min = 6, max = 20, message = "密码必须在6-20个字符之间")
    private String newPassword;
}
