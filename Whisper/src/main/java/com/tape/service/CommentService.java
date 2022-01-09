package com.tape.service;


import com.tape.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface CommentService {

    /**
     * 统计评论数
     *
     * @return 数量
     */
    Integer countComment();

    List<Comment> listCommentByArticleIdService(Integer articleId);

    Comment getCommentByIdService(Integer commentId);

     Integer insertService(Comment comment) ;

    Comment getChildById(Integer commentId);

//    Comment getChildCommentByIdService(Integer commentId);
//
//    Boolean isRepliedByIdService(Integer commentId);

    public List<Comment> getHaveReplyComment(Integer articleid);

}
