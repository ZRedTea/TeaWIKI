package com.zredtea.TeaWIKI.DTO.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserDTO {
    private Integer userId;
    private String username;
    private String nickname;
    private String avatar;
    private String department;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
