package com.zredtea.TeaWIKI.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("teachers")
public class Teacher {
    @TableId(value="teacher_id",type= IdType.AUTO)
    private Integer teacherId;

    @TableField("teacher_name")
    private String teacherName;

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
