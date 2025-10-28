package com.zredtea.TeaWIKI.DTO.request.CourseTeacher;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseTeacherCommitDTO {
    @NotBlank
    private Integer teacherId;
    @NotBlank
    private Integer courseId;
}
