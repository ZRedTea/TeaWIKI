package com.zredtea.TeaWIKI.DTO.request.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentCommitDTO {
    @NotBlank
    private Integer teacherId;
    private String content;

    @NotBlank(message="评分不能为空!")
    @Size(min=0,max=5,message="评分必须在0-5分之间!")
    private Double rating;
}
