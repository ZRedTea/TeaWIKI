package com.zredtea.TeaWIKI.controller;


import com.zredtea.TeaWIKI.DTO.Result;
import com.zredtea.TeaWIKI.DTO.request.CourseTeacher.CourseTeacherCommitDTO;
import com.zredtea.TeaWIKI.DTO.request.CourseTeacher.CourseTeacherDeleteDTO;
import com.zredtea.TeaWIKI.DTO.response.CourseDTO;
import com.zredtea.TeaWIKI.DTO.response.TeacherDTO;
import com.zredtea.TeaWIKI.common.exception.BusinessException;
import com.zredtea.TeaWIKI.common.exception.ExceptionEnum;
import com.zredtea.TeaWIKI.entity.Teacher;
import com.zredtea.TeaWIKI.service.CourseService;
import com.zredtea.TeaWIKI.service.CourseTeacherService;
import com.zredtea.TeaWIKI.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CTConnect")
public class CourseTeacherController {
    @Autowired
    private CourseTeacherService courseTeacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/create")
    public Result<Boolean> createConnect(@RequestBody @Valid CourseTeacherCommitDTO dto) {
        if(dto == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        Boolean result = courseTeacherService.commitCTConnect(dto);
        return Result.success(result);
    }

    @DeleteMapping("/delete")
    public Result<Boolean> deleteConnect(@RequestBody @Valid CourseTeacherDeleteDTO dto) {
        if(dto == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        Boolean result = courseTeacherService.cancelCTConnect(dto);
        return Result.success(result);
    }

    @GetMapping("/course")
    public Result<List<TeacherDTO>> getTeachersByCourseId(@RequestParam Integer courseId) {
        if(courseId == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        List<TeacherDTO> result = teacherService.getTeachersByCourseId(courseId);
        return Result.success(result);
    }

    @GetMapping("/teacher")
    public Result<List<CourseDTO>> getTeachersByTeacherId(@RequestParam Integer teacherId) {
        if(teacherId == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        List<CourseDTO> result = courseService.getCourseByTeacherId(teacherId);
        return Result.success(result);
    }
}
