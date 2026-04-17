package com.blackwater.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;


import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.AuthorizationScope;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Author scott
 */
@Configuration
@EnableSwagger2
//@EnableSwaggerBootstrapUI
@EnableKnife4j
public class SwaggerConfig implements WebMvcConfigurer {


    /**
     * 显示swagger-ui.html文档展示页，还必须注入swagger资源：
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决 doc.html 404 报错
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     *
     * @return Docket
     */
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //此包路径下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.blackwater.controller"))
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //过滤路径
                .paths(PathSelectors.any())
                .build();
        //.globalOperationParameters(setHeaderToken());
    }

//    /**
//     * JWT token
//     * @return
//     */
//    private List<Parameter> setHeaderToken() {
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//        tokenPar.name("X-Access-Token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());
//        return pars;
//    }



    /**
     * api文档的详细信息函数,注意这里的注解引用的是哪个
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // //大标题
                .title(" 黑臭水体")
                // 版本号
                .version("1.0")
//				.termsOfServiceUrl("NO terms of service")
                // 描述
                .description("后台API接口")

                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }


    /**
     * 解决springboot2.6 和springfox不兼容问题
     * @return
     */
//    @Bean
//    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
//        return new BeanPostProcessor() {
//
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
//                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
//                }
//                return bean;
//            }
//
//            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
//                List<T> copy = mappings.stream()
//                        .filter(mapping -> mapping.getPatternParser() == null)
//                        .collect(Collectors.toList());
//                mappings.clear();
//                mappings.addAll(copy);
//            }
//
//            @SuppressWarnings("unchecked")
//            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
//                try {
//                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
//                    field.setAccessible(true);
//                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
//                } catch (IllegalArgumentException | IllegalAccessException e) {
//                    throw new IllegalStateException(e);
//                }
//            }
//        };
//    }





}
