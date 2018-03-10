package com.fdd.lms.configuartion;

import com.fdd.lms.interceptor.AdminPassInterceptor;
import com.fdd.lms.interceptor.AdminRequireInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Lin.wang
 * @date 2018-03-10 11:57.
 */

@Component
public class FddLibConfig extends WebMvcConfigurerAdapter {
    @Autowired
    AdminPassInterceptor adminPassInterceptor;
    @Autowired
    AdminRequireInterceptor adminRequireInterceptor;

    /*
    *功能：拦截器配置
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminPassInterceptor);
        registry.addInterceptor(adminRequireInterceptor).addPathPatterns("/admin/*");
        super.addInterceptors(registry);
    }
}
