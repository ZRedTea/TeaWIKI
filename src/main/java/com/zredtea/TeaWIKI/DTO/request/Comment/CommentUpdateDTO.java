package com.zredtea.TeaWIKI.DTO.request.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentUpdateDTO {
    @NotBlank
    private Integer commentId;

    @NotBlank
    private Integer userId;

    private String content;

    @Size(min=0,max=5,message="评分必须在0-5分之间!")
    private Double rating;
}
