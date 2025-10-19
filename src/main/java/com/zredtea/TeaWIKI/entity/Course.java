package com.zredtea.TeaWIKI.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("courses")
public class Course {
    @TableId(value="course_id", type=IdType.AUTO)
    private Integer courseId;

    @TableField("course_name")
    private String courseName;

    @TableField("course_type")
    private String courseType;

    @TableField("department")
    private String department;

    @TableField("created_at")
    private Date createdAt;

    @TableField("updated_at")
    private Date updatedAt;

    @TableLogic
    @TableField("deleted")
    private Integer deleted;
}
