package com.zredtea.TeaWIKI.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.zredtea.TeaWIKI.DTO.request.User.UserLoginDTO;
import com.zredtea.TeaWIKI.DTO.request.User.UserPasswordUpdateDTO;
import com.zredtea.TeaWIKI.DTO.request.User.UserRegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    UserDTO register(UserRegisterDTO dto);

    UserDTO login(UserLoginDTO dto);

    UserDTO getUserInfo(String username);

    UserDTO updateNickname(String username, String nickname);

    UserDTO updateAvatar(String username, String avatar);

    UserDTO updatePassword(String username, UserPasswordUpdateDTO dto);

    Boolean isUserExist(Integer userId);

    Boolean isUserExist(String username);
//
//    User convertToEntity(UserRegisterDTO dto);
//
//    UserDTO convertToDTO(User user);
//
//    List<UserDTO> convertToDTO(List<User> users);
}
