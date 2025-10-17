package com.zredtea.TeaWIKI.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.zredtea.TeaWIKI.DTO.request.LoginDTO;
import com.zredtea.TeaWIKI.DTO.request.PasswordUpdateDTO;
import com.zredtea.TeaWIKI.DTO.request.RegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.entity.User;
import org.springframework.stereotype.Service;

public interface UserService extends IService<User> {
    UserDTO register(RegisterDTO dto);

    UserDTO login(LoginDTO dto);

    UserDTO getUserInfo(String username);

    UserDTO updateNickname(String username, String nickname);

    UserDTO updateAvatar(String username, String avatar);

    UserDTO updatePassword(String username, PasswordUpdateDTO dto);

    Boolean isUserExist(String username);
}
