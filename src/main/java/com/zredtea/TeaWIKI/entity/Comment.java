package com.zredtea.TeaWIKI.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("comments")
public class Comment {
    @TableId(value="comment_id", type=IdType.AUTO)
    private Integer commentId;

    
}
