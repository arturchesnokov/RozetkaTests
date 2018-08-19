package ua.com.rozetka.tests;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import ua.com.rozetka.pages.*;

import java.io.*;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;


public class RozetkaWebTests {
    private static WebDriver webDriver;
    private MainPage mainPage = new MainPage();
    private Logger log = Logger.getLogger(RozetkaWebTests.class);

    private static String webdriverBrowser;
    private static String webdriverPath;
    private static String siteAddress;

    //properties config
    private static InputStream file;
    private static Properties config = new Properties();

    @BeforeClass
    public static void setUp() throws IOException {
        //file = new FileInputStream("src/test/java/ua/com/rozetka/config/config.properties");
        file = new FileInputStream("d:/Software-Testing/Git/RozetkaTests/src/test/java/ua/com/rozetka/config/config.properties");
        config.load(file);

        //read properties from config.properties
        siteAddress = config.getProperty("site.address");
        //webdriverBrowser = config.getProperty("webdriver.browser");
        //webdriverPath = config.getProperty("webdriver.path");

        System.setProperty("selenide.browser", "Chrome"); //set Chrome as browser for start
        open(siteAddress);
    }


    @Test
    public void laptopDifferenceCount(){
        String mainMenuCategoryName = "Ноутбуки и компьютеры";
        String menuCategory = "Ноутбуки";
        String itemsCategory = "Ноутбуки с SSD";
        int numberOfComparableItems = 2;

        int diffCountOnAllParametersTab = 0;
        int diffCountOnOnlyDifferencesTab = 0;

        //Main menu on the main page
        MainMenu mainMenu = mainPage.getMainMenu();
        mainMenu.selectMainCategory(mainMenuCategoryName);
        mainMenu.selectCategory(menuCategory);

        //CategoriesCatalog
        CategoriesCatalogPage categoriesCatalog = mainMenu.getCategoriesCatalogPage();
        categoriesCatalog.selectItemsCategory(itemsCategory);

        //ItemsCatalog
        ItemsCatalogPage itemsCatalog = categoriesCatalog.getItemsCatalogPage();
        itemsCatalog.selectItemsForCompare(numberOfComparableItems);

        //Click compare button in header
        Header header = itemsCatalog.getHeader();
        header.clickCompareButtonAfterCounterCheck(numberOfComparableItems);

        //Click compare button on comapre list page
        CompareListPage compareList = itemsCatalog.getCompareListPage();
        compareList.clickCompareButton();

        CompareItemsPage compareItems = compareList.getCompareItemsPage();
        //get difference rows count on tabs
        diffCountOnAllParametersTab = compareItems.getDiffCountOnAllParametersTab();
        diffCountOnOnlyDifferencesTab = compareItems.getDiffCountOnOnlyDifferencesTab();

        Assert.assertEquals("Diffrences count without Differences filter and without are'nt same ",
                diffCountOnAllParametersTab, diffCountOnOnlyDifferencesTab);


    }

}
