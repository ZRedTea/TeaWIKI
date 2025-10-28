package com.zredtea.TeaWIKI.DTO.request.Course;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseDeleteDTO {
    @NotBlank
    private Integer courseId;
}
