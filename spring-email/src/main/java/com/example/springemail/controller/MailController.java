package com.example.springemail.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springemail.send.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/4/9
 */
@CrossOrigin
@RestController
@RequestMapping("mail")
public class MailController {

    @Autowired
    private Sender sender;

    @RequestMapping("test")
    public String test(@RequestBody JSONObject requestJson, HttpServletRequest request) throws Exception{
        sender.sendMessageMail(requestJson.getString("title"),requestJson.getString("content"),requestJson.getString("to"),false);
        return "ok";
    }
}
