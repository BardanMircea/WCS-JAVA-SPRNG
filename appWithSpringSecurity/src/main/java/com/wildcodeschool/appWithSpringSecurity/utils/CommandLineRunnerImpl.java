package com.wildcodeschool.appWithSpringSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final GeneratorService generatorService;

    public CommandLineRunnerImpl(@Autowired  GeneratorService generatorService){
            this.generatorService = generatorService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.generatorService.seedDatabase();
    }
}
