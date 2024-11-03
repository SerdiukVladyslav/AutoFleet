package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AutoFleetApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoFleetApplication.class, args);
    }

    @Bean
    public CommandLineRunner runMainMenu(ApplicationContext context) {
        return args -> {
            MainMenu mainMenu = context.getBean(MainMenu.class);
            mainMenu.displayMenu();
        };
    }
}
