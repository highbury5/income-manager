package manager.configuration;

import lombok.extern.slf4j.Slf4j;
import manager.advice.AuthorizationInterceptor;
import manager.core.converter.BaseMappingJackson2HttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 名称: PlumWebMvcConfigurer
 * 描述: WEB MVC 配置
 *
 * @author YangHongyu
 * @version 1.0
 * @since 2018/6/13 23:48
 *
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FoundationWebMvcConfigurer implements WebMvcConfigurer {


    public FoundationWebMvcConfigurer() {
        log.info("初始化FoundationWebMvcConfigurer...");
    }

    @Bean
    public AuthorizationInterceptor authorizationInterceptor(){
        return new AuthorizationInterceptor();
    }

    /**
     * Jackson自定义配置
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new BaseMappingJackson2HttpMessageConverter());
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(excludedPathPatterns());
    }

    /**
     * 排除不需拦截的URL路径数组
     */
    private String[] excludedPathPatterns() {
        return new String[]{
                "/error",
                "/v2/api-docs",
                "/swagger-resources/**",
                "/hystrix.stream"
        };
    }

}
