package com.bravo.bravobest.api.interceptor;

import com.bravo.bravobest.api.entity.User;
import com.bravo.bravobest.api.jwt.JwtKit;
import com.bravo.bravobest.api.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtKit jwtKit;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 之前校验
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       /* String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURI);
        System.out.println(requestURL);
        Object user = request.getSession().getAttribute("user");
        if(user == null || !(user instanceof User)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print("登录超时,请重新登录!");
            return false;
        }*/

        /*jwt验证是否登陆*/
        System.out.println("jwt拦截器");
        String authorization = request.getHeader(jwtProperties.getTokenHeader());
        if (authorization != null && !"".equals(authorization) && authorization.startsWith(jwtProperties.getTokenHead())){
            String authToken = authorization.substring(jwtProperties.getTokenHead().length());
            Claims claims = jwtKit.parseToken(authToken);
            if (claims != null){
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }


}
