package com.tape.controller;

import com.tape.entity.Article;
import com.tape.entity.Comment;
import com.tape.service.impl.ArticelServiceImpl;
import com.tape.service.impl.CommentServiceImpl;
import com.tape.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("CommentController")
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    ArticelServiceImpl articelService;
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("")
    public ModelAndView showComments(int userId, int articleId)
    {
        ModelAndView mv = new ModelAndView();
        Article article = articelService.getArticleByIdService(articleId);
        List<Comment> comments;
        if(userId == article.getArticleUserId())
        {
            comments=commentService.listCommentByArticleIdService(articleId);
        }
        else
        {
            comments=commentService.getHaveReplyComment(articleId);
        }
        mv.addObject("comments",comments);
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("articleId",articleId);
        mv.setViewName("comments");
        return mv;
    }
    @RequestMapping("/reply")
    public ModelAndView replyComment(int userId, int commentId)
    {
        ModelAndView mv = new ModelAndView();
        Comment selectedComment = commentService.getCommentByIdService(commentId);
        Comment childComment = commentService.getChildById(commentId);
        Article article = articelService.getArticleByIdService(selectedComment.getCommentArticleId());
        if(childComment == null && userId == article.getArticleUserId())
        {
            mv.addObject("selectedComment", selectedComment);
            mv.addObject("userId",userId);
            mv.addObject("userName",userName(userId));
            mv.setViewName("reply");
            return mv;
        }
        else
        {
            mv.addObject("selectedComment",selectedComment);
            mv.addObject("childComment",childComment);
            mv.addObject("userId",userId);
            mv.addObject("userName",userName(userId));
            mv.setViewName("replied");
            return mv;
        }
    }

    @RequestMapping("/reply/commit")
    public ModelAndView replyCommit(Comment comment)
    {
        System.out.println("replyCommit执行了");
        ModelAndView mv = new ModelAndView();
        commentService.insertService(comment);
        Comment selectedComment = commentService.getCommentByIdService(comment.getCommentPid());
        mv.addObject("selectedComment",selectedComment);
        mv.addObject("childComment",comment);
        mv.addObject("userId",comment.getCommentUserId());
        mv.setViewName("replied");
        return mv;
    }

    @RequestMapping("/create")
    public ModelAndView createTape(Integer userId, Integer articleId)
    {
        System.out.println("createTapes执行了");
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("articleId",articleId);

        mv.setViewName("newComment");
        return mv;
    }

    @RequestMapping("/create/commit")
    public String createCommit(Comment comment)
    {
        System.out.println("createCommit执行了"+comment);
        commentService.insertService(comment);
        return "redirect:/comment?userId="+comment.getCommentUserId()+"&articleId="+comment.getCommentArticleId();
    }

    String userName(int userId)
    {
        return userService.getUserByIdService(userId).getUserName();
    }

}
