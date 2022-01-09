package com.tape.service.impl;

import com.tape.dao.IArticleDao;
import com.tape.dao.ICommentDao;
import com.tape.dao.IUserDao;
import com.tape.entity.Article;
import com.tape.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service

public class ArticelServiceImpl implements ArticleService {

    @Autowired
    private IArticleDao articleDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ICommentDao commentDao;

    @Override
    public Integer countArticle(Integer status) {
        Integer count = 0;
        count = articleDao.countArticle(status);
        return count;
    }

    @Override
    public Integer insertService(Article article)
    {
        return articleDao.insert(article);
    }

    @Override
    public List<Article> listAllNotWithContent() {
        return articleDao.listAllNotWithContent();
    }

    @Override
    public List<Article> getArticleByUserIdService(Integer userid) {
        return articleDao.getArticleByUserId(userid);
    }

    @Override
    public List<Article> listOthersArticle(Integer userId){
        List<Article> allArticle = articleDao.listAllNotWithContent();
        List<Article> otherAticle = new ArrayList<Article>();
        for(Article article : allArticle){
            if(article.getArticleUserId() != userId)
            {
                otherAticle.add(article);
            }
        }
        return otherAticle;
    }

    @Override
    public Article getArticleByIdService(Integer articleId) {
        return articleDao.getArticleById(articleId);
    }
}

