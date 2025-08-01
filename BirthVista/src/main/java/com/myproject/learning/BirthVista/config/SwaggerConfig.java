package com.myproject.learning.BirthVista.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("BirthVista API")
                .version("1.0.0")
                .description("API documentation for the BirthVista application.")
                .contact(new Contact()
                    .name("Anshul Pawar")
                    .email("anshulpawar100@gmail.com")
                    .url("https://github.com/a-pawar"))
                
            );
    }
}
