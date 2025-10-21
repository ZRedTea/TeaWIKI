package com.zredtea.TeaWIKI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zredtea.TeaWIKI.DTO.request.User.UserLoginDTO;
import com.zredtea.TeaWIKI.DTO.request.User.UserPasswordUpdateDTO;
import com.zredtea.TeaWIKI.DTO.request.User.UserRegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.entity.User;
import com.zredtea.TeaWIKI.service.UserService;

import com.zredtea.TeaWIKI.mapper.UserMapper;
import com.zredtea.TeaWIKI.util.SaltUtil;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
                             implements UserService {

    @Override
    public UserDTO register(UserRegisterDTO dto) {
        UserMapper userMapper = getBaseMapper();
        User user = convertToEntity(dto);

        boolean success = super.save(user);
        if(!success) {
            throw new DatabaseException("数据库操作时发生错误");
        }

        return convertToDTO(user);
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
        return convertToDTO(user);
    }

    @Override
    public UserDTO getUserInfo(String username) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.selectByUsername(username);
        return convertToDTO(user);
    }

    @Override
    public UserDTO updateNickname(String username, String nickname) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.selectByUsername(username);
        user.setNickname(nickname);
        int success = userMapper.updateById(user);
        if(success <= 0) {
            throw new DatabaseException("数据库操作时发生错误");
        }
        return convertToDTO(user);
    }

    @Override
    public UserDTO updateAvatar(String username, String avatar) {
        UserMapper userMapper = getBaseMapper();
        User user = userMapper.selectByUsername(username);
        user.setAvatar(avatar);
        int success = userMapper.updateById(user);
        if(success <= 0) {
            throw new DatabaseException("数据库操作时发生错误");
        }
        return convertToDTO(user);
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

        String newSalt = SaltUtil.getSalt();
        String newPassword = SaltUtil.getPasswordCrypto(dto.getNewPassword(), newSalt);
        user.setPassword(newPassword);
        user.setSalt(newSalt);
        int success = userMapper.updateById(user);
        if(success <= 0) {
            throw new DatabaseException("数据库操作时发生错误");
        }

        return convertToDTO(user);
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

    @Override
    public User convertToEntity(UserRegisterDTO dto) {
        User user =  new User();
        String salt = SaltUtil.getSalt();

        user.setUsername(dto.getUsername());
        if(dto.getNickname() != null ) {
            user.setNickname(dto.getNickname());
        } else {
            user.setNickname(dto.getUsername());
        }

        user.setSalt(salt);
        user.setPassword(SaltUtil.getPasswordCrypto(dto.getPassword(), salt));
        return user;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setNickname(user.getNickname());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setDepartment(user.getDepartment());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }

    @Override
    public List<UserDTO> convertToDTO(List<User> users){
        List<UserDTO> userDTOs = new ArrayList<>();
        for(User user : users) {
            userDTOs.add(convertToDTO(user));
        }
        return userDTOs;
    }
}
