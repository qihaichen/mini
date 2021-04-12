package com.ruochen.shop.dao;


import com.ruochen.shop.domain.User;
import com.ruochen.shop.domain.UserMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MyDao {
    @Select("select id,uname,upw from users where uname=#{uname}")
    User selectUser(String uname);
    @Insert("insert into users (uname,upw) value(#{uname},#{upw})")
    void userRegistered(User user);
    @Insert("insert into usermessage (userId,firstName,lastName,email,address) value(#{userId},#{firstName},#{lastName},#{email},#{address})")
    void messageInsert(UserMessage userMessage);
    @Select("select * from usermessage")
    List<UserMessage> selectAll();
    @Select("select * from users where id=#{id}")
    User selectId(int id);
    @Select("select * from usermessage where userId = (select id from users where uname=#{uname})")
    UserMessage selectForUname(String uname);
    @Update("update usermessage set firstName=#{firstName},lastName=#{lastName},email=#{email},address=#{address} where userId = (select id from users where uname=#{uname})")
    void updateUser(UserMessage userMessage);
}
