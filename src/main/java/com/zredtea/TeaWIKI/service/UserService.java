package com.zredtea.TeaWIKI.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.zredtea.TeaWIKI.DTO.request.LoginDTO;
import com.zredtea.TeaWIKI.DTO.request.RegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserInfoDTO;
import com.zredtea.TeaWIKI.entity.User;

public interface UserService extends IService<User> {
    UserInfoDTO register(RegisterDTO registerDTO);

    String login(LoginDTO loginDTO);

    UserInfoDTO getUserInfo(String username);

    Boolean isUserExist(String username);
}
