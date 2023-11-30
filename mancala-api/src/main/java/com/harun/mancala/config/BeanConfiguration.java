package com.harun.mancala.config;

import com.harun.mancala.domain.repository.IGameRepository;
import com.harun.mancala.domain.service.GameService;
import com.harun.mancala.domain.service.IGameService;
import com.harun.mancala.repository.GameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BeanConfiguration {

    @Bean
    IGameRepository gameRepository() {
        return new GameRepository();
    }

    @Bean
    IGameService gameService(final IGameRepository gameRepository) {
        return new GameService(gameRepository);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
