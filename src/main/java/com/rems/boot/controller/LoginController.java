package com.rems.boot.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rems.boot.entity.CourseLearningEntity;
import com.rems.boot.entity.PopularNavEntity;
import com.rems.boot.entity.UserEntity;
import com.rems.boot.enums.UserTypeEnum;
import com.rems.boot.service.CourseLearningService;
import com.rems.boot.service.PopularNavService;
import com.rems.boot.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author qinj
 * @Date 2023/4/7 16:05
 * @Description 登录控制器
 * @Version 1.0
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseLearningService courseLearningService;
    @Autowired
    private PopularNavService popularNavService;

    // 登录验证
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest req) {
        UserEntity userEntity = userService.checkLogin(username, password);
        String result;
        if (userEntity != null) {
            req.getSession().setAttribute("user", userEntity);
            result = "ok";
        } else {
            result = "error";
        }
        return result;
    }

    // 注册用户
    @RequestMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 检查用户名是否重复
        UserEntity userEntity2 = userService.get(UserEntity.builder().username(username).build());
        if (userEntity2 != null) {
            return "Duplicate username";
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        UserEntity newUser = userService.add(userEntity);

        String result;
        if (newUser != null) {
            result = "ok";
        } else {
            result = "error";
        }
        return result;
    }

    // 进入首页 -> 加载数据 (热门导航信息)
    @RequestMapping("/welcome")
    public ModelAndView loginCheck(HttpServletRequest req) {
        // 查询数据
        List<PopularNavEntity> pnList = popularNavService.list(new PopularNavEntity());
        List<CourseLearningEntity> cList = courseLearningService.list(new CourseLearningEntity());
        req.getSession().setAttribute("cList", cList);
        req.getSession().setAttribute("pnList", pnList);
        return new ModelAndView("red-page/welcome");
    }

    // 进入后台管理检查管理员身份
    @RequestMapping("/checkAdmin")
    public String checkAdmin(HttpServletRequest request) {
        UserEntity userEntity = (UserEntity)request.getSession().getAttribute("user");
        log.info("#checkAdmin userEntity: " + userEntity);
        return UserTypeEnum.ADMIN.getCode().equals(userEntity.getType().getCode()) ? "ok" : "error";
    }

    // 退出登录
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest req) {
        UserEntity userEntity = (UserEntity)req.getSession().getAttribute("user");
        if (Objects.nonNull(userEntity)) {
            req.getSession().removeAttribute("user");
        }
        return new ModelAndView("redirect: /login");
    }

}
