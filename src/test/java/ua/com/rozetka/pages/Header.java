package ua.com.rozetka.pages;

import com.codeborne.selenide.Condition;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$;

public class Header {
  private Logger logger = Logger.getLogger(Header.class);

  public void clickCompareButtonAfterCounterCheck(int numberOfComparableItems) {
    if ($("span.hub-i-count").shouldHave(Condition.text(String.valueOf(numberOfComparableItems))).exists()) {
      $("div#comparison").click();
    } else {
      logger.error("Number of selected item doesn't match to expected! Expected:" + numberOfComparableItems +
              ", Actual:" + Integer.parseInt($("span.hub-i-count").getText()));
    }
  }
}