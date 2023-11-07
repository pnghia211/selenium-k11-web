package support.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitForElementEnable implements ExpectedCondition<Boolean> {

    WebElement webElement;

    public WaitForElementEnable(WebElement webElement) {
        this.webElement = webElement;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        return webElement.isEnabled();
    }

    @Override
    public String toString() {
        return "Element located by" + this.webElement.toString();
    }
}
