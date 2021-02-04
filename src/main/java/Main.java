import config.Configuration;
import mainMenu.MainMenu;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.initConfig();

        MainMenu mainMenu = new MainMenu();
        mainMenu.initMenu();
    }
}
