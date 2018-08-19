package ua.com.rozetka.pages;

import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import ua.com.rozetka.pages.Header;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private Logger logger = Logger.getLogger(MainPage.class);

    private MainMenu mainMenu = new MainMenu();

    public MainMenu getMainMenu(){
        logger.debug("Get main menu");
        return mainMenu;
    }
}
