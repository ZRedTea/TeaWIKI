import org.junit.Test;

import com.zredtea.TeaWIKI.util.SaltUtil;
import cn.hutool.core.util.IdUtil;

public final class test {
    public static void main(String[] args) {
        System.out.println(SaltUtil.getSalt());
        System.out.println(IdUtil.randomUUID());
    }
}
