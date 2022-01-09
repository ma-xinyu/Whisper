package com.tape.dao;

import com.tape.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户的持久层接口
 */
@Mapper
public interface IUserDao {

    /**
     * 添加
     * @param user 用户
     * @return 影响行数
     */
    int insert(User user);

    User getUserById(Integer userId);
    /**
     * 获得用户列表
     *
     * @return  用户列表
     */
    List<User> listUser() ;

    /**
     * 根据用户名查用户
     *
     * @param name 用户名
     * @return 用户
     */
    User getUserByName(String name) ;

}
