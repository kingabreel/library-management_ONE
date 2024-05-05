package one.alura.gablibrary;

import one.alura.gablibrary.config.AppConfig;
import one.alura.gablibrary.controller.ConsoleAppController;
import one.alura.gablibrary.repository.LibraryRepository;
import one.alura.gablibrary.service.LibraryService;
import one.alura.gablibrary.view.ConsoleApp;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GabLibraryApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(GabLibraryApplication.class, args);
    }

    @Override
    public void run(String... args) {
        new ConsoleApp(new ConsoleAppController());
    }
}
