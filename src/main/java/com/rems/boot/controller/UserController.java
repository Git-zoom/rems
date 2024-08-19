package com.rems.boot.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rems.boot.core.LayResult;
import com.rems.boot.entity.UserEntity;
import com.rems.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/add")
    public LayResult<Void> addr(@RequestBody UserEntity userEntity) {
        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = userService.get(UserEntity.builder().username(userEntity.getUsername()).build());
        if (userEntity2 != null) {
            LayResult.error("用户名已存在");
        }
        userEntity1.setUsername(userEntity.getUsername());
        userEntity1.setPassword(userEntity.getPassword());
        userEntity1.setAvatar(userEntity.getAvatar());
        userEntity1.setRole(userEntity.getRole());
        UserEntity newUser = userService.add(userEntity);
        return newUser != null ? LayResult.success() : LayResult.error("添加用户失败");
    }

    @DeleteMapping("/delete")
    public LayResult<Void> deleteUser(@RequestBody Integer id) {
        userService.delete(UserEntity.builder().id(id).build());
        return LayResult.success();
    }

    @RequestMapping("/delete-batch")
    public LayResult<Void> deleteUsers(@RequestBody List<Long> ids) {
        boolean flag = userService.deleteBatch(ids);
        return flag ? LayResult.success() : LayResult.error("批量删除失败");
    }

    @PostMapping("/update")
    public LayResult<Void> updateUser(@RequestBody UserEntity userEntity) {
        userService.update(userEntity);
        return LayResult.success();
    }

    @RequestMapping("/get")
    public UserEntity viewUser(@RequestBody Integer id, HttpServletRequest request) {
        UserEntity userEntity = userService.get(UserEntity.builder().id(id).build());
        request.setAttribute("user", userEntity);
        return userEntity;
    }

    @GetMapping("/list")
    public LayResult<UserEntity> list(@RequestParam("page") Integer pageIndex, @RequestParam("limit") Integer pageSize) {
        Page<UserEntity> result = userService.page(UserEntity.builder().build(), new Page<>(pageIndex, pageSize));
        return LayResult.success(result.getRecords(), result.getTotal());
    }

    // 搜索用户
    @RequestMapping("/search")
    public LayResult<Void> searchUser(@RequestParam("username") String username, HttpServletRequest req) {
        LayResult<Void> result;
        List<UserEntity> userEntityList;
        if (StringUtils.isEmpty(username)) {
            result = LayResult.error("请输入用户名");
            req.getSession().setAttribute("msg", "未查到");
            return result;
        } else {
            UserEntity userEntity = userService.get(UserEntity.builder().username(username).build());
            userEntityList = new ArrayList<>();
            userEntityList.add(userEntity);
            result = LayResult.success();
            req.getSession().removeAttribute("msg");
            // 结果返回
            req.getSession().setAttribute("userList", userEntityList);
        }
        return result;
    }

    @PostMapping("/to-add")
    public ModelAndView addUserTest() {
        return new ModelAndView("/rems-back/rems-user-manage/rems-user-manage-edit");
    }

    @RequestMapping("/to-edit/{userId}")
    public ModelAndView editUser(Model model, @PathVariable("userId") Integer userId) {
        model.addAttribute("userId", userId);
        return new ModelAndView("/rems-back/rems-user-manage/rems-user-manage-edit");
    }

    @RequestMapping("/to-view/{userId}")
    public ModelAndView viewTest(Model model, @PathVariable("userId") Integer userId) {
        model.addAttribute("userId", userId);
        return new ModelAndView("/rems-back/rems-user-manage/rems-user-manage-view");
    }

    @RequestMapping("/to-home")
    public ModelAndView toHome() {
        return new ModelAndView("/rems-back/rems-core/home");
    }

}
