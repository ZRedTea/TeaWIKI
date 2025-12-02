package com.zredtea.TeaWIKI.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "课程响应对象")
@Data
public class CourseDTO {
    @Schema(description = "课程ID", example = "100001")
    private Integer courseId;

    @Schema(description = "课程名", example = "经济与生活")
    private String courseName;

    @Schema(description = "课程代码", example = "94311240-2")
    private String courseCode;

    @Schema(description = "课程类型", example = "通识选修课")
    private String courseType;

    @Schema(description = "课程学院", example = "商学院")
    private String department;

    @Schema(description = "创建时间", example = "2025-10-18 23:00:17")
    private LocalDateTime createdAt;

    @Schema(description = "修改时间", example = "2025-10-18 23:00:17")
    private LocalDateTime updatedAt;
}
