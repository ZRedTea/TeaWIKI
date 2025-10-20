package com.zredtea.TeaWIKI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zredtea.TeaWIKI.DTO.request.TeacherCreateDTO;
import com.zredtea.TeaWIKI.DTO.response.TeacherDTO;
import com.zredtea.TeaWIKI.entity.Teacher;

public interface TeacherService extends IService<Teacher> {
    TeacherDTO createTeacher(TeacherCreateDTO dto);
}
