package com.hgx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JwtTest {

    @Autowired
    private JwtProperties properties;

    @Test
    public void test() {
        String token = null;
        try {
            token = JwtUtils.generateToken(new UserInfo(12l, "hgx"),
                    properties.getPrivateKey(), properties.getExpire());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(token);
    }


}