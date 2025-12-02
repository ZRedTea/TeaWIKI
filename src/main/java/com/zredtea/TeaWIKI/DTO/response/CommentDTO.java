package com.zredtea.TeaWIKI.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "评论响应对象")
@Data
public class CommentDTO {
    @Schema(description = "评论ID", example = "100000001")
    private Integer commentId;

    @Schema(description = "用户ID", example = "100001")
    private Integer userId;

    @Schema(description = "教师ID", example = "10001")
    private Integer teacherId;

    @Schema(description = "评论内容", example = "不赖")
    private String content;

    @Schema(description = "评论评分", example = "4.0")
    private Double rating;

    @Schema(description = "点赞数", example = "11")
    private Integer likes;

    @Schema(description = "点踩数", example = "1")
    private Integer dislikes;

    @Schema(description = "创建时间", example = "2025-10-18 23:00:17")
    private LocalDateTime commentTime;

    @Schema(description = "修改时间", example = "2025-10-18 23:00:17")
    private LocalDateTime updatedAt;

    @Schema(description = "逻辑删除", example = "0")
    private Integer deleted;

    @Schema(description = "用户名", example = "user4")
    private String username;

    @Schema(description = "用户头像", example = "url")
    private String userAvatar;

    @Schema(description = "教师名", example = "胡春")
    private String teacherName;
}