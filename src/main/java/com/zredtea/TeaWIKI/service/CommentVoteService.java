package com.zredtea.TeaWIKI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zredtea.TeaWIKI.entity.CommentVote;

public interface CommentVoteService extends IService<CommentVote> {
    boolean likeComment(Integer userId,Integer commentId);

    boolean dislikeComment(Integer userId,Integer commentId);

    boolean cancelVote(Integer userId,Integer commentId);

    boolean toggleVote(Integer userId,Integer commentId);
}
