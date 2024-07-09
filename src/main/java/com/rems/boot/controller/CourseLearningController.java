package com.rems.boot.controller;

import java.util.List;

import com.rems.boot.core.LayResult;
import com.rems.boot.core.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rems.boot.entity.CourseLearningEntity;
import com.rems.boot.service.CourseLearningService;
import com.rems.boot.service.UserService;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author qinj
 * @Date 2022/8/6 11:14
 * @Description 课程学习管理接口
 * @Version 1.0
 */
@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseLearningController {

    @Autowired
    CourseLearningService courseLearningService;

    @Autowired
    UserService userService;

    /**
     * 获取数据列表
     * @param pageIndex 页数
     * @param pageSize 每页记录数
     * @return list
     */
    @GetMapping(value = "/list")
    public LayResult<CourseLearningEntity> list(@RequestParam("page") Integer pageIndex, @RequestParam("limit") Integer pageSize) {
        Page<CourseLearningEntity> result = courseLearningService.page(CourseLearningEntity.builder().build(), new Page<>(pageIndex, pageSize));
        return LayResult.ok(result.getRecords(), result.getTotal());
    }

    /* 增加 */
    @RequestMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("red-course-learning/red-course-learning-edit");
    }

    @RequestMapping("/addData")
    public String addCourse(@RequestBody CourseLearningEntity courseLearningEntity) {
        CourseLearningEntity newCourse = courseLearningService.add(courseLearningEntity);
        return newCourse != null ? "ok" : "error";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("clId", id);
        return new ModelAndView("red-course-learning/red-course-learning-edit");
    }

    /* 删除 */
    @RequestMapping("/delete")
    public String delete(@RequestBody int id) {
        courseLearningService.delete(CourseLearningEntity.builder().id(id).build());
        return "ok";
    }

    /* 删除多条记录 */
    @RequestMapping("/deletes")
    public String deletes(@RequestBody List<Long> ids) {
        boolean result = courseLearningService.deleteBatch(ids);
        return result ? "ok" : "error";
    }

    /* 更新 */
    @RequestMapping("/update")
    public String update(@RequestBody CourseLearningEntity cl) {
        courseLearningService.update(cl);
        return "ok";
    }

    /* 查看 */
    @RequestMapping("/view/{id}")
    public ModelAndView view(Model model, @PathVariable("id") int id) {
        model.addAttribute("clId", id);
        return new ModelAndView("red-course-learning/red-course-learning-view");
    }

    /* 数据回显 */
    @RequestMapping("/viewData")
    public CourseLearningEntity viewData(@RequestBody int id) {
        return courseLearningService.get(CourseLearningEntity.builder().id(id).build());
    }

}
