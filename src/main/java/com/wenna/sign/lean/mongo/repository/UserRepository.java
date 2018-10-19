package com.wenna.sign.lean.mongo.repository;

import com.wenna.sign.lean.mongo.entity.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserEntity,Long> {

    /**
     * 根据name查询数据
     * @param name
     * @return
     */
    List<UserEntity> findByName(String name);
}
