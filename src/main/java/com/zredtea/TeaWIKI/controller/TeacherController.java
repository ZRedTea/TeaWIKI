package com.zredtea.TeaWIKI.controller;

import com.zredtea.TeaWIKI.DTO.Result;
import com.zredtea.TeaWIKI.DTO.request.Teacher.TeacherCreateDTO;
import com.zredtea.TeaWIKI.DTO.response.TeacherDTO;
import com.zredtea.TeaWIKI.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/create")
    public Result<TeacherDTO> createTeacher(@RequestBody @Valid TeacherCreateDTO dto) {
        if(dto == null) {
            return Result.error(400,"dto不存在!");
        }
        TeacherDTO result = teacherService.createTeacher(dto);
        return Result.success(result);
    }

    @GetMapping("/teachers")
    public Result<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> result = teacherService.getAllTeachers();
        return Result.success(result);
    }

    @GetMapping("/{teacherId}")
    public Result<TeacherDTO> getTeacher(@PathVariable Integer teacherId){
        if(teacherId == null) {
            return Result.error(400,"输入错误!");
        }
        if(!teacherService.isTeacherExist(teacherId)) {
            return Result.error(404,"该页面不存在!");
        }
        TeacherDTO result = teacherService.getTeacherById(teacherId);
        return Result.success(result);
    }
}
