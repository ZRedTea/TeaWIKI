package com.zredtea.TeaWIKI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zredtea.TeaWIKI.DTO.request.CommentVote.CommentVoteCommitDTO;
import com.zredtea.TeaWIKI.entity.CommentVote;

public interface CommentVoteService extends IService<CommentVote> {
    boolean voteComment(Integer userId, CommentVoteCommitDTO dto);

    boolean cancelVote(Integer userId, Integer commentId);

    boolean toggleVote(Integer userId, Integer commentId);

    Integer countLikes(Integer commentId);

    Integer countDislikes(Integer commentId);
}
