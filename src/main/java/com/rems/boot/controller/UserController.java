package com.rems.boot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rems.boot.core.LayResult;
import com.rems.boot.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rems.boot.entity.UserEntity;
import com.rems.boot.service.UserService;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author qinj
 * @Date 2023/4/7 16:05
 * @Description 用户管理接口
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    /* 获取数据列表 */
    @GetMapping("/list")
    public LayResult<UserEntity> list(@RequestParam("page") Integer pageIndex, @RequestParam("limit") Integer pageSize) {
        Page<UserEntity> result = userService.page(UserEntity.builder().build(), new Page<>(pageIndex, pageSize));
        return LayResult.ok(result.getRecords(), result.getTotal());
    }

    /* 增加 */
    @PostMapping("/add")
    public String addUserTest() {
        return "red-user-manage/red-user-manage-edit";
    }

    @PostMapping("/addData")
    public String addUser(@RequestBody UserEntity userEntity) {
        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = userService.get(UserEntity.builder().username(userEntity.getUsername()).build());
        if (userEntity2 != null) {
            return "error,用户名已存在";
        }
        userEntity1.setUsername(userEntity.getUsername());
        userEntity1.setPassword(userEntity.getPassword());
        userEntity1.setFace(userEntity.getFace());
        userEntity1.setType(userEntity.getType());
        UserEntity newUser = userService.add(userEntity);
        return newUser != null ? "ok" : "error";
    }

    @RequestMapping("/edit/{userId}")
    public ModelAndView editUser(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("userId", userId);
        return new ModelAndView("red-user-manage/red-user-manage-edit");
    }

    /* 删除 */
    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody int id) {
        userService.delete(UserEntity.builder().id(id).build());
        return "ok";
    }

    /* 删除多条记录 */
    @RequestMapping("/deletes")
    public String deleteUsers(@RequestBody List<Long> ids) {
        boolean flag = userService.deleteBatch(ids);
        return flag ? "ok" : "error";
    }

    /* 修改 */
    @RequestMapping("/update")
    public String updateUser(@RequestBody UserEntity userEntity) {
        userService.update(userEntity);
        return "ok";
    }

    /* 查看 */
    @RequestMapping("/view/{userId}")
    public ModelAndView viewTest(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("userId", userId);
        return new ModelAndView("red-user-manage/red-user-manage-view");
    }

    /* 数据回显 */
    @RequestMapping("/viewData")
    public UserEntity viewUser(@RequestBody int id, HttpServletRequest request) {
        UserEntity userEntity = userService.get(UserEntity.builder().id(id).build());
        request.setAttribute("user", userEntity);
        return userEntity;
    }

    // 搜索用户
    @RequestMapping("/searchUser")
    public String searchUser(@RequestParam("username") String username, HttpServletRequest req) {
        String result;
        List<UserEntity> userEntityList;
        if (StringUtils.isEmpty(username)) {
            result = "error";
            req.getSession().setAttribute("msg", "未查到");
            return result;
        } else {
            UserEntity userEntity = userService.get(UserEntity.builder().username(username).build());
            userEntityList = new ArrayList<>();
            userEntityList.add(userEntity);
            result = "ok";
            req.getSession().removeAttribute("msg");
            // 结果返回
            req.getSession().setAttribute("userList", userEntityList);
        }
        return result;
    }

}
