package com.rems.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rems.boot.core.LayResult;
import com.rems.boot.entity.CourseLearningEntity;
import com.rems.boot.service.CourseLearningService;
import com.rems.boot.service.UserService;

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
     * 添加课程学习记录
     * 
     * @param courseLearningEntity 课程学习实体
     * @return result
     */
    @RequestMapping("/add")
    public LayResult<Void> addCourse(@RequestBody CourseLearningEntity courseLearningEntity) {
        CourseLearningEntity newCourse = courseLearningService.add(courseLearningEntity);
        return newCourse != null ? LayResult.success() : LayResult.error("添加失败");
    }

    /**
     * 删除课程学习记录
     * 
     * @param id 课程学习id
     * @return result
     */
    @RequestMapping("/delete")
    public LayResult<Void> delete(@RequestBody Integer id) {
        courseLearningService.delete(CourseLearningEntity.builder().id(id).build());
        return LayResult.success();
    }

    /**
     * 批量删除课程学习记录
     * 
     * @param ids id列表
     * @return result
     */
    @RequestMapping("/delete-batch")
    public LayResult<Void> deleteBatch(@RequestBody List<Long> ids) {
        boolean result = courseLearningService.deleteBatch(ids);
        return result ? LayResult.success() : LayResult.error("批量删除失败");
    }

    /**
     * 修改课程学习记录
     * 
     * @param cl 课程学习实体
     * @return result
     */
    @RequestMapping("/update")
    public LayResult<Void> update(@RequestBody CourseLearningEntity cl) {
        courseLearningService.update(cl);
        return LayResult.success();
    }

    /**
     * 查看课程学习记录
     * 
     * @param id 课程学习id
     * @return CourseLearningEntity
     */
    @RequestMapping("/get")
    public CourseLearningEntity get(@RequestBody Integer id) {
        return courseLearningService.get(CourseLearningEntity.builder().id(id).build());
    }

    /**
     * 获取数据列表
     * 
     * @param pageIndex 页数
     * @param pageSize 每页记录数
     * @return list
     */
    @GetMapping(value = "/list")
    public LayResult<CourseLearningEntity> list(@RequestParam("page") Integer pageIndex, @RequestParam("limit") Integer pageSize) {
        Page<CourseLearningEntity> result = courseLearningService.page(CourseLearningEntity.builder().build(), new Page<>(pageIndex, pageSize));
        return LayResult.success(result.getRecords(), result.getTotal());
    }

    /**
     * 跳转到添加页面
     * 
     * @return Model
     */
    @RequestMapping("/to-add")
    public ModelAndView add() {
        return new ModelAndView("red-course-learning/red-course-learning-edit");
    }

    /**
     * 跳转到编辑页面
     * 
     * @param model 模型，用于传递参数
     * @param id id
     * @return ModelAndView
     */
    @RequestMapping("/to-edit/{id}")
    public ModelAndView edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("clId", id);
        return new ModelAndView("red-course-learning/red-course-learning-edit");
    }

    /**
     * 跳转到查看页面
     * 
     * @param model 模型，用于传递参数
     * @param id id
     * @return ModelAndView
     */
    @RequestMapping("/to-view/{id}")
    public ModelAndView view(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("clId", id);
        return new ModelAndView("red-course-learning/red-course-learning-view");
    }

}
