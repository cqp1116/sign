package com.wenna.sign.lean.solr.controller;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @auther 陈庆朋
 * @create 2018/10/9
 */
@RestController
@RequestMapping("solr")
public class SolrController {

    @Autowired
    private SolrClient client;

    /**
     * 增加分词
     * @return
     */
    @RequestMapping("add")
    public String addSolr(){
        //生成一个随机数
        String uuid = UUID.randomUUID().toString().replace("-","");
        try{
            SolrInputDocument input = new SolrInputDocument();
            input.setField("id",uuid);
            input.setField("_text_","问呐科技,问呐");

            client.add("sign_core",input);
            client.commit("sign_core");
            System.out.println("success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return uuid;
    }


    /**
     * 根据id删除索引
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public String delete(String id)  {
        try {
            client.deleteById(id);
            client.commit("sign_core");

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "error";
    }

    /**
     * 删除所有的索引
     * @return
     */
    @RequestMapping("deleteAll")
    public String deleteAll(){
        try {

            client.deleteByQuery("*:*");
            client.commit("sign_core");

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 根据id查询索引
     * @return
     * @throws Exception
     */
    @RequestMapping("getById")
    public String getById() throws Exception {
        SolrDocument document = client.getById("42f9416a957541428a31c7576a27230c");
        System.out.println(document);
        return document.toString();
    }



}
