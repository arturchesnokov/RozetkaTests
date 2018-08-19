package ua.com.rozetka.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainMenu {
  private Logger logger = Logger.getLogger(MainMenu.class);

  private CategoriesCatalogPage categoriesCatalogPage = new CategoriesCatalogPage();

  public void selectMainCategory(String mainMenuCategoryName){
    $(By.linkText(mainMenuCategoryName)).hover();
    logger.info("Hover cursor on main category in menu: " + mainMenuCategoryName + ". OK");
  }

  public void selectCategory(String menuCategoryName){
    $(By.linkText(menuCategoryName)).click();
    logger.info("Click on category in menu: " + menuCategoryName + ". OK");
  }

  public CategoriesCatalogPage getCategoriesCatalogPage(){
    logger.info("Get Categories catalog page");
    return  categoriesCatalogPage;
  }

}
