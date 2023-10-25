package models.components.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentCssSelector(value = ".cart-footer .totals")
public class TotalComponent extends Component {

    private static final By cartTotalKey = By.cssSelector(".cart-total-left");
    private static final By cartTotalValue = By.cssSelector(".cart-total-right");
    private static final By tableRowComponentSel = By.cssSelector("table tr");
    private static final By TOSSel = By.cssSelector("#termsofservice");
    private static final By checkoutBTNSel = By.cssSelector("#checkout");


    public TotalComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Map<String, Double> getMapTableRowComp() {
        List<WebElement> tableRowComp = component.findElements(tableRowComponentSel);
        Map<String, Double> mapTableRowComp = new HashMap<>();
        for (WebElement webElement : tableRowComp) {
            WebElement keyEle = webElement.findElement(cartTotalKey);
            WebElement valueEle = webElement.findElement(cartTotalValue);

            String cartTotalLeft = keyEle.getText().trim();
            double cartTotalRight = Double.parseDouble(valueEle.getText().trim());

            mapTableRowComp.put(cartTotalLeft,cartTotalRight);
        }
        return mapTableRowComp;
    }

    public void agreeTOS() {
        component.findElement(TOSSel).click();
    }

    public void clickCheckoutBTN () {
        component.findElement(checkoutBTNSel).click();
    }

}
