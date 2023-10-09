package models.pages;

import models.components.Component;
import models.components.global.footer.FooterComponent;
import models.components.global.header.TopMenuComponent;
import models.products.ProductGridComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage extends Component {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver, driver.findElement(By.tagName("html")));
        this.driver = driver;
    }

    public TopMenuComponent topMenuComponent() {
        return findComponent(TopMenuComponent.class, driver);
    }

    public ProductGridComponent productGridComp() {
        return findComponent(ProductGridComponent.class, driver);
    }

    public FooterComponent footerComp() {
        return findComponent(FooterComponent.class, driver);
    }
}
