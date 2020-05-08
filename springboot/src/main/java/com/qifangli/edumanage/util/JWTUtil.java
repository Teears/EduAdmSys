package com.qifangli.edumanage.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {
    // 过期时间10分钟
    private static final long EXPIRE_TIME = 10*60*1000;

    /**
     * 校验token有效性
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();

        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 判断token的时效性
     */
//    public static boolean isTimeout(String token, String secret){
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            DecodedJWT jwt = verifier.verify(token);
//            if (jwt.getExpiresAt().before(new Date())) {
//                System.out.println("token已过期");
//                return false;
//            }
//            return true;
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            return false;
//        }
//    }


    /**
     * 生成签名,10min后过期
     * @param username 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }
}