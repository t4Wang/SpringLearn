package com.smart.web;

import com.smart.domain.User;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class LoginController {
    private UserService userService;

    @RequestMapping(value="/index")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value="/loginCheck")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
        User temp = new User();
        temp.setUserName(loginCommand.getUsername());
        temp.setPassword(loginCommand.getPassword());
        User user = userService.getByNameAndPassword(temp);
        if (user.getUserId() == 0) {
            return new ModelAndView("login", "error", "用户名或密码错误");
        } else {
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}