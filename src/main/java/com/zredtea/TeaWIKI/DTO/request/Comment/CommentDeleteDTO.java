package com.zredtea.TeaWIKI.DTO.request.Comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDeleteDTO {
    @NotBlank
    private Integer commentId;

    @NotBlank
    private Integer userId;
}