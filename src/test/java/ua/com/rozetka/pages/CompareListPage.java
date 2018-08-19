package ua.com.rozetka.pages;

import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$;

public class CompareListPage {
  private Logger logger = Logger.getLogger(CompareListPage.class);

  private CompareItemsPage compareItemsPage = new CompareItemsPage();

  public void clickCompareButton(){
    logger.debug("Check and click Compare button on the compare list page");
    if ($("div.btn-link-to-compare").$("span.btn-link-i").exists()){
      $("div.btn-link-to-compare").$("span.btn-link-i").click();
    } else {
      logger.error("Compare button doesn't exist on compare list page");
    }
  }

  public CompareItemsPage getCompareItemsPage(){
    logger.debug("Get compare Items page");
    return compareItemsPage;
  }


}

