package com.zredtea.TeaWIKI.controller;

import com.zredtea.TeaWIKI.DTO.Result;
import com.zredtea.TeaWIKI.DTO.request.Comment.CommentCommitDTO;
import com.zredtea.TeaWIKI.DTO.request.Comment.CommentUpdateDTO;
import com.zredtea.TeaWIKI.DTO.response.CommentDTO;
import com.zredtea.TeaWIKI.common.exception.BusinessException;
import com.zredtea.TeaWIKI.common.exception.ExceptionEnum;
import com.zredtea.TeaWIKI.costumer.annotation.CurrentUser;
import com.zredtea.TeaWIKI.service.CommentService;
import com.zredtea.TeaWIKI.service.TeacherService;
import com.zredtea.TeaWIKI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/commit")
    public Result<CommentDTO> commitComment(@CurrentUser Integer userId,
                                            @RequestBody @Valid CommentCommitDTO dto) {
        if(dto == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        Integer teacherId = dto.getTeacherId();
        if(commentService.isCommentExist(userId, teacherId)) {
            throw new BusinessException(ExceptionEnum.COMMENT_HAS_EXIST);
        }
        if(!teacherService.isTeacherExist(teacherId)) {
            throw new BusinessException(ExceptionEnum.COMMENT_CONNECT_NOT_FOUND);
        }

        CommentDTO result = commentService.createComment(dto, userId);
        return Result.success(result);
    }

    @PutMapping("/update")
    public Result<CommentDTO> updateComment(@CurrentUser Integer userId,
                                            @RequestBody @Valid CommentUpdateDTO dto) {
        if(dto == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        if(!commentService.isCommentExist(dto.getCommentId())) {
            throw new BusinessException(ExceptionEnum.COMMENT_NOT_FOUND);
        }
        if(!commentService.getById(dto.getCommentId()).getUserId().
                equals(userId)) {
            throw new BusinessException(ExceptionEnum.PERMISSION_ERROR);
        }
        CommentDTO result = commentService.updateComment(dto, userId);
        return Result.success(result);
    }

    @DeleteMapping("/delete")
    public Result<Boolean> deleteComment(@CurrentUser Integer userId,
                                         @RequestParam Integer commentId) {
        if(!commentService.isCommentExist(commentId)) {
            throw new BusinessException(ExceptionEnum.COMMENT_NOT_FOUND);
        }
        if(!commentService.getById(commentId).getUserId()
                .equals(userId)) {
            throw new BusinessException(ExceptionEnum.PERMISSION_ERROR);
        }

        Boolean result = commentService.deleteComment(commentId, userId);
        return Result.success(result);
    }

    @GetMapping("/{teacherId}")
    public Result<List<CommentDTO>> getAllCommentsByTeacher(@PathVariable Integer teacherId,
                                                            @RequestParam String sortType) {
        if(teacherId == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        List<CommentDTO> result = commentService.searchCommentsByTeacherId(teacherId, sortType);
        return Result.success(result);
    }
}
