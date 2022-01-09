package com.tape.service.impl;
import com.tape.dao.IArticleDao;
import com.tape.dao.ICommentDao;
import com.tape.dao.IUserDao;
import com.tape.entity.User;
import com.tape.service.ArticleService;
import com.tape.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IArticleDao articleDao;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ICommentDao commentDao;

    @Override
    public List<User> listUser() {
        List<User> userList = userDao.listUser();
        return userList;
    }

    @Override
    public User getUserByNameService(String username){
        return userDao.getUserByName(username);
    }

    @Override
    public User getUserByIdService(Integer userId)
    {return userDao.getUserById(userId);}

}
