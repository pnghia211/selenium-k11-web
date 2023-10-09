package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import support.ui.SelectEx;
import url.Urls;

public class Dropdown implements Urls {
    public static void main(String[] args) {

        // Get chrome section:
        WebDriver driver = DriverFactory.getChromeDriver();

        //Navigate to target base:
        try {
            driver.get(baseUrl.concat(dropdownSlug));
        // Locate dropdown
            By dropdownSle = By.id("dropdown");
            WebElement dropdownEle = driver.findElement(dropdownSle);

        // Select dropdown
            SelectEx select = new SelectEx(dropdownEle);

        // Select visible text | Option 1
            select.selectByVisibleText("Option 1");
            Thread.sleep(2000);

        // Select by index | Option 2
            select.selectByIndex(2);
            Thread.sleep(2000);

        // Select by value | Option 3
            select.selectByValue("1");
            Thread.sleep(2000);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
