package com.tape.test;


import com.tape.dao.IArticleDao;
import com.tape.dao.ICommentDao;
import com.tape.dao.IUserDao;
import com.tape.entity.Article;
import com.tape.entity.Comment;
import com.tape.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *
 * 测试mybatis的crud操作
 */
@WebAppConfiguration("src/main/resources")
public class MybatisTest1 extends BaseTest{

    @Autowired
    private IArticleDao articleDao;

    @Test
    public void count(){
        int count = articleDao.countArticle(1);
        System.out.println(count);
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        //5.执行查询所有方法
        List<Article> articles = articleDao.listAllNotWithContent();
        System.out.println(articles.size());
        for(Article article : articles){
            System.out.println(article);
        }
    }
}
