package com.zredtea.TeaWIKI.DTO.request.CommentVote;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentVoteCommitDTO {
    @NotBlank
    private Integer commentId;
    @NotBlank
    private String voteType;
}
