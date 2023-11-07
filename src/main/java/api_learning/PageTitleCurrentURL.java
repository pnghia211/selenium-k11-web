 package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageTitleCurrentURL {
    public static void main(String[] args) {

        // Get a chrome section
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            // Define selector values
            By powerByLinkTestSel = By.partialLinkText(" Selenium");


            // Find elements
            WebElement powerByLinkTestEle = driver.findElement(powerByLinkTestSel);

            // Interaction
//            driver.findElement(powerByLinkTestSel).click();
            System.out.println(driver.getTitle());
            System.out.println(driver.getCurrentUrl());


            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Close browser
        driver.quit();
    }
}
