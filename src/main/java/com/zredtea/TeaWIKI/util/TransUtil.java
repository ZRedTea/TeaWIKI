package com.zredtea.TeaWIKI.util;

import com.zredtea.TeaWIKI.DTO.request.RegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.TeacherDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.entity.Teacher;
import com.zredtea.TeaWIKI.entity.User;

import java.util.Date;

public class TransUtil {
    public static UserDTO User2UserInfoDTO(User user){
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

    public static TeacherDTO teacher2TeacherDTO(Teacher teacher){
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setTeacherId(teacher.getTeacherId());
        teacherDTO.setTeacherName(teacher.getTeacherName());
        teacherDTO.setDepartment(teacher.getDepartment());
        teacherDTO.setCreatedAt(teacher.getCreatedAt());
        teacherDTO.setUpdatedAt(teacher.getUpdatedAt());
        return teacherDTO;
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
