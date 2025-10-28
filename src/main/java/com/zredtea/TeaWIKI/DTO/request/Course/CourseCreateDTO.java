package com.zredtea.TeaWIKI.DTO.request.Course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseCreateDTO {
    @NotBlank
    @Size(min = 1, max = 32, message="课程名必须小于32字符!")
    private String courseName;

    @NotBlank
    @Size(min = 1, max = 16, message="课程代码必须小于16字符!")
    private String courseCode;

    @Size(min = 1, max = 32, message="课程类型必须小于32字符!")
    private String courseType;

    @Size(min = 1, max = 32, message="所属学院必须小于32字符!")
    private String department;
}
