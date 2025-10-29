package com.zredtea.TeaWIKI.DTO.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeacherDTO {
    private Integer teacherId;
    private String teacherName;
    private Double rating;
    private String department;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
