package com.zredtea.TeaWIKI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zredtea.TeaWIKI.DTO.request.LoginDTO;
import com.zredtea.TeaWIKI.DTO.request.RegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserInfoDTO;
import com.zredtea.TeaWIKI.entity.User;
import com.zredtea.TeaWIKI.service.UserService;

import com.zredtea.TeaWIKI.mapper.UserMapper;
import com.zredtea.TeaWIKI.util.SaltUtil;


public class UserServiceImpl extends ServiceImpl<UserMapper, User>
                             implements UserService {


    @Override
    public UserInfoDTO register(RegisterDTO registerDTO) {
        User user = new User();
        String salt = SaltUtil.getSalt();
        user.setUsername(registerDTO.getUsername());
        if(registerDTO.getNickname() != null ) {
            user.setNickname(registerDTO.getNickname());
        } else {
            user.setNickname(registerDTO.getUsername());
        }
        user.setSalt(salt);
        user.setPassword(SaltUtil.getPasswordCrypto(registerDTO.getPassword(), salt));

        boolean success = super.save(user);
        if(!success) {
            throw new RuntimeException("注册时发生错误");
        }

        return new UserInfoDTO();
    }

    @Override
    public String login(LoginDTO loginDTO) {
        return "";
    }

    @Override
    public UserInfoDTO getUserInfo(String username) {
        return null;
    }

    @Override
    public Boolean isUserExist(String username) {
        UserMapper userMapper = getBaseMapper();
        if(userMapper.CheckByUsername(username) > 0) {
            return true;
        }
        return false;
    }
}
