package com.zredtea.TeaWIKI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zredtea.TeaWIKI.DTO.request.Teacher.TeacherCreateDTO;
import com.zredtea.TeaWIKI.DTO.response.TeacherDTO;
import com.zredtea.TeaWIKI.common.exception.BusinessException;
import com.zredtea.TeaWIKI.common.exception.ExceptionEnum;
import com.zredtea.TeaWIKI.common.exception.ServerException;
import com.zredtea.TeaWIKI.mapper.TeacherMapper;
import com.zredtea.TeaWIKI.entity.Teacher;
import com.zredtea.TeaWIKI.service.CommentService;
import com.zredtea.TeaWIKI.service.CourseTeacherService;
import com.zredtea.TeaWIKI.service.TeacherService;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper,Teacher>
                                implements TeacherService {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CourseTeacherService courseTeacherService;

    @Override
    public TeacherDTO createTeacher(TeacherCreateDTO dto) {
        TeacherMapper teacherMapper = getBaseMapper();
        Teacher teacher = new Teacher();
        teacher.setTeacherName(dto.getTeacherName());
        teacher.setDepartment(dto.getDepartment());
        boolean success = super.save(teacher);
        if(!success) {
            throw new ServerException(ExceptionEnum.DATABASE_ERROR);
        }

        return convertToDTO(teacher);
    }

    @Override
    public TeacherDTO getTeacherById(Integer teacherId) {
        TeacherMapper teacherMapper = getBaseMapper();
        Teacher teacher = teacherMapper.selectById(teacherId);
        if(teacher == null) {
            throw new BusinessException(ExceptionEnum.TEACHER_NOT_FOUND);
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
    public List<TeacherDTO> getTeachersByCourseId(Integer courseId) {
        List<Teacher> teachers = courseTeacherService.getTeachersByCourseId(courseId);
        return convertToDTO(teachers);
    }

    @Override
    public TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setTeacherId(teacher.getTeacherId());
        teacherDTO.setTeacherName(teacher.getTeacherName());
        teacherDTO.setRating(commentService.getRatingByTeacherId(teacher.getTeacherId()));
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
