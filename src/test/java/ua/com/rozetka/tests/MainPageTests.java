package ua.com.rozetka.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ua.com.rozetka.pages.main.MainPage;

import javax.swing.*;

import java.io.*;
import java.util.Properties;

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


    //.shouldHave(Condition.attribute("title", "Ноутбуки с SSD")).click();


        Selenide.sleep(5000);


    }

    @Test @Ignore
    public void  headerTest(){
       // $("input.rz-header-search-input-text").shouldBe(Condition.appear);
        $("input.rz-header-search-input-text").sendKeys("Lenovo");
        log.info("sendKeys Lenovo");

        $("div.rz-header-search-suggest-g").shouldBe(Condition.visible);

        ElementsCollection items = $$("div.rz-header-search-suggest-i");

        for (SelenideElement element : items) {
            if (element.getText().contains("Мобильные телефоны")){
                element.click();
                break;
            }
        }

                Selenide.sleep(5000);
    }
/*
   @Test
    public void headerTestHW(){
        SelenideElement searchField = page.getHeader().
       // SelenideElement searchField = page.getHeader().getSearch().getTextField;
       // SelenideElement searchBtn = page.getHeader().getSearch().getOkButton;

      //  searchField.setValue("Lenovo");
      //  searchBtn.click();

       // searchResultPage.shouldBe(Condition.exist);
    }*/








   /* @Test @Ignore
    public void shouldAppearCityPopUpOnCityRefClick()throws InterruptedException{
        //given
        webDriver.get(siteAddress);
        String cityRef = page.getHeader().getCityRef();
        WebElement cityRefElem = webDriver.findElement(By.cssSelector(cityRef));

        //when
        Thread.sleep(3000);
        cityRefElem.click();

        //then
        String cityChoosePopup = page.getHeader().getCityChoosePopUp();
        WebElement popUpElem = webDriver.findElement(By.cssSelector(cityChoosePopup));

        assertTrue("Блок с названиями городов не появился!", popUpElem.isDisplayed());


    }
*/
    /*@AfterClass
    public static void close(){
        webDriver.close();
    }*/
}
