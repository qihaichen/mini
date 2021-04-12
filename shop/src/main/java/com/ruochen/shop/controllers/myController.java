package com.ruochen.shop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruochen.shop.dao.MyDao;
import com.ruochen.shop.domain.User;
import com.ruochen.shop.domain.UserMessage;
import com.ruochen.shop.service.UserRegistered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
public class myController {
    @Autowired
    MyDao myDao;
    //由于ajax设置发送post请求，所以这里也接受post请求
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody User rUser, HttpSession session) throws IOException {
        //查找数据库中是否有对应的用户信息
        User user = myDao.selectUser(rUser.getUname());
        //创建map用于向前台传递信息
        HashMap<String,String> map = new HashMap<>();
        //创建对象映射器，返回json字符串
        ObjectMapper mapper = new ObjectMapper();
        if (user==null){
            map.put("msg", "用户名错误");
            return mapper.writeValueAsString(map);
        }else if (!user.getUpw().equals(rUser.getUpw())){
            map.put("msg", "密码错误");
            return mapper.writeValueAsString(map);
        }else {
            List<UserMessage> all = myDao.selectAll();
            all.forEach(userMessage -> {
                userMessage.setUname(myDao.selectId(userMessage.getUserId()).getUname());
                userMessage.setUpw(myDao.selectId(userMessage.getUserId()).getUpw());
            });
            session.setAttribute("allUser",all);
            session.setAttribute("userName",user.getUname());
            session.setAttribute("isLogin",true);
            map.put("msg", "true");
            return mapper.writeValueAsString(map);
        }
    }


    //实现用户的注册
    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    @ResponseBody
    public String Register(@RequestBody UserMessage userMessage){
        if (myDao.selectUser(userMessage.getUname())==null) {
            System.out.println(userMessage);
            UserRegistered userRegistered = new UserRegistered(userMessage, myDao);
            userRegistered.doRegistered();
            return "true";
        }
        else
            return "false";
    }

    @RequestMapping("/user.html")
    public String goUser(HttpSession session){
        UserMessage user = myDao.selectForUname((String) session.getAttribute("userName"));
        session.setAttribute("mine",user);
        return "user";
    }


    //修改员工信息
    @RequestMapping("/save")
    public String doSave(UserMessage uMessage,HttpSession session){
        uMessage.setUname((String) session.getAttribute("userName"));
        myDao.updateUser(uMessage);
        List<UserMessage> all = myDao.selectAll();
        all.forEach(userMessage -> {
            userMessage.setUname(myDao.selectId(userMessage.getUserId()).getUname());
            userMessage.setUpw(myDao.selectId(userMessage.getUserId()).getUpw());
        });
        session.setAttribute("allUser",all);
        return "index";
    }
    //登出功能
    @RequestMapping("/logout")
    public String Logout(HttpSession session){
        session.setAttribute("isLogin",null);
        return "/";
    }




}
