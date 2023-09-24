package com.fqf.warehouse.filter;


import com.alibaba.fastjson.JSON;
import com.fqf.warehouse.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static com.fqf.warehouse.utils.WarehouseConstants.HEADER_TOKEN_NAME;

public class LoginCheckFilter implements Filter {
    private StringRedisTemplate redisTemplate;
    //手动注入
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //白名单请求直接放行


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getServletPath();

        List<String> urlList = new ArrayList<>();
        urlList.add("/login");
        urlList.add("/logout");
        urlList.add("/captcha/captchaImage");
        urlList.add("/product/img-upload");
        //过滤器拦截到的当前请求的url接口
        if(urlList.contains(url)||url.contains("/img/upload")){
            filterChain.doFilter(request, response);
            return;
        }
        //其他请求校验 是否携带token 并且判断redis中是否存在token的键
        String token = request.getHeader(HEADER_TOKEN_NAME);
        if (StringUtils.hasText(token)&&redisTemplate.hasKey(token)){
            filterChain.doFilter(request,response);
            return;
        }
        //1>有说明已经登录 请求放行
        else {
            //未登录401 msg 未登录
            Result result = Result.err(Result.CODE_ERR_UNLOGINED, "尚未登录");
            String jsonStr = JSON.toJSONString(result);
            response.setContentType("appliaction/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(jsonStr);
            out.flush();
            out.close();
        }
        //2>没有 说明未登录或者Token过期,请求不放行并给前端做出响应
    }
}
