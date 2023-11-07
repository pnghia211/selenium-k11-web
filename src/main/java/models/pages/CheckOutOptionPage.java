package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CheckOutOptionPage extends BasePage {

    private final By checkoutBTNSel = By.cssSelector(".checkout-as-guest-button");

    public CheckOutOptionPage(WebDriver driver) {
        super(driver);
    }

    public void checkoutAsGuess() {
        component.findElement(checkoutBTNSel).click();
    }
}
