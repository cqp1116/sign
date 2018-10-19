package com.wenna.sign.lean.user.dao;

import com.wenna.sign.lean.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {



}
