package ua.com.rozetka.pages;

import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$;

public class Header {
  private Logger logger = Logger.getLogger(Header.class);

  public void clickCompareButtonAfterCounterCheck(int numberOfComparableItems) {
    if ($("span.hub-i-count").exists()) {
      if (Integer.parseInt($("span.hub-i-count").getText()) == numberOfComparableItems) {
        $("div#comparison").click();
      } else {
        logger.error("Number of selected item doesn't match to expected");
      }
    } else {
      logger.error("No selected items to compare");
    }
  }
}
