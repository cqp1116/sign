package com.wenna.sign.base.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @auther 陈庆朋
 * @create 2018/10/9
 */
@Configuration
public class SolrClientConfiguration {
    @Autowired
    private Environment environment;

    @Bean
    public SolrClient solrClient() {
        System.out.println("自定义配置SolrClient");
        return new HttpSolrClient(environment.getRequiredProperty("spring.data.solr.host"));
    }
}
