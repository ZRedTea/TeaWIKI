package com.zredtea.TeaWIKI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zredtea.TeaWIKI.DTO.request.TeacherCreateDTO;
import com.zredtea.TeaWIKI.DTO.response.TeacherDTO;
import com.zredtea.TeaWIKI.mapper.TeacherMapper;
import com.zredtea.TeaWIKI.entity.Teacher;
import com.zredtea.TeaWIKI.service.TeacherService;
import com.zredtea.TeaWIKI.util.TransUtil;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper,Teacher>
                                implements TeacherService {
    @Override
    public TeacherDTO createTeacher(TeacherCreateDTO dto) {
        TeacherMapper teacherMapper = getBaseMapper();
        Teacher teacher = new Teacher();
        teacher.setTeacherName(dto.getTeacherName());
        teacher.setDepartment(dto.getDepartment());
        boolean success = super.save(teacher);
        if(!success) {
            throw new DatabaseException("数据库操作时发生错误");
        }

        return TransUtil.teacher2TeacherDTO(teacher);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        TeacherMapper teacherMapper = getBaseMapper();
        List<Teacher> list = teacherMapper.selectAllTeacher();
        return TransUtil.teacher2TeacherDTO(list);
    }
}
