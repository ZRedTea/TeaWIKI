package com.zredtea.TeaWIKI.DTO.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentVoteDTO {
    private Integer voteId;
    private Integer commentId;
    private Integer userId;
    private String voteType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
