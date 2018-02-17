package com.northbrain.base.common.util;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.exception.ParameterConfigException;
import com.northbrain.base.common.exception.TokenVerificationException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Names;
import com.northbrain.base.common.model.bo.Parameters;

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
     * token中除了预留的部分外，还包括私有部分：参与者编码、角色编码和组织机构编码
     * @param partyId 参与者编码
     * @param roleId 角色编码
     * @param organizationId 组织机构编码
     * @return 生成的JWT
     * @throws Exception 异常
     */
    public static String generateJsonWebToken(int partyId, int roleId, int organizationId) throws Exception
    {
        if(partyId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "partyId");
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(organizationId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "organizationId");
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        //获取后端继续JWT 相关信息
        String businessCommonJWTTokenKey = Parameters.get(Names.BUSINESS_COMMON_JWT_TOKEN_KEY);
        String businessCommonJWTTokenCompanyId = Parameters.get(Names.BUSINESS_COMMON_JWT_TOKEN_COMPANY_ID);
        String businessCommonJWTTokenAudience = Parameters.get(Names.BUSINESS_COMMON_JWT_TOKEN_AUDIENCE);
        String businessCommonJWTTokenIssuer = Parameters.get(Names.BUSINESS_COMMON_JWT_TOKEN_ISSUER);
        String businessCommonJWTTokenExpireTTL = Parameters.get(Names.BUSINESS_COMMON_JWT_TOKEN_EXPIRE_TTL);

        if(businessCommonJWTTokenKey == null || businessCommonJWTTokenKey.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_JWT_TOKEN_KEY");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(businessCommonJWTTokenCompanyId == null || businessCommonJWTTokenCompanyId.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_JWT_TOKEN_COMPANY_ID");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(businessCommonJWTTokenAudience == null || businessCommonJWTTokenAudience.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_JWT_TOKEN_AUDIENCE");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(businessCommonJWTTokenIssuer == null || businessCommonJWTTokenIssuer.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_JWT_TOKEN_ISSUER");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(businessCommonJWTTokenExpireTTL == null || businessCommonJWTTokenExpireTTL.equals("") ||
                Long.parseLong(businessCommonJWTTokenExpireTTL) <= 0L)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_JWT_TOKEN_EXPIRE_TTL");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);

        //生成签名密钥
        byte[] keySecretBytes = DatatypeConverter.parseBase64Binary(businessCommonJWTTokenKey);
        Key secretKey = new SecretKeySpec(keySecretBytes, signatureAlgorithm.getJcaName());

        //私有claims部分，目前只保持id号
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.BUSINESS_COMMON_JWT_PAYLOAD_PARAM_PARTY_ID, partyId);
        claims.put(Constants.BUSINESS_COMMON_JWT_PAYLOAD_PARAM_ROLE_ID, roleId);
        claims.put(Constants.BUSINESS_COMMON_JWT_PAYLOAD_PARAM_ORGANIZATION_ID, organizationId);

        //添加构成JWT的参数
        JwtBuilder jwtBuilder = Jwts
                .builder()
                .setHeaderParam(Constants.BUSINESS_COMMON_JWT_HEADER_PARAM_TYPE, Constants.BUSINESS_COMMON_JWT_HEADER_PARAM_TYPE_VALUE)
                .setIssuedAt(now)
                .setClaims(claims)
                .setIssuer(businessCommonJWTTokenIssuer)
                .setAudience(businessCommonJWTTokenAudience)
                .setId(businessCommonJWTTokenCompanyId)
                .signWith(signatureAlgorithm, secretKey);

        //添加Token过期时间
        long expireMillis = currentTimeMillis + Long.parseLong(businessCommonJWTTokenExpireTTL);
        Date expire = new Date(expireMillis);

        jwtBuilder
                .setExpiration(expire)
                .setNotBefore(now);

        //生成JWT
        return jwtBuilder.compact();
    }

    /**
     * 方法：解析JWT
     * @param jsonWebToken token
     * @return 申明在token中的id信息
     * @throws Exception 异常
     */
    public static Map<String, Object> parseJsonWebToken(String jsonWebToken) throws Exception
    {
        if(jsonWebToken == null || jsonWebToken.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "jsonWebToken");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        //获取后端继续JWT 相关信息
        String businessCommonJWTTokenKey = Parameters.get(Names.BUSINESS_COMMON_JWT_TOKEN_KEY);
        String businessCommonJWTTokenCompanyId = Parameters.get(Names.BUSINESS_COMMON_JWT_TOKEN_COMPANY_ID);
        String businessCommonJWTTokenAudience = Parameters.get(Names.BUSINESS_COMMON_JWT_TOKEN_AUDIENCE);
        String businessCommonJWTTokenIssuer = Parameters.get(Names.BUSINESS_COMMON_JWT_TOKEN_ISSUER);
        String businessCommonJWTTokenExpireTTL = Parameters.get(Names.BUSINESS_COMMON_JWT_TOKEN_EXPIRE_TTL);

        if(businessCommonJWTTokenKey == null || businessCommonJWTTokenKey.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_JWT_TOKEN_KEY");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(businessCommonJWTTokenCompanyId == null || businessCommonJWTTokenCompanyId.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_JWT_TOKEN_COMPANY_ID");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(businessCommonJWTTokenAudience == null || businessCommonJWTTokenAudience.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_JWT_TOKEN_AUDIENCE");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(businessCommonJWTTokenIssuer == null || businessCommonJWTTokenIssuer.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_JWT_TOKEN_ISSUER");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(businessCommonJWTTokenExpireTTL == null || businessCommonJWTTokenExpireTTL.equals("") ||
                Long.parseLong(businessCommonJWTTokenExpireTTL) <= 0L)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_JWT_TOKEN_EXPIRE_TTL");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(businessCommonJWTTokenKey))
                .parseClaimsJws(jsonWebToken.replace(Constants.BUSINESS_COMMON_JWT_HEADER_PARAM_AUTHORIZATION_STARTER, ""))
                .getBody();

        //判断发行者、接收者等信息是否正确
        if(!claims.getIssuer().equals(businessCommonJWTTokenIssuer))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_JWT_TOKEN_VERIFICATION + "Issuer");
            throw new TokenVerificationException(Errors.ERROR_BUSINESS_COMMON_JWT_TOKEN_VERIFICATION_EXCEPTION);
        }

        if(!claims.getId().equals(businessCommonJWTTokenCompanyId))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_JWT_TOKEN_VERIFICATION + "CompanyId");
            throw new TokenVerificationException(Errors.ERROR_BUSINESS_COMMON_JWT_TOKEN_VERIFICATION_EXCEPTION);
        }

        if(!claims.getAudience().equals(businessCommonJWTTokenAudience))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_JWT_TOKEN_VERIFICATION + "Audience");
            throw new TokenVerificationException(Errors.ERROR_BUSINESS_COMMON_JWT_TOKEN_VERIFICATION_EXCEPTION);
        }

        //解析私有claims
        Map<String, Object> privateClaims = new HashMap<>();
        privateClaims.put(Constants.BUSINESS_COMMON_JWT_PAYLOAD_PARAM_PARTY_ID, claims.get(Constants.BUSINESS_COMMON_JWT_PAYLOAD_PARAM_PARTY_ID));
        privateClaims.put(Constants.BUSINESS_COMMON_JWT_PAYLOAD_PARAM_ROLE_ID, claims.get(Constants.BUSINESS_COMMON_JWT_PAYLOAD_PARAM_ROLE_ID));
        privateClaims.put(Constants.BUSINESS_COMMON_JWT_PAYLOAD_PARAM_ORGANIZATION_ID, claims.get(Constants.BUSINESS_COMMON_JWT_PAYLOAD_PARAM_ORGANIZATION_ID));

        return privateClaims;
    }
}
