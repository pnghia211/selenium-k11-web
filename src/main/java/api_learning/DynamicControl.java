package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForBylocatortEnable;
import url.Urls;

import java.time.Duration;

public class DynamicControl implements Urls {
    public static void main(String[] args) {

        // Get chrome section:
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            //Navigate to target base:
            driver.get(baseUrl.concat(dynamicControlSlug));

            // Define parent locators
            By checkboxFormSel = By.id("checkbox-example");
            By inputFormSel = By.id("input-example");
            By message = By.id("message");

            // Checkbox form interaction
            WebElement checkboxFormEle = driver.findElement(checkboxFormSel);
            WebElement checkboxEle = checkboxFormEle.findElement(By.tagName("input"));
            if (!checkboxEle.isSelected()) checkboxEle.click();
            Thread.sleep(1000);

            // Input form interaction
            WebElement inputFormEle = driver.findElement(inputFormSel);
            WebElement inputEle = inputFormEle.findElement(By.tagName("input"));
            WebElement BTNEle = inputFormEle.findElement(By.tagName("button"));
            if (!inputEle.isEnabled()) BTNEle.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // 3 ways
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
//            wait.until(new WaitForElementEnable(inputEle));
            wait.until(new WaitForBylocatortEnable(By.cssSelector("#input-example input")));

            inputEle.sendKeys("Toi la Nghia dep trai");
            System.out.println(driver.findElement(message).getText());

            Thread.sleep(1000);

            // Debug purpose only
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
