package com.zredtea.TeaWIKI.util;

import com.zredtea.TeaWIKI.DTO.request.RegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.entity.User;

import java.util.Date;

public class TransUtil {
    public static UserDTO User2UserInfoDTO(User user, Integer statuCode){
        UserDTO userDTO = new UserDTO();
        userDTO.setStatusCode(statuCode);
        userDTO.setUsername(user.getUsername());
        userDTO.setNickname(user.getNickname());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setDepartment(user.getDepartment());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());

        return userDTO;
    }

    public static User RegisterDTO2User(RegisterDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setNickname(userDTO.getNickname());
        user.setAvatar(userDTO.getAvatar());
        user.setDepartment(userDTO.getDepartment());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return user;
    }
}
