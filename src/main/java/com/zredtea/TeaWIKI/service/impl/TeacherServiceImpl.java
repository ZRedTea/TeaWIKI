package com.zredtea.TeaWIKI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zredtea.TeaWIKI.DTO.request.Teacher.TeacherCreateDTO;
import com.zredtea.TeaWIKI.DTO.response.TeacherDTO;
import com.zredtea.TeaWIKI.mapper.TeacherMapper;
import com.zredtea.TeaWIKI.entity.Teacher;
import com.zredtea.TeaWIKI.service.TeacherService;
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

        return convertToDTO(teacher);
    }

    @Override
    public TeacherDTO getTeacherById(Integer teacherId) {
        TeacherMapper teacherMapper = getBaseMapper();
        Teacher teacher = teacherMapper.selectById(teacherId);
        if(teacher == null) {
            throw new RuntimeException("实体不存在");
        }
        return convertToDTO(teacher);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        TeacherMapper teacherMapper = getBaseMapper();
        List<Teacher> teachers = teacherMapper.selectAllTeacher();
        return convertToDTO(teachers);
    }

    @Override
    public List<TeacherDTO> searchTeachersByName(String name) {
        TeacherMapper teacherMapper = getBaseMapper();
        List<Teacher> teachers = teacherMapper.matchByTeacherName(name);
        return convertToDTO(teachers);
    }

    @Override
    public List<TeacherDTO> searchTeachersByCourseId(Integer courseId) {
        return List.of();
    }

    @Override
    public List<TeacherDTO> searchTeachersByUnionId(String name, Integer courseId) {
        return List.of();
    }

    @Override
    public TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setTeacherId(teacher.getTeacherId());
        teacherDTO.setTeacherName(teacher.getTeacherName());
        teacherDTO.setDepartment(teacher.getDepartment());
        teacherDTO.setCreatedAt(teacher.getCreatedAt());
        teacherDTO.setUpdatedAt(teacher.getUpdatedAt());
        return teacherDTO;
    }

    @Override
    public List<TeacherDTO> convertToDTO(List<Teacher> teachers) {
        List<TeacherDTO> teacherDTOs = new ArrayList<>();
        for (Teacher teacher : teachers) {
            TeacherDTO teacherDTO = convertToDTO(teacher);
            teacherDTOs.add(teacherDTO);
        }
        return teacherDTOs;
    }

    @Override
    public Boolean isTeacherExist(Integer teacherId) {
        TeacherMapper teacherMapper = getBaseMapper();
        return teacherMapper.checkByTeacherId(teacherId);
    }
}
