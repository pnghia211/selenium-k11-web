package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormInteraction {
    public static void main(String[] args) {

        // Get a chrome section
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            // Define selector values
            By usernameSle = By.id("username");
            By passwordSle = By.cssSelector("#password");
            By loginBtnSle = By.cssSelector("[type='submit']");
            By LoginFormField = By.tagName("inputs");

            // Find elements
            List<WebElement> LoginFormFieldsEle = driver.findElements(LoginFormField);
            WebElement loginBtnEle = driver.findElement(loginBtnSle);

            // Get attribute values
            System.out.println(loginBtnEle.getAttribute("type"));
            System.out.println(loginBtnEle.getCssValue("background-color"));

            // Interaction
            LoginFormFieldsEle.get(0).sendKeys("tomsmith");
            LoginFormFieldsEle.get(1).sendKeys("SuperSecretPassword!");
            driver.findElement(loginBtnSle).click();


            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Close browser
        driver.quit();
    }
}
