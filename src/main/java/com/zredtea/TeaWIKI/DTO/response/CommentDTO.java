package com.zredtea.TeaWIKI.DTO.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Integer commentId;
    private Integer userId;
    private Integer teacherId;
    private String content;
    private Double rating;
    private Integer likes;
    private Integer dislikes;
    private LocalDateTime commentTime;
    private LocalDateTime updatedAt;
    private Integer deleted;

    private String userName;
    private String userAvatar;

    private String teacherName;
    private String teacherSubject;
}