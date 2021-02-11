package pl.brzezinski;

import pl.brzezinski.config.Configuration;
import pl.brzezinski.mainMenuForConsoleApp.MainMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

        Configuration configuration = new Configuration();
        configuration.initConfig();

//        MainMenu mainMenu = new MainMenu();
//        mainMenu.initMenu();
    }
}
