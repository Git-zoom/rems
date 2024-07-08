package com.rems.boot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rems.boot.entity.MessageEntity;
import com.rems.boot.entity.UserEntity;
import com.rems.boot.service.MessageService;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author qinj
 * @Date 2023/4/7 16:05
 * @Description 留言控制器
 * @Version 1.0
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    protected final MessageService messageService;

    public MessageController(@Qualifier("messageServiceImpl") MessageService messageService) {
        this.messageService = messageService;
    }

    // 查询所有留言
    @RequestMapping("/queryAllMessage")
    public String queryAllMessage() {
        List<MessageEntity> messageEntityList = messageService.list(new MessageEntity());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr;
        try {
            jsonStr = mapper.writeValueAsString(messageEntityList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    // 发表留言
    @RequestMapping("/publishMessage")
    public String publishMessage(String content, HttpServletRequest req) {
        MessageEntity messageEntity = new MessageEntity();
        // 获取当前用户
        UserEntity userEntity = (UserEntity)req.getSession().getAttribute("user");
        messageEntity.setMsgContent(content);
        messageEntity.setMsgUsername(userEntity.getUsername());
        messageEntity.setMsgUserFace(userEntity.getFace());
        messageService.add(messageEntity);
        List<MessageEntity> messageEntityList = new ArrayList<>();
        messageEntityList.add(messageEntity);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr;
        try {
            jsonStr = mapper.writeValueAsString(messageEntityList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    /* 获取数据列表 */
    @GetMapping("/getList")
    public JSONObject getList(int page, int limit) {
        Page<MessageEntity> mesPage = new Page<>(page, limit);
        Page<MessageEntity> result = messageService.page(MessageEntity.builder().build(), mesPage);
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", 1);
        json.put("count", result.getTotal());
        json.put("data", result.getRecords());
        return json;
    }

    /* 增加 */
    @RequestMapping("/add")
    public String add() {
        return "red-website-message/red-website-message-edit";
    }

    @RequestMapping("/addData")
    public String addMessage(@RequestBody MessageEntity messageEntity) {
        MessageEntity newMessage = messageService.add(messageEntity);
        return newMessage != null ? "ok" : "error";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("mId", id);
        return new ModelAndView("red-website-message/red-website-message-edit");
    }

    /* 删除 */
    @RequestMapping("/delete")
    public String delete(@RequestBody int id) {
        messageService.delete(MessageEntity.builder().id(id).build());
        return "ok";
    }

    /* 删除多条记录 */
    @RequestMapping("/deletes")
    public String deletes(@RequestBody List<Long> ids) {
        messageService.deleteBatch(ids);
        return "ok";
    }

    /* 更新 */
    @RequestMapping("/update")
    public String update(@RequestBody MessageEntity messageEntity) {
        messageService.update(messageEntity);
        return "ok";
    }

    /* 查看 */
    @RequestMapping("/view/{id}")
    public ModelAndView view(Model model, @PathVariable("id") int id) {
        model.addAttribute("mId", id);
        return new ModelAndView("red-website-message/red-website-message-view");
    }

    /* 数据回显 */
    @RequestMapping("/viewData")
    public MessageEntity viewData(@RequestBody int id) {
        return messageService.get(MessageEntity.builder().id(id).build());
    }

}