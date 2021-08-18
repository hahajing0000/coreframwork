package com.zy.storage.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.storage.utils
 * @ClassName: MD5
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/18 11:06
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/18 11:06
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MD5 {
    public static String encrypt(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xFF & digest[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
