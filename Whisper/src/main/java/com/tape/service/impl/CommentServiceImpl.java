package com.tape.service.impl;


import com.tape.dao.IArticleDao;
import com.tape.dao.ICommentDao;
import com.tape.entity.Article;
import com.tape.entity.Comment;
import com.tape.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service

public class CommentServiceImpl implements CommentService {

    @Autowired
    private ICommentDao commentDao;

    @Autowired
    private IArticleDao articleDao;

    @Override
    public Integer countComment() {
        Integer commentCount = null;
        commentCount = commentDao.countComment();
        return commentCount;
    }

    @Override
    public List<Comment> listCommentByArticleIdService(Integer articleId)
    {
        return commentDao.listCommentByArticleId(articleId);
    }

    @Override
    public Comment getCommentByIdService(Integer commentId)
    {
        return commentDao.getCommentById(commentId);
    }

    @Override
    public Integer insertService(Comment comment)
    {
        return commentDao.insert(comment);
    }

    @Override
    public Comment getChildById(Integer commentId) {
        List<Comment> comments = commentDao.listComments();
        for (Comment comment : comments) {
            if (comment.getCommentPid() == commentId) {
                return comment;
            }
        }
        return null;
    }


//    @Override
//    public Comment getChildCommentByIdService(Integer commentId)
//    {
//        List<Comment> comments = commentDao.
//        return childComment;
//    }
//
//    @Override
//    public Boolean isRepliedByIdService(Integer commentId)
//    {
//
//    }

    @Override
    public List<Comment> getHaveReplyComment(Integer articleid){
        List<Comment> havereplycomments = new ArrayList<Comment>();
        List<Comment> bigcomments = commentDao.listCommentByArticleId(articleid);
        List<Comment> smallcomments = commentDao.listComments();
        //System.out.println(comments.size());
        for(Comment bigcomment : bigcomments){
            int bigcommentId = bigcomment.getCommentId();
            for(Comment smallcomment : smallcomments){
                if(smallcomment.getCommentPid() == bigcommentId){
                    //添加被回复的评论
                    //System.out.println(bigcomment);
                    havereplycomments.add(bigcomment);
                    break;
                    //打印此评论的所有回复
                    //System.out.println(smallcomment);
                }
            }
        }
        return havereplycomments;
    }
}
