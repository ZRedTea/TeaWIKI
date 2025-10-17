package com.zredtea.TeaWIKI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zredtea.TeaWIKI.DTO.request.LoginDTO;
import com.zredtea.TeaWIKI.DTO.request.PasswordUpdateDTO;
import com.zredtea.TeaWIKI.DTO.request.RegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.entity.User;
import com.zredtea.TeaWIKI.service.UserService;

import com.zredtea.TeaWIKI.mapper.UserMapper;
import com.zredtea.TeaWIKI.util.SaltUtil;
import com.zredtea.TeaWIKI.util.TransUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
                             implements UserService {

    @Override
    public UserDTO register(RegisterDTO dto) {
        User user = TransUtil.RegisterDTO2User(dto);
        String salt = SaltUtil.getSalt();
        user.setUsername(dto.getUsername());
        if(dto.getNickname() != null ) {
            user.setNickname(dto.getNickname());
        } else {
            user.setNickname(dto.getUsername());
        }
        user.setSalt(salt);
        user.setPassword(SaltUtil.getPasswordCrypto(dto.getPassword(), salt));

        boolean success = super.save(user);
        if(!success) {
            throw new RuntimeException("注册时发生错误");
        }

        return TransUtil.User2UserInfoDTO(user,200);
    }

    @Override
    public UserDTO login(LoginDTO dto) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.SelectByUsername(dto.getUsername());
        String passwordInput = dto.getPassword();
        passwordInput = SaltUtil.getPasswordCrypto(passwordInput, user.getSalt());
        if(passwordInput.equals(user.getPassword())) {
            return TransUtil.User2UserInfoDTO(user,200);
        } else {
            return TransUtil.User2UserInfoDTO(user,400);
        }
    }

    @Override
    public UserDTO getUserInfo(String username) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.SelectByUsername(username);
        return TransUtil.User2UserInfoDTO(user,200);
    }

    @Override
    public UserDTO updateNickname(String username, String nickname) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.SelectByUsername(username);
        user.setNickname(nickname);
        userMapper.updateById(user);
        return TransUtil.User2UserInfoDTO(user,200);
    }

    @Override
    public UserDTO updateAvatar(String username, String avatar) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.SelectByUsername(username);
        user.setAvatar(avatar);
        userMapper.updateById(user);
        return TransUtil.User2UserInfoDTO(user,200);
    }

    @Override
    public UserDTO updatePassword(String username, PasswordUpdateDTO dto) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.SelectByUsername(username);
        String salt = user.getSalt();
        String oldPasswordInput = SaltUtil.getPasswordCrypto(dto.getOldPassword(), salt);
        String oldPassword = user.getPassword();
        if(oldPasswordInput.equals(oldPassword)) {
            String newSalt =  SaltUtil.getSalt();
            String newPassword =  SaltUtil.getPasswordCrypto(dto.getNewPassword(), newSalt);
            user.setPassword(newPassword);
            user.setSalt(salt);
            userMapper.updateById(user);
            return TransUtil.User2UserInfoDTO(user,200);
        } else {
            throw new RuntimeException("旧密码不正确");
        }
    }

    @Override
    public Boolean isUserExist(String username) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.SelectByUsername(username);
        return user != null;
    }
}
