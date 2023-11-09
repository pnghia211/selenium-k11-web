package test.footer;

import models.components.global.footer.FooterColumnComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTest extends BaseTest {

    @Test
    public void testFooterCategoryPage() {
        WebDriver driver = getDriver();
        driver.get(Urls.demoPageUrl);
//        Assert.fail("Demo taking screenshot when test is failed");
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyTopMenuCatComponent();
    }

    @Test
    public void testFooterHomePage() {
        WebDriver driver = getDriver();
        driver.get(Urls.demoPageUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test
    private static void testFooterRegisterPage() {
    }

    @Test
    private static void testFooterLoginPage() {
    }

    private static void testFooterColumn(FooterColumnComponent footerColumnComp) {
        System.out.println(footerColumnComp.headerElem().getText());
        footerColumnComp.linksElem().forEach(link -> {
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });
    }
}




