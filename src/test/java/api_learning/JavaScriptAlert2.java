package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import url.Urls;

import java.sql.Driver;
import java.time.Duration;

public class JavaScriptAlert2 implements Urls {
    private static final By alertSel = By.cssSelector("[onclick=\"jsAlert()\"]");
    private static final By confirmSel = By.cssSelector("[onclick=\"jsConfirm()\"]");
    private static final By promptSel = By.cssSelector("[onclick=\"jsPrompt()\"]");
    private static final By resultSel = By.id("result");

    public static void main(String[] args) {

        // Get chrome section:
        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(baseUrl.concat(JSAlertSlug));
        Alert alert;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            // Handles Alert
//            WebElement alertEle = driver.findElement(alertSel);
//            alertEle.click();
            HandlesAlert(driver,alertSel,false);
            System.out.println(driver.findElement(resultSel).getText());

            // Handles Confirm
            driver.findElement(confirmSel).click();
            alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            System.out.println(driver.findElement(resultSel).getText());

            //Handles Prompt
            WebElement promptEle = driver.findElement(promptSel);
            promptEle.click();
            alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.sendKeys("Hello cac ban");
            alert.accept();
            System.out.println(driver.findElement(resultSel).getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void HandlesAlert(WebDriver driver, By eleSel, Boolean isAccept) {
        WebElement webElement = driver.findElement(eleSel);
        webElement.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        if(isAccept) alert.accept();
        else alert.dismiss();
    }
}
