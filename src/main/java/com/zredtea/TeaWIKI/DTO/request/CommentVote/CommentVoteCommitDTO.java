package com.zredtea.TeaWIKI.DTO.request.CommentVote;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "点赞点踩提交表单对象")
@Data
public class CommentVoteCommitDTO {
    @Schema(description = "评论ID", example = "100000001")
    @NotBlank
    private Integer commentId;

    @Schema(description = "投票类型", example = "like")
    @NotBlank
    private String voteType;
}
