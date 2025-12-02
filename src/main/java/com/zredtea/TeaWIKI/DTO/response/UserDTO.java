package com.zredtea.TeaWIKI.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "用户响应对象")
@Data
public class UserDTO {
    @Schema(description = "用户ID", example = "100001")
    private Integer userId;

    @Schema(description = "用户名", example = "user2")
    private String username;

    @Schema(description = "用户昵称", example = "小强")
    private String nickname;

    @Schema(description = "用户头像", example = "url")
    private String avatar;

    @Schema(description = "用户学院", example = "工学部")
    private String department;

    @Schema(description = "创建时间", example = "2025-10-18 23:00:17")
    private LocalDateTime createdAt;

    @Schema(description = "修改时间", example = "2025-10-18 23:00:17")
    private LocalDateTime updatedAt;
}
