package com.rems.boot.controller;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import com.rems.boot.core.LayResult;
import com.rems.boot.utils.MD5Util;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public LayResult<Void> login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest req) {
        log.info("#login username: " + username);
        if (Strings.isEmpty(username) || Strings.isEmpty(password)) {
            throw new IllegalArgumentException("用户名或密码不能为空");
        }

        UserEntity userEntity = userService.checkLogin(username, password);
        LayResult<Void> result;
        if (userEntity != null) {
            req.getSession().setAttribute("user", userEntity);
            result = LayResult.success();
            log.info("#login success username: " + username);
        } else {
            result = LayResult.error("用户名或密码错误");
            log.error("#login error username: " + username);
        }
        return result;
    }

    // 注册用户
    @RequestMapping("/register")
    public LayResult<Void> register(@RequestParam("username") String username, @RequestParam("password") String password) {
        log.info("#register username: " + username);
        if (Strings.isEmpty(username) || Strings.isEmpty(password)) {
            throw new IllegalArgumentException("用户名或密码不能为空");
        }

        // 检查用户名是否重复
        UserEntity userEntity2 = userService.get(UserEntity.builder().username(username).build());
        if (userEntity2 != null) {
            return LayResult.error("用户名已存在");
        }
        UserEntity newUser = userService.add(
                UserEntity.builder()
                        .username(username)
                        .password(MD5Util.generateSaltPassword(password))
                        .avatar("face.gif")
                        .role(UserTypeEnum.USER).build());
        return Objects.nonNull(newUser) ? LayResult.success() : LayResult.error("注册失败");
    }

    // 进入首页 -> 加载数据 (热门导航信息)
    @RequestMapping("/front-index")
    public ModelAndView loginCheck(HttpServletRequest req) {
        // 数据预热
        List<PopularNavEntity> pnList = popularNavService.list(new PopularNavEntity());
        // 给导航设置序号
        AtomicInteger i = new AtomicInteger(1);
        pnList.forEach(nav -> nav.setNavNum(String.valueOf(i.getAndIncrement())));
        List<CourseLearningEntity> cList = courseLearningService.list(new CourseLearningEntity());
        req.getSession().setAttribute("cList", cList);
        req.getSession().setAttribute("pnList", pnList);
        return new ModelAndView("rems-front/index");
    }

    // 进入后台管理检查管理员身份
    @RequestMapping("/check-admin")
    public LayResult<Void> checkAdmin(HttpServletRequest request) {
        UserEntity userEntity = (UserEntity)request.getSession().getAttribute("user");
        log.info("#checkAdmin userEntity: " + userEntity);
        return UserTypeEnum.ADMIN.getCode().equals(userEntity.getRole().getCode()) ? LayResult.success() :
                LayResult.error("权限不足，请联系管理员");
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
