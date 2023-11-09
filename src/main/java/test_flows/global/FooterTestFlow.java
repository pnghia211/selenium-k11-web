package test_flows.global;

import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.FooterComponent;
import models.components.global.header.TopMenuComponent;
import models.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import url.Urls;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static models.components.global.header.TopMenuComponent.MainCatComp;
import static models.components.global.header.TopMenuComponent.SubCatComp;


public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyTopMenuCatComponent() {
        // Random pick main category component
        TopMenuComponent topMenuComponent = new BasePage(driver).topMenuComponent();
        List<MainCatComp> mainCatComps = topMenuComponent.getMainCatComp();
        if (mainCatComps.isEmpty()) {
            Assert.fail("[ERR] Main category is empty");
        }

        MainCatComp randomMainCatComp = mainCatComps.get(new SecureRandom().nextInt(mainCatComps.size()));
        String randomCatHref = randomMainCatComp.getMainHref().getAttribute("href");
        System.out.println(randomCatHref);
        // Get sub list
        List<SubCatComp> subCatComps = randomMainCatComp.getSubCatComps();

        // Logic
        if (subCatComps.isEmpty()) {
            randomMainCatComp.getComponent().click();
        } else {
            SubCatComp randomSubCatComp = subCatComps.get(new SecureRandom().nextInt(subCatComps.size()));
            randomCatHref = randomSubCatComp.getComponent().getAttribute("href");
            randomSubCatComp.getComponent().click();
        }

        // Wait for URL
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains(randomCatHref));

        // Verify Footer Comps
        verifyFooterComponent();
    }

    public void verifyFooterComponent() {
        BasePage basePage = new BasePage(driver);
        FooterComponent footerColumnComp = basePage.footerComp();
        verifyInformationColumn(footerColumnComp.informationFooterComp());
        verifyCustomServiceColumn(footerColumnComp.customerServiceComp());
        verifyAccountColumn(footerColumnComp.accountColumnComp());
        verifyFollowUsColumn(footerColumnComp.followUsColumnComp());
    }

    private void verifyInformationColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = Arrays.asList("Sitemap", "Shipping & Returns", "Privacy Notice", "Conditions of Use",
                "About us", "Contact us");
        List<String> expectedHref = Arrays.asList(Urls.demoPageUrl + "/sitemap", Urls.demoPageUrl + "/shipping-returns",
                Urls.demoPageUrl + "/privacy-policy", Urls.demoPageUrl + "/conditions-of-use", Urls.demoPageUrl +
                        "/about-us", Urls.demoPageUrl + "/contactus");
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHref);
    }

    private void verifyCustomServiceColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = Arrays.asList("Search", "News", "Blog", "Recently viewed products",
                "Compare products list", "New products");
        List<String> expectedHref = Arrays.asList(Urls.demoPageUrl + "/search", Urls.demoPageUrl + "/news",
                Urls.demoPageUrl + "/blog", Urls.demoPageUrl + "/recentlyviewedproducts", Urls.demoPageUrl +
                        "/compareproducts", Urls.demoPageUrl + "/newproducts");
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHref);
    }

    private void verifyAccountColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = Arrays.asList("My account", "Orders", "Addresses", "Shopping cart", "Wishlist");
        List<String> expectedHref = Arrays.asList(Urls.demoPageUrl + "/customer/info", Urls.demoPageUrl + "/customer/orders",
                Urls.demoPageUrl + "/customer/addresses", Urls.demoPageUrl + "/cart", Urls.demoPageUrl + "/wishlist");
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHref);
    }

    private void verifyFollowUsColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = Arrays.asList("Facebook", "Twitter", "RSS", "YouTube", "Google+");
        List<String> expectedHref = Arrays.asList("http://www.facebook.com/nopCommerce", "https://twitter.com/nopCommerce",
                "https://demowebshop.tricentis.com/news/rss/1", "http://www.youtube.com/user/nopCommerce", "https://plus.google.com/+nopcommerce");
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHref);
    }

    private void verifyFooterColumn(FooterColumnComponent footerColumnComponent, List<String> expectedLinkTexts,
                                    List<String> expectedHref) {
        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHref = new ArrayList<>();
        for (WebElement link : footerColumnComponent.linksElem()) {
            actualLinkTexts.add(link.getText().trim());
            actualHref.add(link.getAttribute("href"));
        }

        if (actualLinkTexts.isEmpty() || actualHref.isEmpty()) {
            Assert.fail("[ERR] hyperLinks or linkTexts is empty");
        }

        // Verify link text
        Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "[ERR] actual link text and expected are different");

        // Verify href
        Assert.assertEquals(actualHref, expectedHref, "[ERR] actual href and expected are different");

    }

}
