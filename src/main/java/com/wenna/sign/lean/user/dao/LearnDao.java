package com.wenna.sign.lean.user.dao;


import com.wenna.sign.base.tools.Page;
import com.wenna.sign.lean.user.pojo.LearnResouce;

import java.util.Map;

/**
 * Created by tengj on 2017/4/8.
 */
public interface LearnDao {
    int add(LearnResouce learnResouce);
    int update(LearnResouce learnResouce);
    int deleteByIds(String ids);
    LearnResouce queryLearnResouceById(Long id);
    Page queryLearnResouceList(Map<String, Object> params);
}
