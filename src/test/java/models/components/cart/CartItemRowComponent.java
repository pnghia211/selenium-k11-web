package models.components.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".cart-item-row")
public class CartItemRowComponent extends Component {

    private static By unitPriceSel = By.cssSelector(".product-unit-price");
    private static By unitQuantitySel= By.cssSelector(".qty-input");
    private static By unitSubTotalSel= By.cssSelector(".product-subtotal");

    public CartItemRowComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double getUnitPrice () {
        WebElement unitPriceEle = component.findElement(unitPriceSel);
        return Double.parseDouble(unitPriceEle.getText());
    }

    public double getUnitQuantity () {
        WebElement unitQuantityEle = component.findElement(unitQuantitySel);
        return Double.parseDouble(unitQuantityEle.getAttribute("value"));
    }

    public double getUnitSubTotal () {
        WebElement unitSubTotalEle = component.findElement(unitSubTotalSel);
        return Double.parseDouble(unitSubTotalEle.getText());
    }

}
