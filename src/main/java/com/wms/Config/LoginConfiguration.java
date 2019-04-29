package com.wms.Config;

import com.wms.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




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
        loginRegistry.excludePathPatterns("*.css");
        loginRegistry.excludePathPatterns("*.js");
        loginRegistry.excludePathPatterns("/images/*.jpg");
        loginRegistry.excludePathPatterns("/images/*.png");
        loginRegistry.excludePathPatterns("/css/style.css");


    }
}
