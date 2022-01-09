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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.InputStream;
import java.util.List;

/**
 * 测试mybatis的crud操作
 */
@WebAppConfiguration("src/main/resources")
public class MybatisTest extends BaseTest{

    @Autowired
    private IUserDao userDao;

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        //5.执行查询所有方法
        List<User> users = userDao.listUser();
        System.out.println(users.size());
        for(User user : users){
            System.out.println(user);
        }
    }
}
