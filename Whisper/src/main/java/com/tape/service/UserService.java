package com.tape.service;

import com.tape.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    /**
     * 获得用户列表
     *
     * @return 用户列表
     */
    List<User> listUser();

    User getUserByNameService(String username);

    User getUserByIdService(Integer userId);

}
