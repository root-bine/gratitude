package com.zgy.springboot_biye.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class TokenUtil {
    /**
     * 生成token
     */
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;
    public static String getToken(String userID, String password){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String token = JWT.create().withAudience(userID) // 将 userID 保存到 token 里面, 作为载荷
                .withExpiresAt(date) //2h后token过期
                .sign(Algorithm.HMAC256(password)); // 以 password 作为 token 的密钥
        return token;
    }
}
