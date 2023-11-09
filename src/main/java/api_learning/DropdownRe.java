package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import url.Urls;

public class DropdownRe implements Urls {
    public static void main(String[] args) {

        // Get chrome section:
        WebDriver driver = DriverFactory.getChromeDriver();

        //Navigate to target base:
        try {
            driver.get(baseUrl.concat(dropdownSlug));
            // Locate dropdown
            By dropdown = By.id("dropdown");
            WebElement dropdownEle = driver.findElement(dropdown);



            // Define selector
            Select select = new Select(dropdownEle);

            select.selectByVisibleText("Option 1");
            Thread.sleep(2000);
            select.selectByValue("2");
            Thread.sleep(2000);
            select.selectByIndex(1);
            Thread.sleep(2000);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
