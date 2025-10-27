package com.zredtea.TeaWIKI.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("comment_votes")
public class CommentVote {
    @TableId(value="vote_id", type=IdType.AUTO)
    private Integer voteId;

    @TableField("comment_id")
    private Integer commentId;

    @TableField("user_id")
    private Integer userId;

    @TableField("vote_type")
    private String voteType;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
