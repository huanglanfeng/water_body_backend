package com.blackwater.config.until;

public class Code {

    /**
     * 正确Code
     */
    public static final Integer ADD_OK = 10001;
    public static final Integer DELETE_OK = 20001;
    public static final Integer SELECT_OK = 30001;
    public static final Integer UPDATE_OK = 40001;
    /**
     * 错误Code
     */
    public static final Integer ADD_ERR = 10000;
    public static final Integer DELETE_ERR = 20000;
    public static final Integer SELECT_ERR = 30000;
    public static final Integer UPDATE_ERR = 40000;
    public static final Integer EXCEPTION = 50000;


    /**
     *
     */

    public static final Integer ERR = 0;
    public static final Integer OK = 1;
    public static final Integer HALF = 2;

    /**
     * 签名密钥串(字典等敏感接口)
     */
    private static String signatureSecret = "dd05f1c54d63749eda95f9fa6d49v442a";
    public static String getSignatureSecret() {
        return signatureSecret;
    }






}
