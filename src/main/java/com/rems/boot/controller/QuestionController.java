package com.rems.boot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rems.boot.entity.QuestionEntity;
import com.rems.boot.service.QuestionService;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

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
    @Qualifier("questionServiceImpl")
    protected QuestionService questionService;

    // 查询问题 模糊查询
    @RequestMapping("/searchQuestion")
    public ModelAndView searchQuestion(Model model, String title, HttpServletRequest req) {
        if (StringUtils.isEmpty(title)) {
            model.addAttribute("error", "未查到");
        }
        List<QuestionEntity> queryList = questionService.list(QuestionEntity.builder().quesTitle(title).build());
        req.getSession().setAttribute("queryList", queryList);
        return new ModelAndView("red-page/queryResult");
    }

    /* 获取数据列表 */
    @GetMapping("/getList")
    public JSONObject getList(int page, int limit) {
        Page<QuestionEntity> quesPage = new Page<>(page, limit);
        Page<QuestionEntity> result = questionService.page(QuestionEntity.builder().build(), quesPage);
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", 1);
        json.put("count", result.getTotal());
        json.put("data", result.getRecords());
        return json;
    }

    /* 增加 */
    @RequestMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("red-question-query/red-question-query-edit");
    }

    @RequestMapping("/addData")
    public String addQuestion(@RequestBody QuestionEntity questionEntity) {
        QuestionEntity newQuestion = questionService.add(questionEntity);
        return newQuestion != null ? "ok" : "error";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("qId", id);
        return new ModelAndView("red-question-query/red-question-query-edit");
    }

    /* 删除 */
    @RequestMapping("/delete")
    public String delete(@RequestBody int id) {
        questionService.delete(QuestionEntity.builder().id(id).build());
        return "ok";
    }

    /* 删除多条记录 */
    @RequestMapping("/deletes")
    public String deletes(@RequestBody List<Long> ids) {
        boolean flag = questionService.deleteBatch(ids);
        return flag ? "ok" : "error";
    }

    /* 更新 */
    @RequestMapping("/update")
    public String update(@RequestBody QuestionEntity q) {
        questionService.update(q);
        return "ok";
    }

    /* 查看 */
    @RequestMapping("/view/{id}")
    public ModelAndView view(Model model, @PathVariable("id") int id) {
        model.addAttribute("qId", id);
        return new ModelAndView("red-question-query/red-question-query-view");
    }

    /* 数据回显 */
    @RequestMapping("/viewData")
    public QuestionEntity viewData(@RequestBody int id) {
        return questionService.get(QuestionEntity.builder().id(id).build());
    }

}
