package models.components.global.footer;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".column.customer-service")
public class CustomerServiceComponent extends FooterColumnComponent{
    public CustomerServiceComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
