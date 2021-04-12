package com.ruochen.shop.service;

import com.ruochen.shop.dao.MyDao;
import com.ruochen.shop.domain.User;
import com.ruochen.shop.domain.UserMessage;

public class UserRegistered {
    private final UserMessage userMessage;
    private final MyDao myDao;


    public UserRegistered(UserMessage userMessage,MyDao myDao) {
        this.userMessage = userMessage;
        this.myDao=myDao;
    }


    //将获取到的用户信息注册进数据库中
    public void doRegistered(){


            User user = new User(userMessage.getUname(), userMessage.getUpw());
            myDao.userRegistered(user);
            userMessage.setUserId(myDao.selectUser(userMessage.getUname()).getId());
            myDao.messageInsert(userMessage);


    }




}
