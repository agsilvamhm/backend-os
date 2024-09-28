package com.agsilva.os.config;

import com.agsilva.os.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class Testconfig {
    @Autowired
    private DbService dbService;

    @Bean
    public void instanciaDb(){
        dbService.instanciaDb();
    }
}
