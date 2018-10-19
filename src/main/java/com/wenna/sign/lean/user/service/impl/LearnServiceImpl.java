package com.wenna.sign.lean.user.service.impl;

import com.wenna.sign.base.tools.Page;
import com.wenna.sign.lean.user.dao.LearnDao;
import com.wenna.sign.lean.user.dao.LearnMapper;
import com.wenna.sign.lean.user.pojo.LearnResouce;
import com.wenna.sign.lean.user.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tengj on 2017/4/7.
 */
@Service
public class LearnServiceImpl implements LearnService {

    @Autowired
    LearnMapper learnMapper;
    @Autowired
    LearnDao learnDao;
    @Override
    public int add(LearnResouce learnResouce) {
        //return this.learnDao.add(learnResouce);
        return this.learnMapper.add(learnResouce);
    }

    @Override
    public int update(LearnResouce learnResouce) {
        //return this.learnDao.update(learnResouce);
        return this.learnMapper.update(learnResouce);
    }

    @Override
    public int deleteByIds(String ids) {
        //return this.learnDao.deleteByIds(ids);
        return this.learnMapper.deleteByIds(ids.split(","));
    }

    @Override
    public LearnResouce queryLearnResouceById(Long id) {
        //return this.learnDao.queryLearnResouceById(id);
        return this.learnMapper.queryLearnResouceById(id);
    }

    @Override
    public Page queryLearnResouceList(Map<String,Object> params) {
        return this.learnDao.queryLearnResouceList(params);
    }

    /*@Override
    public List<LearnResouce> queryLearnResouceList(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return this.learnMapper.queryLearnResouceList(params);
    }*/
}
