package one.alura.gablibrary.config;

import one.alura.gablibrary.controller.ConsoleAppController;
import one.alura.gablibrary.service.LibraryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "one.alura.gablibrary.repository")
public class AppConfig {
    @Bean
    public LibraryService libraryService() {
        return new LibraryService();
    }

    @Bean
    public ConsoleAppController consoleAppController() {
        return new ConsoleAppController();
    }
}
