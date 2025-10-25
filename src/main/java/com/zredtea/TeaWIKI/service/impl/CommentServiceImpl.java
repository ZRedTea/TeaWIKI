package com.zredtea.TeaWIKI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zredtea.TeaWIKI.DTO.request.Comment.CommentCommitDTO;
import com.zredtea.TeaWIKI.DTO.request.Comment.CommentDeleteDTO;
import com.zredtea.TeaWIKI.DTO.request.Comment.CommentUpdateDTO;
import com.zredtea.TeaWIKI.DTO.response.CommentDTO;
import com.zredtea.TeaWIKI.common.exception.BusinessException;
import com.zredtea.TeaWIKI.common.exception.ExceptionEnum;
import com.zredtea.TeaWIKI.common.exception.ServerException;
import com.zredtea.TeaWIKI.entity.Comment;
import com.zredtea.TeaWIKI.entity.Teacher;
import com.zredtea.TeaWIKI.entity.User;
import com.zredtea.TeaWIKI.mapper.CommentMapper;
import com.zredtea.TeaWIKI.service.CommentService;
import com.zredtea.TeaWIKI.service.TeacherService;
import com.zredtea.TeaWIKI.service.UserService;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
                                implements CommentService {
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;

    @Override
    public CommentDTO createComment(CommentCommitDTO dto, Integer userId) {
        Comment comment = convertToEntity(dto);
        comment.setUserId(userId);
        boolean success = save(comment);
        if(!success) {
            throw new ServerException(ExceptionEnum.DATABASE_ERROR);
        }
        return convertToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(CommentUpdateDTO dto, Integer userId) {
        Comment comment = convertToEntity(dto);
        boolean success = updateById(comment);
        if(!success) {
            throw new ServerException(ExceptionEnum.DATABASE_ERROR);
        }
        return convertToDTO(comment);
    }

    @Override
    public boolean deleteComment(Integer commentId, Integer userId) {
        Comment comment = getById(commentId);
        comment.setDeleted(1);
        boolean success = save(comment);
        if(!success) {
            throw new ServerException(ExceptionEnum.DATABASE_ERROR);
        }

        return true;
    }

    @Override
    public CommentDTO getCommentByCommentId(Integer commentId) {
        Comment comment = getById(commentId);
        if(comment == null) {
            throw new BusinessException(ExceptionEnum.COMMENT_NOT_FOUND);
        }
        return convertToDTO(comment);
    }

    @Override
    public List<CommentDTO> searchCommentsByTeacherId(Integer teacherId, String sortType) {
        CommentMapper commentMapper = getBaseMapper();
        List<Comment> comments = new ArrayList<>();
        if(sortType == null || sortType.equals("hot")) {
            comments = commentMapper.selectCommentsByTeacherId(teacherId,
                    wrapper -> wrapper.orderByDesc(Comment::getLikes));
        } else if(sortType.equals("latest")) {
            comments = commentMapper.selectCommentsByTeacherId(teacherId,
                    wrapper -> wrapper.orderByDesc(Comment::getCommentTime));
        } else {
            throw new BusinessException(ExceptionEnum.ILLEGAL_ARGUMENT);
        }
        return convertToDTO(comments);

    }

    public Comment convertToEntity(CommentDTO dto) {
        return null;
    }

    public Comment convertToEntity(CommentCommitDTO dto) {
        if(dto == null) {
            throw new RuntimeException("dto错误");
        }

        Comment entity = new Comment();
        entity.setTeacherId(dto.getTeacherId());
        entity.setRating(dto.getRating());
        if(dto.getContent() != null) {
            entity.setContent(dto.getContent());
        }
        entity.setLikes(0);
        entity.setDislikes(0);
        entity.setCommentTime(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public Comment convertToEntity(CommentUpdateDTO dto) {
        CommentMapper commentMapper = getBaseMapper();
        if(dto == null) {
            throw new RuntimeException("dto错误");
        }

        Comment entity = commentMapper.selectCommentByCommentId(dto.getCommentId());
        if(entity == null) {
            throw new RuntimeException("此评论不存在!");
        }
        entity.setRating(dto.getRating());
        entity.setContent(dto.getContent());
        return entity;
    }

    public CommentDTO convertToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setCommentId(comment.getCommentId());
        dto.setUserId(comment.getUserId());
        dto.setTeacherId(comment.getTeacherId());
        dto.setRating(comment.getRating());
        dto.setContent(comment.getContent());

        User conUser = userService.getById(comment.getUserId());
        Teacher conTeacher = teacherService.getById(comment.getTeacherId());
        dto.setUserName(conUser.getNickname());
        dto.setUserAvatar(conUser.getAvatar());

        dto.setTeacherName(conTeacher.getTeacherName());
        return dto;
    }

    public List<CommentDTO> convertToDTO(List<Comment> comments) {
        List<CommentDTO> CommentDTOs = new ArrayList<>();
        for(Comment comment : comments) {
            CommentDTOs.add(convertToDTO(comment));
        }
        return CommentDTOs;
    }

    @Override
    public Boolean isCommentExist(Integer commentId) {
        CommentMapper commentMapper = getBaseMapper();
        return commentMapper.checkByCommentId(commentId);
    }

    @Override
    public Boolean isCommentExist(Integer userId, Integer teacherId) {
        CommentMapper commentMapper = getBaseMapper();
        return commentMapper.checkByUnionId(userId, teacherId);
    }
}
