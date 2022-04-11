package com.zy.common.encry;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.android.utils.common.LogUtils;
import com.android.utils.devices.DeviceInfo;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Random;

import sun.misc.BASE64Encoder;

/**
 * @author:zhangyue
 * @date:2020/5/29
 */
public class KeyUtils {
    /**
     * 创建AESkey
     * @return
     */
    public static String createAESKey(){
        Random random=new Random();
        String deviceMd5 = DeviceInfo.getDeviceMd5(DeviceInfo.getDeviceNumber());
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuilder sb=new StringBuilder();
        String result= sb.append(deviceMd5).append(dateFormat.format(System.currentTimeMillis())).append(random.nextInt(99999999)).toString();
        LogUtils.w("AES KEY = "+result);
        return result;
    }

    /**
     * 加密AESkey
     * @param publicKey 公钥
     * @param key key
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encryAESKey(String publicKey, String key){
        try {
            LogUtils.d("publickey -> "+publicKey);

            PublicKey publicKey1 = RSAUtils.getPublicKey(publicKey);

            return RSAUtils.encrypt(new BASE64Encoder().encode(key.getBytes()),publicKey1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
