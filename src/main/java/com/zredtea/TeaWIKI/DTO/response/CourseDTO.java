package com.zredtea.TeaWIKI.DTO.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseDTO {
    private Integer courseId;
    private String courseName;
    private String courseCode;
    private String courseType;
    private String department;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
