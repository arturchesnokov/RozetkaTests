package ua.com.rozetka.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainMenu {
  private Logger logger = Logger.getLogger(MainMenu.class);

  private CategoriesCatalogPage categoriesCatalogPage = new CategoriesCatalogPage();

  public void selectMainCategory(String mainMenuCategoryName){
    logger.debug("Hover cursor on main category in menu: " + mainMenuCategoryName);
    $(By.linkText(mainMenuCategoryName)).hover();
  }

  public void selectCategory(String menuCategoryName){
    logger.debug("Click on category in menu: " + menuCategoryName);
    $(By.linkText(menuCategoryName)).click();
  }

  public CategoriesCatalogPage getCategoriesCatalogPage(){
    logger.debug("Categories catalog page");
    return  categoriesCatalogPage;
  }

}
