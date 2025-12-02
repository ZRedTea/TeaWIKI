package com.zredtea.TeaWIKI.DTO.request.Teacher;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "教师创建表单对象")
@Data
public class TeacherCreateDTO {
    @Schema(description = "教师名", example = "高建国")
    @NotBlank(message="教师名不能为空")
    private String teacherName;

    @Schema(description = "教师学院", example = "工学部")
    @NotBlank(message="教师所属学院不能为空")
    private String department;
}
