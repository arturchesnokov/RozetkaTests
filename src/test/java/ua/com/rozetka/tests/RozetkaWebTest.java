package ua.com.rozetka.tests;

import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import ua.com.rozetka.pages.*;
import java.io.*;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class RozetkaWebTest {
  private static WebDriver webDriver;
  private MainPage mainPage = new MainPage();
  private Logger log = Logger.getLogger(RozetkaWebTest.class);

  private static String webdriverBrowser;
  private static String webdriverPath;
  private static String siteAddress;

  //properties config
  private static InputStream file;
  private static Properties config = new Properties();

  @BeforeClass
  public static void setUp() throws IOException {
    //se
    file = new FileInputStream("d:/Software-Testing/Git/RozetkaTests/src/test/java/ua/com/rozetka/config/config.properties");
    config.load(file);

    //read properties from config.properties
    System.setProperty("selenide.browser", "Chrome"); //set Chrome as browser for start

    siteAddress = config.getProperty("site.address");
    open(siteAddress);
  }

  @Test
  public void itemsCompareDiferencesCount() {
    //Main menu on the main page
    MainMenu mainMenu = mainPage.getMainMenu();
    mainMenu.selectMainCategory("Ноутбуки и компьютеры", "Ноутбуки");

    //CategoriesCatalog
    CategoriesCatalogPage categoriesCatalog = mainMenu.getCategoriesCatalogPage();
    categoriesCatalog.selectItemsCategory("Ноутбуки с SSD");

    //ItemsCatalog
    ItemsCatalogPage itemsCatalog = categoriesCatalog.getItemsCatalogPage();

    int numberOfComparableItems = 2; // Set q-ty of comparable items (recomend 2-4)
    itemsCatalog.selectItemsForCompare(numberOfComparableItems);

    //Click compare button in header
    Header header = itemsCatalog.getHeader();
    header.clickCompareButtonAfterCounterCheck(numberOfComparableItems);

    //Click compare button on comapre list page
    CompareListPage compareList = itemsCatalog.getCompareListPage();
    compareList.clickCompareButton();

    CompareItemsPage compareItems = compareList.getCompareItemsPage();
    //get difference rows count on tabs
    int actualDifferenceCounter = compareItems.getDiffCountOnAllParametersTab();
    int expectedDifferenceCounter = compareItems.getDiffCountOnOnlyDifferencesTab();

    Assert.assertEquals("Diffrences count without Differences filter and without are'nt same ",
           expectedDifferenceCounter, actualDifferenceCounter);
  }
}
