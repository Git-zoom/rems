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
public class PopularNavController {

    @Autowired
    @Qualifier("popularNavServiceImpl")
    protected PopularNavService popularNavService;

    @RequestMapping("/add")
    public LayResult<PopularNavEntity> addNav(@RequestBody PopularNavEntity popularNavEntity) {
        PopularNavEntity newNav = popularNavService.add(popularNavEntity);
        return newNav != null ? LayResult.success() : LayResult.error("添加失败");
    }

    @RequestMapping("/delete")
    public LayResult<Void> deleteNav(@RequestBody Integer id) {
        popularNavService.delete(PopularNavEntity.builder().id(id).build());
        return LayResult.success();
    }

    @RequestMapping("/delete-batch")
    public LayResult<Void> deleteBatch(@RequestBody List<Long> ids) {
        boolean flag = popularNavService.deleteBatch(ids);
        return flag ? LayResult.success() : LayResult.error("批量删除失败");
    }

    @RequestMapping("/update")
    public LayResult<Void> updateNav(@RequestBody PopularNavEntity popularNavEntity) {
        popularNavService.update(popularNavEntity);
        return LayResult.success();
    }

    @RequestMapping("/get")
    public PopularNavEntity get(@RequestBody Integer id) {
        return popularNavService.get(PopularNavEntity.builder().id(id).build());
    }

    @GetMapping(value = "/list")
    public LayResult<PopularNavEntity> list(@RequestParam("page") Integer pageIndex, @RequestParam("limit") Integer pageSize) {
        Page<PopularNavEntity> result = popularNavService.page(PopularNavEntity.builder().build(), new Page<>(pageIndex, pageSize));
        return LayResult.success(result.getRecords(), result.getTotal());
    }

    @RequestMapping("/to-add")
    public ModelAndView addNav() {
        return new ModelAndView("red-nav-manage/red-nav-manage-edit");
    }

    @RequestMapping("/to-edit/{id}")
    public ModelAndView editUser(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("pnId", id);
        return new ModelAndView("red-nav-manage/red-nav-manage-edit");
    }

    @RequestMapping("/to-view/{id}")
    public ModelAndView viewNav(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("pnId", id);
        return new ModelAndView("red-nav-manage/red-nav-manage-view");
    }

}
