package com.zredtea.TeaWIKI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zredtea.TeaWIKI.DTO.request.Course.CourseCreateDTO;
import com.zredtea.TeaWIKI.DTO.request.Course.CourseDeleteDTO;
import com.zredtea.TeaWIKI.DTO.request.Course.CourseUpdateDTO;
import com.zredtea.TeaWIKI.DTO.response.CourseDTO;
import com.zredtea.TeaWIKI.common.exception.BusinessException;
import com.zredtea.TeaWIKI.common.exception.ExceptionEnum;
import com.zredtea.TeaWIKI.entity.Course;
import com.zredtea.TeaWIKI.mapper.CourseMapper;
import com.zredtea.TeaWIKI.service.CourseService;
import com.zredtea.TeaWIKI.service.CourseTeacherService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
                               implements CourseService {
    private final CourseTeacherService courseTeacherService;
    private CourseMapper courseMapper;

    public CourseServiceImpl(CourseTeacherService courseTeacherService) {
        this.courseTeacherService = courseTeacherService;
    }

    @Override
    public CourseDTO createCourse(CourseCreateDTO dto) {
        Course course = convertToEntity(dto);
        boolean success = save(course);
        if(!success) {
            throw new BusinessException(ExceptionEnum.DATABASE_ERROR);
        }
        return convertToDTO(course);
    }

    @Override
    public CourseDTO updateCourse(CourseUpdateDTO dto) {
        Course course = convertToEntity(dto);
        boolean success = updateById(course);
        if(!success) {
            throw new BusinessException(ExceptionEnum.DATABASE_ERROR);
        }
        return convertToDTO(course);
    }

    @Override
    public boolean deleteCourse(CourseDeleteDTO dto) {
        Course course = convertToEntity(dto);
        boolean success = removeById(course);
        if(!success) {
            throw new BusinessException(ExceptionEnum.DATABASE_ERROR);
        }
        return true;
    }

    @Override
    public CourseDTO getCourseById(Integer courseId) {
        Course course = courseMapper.selectById(courseId);
        return convertToDTO(course);
    }

    @Override
    public CourseDTO getCourseByName(String courseName) {
        Course course = courseMapper.selectByName(courseName);
        return convertToDTO(course);
    }

    @Override
    public List<CourseDTO> matchCourseByName(String courseName) {
        List<Course> courses = courseMapper.matchByName(courseName);
        return convertToDTO(courses);
    }

    @Override
    public List<CourseDTO> getCourseByTeacherId(Integer teacherId) {
        List<Course> courses = courseTeacherService.getCoursesByTeacherId(teacherId);
        return convertToDTO(courses);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseMapper.selectAllCourses();
        return convertToDTO(courses);
    }

    @Override
    public boolean isCourseExist(Integer courseId) {
        return false;
    }

    Course convertToEntity(CourseCreateDTO courseCreateDTO) {
        Course course = new Course();
        course.setCourseName(courseCreateDTO.getCourseName());
        course.setCourseType(courseCreateDTO.getCourseType());
        course.setDepartment(courseCreateDTO.getDepartment());
        return course;
    }

    Course convertToEntity(CourseUpdateDTO courseUpdateDTO) {
        Course course = getById(courseUpdateDTO.getCourseId());
        course.setCourseName(courseUpdateDTO.getCourseName());
        course.setCourseCode(courseUpdateDTO.getCourseCode());
        course.setCourseType(courseUpdateDTO.getCourseType());
        course.setDepartment(courseUpdateDTO.getDepartment());
        return course;
    }

    Course convertToEntity(CourseDeleteDTO courseDeleteDTO) {
        return getById(courseDeleteDTO.getCourseId());
    }

    CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseType(course.getCourseType());
        courseDTO.setDepartment(course.getDepartment());
        return courseDTO;
    }

    List<CourseDTO> convertToDTO(List<Course> courseList) {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for(Course course : courseList) {
            CourseDTO courseDTO = convertToDTO(course);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }
}
