package com.blackwater.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

@Configuration
public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
    //配置字符编码过滤器

    @Override
    protected Filter[] getServletFilters() {

        CharacterEncodingFilter f = new CharacterEncodingFilter();
        f.setEncoding("utf-8");

        return new Filter[]{f};
    }
}
