package com.zredtea.TeaWIKI.util;

import com.zredtea.TeaWIKI.DTO.request.RegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.entity.User;

import java.util.Date;

public class TransUtil {
    public static UserDTO User2UserInfoDTO(User user, Integer statuCode){
        UserDTO userDTO = new UserDTO();
        userDTO.setStatusCode(statuCode);
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setNickname(user.getNickname());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setDepartment(user.getDepartment());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());

        return userDTO;
    }

    public static User RegisterDTO2User(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setNickname(registerDTO.getNickname());
        user.setDepartment(registerDTO.getDepartment());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return user;
    }
}
