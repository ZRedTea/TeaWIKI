package com.zredtea.TeaWIKI.DTO.request.CourseTeacher;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "课程教师连接提交表单对象")
@Data
public class CourseTeacherCommitDTO {
    @Schema(description = "教师ID", example = "10001")
    @NotBlank
    private Integer teacherId;

    @Schema(description = "课程ID", example = "10001")
    @NotBlank
    private Integer courseId;
}
