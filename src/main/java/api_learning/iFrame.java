package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.ui.SelectEx;
import url.Urls;

public class iFrame implements Urls {
    public static void main(String[] args) {

        // Get chrome section:
        WebDriver driver = DriverFactory.getChromeDriver();

        //Navigate to target base:
        try {
            driver.get(baseUrl.concat(iframeSlug));

            // Locate iframe:
            By iframeSle = By.cssSelector("[id$='ifr']");
            WebElement iframeEle = driver.findElement(iframeSle);

            // Switch to iframe:
            driver.switchTo().frame(iframeEle);

            // Locate element inside iframe:
            WebElement subIframeEle = driver.findElement(By.id("tinymce"));
            subIframeEle.click();
            subIframeEle.clear();
            subIframeEle.sendKeys("This is new text ..........");
            Thread.sleep(2000);

            // Navigate back to default scope:
            driver.switchTo().defaultContent();
            driver.findElement(By.linkText("Elemental Selenium")).click();
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
