package com.wjl.testdata.sm2;

import org.bouncycastle.util.encoders.Base64;

/**
 * 国密工具类（公钥私钥等均为带结构数据）
 *
 * @author acer
 */
public class GMUtil {

    /**
     * SM3hash运算
     *
     * @param indata 原文
     * @return hash结果（base64）
     * @throws Exception
     */
    public static String SM3Hash(String indata) throws Exception {
        if (CheckEmpty.isEmpty(indata)) {
            throw new Exception("indata is null");
        }
        byte[] hashdate = null;
        hashdate = SM3Hash.GetHashValue(indata.getBytes());
        return new String(Base64.encode(hashdate));
    }
}
