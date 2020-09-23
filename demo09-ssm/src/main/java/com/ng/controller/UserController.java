package com.ng.controller;


import com.ng.domain.User;
import com.ng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    UserService userService;

    @RequestMapping(value = "/regist.do")
    public ModelAndView registUser(HttpServletRequest request, String code, User user) {
        ModelAndView mv = new ModelAndView();
       HttpSession session = request.getSession();

        String realCode = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        System.out.println(realCode);

        System.out.println(user);
        mv.addObject("username", user.getUsername());
        mv.addObject("password", user.getPassword());
        mv.addObject("email", user.getEmail());
        if (realCode.equalsIgnoreCase(code)) {
            System.out.println(user);
            int i = userService.registUser(user);
            System.out.println(user);
            if (i != 0) {
                mv.setViewName("user/regist_success");
                session.setAttribute("user",user);
            } else {
                mv.addObject("msg", "注册失败,请重试！");
                mv.setViewName("regist");
            }
        } else {
            mv.addObject("msg", "验证码错误,请重试！");
            mv.setViewName("user/regist");
        }
        return mv;
    }

    @RequestMapping(value = "/loginUser.do")
    public ModelAndView loginUser(HttpServletRequest request, User user) {
        ModelAndView mv = new ModelAndView();
        User selectUser = userService.loginUser(user);
        if (selectUser == null) {
            mv.addObject("msg", "帐号与密码不匹配！请重新输入");
            mv.setViewName("user/login");
            return mv;
        }
        mv.addObject("username", selectUser.getUsername());
        mv.addObject("password", selectUser.getPassword());
        mv.addObject("user", selectUser);
        request.getSession().setAttribute("user",selectUser);
        mv.setViewName("user/login_success");
        return mv;
    }

    //Ajax
    @RequestMapping(value = "/existUsernameAjax.do",produces ="text/pain; charset=utf-8")
    @ResponseBody
    public String existUsernameAjax(String username) {
        System.out.println(username);
        if (username.isEmpty()) {
            return "用户名不能为空";
        } else {
            if (userService.existUsername(username)) {
                return "该用户名已存在了哦！";
            } else {
                return "该用户名可用！";
            }
        }
    }


}
