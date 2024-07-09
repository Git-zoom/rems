package com.rems.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rems.boot.core.LayResult;
import com.rems.boot.entity.PopularNavEntity;
import com.rems.boot.service.PopularNavService;

/**
 * @Author qinj
 * @Date 2023/4/7 16:05
 * @Description 热门导航接口
 * @Version 1.0
 */
@RestController
@RequestMapping("/nav")
public class NavController {

    @Autowired
    @Qualifier("popularNavServiceImpl")
    protected PopularNavService popularNavService;

    @RequestMapping("/add")
    public String addNav(@RequestBody PopularNavEntity popularNavEntity) {
        PopularNavEntity newNav = popularNavService.add(popularNavEntity);
        return newNav != null ? "ok" : "error";
    }

    @RequestMapping("/delete")
    public String deleteNav(@RequestBody Integer id) {
        popularNavService.delete(PopularNavEntity.builder().id(id).build());
        return "ok";
    }

    @RequestMapping("/delete-batch")
    public String deleteBatch(@RequestBody List<Long> ids) {
        boolean flag = popularNavService.deleteBatch(ids);
        return flag ? "ok" : "error";
    }

    @RequestMapping("/update")
    public String updateNav(@RequestBody PopularNavEntity popularNavEntity) {
        popularNavService.update(popularNavEntity);
        return "ok";
    }

    @RequestMapping("/get")
    public PopularNavEntity get(@RequestBody Integer id) {
        return popularNavService.get(PopularNavEntity.builder().id(id).build());
    }

    @GetMapping(value = "/list")
    public LayResult<PopularNavEntity> list(@RequestParam("page") Integer pageIndex, @RequestParam("limit") Integer pageSize) {
        Page<PopularNavEntity> result = popularNavService.page(PopularNavEntity.builder().build(), new Page<>(pageIndex, pageSize));
        return LayResult.ok(result.getRecords(), result.getTotal());
    }

    @RequestMapping("/to-add")
    public ModelAndView addNav() {
        return new ModelAndView("red-nav-manage/red-nav-manage-edit");
    }

    @RequestMapping("/to-edit/{id}")
    public String editUser(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("pnId", id);
        return "red-nav-manage/red-nav-manage-edit";
    }

    @RequestMapping("/to-view/{id}")
    public ModelAndView viewNav(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("pnId", id);
        return new ModelAndView("red-nav-manage/red-nav-manage-view");
    }

}
