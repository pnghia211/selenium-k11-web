package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector("#checkout-step-shipping")
public class ShippingAddressComponent extends Component {

    private final By continueBtnSel = By.cssSelector(".new-address-next-step-button");

    public ShippingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickContinueBtn () {
        WebElement continueBtn = component.findElement(continueBtnSel);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
    }
}
