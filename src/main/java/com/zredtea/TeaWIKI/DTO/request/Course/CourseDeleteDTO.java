package com.zredtea.TeaWIKI.DTO.request.Course;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "课程删除表单对象")
@Data
public class CourseDeleteDTO {
    @Schema(description = "课程ID", example = "10001")
    @NotBlank
    private Integer courseId;
}
