package com.zredtea.TeaWIKI.service;

import com.zredtea.TeaWIKI.DTO.request.LoginDTO;
import com.zredtea.TeaWIKI.DTO.request.PasswordUpdateDTO;
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
        RegisterDTO dto0 = new RegisterDTO();
        dto0.setUsername("zrt3");
        dto0.setPassword("123456");
        UserDTO registerResult = userService.register(dto0);
        System.out.println(registerResult);

        LoginDTO dto1 = new LoginDTO();
        dto1.setUsername("zrt3");
        dto1.setPassword("123456");
        UserDTO user = userService.login(dto1);
        System.out.println("user.statuscode: "+user.getStatusCode());

        PasswordUpdateDTO dto2 = new PasswordUpdateDTO();
        dto2.setOldPassword("123456");
        dto2.setNewPassword("123");
        UserDTO response = userService.updatePassword("zrt3",dto2);
        System.out.println("response.statuscode: "+response.getStatusCode());

        dto1.setPassword("123");
        UserDTO response2 = userService.login(dto1);
        System.out.println("response2.statuscode: "+response2.getStatusCode());
    }
}
