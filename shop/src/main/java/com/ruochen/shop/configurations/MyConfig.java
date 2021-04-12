package com.ruochen.shop.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyConfig implements WebMvcConfigurer {
    //配置页面的跳转功能
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("sign-in");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/403.html").setViewName("403");
        registry.addViewController("/404.html").setViewName("404");
        registry.addViewController("/500.html").setViewName("500");
        registry.addViewController("/503.html").setViewName("503");
        registry.addViewController("/calendar.html").setViewName("calendar");
        registry.addViewController("/faq.html").setViewName("faq");
        registry.addViewController("/gallery.html").setViewName("gallery");
        registry.addViewController("/help.html").setViewName("help");
        registry.addViewController("/privacy-policy.html").setViewName("privacy-policy");
        registry.addViewController("/reset-password.html").setViewName("reset-password");
        registry.addViewController("/sign-in.html").setViewName("sign-in");
        registry.addViewController("/sign-up.html").setViewName("sign-up");
        registry.addViewController("/terms-and-conditions.html").setViewName("terms-and-conditions");
        registry.addViewController("/users.html").setViewName("users");
    }
    //给项目添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加过滤请求地址和不过滤的请求地址，注意放行静态资源
        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns
                        ("/","/login","/sign-up.html","/reg","/sign-in.html","/images/**","/javascripts/**","/lib/**","/stylesheets/**");
    }
}
