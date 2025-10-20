package com.zredtea.TeaWIKI.DTO.response;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherDTO {
    private Integer teacherId;
    private String teacherName;
    private String department;
    private Date createdAt;
    private Date updatedAt;
}
