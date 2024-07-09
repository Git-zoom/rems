package com.rems.boot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rems.boot.core.LayResult;
import com.rems.boot.entity.QuestionEntity;
import com.rems.boot.service.QuestionService;

/**
 * @Author qinj
 * @Date 2023/4/7 16:05
 * @Description 问题查询接口
 * @Version 1.0
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    protected QuestionService questionService;

    @RequestMapping("/add")
    public LayResult<Void> addQuestion(@RequestBody QuestionEntity questionEntity) {
        QuestionEntity newQuestion = questionService.add(questionEntity);
        return newQuestion != null ? LayResult.success() : LayResult.error("添加失败");
    }

    @RequestMapping("/delete")
    public LayResult<Void> delete(@RequestBody Integer id) {
        questionService.delete(QuestionEntity.builder().id(id).build());
        return LayResult.success();
    }

    @RequestMapping("/delete-batch")
    public LayResult<Void> deleteBatch(@RequestBody List<Long> ids) {
        boolean flag = questionService.deleteBatch(ids);
        return flag ? LayResult.success() : LayResult.error("批量删除失败");
    }

    @RequestMapping("/update")
    public LayResult<Void> update(@RequestBody QuestionEntity q) {
        questionService.update(q);
        return LayResult.success();
    }

    @RequestMapping("/get")
    public QuestionEntity get(@RequestBody Integer id) {
        return questionService.get(QuestionEntity.builder().id(id).build());
    }

    @GetMapping("/list")
    public LayResult<QuestionEntity> list(@RequestParam("page") Integer pageIndex, @RequestParam("limit") Integer pageSize) {
        Page<QuestionEntity> result = questionService.page(QuestionEntity.builder().build(), new Page<>(pageIndex, pageSize));
        return LayResult.success(result.getRecords(), result.getTotal());
    }

    @RequestMapping("/search")
    public ModelAndView searchQuestion(Model model, String title, HttpServletRequest req) {
        if (StringUtils.isEmpty(title)) {
            model.addAttribute("error", "请输入搜索内容");
        }
        List<QuestionEntity> queryList = questionService.list(QuestionEntity.builder().quesTitle(title).build());
        req.getSession().setAttribute("queryList", queryList);
        return new ModelAndView("red-page/queryResult");
    }

    @RequestMapping("/to-add")
    public ModelAndView add() {
        return new ModelAndView("red-question-query/red-question-query-edit");
    }

    @RequestMapping("/to-edit/{id}")
    public ModelAndView edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("qId", id);
        return new ModelAndView("red-question-query/red-question-query-edit");
    }

    @RequestMapping("/to-view/{id}")
    public ModelAndView view(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("qId", id);
        return new ModelAndView("red-question-query/red-question-query-view");
    }

}
