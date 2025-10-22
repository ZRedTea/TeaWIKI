package com.zredtea.TeaWIKI.controller;

import com.zredtea.TeaWIKI.DTO.Result;
import com.zredtea.TeaWIKI.DTO.request.User.UserLoginDTO;
import com.zredtea.TeaWIKI.DTO.request.User.UserPasswordUpdateDTO;
import com.zredtea.TeaWIKI.DTO.request.User.UserRegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.service.UserService;
import com.zredtea.TeaWIKI.util.FileUploadUtil;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<UserDTO> register(@RequestBody @Valid UserRegisterDTO dto) {
        if (userService.isUserExist(dto.getUsername())) {
            return Result.error(400, "用户已存在");
        }
        UserDTO result = userService.register(dto);
        return Result.success(result);
    }

    @PostMapping("/login")
    public Result<UserDTO> login(@RequestBody @Valid UserLoginDTO dto) {
        if (!userService.isUserExist(dto.getUsername())) {
            return Result.error(400, "用户不存在");
        }
        UserDTO result = userService.login(dto);
        return Result.success(result);
    }

    @GetMapping("/{username}")
    public Result<UserDTO> getUserInfo(@PathVariable String username) {
        if (!userService.isUserExist(username)) {
            return Result.error(404, "用户不存在");
        }
        UserDTO result = userService.getUserInfo(username);
        return Result.success(result);
    }

    @PutMapping("/{username}/nickname")
    public Result<UserDTO> updateNickname(@PathVariable String username,
                                          @RequestParam("nickname") String nickname) {
        if (!userService.isUserExist(username)) {
            return Result.error(404, "用户不存在");
        }
        UserDTO result = userService.updateNickname(username, nickname);
        return Result.success(result);
    }

    @PutMapping("/{username}/avatar")
    public Result<UserDTO> updateAvatar(@PathVariable String username,
                                        @RequestParam("avatar") MultipartFile avatar)
                                        throws IOException {
        if (!userService.isUserExist(username)) {
            return Result.error(404,"用户不存在");
        }
        String avatarURL = FileUploadUtil.uploadAvatar(avatar);
        UserDTO result = userService.updateAvatar(username, avatarURL);
        return Result.success(result);
    }

    @PutMapping("/{username}/password")
    public Result<UserDTO> updatePassword(@PathVariable String username,
                                          @RequestBody @Valid UserPasswordUpdateDTO dto) {
        if (!userService.isUserExist(username)) {
            return Result.error(404,"用户不存在");
        }
        if (!dto.getOldPassword().equals(dto.getNewPassword())) {
            UserDTO result = userService.updatePassword(username, dto);
            return Result.success(result);
        } else {
            return Result.error(400,"旧密码与新密码不能相同");
        }
    }
}
