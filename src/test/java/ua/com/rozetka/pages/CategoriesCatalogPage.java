package ua.com.rozetka.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CategoriesCatalogPage {
  private Logger logger = Logger.getLogger(CategoriesCatalogPage.class);

  private ItemsCatalogPage itemsCatalogPage = new ItemsCatalogPage();

  public void selectItemsCategory(String itemsCategory){
    $(By.linkText(itemsCategory)).click();
    logger.info("Click on items category: " + itemsCategory + ". OK");
  }

  public ItemsCatalogPage getItemsCatalogPage(){
    logger.debug("Items catalog page");
    return  itemsCatalogPage;
  }

}
