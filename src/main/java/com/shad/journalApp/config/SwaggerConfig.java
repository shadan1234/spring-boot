package com.shad.journalApp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                        new Info().title("Journal API").description("by shadan")

                ).servers(List.of(
                        new Server().url("http://localhost:8080/journal/").description("local"),
                        new Server().url("http://localhost:8082/journal/").description("live")
                )).tags(List.of(
                        new Tag().name("Public API's"),
                        new Tag().name("User API's"),
                        new Tag().name("Journal API's"),
                        new Tag().name("Admin API's")
                )).addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes(
                        "bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme("bearer").bearerFormat("JWT").in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                ))
                ;

    }

}
