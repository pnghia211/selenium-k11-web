package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitMoreThanOneTab;
import url.Urls;

import java.time.Duration;

public class ExplicitWait implements Urls {


    public static void main(String[] args) {

        // Get chrome section:
        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(baseUrl.concat("/login"));

        try {
            By testSel = By.cssSelector("#test");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(testSel));
//            wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(testSel)));
            wait.until(new WaitMoreThanOneTab());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
