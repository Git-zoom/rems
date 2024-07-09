package com.rems.boot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rems.boot.core.LayResult;
import com.rems.boot.entity.MessageEntity;
import com.rems.boot.entity.UserEntity;
import com.rems.boot.service.MessageService;

/**
 * @Author qinj
 * @Date 2023/4/7 16:05
 * @Description 留言控制器
 * @Version 1.0
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    protected MessageService messageService;

    @RequestMapping("/add")
    public String addMessage(@RequestBody MessageEntity messageEntity) {
        MessageEntity newMessage = messageService.add(messageEntity);
        return newMessage != null ? "ok" : "error";
    }

    @RequestMapping("/delete")
    public String delete(@RequestBody Integer id) {
        messageService.delete(MessageEntity.builder().id(id).build());
        return "ok";
    }

    @RequestMapping("/delete-batch")
    public String deleteBatch(@RequestBody List<Long> ids) {
        messageService.deleteBatch(ids);
        return "ok";
    }

    @RequestMapping("/update")
    public String update(@RequestBody MessageEntity messageEntity) {
        messageService.update(messageEntity);
        return "ok";
    }

    @RequestMapping("/get")
    public MessageEntity get(@RequestBody Integer id) {
        MessageEntity result = messageService.get(MessageEntity.builder().id(id).build());

        return messageService.get(MessageEntity.builder().id(id).build());
    }

    @GetMapping("/list")
    public LayResult<MessageEntity> list(@RequestParam("page") Integer pageIndex, @RequestParam("limit") Integer pageSize) {
        Page<MessageEntity> result = messageService.page(MessageEntity.builder().build(), new Page<>(pageIndex, pageSize));
        return LayResult.ok(result.getRecords(), result.getTotal());
    }

    @RequestMapping("/query-all")
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

    @RequestMapping("/publish")
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

    @RequestMapping("/to-add")
    public String add() {
        return "red-website-message/red-website-message-edit";
    }

    @RequestMapping("/to-edit/{id}")
    public ModelAndView edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("mId", id);
        return new ModelAndView("red-website-message/red-website-message-edit");
    }

    @RequestMapping("/to-view/{id}")
    public ModelAndView view(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("mId", id);
        return new ModelAndView("red-website-message/red-website-message-view");
    }

}
