package com.wenna.sign.lean.user.service;


import com.wenna.sign.base.tools.Page;
import com.wenna.sign.lean.user.pojo.LearnResouce;

import java.util.Map;

/**
 * Created by tengj on 2017/4/7.
 */
public interface LearnService {
    int add(LearnResouce learnResouce);
    int update(LearnResouce learnResouce);
    int deleteByIds(String ids);
    LearnResouce queryLearnResouceById(Long learnResouce);
    Page queryLearnResouceList(Map<String, Object> params);
}
