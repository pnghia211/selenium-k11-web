package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import models.pages.CheckOutPage;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test_data.PaymentMethod;

@ComponentCssSelector("#checkout-step-payment-method")
public class PaymentMethodComponent extends Component {

    private static final By codSel = By.cssSelector("[value=\"Payments.CashOnDelivery\"]");
    private static final By moneyOrderSel = By.cssSelector("[value=\"Payments.CheckMoneyOrder\"]");
    private static final By creditCardSel = By.cssSelector("[value=\"Payments.Manual\"]");
    private static final By purchaseOrderSel = By.cssSelector("[value=\"Payments.PurchaseOrder\"]");
    private static final By continueBTNSel = By.cssSelector(".payment-method-next-step-button");

    public PaymentMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCODMethod () {
        component.findElement(codSel).click();
    }

    public void selectMoneyOrderMethod () {
        component.findElement(moneyOrderSel).click();
    }

    public void selectCreditCardMethod () {
        component.findElement(creditCardSel).click();
    }

    public void selectPurchaseOrderMethod () {
        component.findElement(purchaseOrderSel).click();
    }

    public void clickContinueBTN () {
        component.findElement(continueBTNSel).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(continueBTNSel));
    }
}
