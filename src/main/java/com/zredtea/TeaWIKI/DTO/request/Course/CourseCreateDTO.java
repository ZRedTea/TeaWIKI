package com.zredtea.TeaWIKI.DTO.request.Course;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "课程创建表单对象")
@Data
public class CourseCreateDTO {
    @Schema(description = "课程名", example = "高等数学")
    @NotBlank
    @Size(min = 1, max = 32, message="课程名必须小于32字符!")
    private String courseName;

    @Schema(description = "课程代码", example = "08121911-1.5")
    @NotBlank
    @Size(min = 1, max = 16, message="课程代码必须小于16字符!")
    private String courseCode;

    @Schema(description = "课程类别", example = "专业必修课")
    @Size(min = 1, max = 32, message="课程类型必须小于32字符!")
    private String courseType;

    @Schema(description = "课程学院", example = "工学部")
    @Size(min = 1, max = 32, message="所属学院必须小于32字符!")
    private String department;
}
