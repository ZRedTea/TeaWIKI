package com.zredtea.TeaWIKI.DTO.request;

import lombok.Data;

@Data
public class CommentCommitDTO {
    private Integer userId;
    private String username;
    private Integer teacherId;
    private String content;
    private Double rating;
}
