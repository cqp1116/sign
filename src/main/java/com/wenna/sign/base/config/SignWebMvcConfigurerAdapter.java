package com.wenna.sign.base.config;

import com.wenna.sign.base.interceptor.LoginInterceptor;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;


/**
 * @author  cqp1116
 *
 */
public class SignWebMvcConfigurerAdapter implements WebMvcConfigurer {

    /**
     * 添加自己的静态资源文件
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").setCacheControl(CacheControl
                .maxAge(10, TimeUnit.MINUTES)
                .cachePrivate());
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/").setCacheControl(CacheControl
                .maxAge(10, TimeUnit.MINUTES)
                .cachePrivate());
        addResourceHandlers(registry);
    }

    /**
     * 以前要访问一个页面需要先创建个Controller控制类，在写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.html页面了
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin").setViewName("login");
        addViewControllers(registry);
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("toLogin","login");
        addInterceptors(registry);
    }
}
