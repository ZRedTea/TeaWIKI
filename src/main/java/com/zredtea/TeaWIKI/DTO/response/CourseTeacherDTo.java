package com.zredtea.TeaWIKI.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "课程教师连接响应对象")
@Data
public class CourseTeacherDTO {
    @Schema(description = "课程教师连接ID", example = "1000000001")
    Integer id;

    @Schema(description = "教师ID", example = "10001")
    Integer teacherId;

    @Schema(description = "课程ID", example = "10001")
    Integer courseId;
}
