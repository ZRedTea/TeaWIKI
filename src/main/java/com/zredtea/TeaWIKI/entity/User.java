package com.zredtea.TeaWIKI.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

@Data
@TableName("users")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    @TableField("username")
    private String username;

    @TableField("nickname")
    private String nickname;

    @TableField("avatar")
    private String avatar;

    @TableField("department")
    private String department;

    @TableField("password")
    private String password;

    @TableField("salt")
    private String salt;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    @TableLogic
    @TableField("deleted")
    private Integer deleted;
}
