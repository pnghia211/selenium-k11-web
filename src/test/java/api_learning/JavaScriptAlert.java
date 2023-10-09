package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import url.Urls;

import java.time.Duration;

public class JavaScriptAlert implements Urls {
    private final static By jsAlertSel = By.cssSelector("[onclick=\"jsAlert()\"]");
    private final static By jsConfirmSel = By.cssSelector("[onclick=\"jsConfirm()\"]");
    private final static By jsPromptSel = By.cssSelector("[onclick=\"jsPrompt()\"]");
    private final static By resultSel = By.cssSelector("#result");

    public static void main(String[] args) {

        // Get chrome section:
        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(baseUrl.concat(JSAlertSlug));
        Alert alert;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            //JS Alert
            handleAlert(driver, jsAlertSel,true);
            System.out.println("The result: " + driver.findElement(resultSel).getText());

            //JS Confirm
            handleAlert(driver,jsConfirmSel,true);
            System.out.println("The result: " + driver.findElement(resultSel).getText());

            //JS Prompt
            handleAlert(driver,jsPromptSel,"Hi my name is Nghia");
            System.out.println("The result: " + driver.findElement(resultSel).getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleAlert(WebDriver driver, By elemSel, boolean isAccepting) {
        Alert alert = getAlert(driver,elemSel);
        System.out.println("Alert content : " + alert.getText());
        if(isAccepting) alert.accept();
        else alert.dismiss();
    }

    public static void handleAlert(WebDriver driver, By elemSel, String promptContent) {
        Alert alert = getAlert(driver,elemSel);
        System.out.println("Alert content : " + alert.getText());
        alert.sendKeys(promptContent);
        alert.accept();
    }

    private static Alert getAlert (WebDriver driver, By elemSel) {
        WebElement jsAlertEle = driver.findElement(elemSel);
        jsAlertEle.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}
