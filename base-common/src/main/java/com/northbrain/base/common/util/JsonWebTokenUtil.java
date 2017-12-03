package com.northbrain.base.common.util;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 类名：JWT工具类
 * 用途：JWT是 Json Web Token 的缩写。它是基于 RFC 7519 标准定义的一种可以安全传输的 小巧 和 自包含 的JSON对象。
 * 由于数据是使用数字签名的，所以是可信任的和安全的。
 * JWT可以使用HMAC算法对secret进行加密或者使用RSA的公钥私钥对来进行签名。
 * @author Jiakun
 * @version 1.0
 *
 */
public class JsonWebTokenUtil
{
    private static Logger logger = Logger.getLogger(JsonWebTokenUtil.class);

    /**
     * 方法：生成JWT
     * @param claims
     * @param audience
     * @param issuer
     * @param TTLMillis
     * @param security
     * @return
     * @throws Exception
     */
    public static String generateJWT(Map<String, Object> claims,
                                     String audience, String issuer, long TTLMillis,
                                     String security) throws Exception
    {
        if(claims == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "claims");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(audience == null || audience.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "audience");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(TTLMillis < 0L)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "TTLMillis");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(issuer == null || issuer.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "issuer");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(security == null || security.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "security");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);

        //生成签名密钥
        byte[] keySecretBytes = DatatypeConverter.parseBase64Binary(security);
        Key secretKey = new SecretKeySpec(keySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder jwtBuilder = Jwts.builder()
                .setHeaderParam(Constants.BUSINESS_COMMON_JWT_HEADER_PARAM_TYPE, Constants.BUSINESS_COMMON_JWT_HEADER_PARAM_TYPE_VALUE)
                .setClaims(claims)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, secretKey);

        //添加Token过期时间
        long expireMillis = currentTimeMillis + TTLMillis;
        Date expire = new Date(expireMillis);

        jwtBuilder.setExpiration(expire).setNotBefore(now);

        //生成JWT
        return jwtBuilder.compact();
    }

    /**
     * 方法：解析JWT
     * @param jsonWebToken
     * @param security
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jsonWebToken, String security) throws Exception
    {
        if(jsonWebToken == null || jsonWebToken.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "jsonWebToken");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(security == null || security.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "security");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(security))
                .parseClaimsJws(jsonWebToken).getBody();

        return claims;
    }
}
