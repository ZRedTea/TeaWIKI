package com.zredtea.TeaWIKI.controller;

import com.zredtea.TeaWIKI.DTO.Result;
import com.zredtea.TeaWIKI.DTO.request.CommentVote.CommentVoteCommitDTO;
import com.zredtea.TeaWIKI.common.exception.BusinessException;
import com.zredtea.TeaWIKI.common.exception.ExceptionEnum;
import com.zredtea.TeaWIKI.costumer.annotation.CurrentUser;
import com.zredtea.TeaWIKI.service.CommentService;
import com.zredtea.TeaWIKI.service.CommentVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commentVote/")
public class CommentVoteController {
    @Autowired
    private CommentVoteService commentVoteService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/vote")
    public Result<Boolean> vote(@CurrentUser Integer userId,
                                @RequestBody CommentVoteCommitDTO dto) {
        if(dto == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        if(!dto.getVoteType().equals("like") && !dto.getVoteType().equals("dislike")) {
            throw new BusinessException((ExceptionEnum.ILLEGAL_ARGUMENT));
        }
        boolean result = commentVoteService.commitVote(userId,dto);
        return Result.success(result);
    }

    @PutMapping("/toggle")
    public Result<Boolean> toggle(@CurrentUser Integer userId,
                                  @RequestParam Integer commentId) {
        if(!commentService.isCommentExist(commentId)) {
            throw new BusinessException(ExceptionEnum.COMMENT_NOT_FOUND);
        }
        boolean result = commentVoteService.toggleVote(userId,commentId);
        return Result.success(result);
    }

    @DeleteMapping("/cancel")
    public Result<Boolean> cancel(@CurrentUser Integer userId,
                                  @RequestParam Integer commentId) {
        if(!commentService.isCommentExist(commentId)) {
            throw new BusinessException(ExceptionEnum.COMMENT_NOT_FOUND);
        }
        boolean result = commentVoteService.cancelVote(userId,commentId);
        return Result.success(result);
    }
}
