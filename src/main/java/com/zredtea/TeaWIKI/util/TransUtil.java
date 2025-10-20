package com.zredtea.TeaWIKI.util;

import com.zredtea.TeaWIKI.DTO.request.UserRegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.TeacherDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.entity.Teacher;
import com.zredtea.TeaWIKI.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static List<TeacherDTO> teacher2TeacherDTO(List<Teacher> teacherList){
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        for(Teacher teacher : teacherList){
            teacherDTOList.add(teacher2TeacherDTO(teacher));
        }
        return teacherDTOList;
    }


    public static User RegisterDTO2User(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setNickname(userRegisterDTO.getNickname());
        user.setDepartment(userRegisterDTO.getDepartment());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }
}
