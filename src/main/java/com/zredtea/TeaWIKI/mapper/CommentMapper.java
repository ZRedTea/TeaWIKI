package com.zredtea.TeaWIKI.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zredtea.TeaWIKI.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.function.Consumer;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 根据评论id进行精确查询
     */
    default Comment selectCommentByCommentId(Integer commentId){
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getCommentId, commentId);
        return selectOne(wrapper);
    }

    /**
     * 根据教师id和用户id进行精确查询
     */
    default Comment selectCommentByUnionKey(Integer teacherId, Integer userId){
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getTeacherId, teacherId);
        wrapper.eq(Comment::getUserId, userId);
        return selectOne(wrapper);
    }

    /**
     * 根据评论内容进行模糊查询(默认热度排序)
     */
    default List<Comment> matchCommentsByContent(String content,
                                                 Consumer<LambdaQueryWrapper<Comment>> sorter) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Comment::getContent, content);

        if (sorter != null) {
            sorter.accept(wrapper);
        } else {
            // 默认排序
            wrapper.orderByDesc(Comment::getLikes);
        }
        return selectList(wrapper);
    }


    /**
     * 根据教师id返回评论列表(默认热度排序)
     */
    default List<Comment> selectCommentsByTeacherId(Integer teacherId,
                                                    Consumer<LambdaQueryWrapper<Comment>> sorter) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getTeacherId, teacherId);

        if (sorter != null) {
            sorter.accept(wrapper);
        } else {
            wrapper.orderByDesc(Comment::getLikes);
        }

        return selectList(wrapper);
    }

    /**
     * 根据用户id返回评论列表(默认热度排序)
     */
    default List<Comment> selectCommentsByUserId(Integer userId,
                                                 Consumer<LambdaQueryWrapper<Comment>> sorter) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getUserId, userId);

        if (sorter != null) {
            sorter.accept(wrapper);
        } else {
            wrapper.orderByDesc(Comment::getLikes);
        }

        return selectList(wrapper);
    }

    default Boolean checkByCommentId(Integer commentId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getCommentId, commentId);
        return selectCount(wrapper) > 0;
    }

    default Boolean checkByUnionId(Integer userId, Integer teacherId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getUserId, userId);
        wrapper.eq(Comment::getTeacherId, teacherId);
        return selectCount(wrapper) > 0;
    }
}
