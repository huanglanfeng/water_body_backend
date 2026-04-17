package com.blackwater.config;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    //MybatisPlus拦截器
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor mpi = new MybatisPlusInterceptor();
        //配置分页拦截器
        mpi.addInnerInterceptor(new PaginationInnerInterceptor());
        return mpi;
    }

}
