package ua.com.rozetka.pages;

import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CompareItemsPage {
  private Logger logger = Logger.getLogger(CompareItemsPage.class);

  public int getDiffCountOnAllParametersTab(){
    if ($(By.linkText("Все параметры")).exists())
      $(By.linkText("Все параметры")).click();

    List<SelenideElement> params = $$("div.comparison-t-row");
    int allTabDiffCounter = 0;
    for (SelenideElement row:params) {
      List<SelenideElement> cells = row.$$("div.comparison-t-cell");
      Set<String> cellsValues = new HashSet<String>();
      for (SelenideElement cell:cells){
        if (cell.$("img").exists()){
          cellsValues.add(cell.$("img").getAttribute("src"));
        } else if (cell.$("span.chars-value-inner").exists()){
          cellsValues.add(cell.$("span.chars-value-inner").text());
        }
      }
      if (cellsValues.size() > 1){
        allTabDiffCounter++;
      }
      cellsValues = null;
    }
    return allTabDiffCounter;
  }

  public int getDiffCountOnOnlyDifferencesTab(){
    if ($(By.linkText("Только отличия")).exists())
      $(By.linkText("Только отличия")).click();

    List<SelenideElement> onlyDiffRows = $("div.comparison-t")
            .$$("div.comparison-t-cell-first");
    int onlyDiffTabCounter = 0;

    for (SelenideElement diffRow : onlyDiffRows) {
      if (diffRow.isDisplayed()){
        onlyDiffTabCounter++;
      }
    }
    return onlyDiffTabCounter;
  }



}
