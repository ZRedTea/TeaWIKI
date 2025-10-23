package com.zredtea.TeaWIKI.controller;

import com.zredtea.TeaWIKI.DTO.Result;
import com.zredtea.TeaWIKI.DTO.request.Comment.CommentCommitDTO;
import com.zredtea.TeaWIKI.DTO.response.CommentDTO;
import com.zredtea.TeaWIKI.common.exception.BusinessException;
import com.zredtea.TeaWIKI.common.exception.ExceptionEnum;
import com.zredtea.TeaWIKI.entity.Comment;
import com.zredtea.TeaWIKI.service.CommentService;
import com.zredtea.TeaWIKI.service.TeacherService;
import com.zredtea.TeaWIKI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/commit")
    public Result<CommentDTO> commitComment(@RequestBody CommentCommitDTO dto) {
        if(dto == null) {
            throw new BusinessException(ExceptionEnum.INPUT_IS_NULL);
        }
        Integer userId = dto.getUserId();
        Integer teacherId = dto.getTeacherId();
        if(commentService.isCommentExist(userId, teacherId)) {
            throw new BusinessException(ExceptionEnum.COMMENT_HAS_EXIST);
        }
        if(!userService.isUserExist(userId) || !teacherService.isTeacherExist(teacherId)) {
            throw new BusinessException(ExceptionEnum.COMMENT_CONNECT_NOT_FOUND);
        }

        CommentDTO result = commentService.createComment(dto);
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
