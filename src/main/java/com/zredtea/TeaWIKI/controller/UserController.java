package com.zredtea.TeaWIKI.controller;

import com.zredtea.TeaWIKI.DTO.Result;
import com.zredtea.TeaWIKI.DTO.request.LoginDTO;
import com.zredtea.TeaWIKI.DTO.request.PasswordUpdateDTO;
import com.zredtea.TeaWIKI.DTO.request.RegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.service.UserService;
import com.zredtea.TeaWIKI.util.FileUploadUtil;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<UserDTO> register(@RequestBody @Valid RegisterDTO dto) {
        try {
            if (userService.isUserExist(dto.getUsername())) {
                return Result.error(400, "用户已存在");
            }
            UserDTO result = userService.register(dto);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error(500, e.getMessage());
        }
    }

    @PostMapping("/login")
    public Result<UserDTO> login(@RequestBody @Valid LoginDTO dto) {
        try {
            if (!userService.isUserExist(dto.getUsername())) {
                return Result.error(400, "用户不存在");
            }
            UserDTO result = userService.login(dto);
            if (result.getStatusCode() == 200) {
                return Result.success(result);
            } else {
                return Result.error(400, "用户名或密码错误");
            }
        } catch (Exception e) {
            return Result.error(500, e.getMessage());
        }
    }

    @RequestMapping("/{username}")
    public Result<UserDTO> getUserInfo(@PathVariable String username) {
        try {
            if (!userService.isUserExist(username)) {
                return Result.error(400, "用户不存在");
            }
            UserDTO result = userService.getUserInfo(username);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error(500, e.getMessage());
        }
    }

    @PostMapping("/{username}/nickname")
    public Result<UserDTO> updateNickname(@PathVariable String username,
                                          @RequestParam("nickname") String nickname) {
        try {
            if (!userService.isUserExist(username)) {
                return Result.error(400, "用户不存在");
            }
            UserDTO result = userService.updateNickname(username, nickname);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error(500, e.getMessage());
        }
    }

    @PostMapping("/{username}/avatar")
    public Result<UserDTO> updateAvatar(@PathVariable String username,
                                        @RequestParam("avatar") MultipartFile avatar) {
        try {
            if (!userService.isUserExist(username)) {
                return Result.error(400,"用户不存在");
            }
            String avatarURL = FileUploadUtil.uploadAvatar(avatar);
            UserDTO result = userService.updateAvatar(username, avatarURL);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error(500, e.getMessage());
        }
    }

    @PostMapping("/{username}/password")
    public Result<UserDTO> updatePassword(@PathVariable String username,
                                          @RequestBody @Valid PasswordUpdateDTO dto) {
        try {
            if (!userService.isUserExist(username)) {
                return Result.error(400,"用户不存在");
            }
            if (!dto.getOldPassword().equals(dto.getNewPassword())) {
                UserDTO result = userService.updatePassword(username, dto);
                return Result.success(result);
            } else {
                return Result.error(400,"旧密码与新密码不能相同");
            }
        } catch (RuntimeException e) {
            return Result.error(400, e.getMessage());
        } catch (Exception e) {
            return Result.error(500, e.getMessage());
        }
    }
}
