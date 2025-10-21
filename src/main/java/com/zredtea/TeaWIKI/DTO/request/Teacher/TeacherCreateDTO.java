package com.zredtea.TeaWIKI.DTO.request.Teacher;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeacherCreateDTO {
    @NotBlank(message="教师名不能为空")
    private String teacherName;

    @NotBlank(message="教师所属学院不能为空")
    private String department;
}
