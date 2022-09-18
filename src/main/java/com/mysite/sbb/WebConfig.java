package com.mysite.sbb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override // @desc REST API 의 CORS configuration 역할
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") /*허용할 API 호출 주소 패턴*/
                .allowedOrigins("http://localhost:8080") /*허용할 local url */
                .allowedMethods("GET", "POST", "PUT", "DELETE"); /*허용할 METHOD*/
    }

}
