package com.tape.controller;

import com.tape.entity.User;
import com.tape.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;


@Controller
public class LoginController {

    @Autowired
    public UserServiceImpl userService;

    @RequestMapping("login")
    public String loginCheck(User user)
    {
        System.out.println("loginController执行了");
        System.out.println(user);
        User trueUser = userService.getUserByNameService(user.getUserName());
        System.out.println(trueUser);
        if(trueUser != null && Objects.equals(trueUser.getUserPass(), user.getUserPass()))
        {
            System.out.println("登陆成功");
            int id = trueUser.getUserId();
            return "redirect:article?userId="+id;
        }
        else return "redirect:";
    }
}
