package com.zredtea.TeaWIKI.DTO.request.CommentVote;

import jakarta.validation.constraints.NotBlank;

public class CommentVoteCommit {
    @NotBlank
    private Integer commentId;
    @NotBlank
    private String voteType;
}
