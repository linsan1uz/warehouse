package com.fqf.warehouse.config;

import com.fqf.warehouse.filter.LoginCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class ServletConfig {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**IOC容器中配置FilterRegistrationBean对象，将我们自定义的Servlet的过滤器注册给FilterRegistrationBean对象 注册过滤器
    *
    *
    * */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        //创建bean对象
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //创建自定义的过滤器
        LoginCheckFilter loginCheckFilter = new LoginCheckFilter();
        //注入redis模板
        loginCheckFilter.setRedisTemplate(redisTemplate);
        //注册到filterRegistrationBean
        filterRegistrationBean.setFilter(loginCheckFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return  filterRegistrationBean;
    }
}
