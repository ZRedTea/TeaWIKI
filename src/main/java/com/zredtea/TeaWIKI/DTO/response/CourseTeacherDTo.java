package com.zredtea.TeaWIKI.DTO.response;

import lombok.Data;

@Data
public class CourseTeacherDTo {
    private Integer id;
    private Integer courseId;
    private Integer teacherId;
}
