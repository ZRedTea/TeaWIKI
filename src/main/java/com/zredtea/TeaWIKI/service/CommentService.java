package com.zredtea.TeaWIKI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zredtea.TeaWIKI.DTO.request.Comment.CommentCommitDTO;
import com.zredtea.TeaWIKI.DTO.request.Comment.CommentDeleteDTO;
import com.zredtea.TeaWIKI.DTO.request.Comment.CommentUpdateDTO;
import com.zredtea.TeaWIKI.DTO.response.CommentDTO;
import com.zredtea.TeaWIKI.entity.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {
    CommentDTO createComment(CommentCommitDTO dto);

    CommentDTO updateComment(CommentUpdateDTO dto);

    CommentDTO deleteComment(CommentDeleteDTO dto);

    CommentDTO getCommentByCommentId(Integer commentId);

    List<CommentDTO> searchCommentsByTeacherId(Integer teacherId, String sortType);

    Boolean isCommentExist(Integer commentId);

    Boolean isCommentExist(Integer userId, Integer teacherId);

//    Comment convertToEntity(CommentDTO dto);
//    Comment convertToEntity(CommentCommitDTO dto);
//    Comment convertToEntity(CommentUpdateDTO dto);
//    //Comment convertToEntity(CommentDeleteDTO dto);
//
//    CommentDTO convertToDTO(Comment comment);
//    List<CommentDTO> convertToDTO(List<Comment> comments);
}
