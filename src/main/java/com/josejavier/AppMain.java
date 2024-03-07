package com.josejavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@SpringBootApplication
@EntityScan(basePackages = "com.josejavier.model") // Reemplaza con el paquete de tus entidades
@EnableJpaRepositories(basePackages = "com.josejavier.repository")
public class AppMain {
    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(System.getenv("https://tutorialhub-neon.vercel.app")); // Origen permitido
        configuration.addAllowedOrigin(System.getenv("https://tutorialhub-r6fgo2vmw-jjgs-2003s-projects.vercel.app")); // Origen permitido
        configuration.addAllowedOrigin(System.getenv("https://tutorialhub-git-master-jjgs-2003s-projects.vercel.app")); // Origen permitido
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Métodos permitidos
        configuration.setAllowedHeaders(Arrays.asList("*")); // Todos los encabezados permitidos
        configuration.setAllowCredentials(false); // Permitir credenciales
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/", configuration);
        return source;
    }

}
