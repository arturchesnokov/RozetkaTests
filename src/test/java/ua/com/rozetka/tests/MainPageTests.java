package ua.com.rozetka.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ua.com.rozetka.pages.main.MainPage;

import javax.swing.*;

import java.io.*;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;
import static junit.framework.TestCase.assertTrue;

public class MainPageTests {
    private static WebDriver webDriver;
    private MainPage page = new MainPage();
    private Logger log = Logger.getLogger(MainPageTests.class.getName());

    private static String webdriverBrowser;
    private static String webdriverPath;
    private static String siteAddress;

    //properties config
    private static InputStream file;
    private static Properties config = new Properties();

    @BeforeClass
    public static void setUp() throws IOException {
     //   Logger log = ;
        file = new FileInputStream("d:/Software-Testing/Git/RozetkaTests/src/test/java/ua/com/rozetka/config/config.properties");
        config.load(file);

        //read properties from config.properties
        siteAddress = config.getProperty("site.address");
        webdriverBrowser = config.getProperty("webdriver.browser");
        webdriverPath = config.getProperty("webdriver.path");

      //  System.setProperty(webdriverBrowser,webdriverPath); //set browser and path to Chrome
        System.setProperty("selenide.browser", "Chrome"); //set Chrome as browser for start

        //webDriver = new ChromeDriver();
        //webDriver.manage().window().maximize();

        open(siteAddress);
    }

    @Test
    public void laptopDifferenceCount(){
        $(By.linkText("Ноутбуки и компьютеры")).hover();
        $(By.linkText("Ноутбуки")).click();
        $(By.linkText("Ноутбуки с SSD")).click();
        List<SelenideElement> items = $("div.cat-g-b").$$("div.g-i-tile-i-box");


        if (!items.isEmpty()) {
            log.info("Item List not empty. OK");
            for (int i = 0; i < 2; i++) {
                SelenideElement element = items.get(i);
                System.out.println(element.$("div.g-i-tile-i-title").getText());
                element.$("div.g-i-tile-i-image").hover();
                element.$("span.g-compare").click();
                Selenide.sleep(500);
            }
        }
        items = null;

        if ($("div#comparison").$("span.hub-i-count").getText().equals("2")){
            $("ul.header-user-buttons").$("div#comparison").click();
        }
        Selenide.sleep(2000);

        $("div.btn-link-to-compare").$("span.btn-link-i").click();
        //Selenide.sleep(2000);

        if ($(By.linkText("Все параметры")).exists())
            $(By.linkText("Все параметры")).click();

        List<SelenideElement> params = $("div.comparison-t").$$("div.comparison-t-row");
        int allTabDiffCounter = 0;
        for (SelenideElement row:params) {
            List<SelenideElement> cells = row.$$("div.comparison-t-cell");
            Set<String> cellsValues = new HashSet<String>();
            for (SelenideElement cell:cells){

                if (cell.$("span.chars-value-inner").exists()){
                    cellsValues.add(cell.$("span.chars-value-inner").text());
                }
            }
                if (cellsValues.size() > 1){
                    allTabDiffCounter++;
                }
                cellsValues = null;

        }

        System.out.println(allTabDiffCounter);

        if ($(By.linkText("Только отличия")).exists())
            $(By.linkText("Только отличия")).click();

        List<SelenideElement> onlyDiffRows = $("div.comparison-t")
                .$$("div.comparison-t-cell-first");
        int onlyDiffTabCounter = onlyDiffRows.size();

        for (SelenideElement row : onlyDiffRows) {
            if (row.isDisplayed()){
                onlyDiffTabCounter++;
            }

        }

        System.out.println(onlyDiffTabCounter);
        System.out.println("onlyDiffRows " + onlyDiffRows.size());


        Assert.assertEquals(allTabDiffCounter, onlyDiffTabCounter);



    }

}
