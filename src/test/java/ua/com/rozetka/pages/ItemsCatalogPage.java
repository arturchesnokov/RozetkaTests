package ua.com.rozetka.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class ItemsCatalogPage {
  private Logger logger = Logger.getLogger(ItemsCatalogPage.class);

  private Header header = new Header();
  private CompareListPage compareListPage = new CompareListPage();

  public void selectItemsForCompare(int numberOfComparableItems){
    List<SelenideElement> items = $$("div.g-i-tile-i-box");

    if (!items.isEmpty()) {
      logger.debug("Item List in catalog not empty. OK");
      for (int i = 0; i < numberOfComparableItems; i++) {
        SelenideElement element = items.get(i);
        element.$("div.g-i-tile-i-image").hover();
        element.$("span.g-compare").click();
        Selenide.sleep(500);
      }
    } else {
      logger.error("No elements for compare");
    }
    items = null;
  }

  public Header getHeader(){
    logger.debug("Get Header block");
    return header;
  }

  public CompareListPage getCompareListPage(){
    logger.debug("Get Compare List");
    return compareListPage;
  }
}
