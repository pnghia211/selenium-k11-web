package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import test_data.CreditCard;

@ComponentCssSelector("#checkout-step-payment-info")
public class PaymentInformationComponent extends Component {

    private static final By creditCardSel = By.cssSelector("#CreditCardType");
    private static final By cardHolderNameSel = By.cssSelector("#CardholderName");
    private static final By cardNumberSel = By.cssSelector("#CardNumber");
    private static final By monthSel = By.cssSelector("#ExpireMonth");
    private static final By yearSel = By.cssSelector("#ExpireYear");
    private static final By cardCodeSel = By.cssSelector("#CardCode");
    private static final By PONumSel = By.cssSelector("#PurchaseOrderNumber");
    private static final By verifyCODSel = By.cssSelector(".info");
    private static final By verifyMoneyOrderSel = By.cssSelector(".info p b");
    private static final By continueBTNSel = By.cssSelector(".payment-info-next-step-button");


    public PaymentInformationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCreditCard(CreditCard creditCard) {
        WebElement creditCardEle = component.findElement(creditCardSel);
        Select select = new Select(creditCardEle);

        if (creditCard == null) {
            throw new IllegalArgumentException("[ERR] credit card cannot be null!!!");
        }
        switch (creditCard) {
            case VISA -> select.selectByVisibleText("Visa");
            case DISCOVER -> select.selectByVisibleText("Discover");
            case MASTER_CARD -> select.selectByVisibleText("Master card");
            default -> select.selectByVisibleText("Amex");
        }
    }

    public void inputCardHolderName(String cardHolderName) {
        component.findElement(cardHolderNameSel).sendKeys(cardHolderName);
    }

    public void inputCardNumber(String cardNumber) {
        component.findElement(cardNumberSel).sendKeys(cardNumber);
    }

    public void inputMonth(String month) {
        Select select = new Select(component.findElement(monthSel));
    }

    public void inputMYear(String year) {
        Select select = new Select(component.findElement(yearSel));
    }

    public void inputMCardCode(String cardCode) {
        component.findElement(cardCodeSel).sendKeys(cardCode);
    }

    public void inputPONumber(String PONumber) {
        component.findElement(PONumSel).sendKeys(PONumber);
    }

    public void verifyCOD() {
        String codVerify = component.findElement(verifyCODSel).getText();
        if (!codVerify.equals("You will pay by COD")) {
            System.out.println("Verify COD not successfully!!!");
        }
    }

    public void verifyMoneyOrder() {
        String codVerify = component.findElement(verifyMoneyOrderSel).getText();
        if (!codVerify.startsWith("Tricentis GmbH")) {
            System.out.println("Verify Money Order not successfully!!!");
        }
    }

    public void clickContinueBTN() {
        component.findElement(continueBTNSel).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(continueBTNSel));
    }

}
