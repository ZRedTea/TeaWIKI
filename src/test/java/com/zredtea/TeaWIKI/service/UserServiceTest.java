package com.zredtea.TeaWIKI.service;

import com.zredtea.TeaWIKI.DTO.request.LoginDTO;
import com.zredtea.TeaWIKI.DTO.request.RegisterDTO;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.TeaWIKIApplication;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
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
        LoginDTO dto = new LoginDTO();
        dto.setUsername("zredtea");
        dto.setPassword("123456");
        UserDTO user = userService.login(dto);
        System.out.println(user);
        System.out.println(user.getStatusCode());
    }
}
