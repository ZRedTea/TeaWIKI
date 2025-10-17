package com.zredtea.TeaWIKI.DTO.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotBlank(message = "用户名不能为空")
    @Size(max = 16, message = "用户名必须小于16个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码必须在6-20个字符之间")
    private String password;

    @Size(max = 16, message = "昵称必须小于16个字符")
    private String nickname;

    @Size(max = 16, message = "学院必须小于16个字符")
    private String department;
    private String avatar;
}
