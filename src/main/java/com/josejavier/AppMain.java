package com.josejavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EntityScan(basePackages = "com.josejavier.model") // Reemplaza con el paquete de tus entidades
@EnableJpaRepositories(basePackages = "com.josejavier.repository")
public class AppMain {
    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*").allowedHeaders("*");
                registry.addMapping("/api/**").allowedOrigins("https://tutorialhub-git-master-jjgs-2003s-projects.vercel.app").allowedMethods("*").allowedHeaders("*");
                registry.addMapping("/auth/**").allowedOrigins("https://tutorialhub-neon.vercel.app").allowedMethods("*").allowedHeaders("*");
                registry.addMapping("/user/**").allowedOrigins("https://tutorialhub-r6fgo2vmw-jjgs-2003s-projects.vercel.app").allowedMethods("*").allowedHeaders("*");
            }
        };
    }

}
