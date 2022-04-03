package com.yoti.sample.hoover.clean.configuration;

import com.yoti.sample.hoover.clean.ports.HooverRepositoryService;
import com.yoti.sample.hoover.clean.ports.Logger;
import com.yoti.sample.hoover.clean.usecase.CleanTheRoomUseCase;
import com.yoti.sample.hoover.clean.usecase.CleanTheRoomUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class HooverConfig {

    @Autowired
    HooverRepositoryService hooverRepositoryService;

    @Autowired
    Logger logger;

    @Bean
    public CleanTheRoomUseCase createCleanTheRoomUseCase() {
        return new CleanTheRoomUseCaseImpl(hooverRepositoryService, logger);
    }
}