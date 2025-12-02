package com.zredtea.TeaWIKI.DTO.request.Comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(description = "评论提交表单对象")
@Data
public class CommentCommitDTO {
    @Schema(description = "评论教师ID", example = "10001")
    @NotNull
    private Integer teacherId;

    @Schema(description = "评论内容", example = "很不错的老师，喜欢")
    private String content;

    @Schema(description = "评论打分", example = "5.0")
    @NotNull(message="评分不能为空!")
    @Min(value = 0, message = "评分不能小于0!")
    @Max(value = 5, message = "评分不能大于5!")
    private Double rating;
}
