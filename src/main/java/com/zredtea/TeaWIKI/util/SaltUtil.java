package com.zredtea.TeaWIKI.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;

public class SaltUtil {
    /**
     * 获取一个simpleUUID作为盐值
     * @return 盐值 32位String
     */
    public static String getSalt() {
        return IdUtil.simpleUUID();
    }

    /**
     *
     * @param password 加密前密码
     * @param salt 盐值
     * @return 加密后密码 64位String
     */
    public static String getPasswordCrypto(String password, String salt) {
        return DigestUtil.sha256Hex(password + salt);
    }
}
