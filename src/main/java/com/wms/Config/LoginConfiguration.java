/*
package com.wms.Config;

import com.wms.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

*/
/**
 * @author ：LiCan
 * @date ：Created in 2019/1/9 15:31
 * @description：${description}
 * @modified By：
 * @version: $version$
 *//*

@Configuration
public class LoginConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/**");
        // 排除路径
        loginRegistry.excludePathPatterns("/user/login");
        loginRegistry.excludePathPatterns("page/login");
        loginRegistry.excludePathPatterns("/loginout");
        loginRegistry.excludePathPatterns("/login.jsp");
        loginRegistry.excludePathPatterns("/user/register");

        // 排除资源请求
        loginRegistry.excludePathPatterns("/css/*.css");
        loginRegistry.excludePathPatterns("/js/*.js");
        loginRegistry.excludePathPatterns("/image/*.png");


    }
}
*/
