package com.zredtea.TeaWIKI.DTO.request.Course;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "课程更新表单对象")
@Data
public class CourseUpdateDTO {
    @Schema(description = "课程ID", example = "10001")
    @NotBlank
    private Integer courseId;

    @Schema(description = "课程名", example = "经济学概论")
    @Size(min = 1, max = 32, message="课程名必须小于32字符!")
    private String courseName;

    @Schema(description = "课程代码", example = "08139182-3")
    @Size(min = 1, max = 16, message="课程代码必须小于16字符!")
    private String courseCode;

    @Schema(description = "课程类型", example = "专业选修课")
    @Size(min = 1, max = 32, message="课程类型必须小于32字符!")
    private String courseType;

    @Schema(description = "所属学院", example = "商学院")
    @Size(min = 1, max = 32, message="所属学院必须小于32字符!")
    private String department;
}
