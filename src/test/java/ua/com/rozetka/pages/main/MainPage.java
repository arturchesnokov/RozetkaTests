package ua.com.rozetka.pages.main;

import com.codeborne.selenide.SelenideElement;
import ua.com.rozetka.pages.main.header.HeaderBlock;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private HeaderBlock header = new HeaderBlock();

    public SelenideElement getHeader(){
        SelenideElement eHeader = $("header.body-header");

        return eHeader.$("div.wrap");
    }
}
