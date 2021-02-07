package com.bravo.bravobest.api.jwt;

import com.bravo.bravobest.api.entity.User;
import com.bravo.bravobest.api.exception.BravoBestException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtKit
 * @Description
 * @Author chengfeng
 * @Date 2/5/21 3:30 PM
 **/
@Component
public class JwtKit {

    @Autowired
    private JwtProperties jwtProperties;

    /*
     * @Description: 创建token
     * @Author: chengwh
     * @Date: 2/5/21 3:31 PM
     * @Params: []
     * @Return: java.lang.String
     **/
    public String generateToken(User user){
        //用户信息放入claims
        Map<String, Object> map = new HashMap<>(2);
        map.put("loginName",user.getLoginName());
        map.put("userNo",user.getUserNo());
        map.put("userName",user.getUserName());
        map.put("orgNo",user.getOrgNo());

        String token = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration() * 1000))
                .setHeaderParam("typ", "BRAVO")
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
        return token;
    }

    /*
     * @Description: 解码token，获得claim
     * @Author: chengwh
     * @Date: 2/5/21 3:44 PM
     * @Params: [token]
     * @Return: io.jsonwebtoken.Claims
     **/
    public Claims parseToken(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser()
                            .setSigningKey(jwtProperties.getSecret())
                            .parseClaimsJws(token)
                            .getBody();
        } catch (ExpiredJwtException e){
            throw new BravoBestException("JWTtoken过期");
        } catch (UnsupportedJwtException e){
            throw new BravoBestException("JWTtoken格式不支持");
        } catch (MalformedJwtException e){
            throw new BravoBestException("无效的token");
        }catch (SignatureException e){
            throw new BravoBestException("无效的token");
        }catch (IllegalArgumentException e){
            throw new BravoBestException("无效的token");
        }
        return claims;
    }
}
