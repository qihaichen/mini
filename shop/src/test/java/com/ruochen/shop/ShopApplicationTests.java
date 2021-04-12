package com.ruochen.shop;

import com.ruochen.shop.dao.MyDao;
import com.ruochen.shop.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShopApplicationTests {

    @Autowired
    MyDao myDao;

    @Test
    void contextLoads() {
        String password="545558ss4";
        User user = myDao.selectUser("qihaichen");
        if (user==null){
            System.out.println("用户名错误");
        }else if (!user.getUpw().equals(password)){
            System.out.println("密码错误");
        }else {
            System.out.println("成功登录");
        }

    }

    @Test
    void Test2()  {
        List all = myDao.selectAll();
        System.out.println(all);
    }

}
