package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class iFrameRe implements Urls {
    public static void main(String[] args) {

        // Get chrome section:
        WebDriver driver = DriverFactory.getChromeDriver();

        //Navigate to target base:
        try {
            driver.get(baseUrl.concat(iframeSlug));

            // Locate iframe:
            By iframeSel = By.cssSelector("[id$='ifr']");
            WebElement iframeEle = driver.findElement(iframeSel);

            driver.switchTo().frame(iframeEle);

            WebElement subIframeEle = driver.findElement(By.id("tinymce"));
            subIframeEle.clear();
            subIframeEle.sendKeys("Tao la Nghia day");

            driver.switchTo().defaultContent();
            driver.findElement(By.linkText("Elemental Selenium")).click();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
