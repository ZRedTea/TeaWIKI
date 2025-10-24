package com.zredtea.TeaWIKI.controller;

import com.zredtea.TeaWIKI.DTO.Result;
import com.zredtea.TeaWIKI.DTO.request.User.UserLoginDTO;
import com.zredtea.TeaWIKI.DTO.request.User.UserPasswordUpdateDTO;
import com.zredtea.TeaWIKI.DTO.request.User.UserRegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.AuthDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.common.exception.BusinessException;
import com.zredtea.TeaWIKI.common.exception.ExceptionEnum;
import com.zredtea.TeaWIKI.costumer.annotation.CurrentUser;
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
    public Result<AuthDTO> register(@RequestBody @Valid UserRegisterDTO dto) {
        if (userService.isUserExist(dto.getUsername())) {
            throw new BusinessException(ExceptionEnum.USER_HAS_EXIST);
        }
        AuthDTO result = userService.register(dto);
        return Result.success(result);
    }

    @PostMapping("/login")
    public Result<AuthDTO> login(@RequestBody @Valid UserLoginDTO dto) {
        if (!userService.isUserExist(dto.getUsername())) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        AuthDTO result = userService.login(dto);
        return Result.success(result);
    }

    @PostMapping("/logout")
    public Result<UserDTO> logout(@RequestBody @Valid UserLoginDTO dto) {
        if (!userService.isUserExist(dto.getUsername())) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        UserDTO result = userService.logout(dto.getUsername());
        return Result.success(result);
    }

    @GetMapping("/{username}")
    public Result<UserDTO> getUserInfo(@PathVariable String username) {
        if (!userService.isUserExist(username)) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        UserDTO result = userService.getUserInfo(username);
        return Result.success(result);
    }

    @PutMapping("/profile/nickname")
    public Result<UserDTO> updateNickname(@CurrentUser Integer userId,
                                          @RequestParam("nickname") String nickname) {
        if (!userService.isUserExist(userId)) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        UserDTO result = userService.updateNickname(userId, nickname);
        return Result.success(result);
    }

    @PutMapping("/profile/avatar")
    public Result<UserDTO> updateAvatar(@CurrentUser Integer userId,
                                        @RequestParam("avatar") MultipartFile avatar)
                                        throws IOException {
        if (!userService.isUserExist(userId)) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        String avatarURL = FileUploadUtil.uploadAvatar(avatar);
        UserDTO result = userService.updateAvatar(userId, avatarURL);
        return Result.success(result);
    }

    @PutMapping("/profile/password")
    public Result<UserDTO> updatePassword(@CurrentUser Integer userId,
                                          @RequestBody @Valid UserPasswordUpdateDTO dto) {
        if (!userService.isUserExist(userId)) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        if (!dto.getOldPassword().equals(dto.getNewPassword())) {
            UserDTO result = userService.updatePassword(userId, dto);
            return Result.success(result);
        } else {
            throw new BusinessException(ExceptionEnum.USER_OLD_WRONG);
        }
    }
}
