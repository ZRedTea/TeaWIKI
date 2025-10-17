package com.zredtea.TeaWIKI.test;

import com.zredtea.TeaWIKI.DTO.request.RegisterDTO;
import com.zredtea.TeaWIKI.TeaWIKIApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import com.zredtea.TeaWIKI.service.UserService;

@SpringBootTest(classes = TeaWIKIApplication.class)
public class UserServiceTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @Test
    public void testContextLoads() {
        // 检查 Spring 上下文是否正常加载
        System.out.println("Spring 上下文: " + applicationContext);
    }

    @Test
    public void test() {
        RegisterDTO dto = new RegisterDTO();
        dto.setUsername("zredtea");
        dto.setPassword("123456");
        userService.register(dto);
    }
}
