package com.tape.test;

import com.tape.dao.IArticleDao;
import com.tape.dao.ICommentDao;
import com.tape.dao.IUserDao;
import com.tape.entity.Article;
import com.tape.entity.Comment;
import com.tape.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.InputStream;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *
 * 测试mybatis的crud操作
 */
@WebAppConfiguration("src/main/resources")
public class MybatisTest2 extends BaseTest{

    @Autowired
    private ICommentDao commentDao;

    @Test
    public void count(){
        int count = commentDao.countComment();
        System.out.println(count);
    }
}
