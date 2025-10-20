package com.zredtea.TeaWIKI.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPasswordUpdateDTO {
    @NotBlank(message = "旧密码不能为空")
    @Size(min = 6, max = 20, message = "密码必须在6-20个字符之间")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Size(min = 6, max = 20, message = "密码必须在6-20个字符之间")
    private String newPassword;
}
