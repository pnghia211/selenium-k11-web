package models.components.global.header;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@ComponentCssSelector(value = ".top-menu")
public class TopMenuComponent extends Component {

    public TopMenuComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<MainCatComp> getMainCatComp() {
        return findComponents(MainCatComp.class, driver);
    }

    @ComponentCssSelector(value = ".top-menu > li")
    public static class MainCatComp extends Component {
        public MainCatComp(WebDriver driver, WebElement component) {
            super(driver, component);
        }

        public List<SubCatComp> getSubCatComps() {
            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();
            return findComponents(SubCatComp.class, driver);
        }

        public WebElement getMainHref () {
            return component.findElement(By.tagName("a"));
        }
    }

    @ComponentCssSelector(value = ".sublist li a")
    public static class SubCatComp extends Component {
        public SubCatComp(WebDriver driver, WebElement component) {
            super(driver, component);
        }
    }
}
