import com.zredtea.TeaWIKI.TeaWIKIApplication;
import org.junit.Test;

import com.zredtea.TeaWIKI.service.UserService;
import com.zredtea.TeaWIKI.DTO.request.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TeaWIKIApplication.class)
public final class test {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setUsername("zredtea");
        dto.setPassword("123456");
        userService.register(dto);
    }


}
