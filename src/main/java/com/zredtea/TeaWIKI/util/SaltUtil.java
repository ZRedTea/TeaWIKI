package com.zredtea.TeaWIKI.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;

public class SaltUtil {
    public static String getSalt() {
        return IdUtil.simpleUUID();
    }

    public static String getPasswordCrypto(String password, String salt) {
        return DigestUtil.sha256Hex(password + salt);
    }
}
