package test.footer;

import driver.DriverFactory;
import models.components.global.footer.InformationFooterComponent;
import models.pages.HomePage;
import models.products.ProductItemComponent;
import org.openqa.selenium.WebDriver;
import url.Urls;

import java.util.List;

public class FeatureProductTest {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            testFeatureProduct(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    private static void testFeatureProduct(WebDriver driver) {
        driver.get(Urls.demoPageUrl);
        HomePage homePage = new HomePage(driver);
        List<ProductItemComponent> productItemComp = homePage.productGridComp().productItemComps();
        productItemComp.forEach(productItem -> {
            System.out.println(productItem.productTitleElem().getText());
        });
    }
}



