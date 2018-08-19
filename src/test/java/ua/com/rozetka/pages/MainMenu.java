package ua.com.rozetka.pages;

import com.codeborne.selenide.Condition;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainMenu {
  private Logger logger = Logger.getLogger(MainMenu.class);

  private CategoriesCatalogPage categoriesCatalogPage = new CategoriesCatalogPage();

  public void selectMainCategory(String mainMenuCategoryName, String menuCategoryName) {
    $(By.linkText(mainMenuCategoryName)).hover();
    logger.info("Hover cursor on main category in menu: " + mainMenuCategoryName + ". OK");
    if ($(By.linkText(menuCategoryName)).exists()) {
      $(By.linkText(menuCategoryName)).click();
    } else {
      paymentPopupClose();
      $(By.linkText(mainMenuCategoryName)).hover();
      $(By.linkText(menuCategoryName)).click();
      logger.info("Click on category in menu: " + menuCategoryName + ". OK");
    }
  }

  public CategoriesCatalogPage getCategoriesCatalogPage() {
    logger.info("Get Categories catalog page");
    return categoriesCatalogPage;
  }

  private void paymentPopupClose() {
    if ($("a.exponea-banner").exists()) {
      $("span.exponea-close-cross").click();
    }
  }
}
