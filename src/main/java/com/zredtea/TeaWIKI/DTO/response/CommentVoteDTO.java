package com.zredtea.TeaWIKI.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "点赞点踩响应对象")
@Data
public class CommentVoteDTO {
    @Schema(description = "点赞点踩ID", example = "1000000001")
    private Integer voteId;

    @Schema(description = "评论ID", example = "100000001")
    private Integer commentId;

    @Schema(description = "用户ID", example = "100023")
    private Integer userId;

    @Schema(description = "投票类别", example = "dislike")
    private String voteType;

    @Schema(description = "创建时间", example = "2025-10-18 23:00:17")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间", example = "2025-10-18 23:00:17")
    private LocalDateTime updatedAt;
}
