package com.zredtea.TeaWIKI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zredtea.TeaWIKI.DTO.request.UserLoginDTO;
import com.zredtea.TeaWIKI.DTO.request.UserPasswordUpdateDTO;
import com.zredtea.TeaWIKI.DTO.request.UserRegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.entity.User;
import com.zredtea.TeaWIKI.service.UserService;

import com.zredtea.TeaWIKI.mapper.UserMapper;
import com.zredtea.TeaWIKI.util.SaltUtil;
import com.zredtea.TeaWIKI.util.TransUtil;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
                             implements UserService {

    @Override
    public UserDTO register(UserRegisterDTO dto) {
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
            throw new DatabaseException("数据库操作时发生错误");
        }
        return TransUtil.User2UserInfoDTO(user);
    }

    @Override
    public UserDTO login(UserLoginDTO dto) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.selectByUsername(dto.getUsername());
        String passwordInput = dto.getPassword();
        passwordInput = SaltUtil.getPasswordCrypto(passwordInput, user.getSalt());
        if(!passwordInput.equals(user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        return TransUtil.User2UserInfoDTO(user);
    }

    @Override
    public UserDTO getUserInfo(String username) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.selectByUsername(username);
        return TransUtil.User2UserInfoDTO(user);
    }

    @Override
    public UserDTO updateNickname(String username, String nickname) {
        UserMapper userMapper = getBaseMapper();
        Integer userId = userMapper.selectIdByUsername(username);
        Boolean success = userMapper.updateUserNickname(userId, nickname);
        if(!success) {
            throw new DatabaseException("数据库操作时发生错误");
        }

        User updatedUser = userMapper.selectById(userId);
        return TransUtil.User2UserInfoDTO(updatedUser);
    }

    @Override
    public UserDTO updateAvatar(String username, String avatar) {
        UserMapper userMapper = getBaseMapper();
        Integer userId = userMapper.selectIdByUsername(username);
        Boolean success = userMapper.updateUserAvatar(userId, avatar);
        if(!success) {
            throw new DatabaseException("数据库操作时发生错误");
        }

        User updatedUser = userMapper.selectById(userId);
        return TransUtil.User2UserInfoDTO(updatedUser);
    }

    @Override
    public UserDTO updatePassword(String username, UserPasswordUpdateDTO dto) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.selectByUsername(username);
        String salt = user.getSalt();
        String oldPasswordInput = SaltUtil.getPasswordCrypto(dto.getOldPassword(), salt);
        String oldPassword = user.getPassword();
        if(!oldPasswordInput.equals(oldPassword)) {
            throw new RuntimeException("旧密码不正确");
        }

        String newSalt =  SaltUtil.getSalt();
        String newPassword =  SaltUtil.getPasswordCrypto(dto.getNewPassword(), newSalt);
        Boolean success = userMapper.updateUserPassword(user.getUserId(),newPassword,newSalt);
        if(!success) {
            throw new DatabaseException("数据库操作时发生错误");
        }

        User updatedUser = userMapper.selectById(user.getUserId());
        return TransUtil.User2UserInfoDTO(updatedUser);
    }

    @Override
    public Boolean isUserExist(Integer userid) {
        UserMapper userMapper = getBaseMapper();
        return userMapper.checkByUserId(userid);
    }

    @Override
    public Boolean isUserExist(String username) {
        UserMapper userMapper = getBaseMapper();
        return userMapper.checkByUsername(username);
    }
}
