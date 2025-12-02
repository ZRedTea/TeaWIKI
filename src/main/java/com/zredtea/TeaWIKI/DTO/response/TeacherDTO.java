package com.zredtea.TeaWIKI.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "教师响应对象")
@Data
public class TeacherDTO {
    @Schema(description = "教师ID", example = "10001")
    private Integer teacherId;

    @Schema(description = "教师名", example = "孙坡")
    private String teacherName;

    @Schema(description = "综合评分", example = "3.9")
    private Double rating;

    @Schema(description = "教师学院", example = "管理学院")
    private String department;

    @Schema(description = "创建时间", example = "2025-10-11 19:33:23")
    private LocalDateTime createdAt;

    @Schema(description = "修改时间", example = "2025-11-23 11:23:41")
    private LocalDateTime updatedAt;
}
