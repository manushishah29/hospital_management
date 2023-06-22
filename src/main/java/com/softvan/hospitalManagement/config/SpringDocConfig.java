package com.softvan.hospitalManagement.config;

import com.softvan.hospitalManagement.enums.SwaggerEnum;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title(SwaggerEnum.API_TITLE.getValue())
                                .description(SwaggerEnum.API_DESCRIPTION.getValue())
                                .version("0.0.1"))
                .schemaRequirement(
                        HttpHeaders.AUTHORIZATION,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .description("<b>Jwt Token Coming from Sign In API</b>")
                                .name(HttpHeaders.AUTHORIZATION))
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION));

    }

    @Bean
    public GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder().group("actuator").pathsToMatch("/actuator/**").build();
    }

    @Bean
    public GroupedOpenApi helloApi() {
        return GroupedOpenApi.builder()
                .group("backend")
                .packagesToScan(SwaggerEnum.CONTROLLER_BASE_PACKAGE.getValue())
                .build();
    }
}