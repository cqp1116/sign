package com.wenna.sign;

import com.wenna.sign.lean.user.dao.UserRepository;
import com.wenna.sign.lean.user.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Test
    public void contextLoads() {
        userRepository.save(new User("chenqp","cqp1116W"));
    }

}
