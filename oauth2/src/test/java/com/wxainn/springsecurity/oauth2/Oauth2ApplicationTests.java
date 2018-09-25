package com.wxainn.springsecurity.oauth2;

import com.wxainn.springsecurity.oauth2.service.ResourceService;
import com.wxainn.springsecurity.oauth2.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Oauth2ApplicationTests {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ResourceService resourceService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void crud() {
        UserDetails userDetails = userServiceImpl.loadUserByUsername("admin");
        System.out.println(userDetails.getAuthorities());
        System.out.println(userDetails);
    }

    @Test
    public void resourceCache() {
        System.out.println(resourceService.findAll());
    }

}
