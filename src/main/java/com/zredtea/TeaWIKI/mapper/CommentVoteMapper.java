package com.zredtea.TeaWIKI.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zredtea.TeaWIKI.entity.CommentVote;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentVoteMapper extends BaseMapper<CommentVote> {
    /**
     * 根据评论id和用户id精确查询
     */
    default CommentVote selectCommentVoteByUnionId(Integer userId,Integer commentId) {
        LambdaQueryWrapper<CommentVote> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommentVote::getUserId,userId);
        queryWrapper.eq(CommentVote::getCommentId,commentId);
        return selectOne(queryWrapper);
    }

    /**
     * 根据评论id获取赞数
     */
    default Integer countLikesByCommentId(Integer commentId) {
        LambdaQueryWrapper<CommentVote> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommentVote::getCommentId,commentId);
        queryWrapper.eq(CommentVote::getVoteType,"like");
        return Math.toIntExact(selectCount(queryWrapper));
    }

    /**
     * 根据评论id获取踩数
     */
    default Integer countDislikesByCommentId(Integer commentId) {
        LambdaQueryWrapper<CommentVote> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommentVote::getCommentId,commentId);
        queryWrapper.eq(CommentVote::getVoteType,"dislike");
        return Math.toIntExact(selectCount(queryWrapper));
    }
}
