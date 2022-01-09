package com.tape.controller;

import com.tape.entity.Article;
import com.tape.service.ArticleService;
import com.tape.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("ArticleController")
@RequestMapping("article")
public class ArticleController {
    @Autowired
    public ArticleService articleService;

    @Autowired
    public UserService userService;

    @RequestMapping("")
    public ModelAndView showAll(int userId)
    {
        System.out.println("articlecontroller执行了");
        List<Article> articles = articleService.getArticleByUserIdService(userId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("articles",articles);
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("who","我的");
        mv.setViewName("articles");
        return mv;
    }

    @RequestMapping("create")
    public ModelAndView create(int userId)
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.setViewName("create");
        return mv;
    }
    @RequestMapping("commit")
    public String createCommit(Article article)
    {
        //articleService.insert(article);
        System.out.println("创建成功"+article.getArticleUserId());
        articleService.insertService(article);
        return "redirect:/article?userId="+article.getArticleUserId();
    }

    @RequestMapping("all")
    public ModelAndView showOthers(int userId)
    {
        ModelAndView mv =new ModelAndView();
        List<Article> articles = articleService.listAllNotWithContent();
        mv.addObject("articles",articles);
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("who","全部的");
        mv.setViewName("articles");
        return mv;
    }

    String userName(int userId)
    {
        return userService.getUserByIdService(userId).getUserName();
    }
}
