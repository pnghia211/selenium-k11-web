package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseItemDetailsComponent extends Component {

    private static By productPriceSel = By.cssSelector(".product-price");
    private static By addToCartBtn = By.cssSelector("[id^='add-to-cart-button']");
    private static By notificationBar = By.id("bar-notification");

    public BaseItemDetailsComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double productPrice () {
        String productPRiceText = component.findElement(productPriceSel).getText().trim();
        return Double.parseDouble(productPRiceText);
    }

    public void clickAddToCart () {
        component.findElement(addToCartBtn).click();
    }

    public void waitForNotificationBar () {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated
                    (notificationBar, "The product has been added to your shopping cart"));
        } catch (TimeoutException e) {
            clickAddToCart();
        }
    }
}
