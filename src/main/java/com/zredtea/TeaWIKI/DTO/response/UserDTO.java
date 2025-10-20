package com.zredtea.TeaWIKI.DTO.response;

import lombok.Data;
import java.util.Date;

@Data
public class UserDTO {
    private Integer userId;
    private String username;
    private String nickname;
    private String avatar;
    private String department;
    private Date createdAt;
    private Date updatedAt;
}
