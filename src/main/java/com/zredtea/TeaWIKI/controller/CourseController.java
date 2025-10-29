package com.zredtea.TeaWIKI.controller;

import com.zredtea.TeaWIKI.DTO.Result;
import com.zredtea.TeaWIKI.DTO.request.Course.CourseCreateDTO;
import com.zredtea.TeaWIKI.DTO.request.Course.CourseDeleteDTO;
import com.zredtea.TeaWIKI.DTO.request.Course.CourseUpdateDTO;
import com.zredtea.TeaWIKI.DTO.response.CourseDTO;
import com.zredtea.TeaWIKI.common.exception.BusinessException;
import com.zredtea.TeaWIKI.common.exception.ExceptionEnum;
import com.zredtea.TeaWIKI.service.CourseService;
import com.zredtea.TeaWIKI.service.CourseTeacherService;
import com.zredtea.TeaWIKI.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseTeacherService courseTeacherService;

    @PostMapping("/create")
    public Result<CourseDTO> createCourse(@RequestBody @Valid CourseCreateDTO dto) {
        if (dto == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        CourseDTO result = courseService.createCourse(dto);
        return Result.success(result);
    }

    @PutMapping("/update")
    public Result<CourseDTO> updateCourse(@RequestBody @Valid CourseUpdateDTO dto) {
        if (dto == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        CourseDTO result = courseService.updateCourse(dto);
        return Result.success(result);
    }

    @DeleteMapping("/delete")
    public Result<Boolean> deleteCourse(@RequestBody @Valid CourseDeleteDTO dto) {
        if(dto == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        boolean result = courseService.deleteCourse(dto);
        return Result.success(result);
    }

    @GetMapping("/course")
    public Result<CourseDTO> getCourseById(@RequestParam Integer courseId) {
        if(courseId == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        CourseDTO result = courseService.getCourseById(courseId);
        return Result.success(result);
    }

    @GetMapping("/courses")
    public Result<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> result = courseService.getAllCourses();
        return Result.success(result);
    }
}
