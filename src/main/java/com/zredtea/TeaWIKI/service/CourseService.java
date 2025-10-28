package com.zredtea.TeaWIKI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zredtea.TeaWIKI.DTO.request.Course.CourseCreateDTO;
import com.zredtea.TeaWIKI.DTO.request.Course.CourseDeleteDTO;
import com.zredtea.TeaWIKI.DTO.request.Course.CourseUpdateDTO;
import com.zredtea.TeaWIKI.DTO.response.CourseDTO;
import com.zredtea.TeaWIKI.entity.Course;

import java.util.List;

public interface CourseService extends IService<Course> {
    CourseDTO createCourse(CourseCreateDTO dto);

    CourseDTO updateCourse(CourseUpdateDTO dto);

    boolean deleteCourse(CourseDeleteDTO dto);

    CourseDTO getCourseById(Integer courseId);

    CourseDTO getCourseByName(String courseName);

    List<CourseDTO> matchCourseByName(String courseName);

    boolean isCourseExist(Integer courseId);
}
