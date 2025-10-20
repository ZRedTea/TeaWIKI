package com.zredtea.TeaWIKI.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.zredtea.TeaWIKI.DTO.request.UserLoginDTO;
import com.zredtea.TeaWIKI.DTO.request.UserPasswordUpdateDTO;
import com.zredtea.TeaWIKI.DTO.request.UserRegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.entity.User;

public interface UserService extends IService<User> {
    UserDTO register(UserRegisterDTO dto);

    UserDTO login(UserLoginDTO dto);

    UserDTO getUserInfo(String username);

    UserDTO updateNickname(String username, String nickname);

    UserDTO updateAvatar(String username, String avatar);

    UserDTO updatePassword(String username, UserPasswordUpdateDTO dto);

    Boolean isUserExist(Integer userId);

    Boolean isUserExist(String username);
}
