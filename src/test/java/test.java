import com.zredtea.TeaWIKI.Application;
import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.TeaWIKIApplication;
import org.junit.Test;

import com.zredtea.TeaWIKI.util.SaltUtil;
import cn.hutool.core.util.IdUtil;

import com.zredtea.TeaWIKI.service.UserService;
import com.zredtea.TeaWIKI.service.impl.UserServiceImpl;
import com.zredtea.TeaWIKI.entity.User;
import com.zredtea.TeaWIKI.DTO.request.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TeaWIKIApplication.class)
public final class test {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        RegisterDTO dto = new RegisterDTO();
        dto.setUsername("zredtea");
        dto.setPassword("123456");
        userService.register(dto);
    }


}
