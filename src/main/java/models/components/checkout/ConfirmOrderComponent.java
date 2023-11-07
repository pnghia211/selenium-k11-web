package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector("#checkout-step-confirm-order")
public class ConfirmOrderComponent extends Component {

    private final static By actualFullNameSel = By.cssSelector(".billing-info .name");
    private final static By actualEmailSel = By.cssSelector(".billing-info .email");
    private final static By actualPhoneSel = By.cssSelector(".billing-info .phone");
    private final static By actualCityStateZipSel = By.cssSelector(".billing-info .city-state-zip");
    private final static By actualCountrySel = By.cssSelector(".billing-info .country");
    private final static By actualPaymentMethodSel = By.cssSelector(".billing-info .payment-method");
    private final static By continueBtnSel = By.cssSelector(".confirm-order-next-step-button");


    public ConfirmOrderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public String getActualFullName() {
        return component.findElement(actualFullNameSel).getText();
    }

    public String getActualEmail() {
        return component.findElement(actualEmailSel).getText();
    }

    public String getActualPhone() {
        return component.findElement(actualPhoneSel).getText();
    }


    public String getActualCityStateZip() {
        return component.findElement(actualCityStateZipSel).getText();
    }

    public String getActualCountry() {
        return component.findElement(actualCountrySel).getText();
    }

    public String getActualPayment () {
        return component.findElement(actualPaymentMethodSel).getText();
    }

    public void clickContinueBTN () {
        component.findElement(continueBtnSel).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(continueBtnSel));
    }
}
