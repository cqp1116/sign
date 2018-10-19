package com.wenna.sign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author  cqp1116
 * 引入缓存配置
 */
@RestController
@SpringBootApplication
@EnableCaching
@ServletComponentScan(basePackages = {"com.wenna.**"})
public class SignApplication {

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/login");
        return mv;
    }

    public static void main(String[] args) {
        SpringApplication.run(SignApplication.class, args);
    }
}
