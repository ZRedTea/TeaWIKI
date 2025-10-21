package com.zredtea.TeaWIKI.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("comments")
public class Comment {
    @TableId(value="comment_id", type=IdType.AUTO)
    private Integer commentId;

    @TableField("user_id")
    private Integer userId;

    @TableField("teacher_id")
    private Integer teacherId;

    @TableField("content")
    private String content;

    @TableField("rating")
    private Double rating;

    @TableField("likes")
    private Integer likes;

    @TableField("dislikes")
    private Integer dislikes;

    @TableField(value = "comment_time", fill = FieldFill.INSERT)
    private LocalDateTime commentTime;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableLogic
    @TableField("deleted")
    private Integer deleted;
}
