package com.copago.netfflix.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("넷쁠릭스").version("1").description("넷쁠릭스 API 명세서");
        return new OpenAPI().components(new Components()).info(info);
    }
}
