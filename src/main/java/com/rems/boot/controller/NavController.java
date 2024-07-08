package com.rems.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rems.boot.entity.PopularNavEntity;
import com.rems.boot.service.PopularNavService;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

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

    /* 获取数据列表 */
    @GetMapping(value = "/getList")
    public JSONObject getNavList(int page, int limit) {
        Page<PopularNavEntity> navPage = new Page<>(page, limit);
        Page<PopularNavEntity> result = popularNavService.page(PopularNavEntity.builder().build(), navPage);
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", 1);
        json.put("count", result.getTotal());
        json.put("data", result.getRecords());
        return json;
    }

    /* 增加 */
    @RequestMapping("/add")
    public ModelAndView addNav() {
        return new ModelAndView("red-nav-manage/red-nav-manage-edit");
    }

    @RequestMapping("/addData")
    public String addNav(@RequestBody PopularNavEntity popularNavEntity) {
        PopularNavEntity newNav = popularNavService.add(popularNavEntity);
        return newNav != null ? "ok" : "error";
    }

    /* 编辑 */
    @RequestMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("pnId", id);
        return "red-nav-manage/red-nav-manage-edit";
    }

    /* 删除 */
    @RequestMapping("/delete")
    public String deleteNav(@RequestBody int id) {
        popularNavService.delete(PopularNavEntity.builder().id(id).build());
        return "ok";
    }

    /* 删除多条记录 */
    @RequestMapping("/deletes")
    public String deleteNavs(@RequestBody List<Long> ids) {
        boolean flag = popularNavService.deleteBatch(ids);
        return flag ? "ok" : "error";
    }

    /* 更新 */
    @RequestMapping("/update")
    public String updateNav(@RequestBody PopularNavEntity popularNavEntity) {
        popularNavService.update(popularNavEntity);
        return "ok";
    }

    /* 查看 */
    @RequestMapping("/view/{id}")
    public ModelAndView viewNav(Model model, @PathVariable("id") int id) {
        model.addAttribute("pnId", id);
        return new ModelAndView("red-nav-manage/red-nav-manage-view");
    }

    /* 数据回显 */
    @RequestMapping("/viewData")
    public PopularNavEntity viewData(@RequestBody int id) {
        return popularNavService.get(PopularNavEntity.builder().id(id).build());
    }

}
