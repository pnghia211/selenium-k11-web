package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import url.Urls;

import java.util.List;

public class MouseHoverAndNarrowDown implements Urls {
    private final static By figureSel = By.cssSelector(".figure");
    private final static By profileNameSel = By.cssSelector(".figcaption h5");
    private final static By profileLinkSel = By.cssSelector(".figcaption a");

    public static void main(String[] args) {

        // Get chrome section:
        WebDriver driver = DriverFactory.getChromeDriver();

        //Navigate to target base:
        try {
            driver.get(baseUrl.concat(hoverSlug));

            List<WebElement> figuresEle = driver.findElements(figureSel);

            // Define Actions
            Actions actions = new Actions(driver);
            for (WebElement figureEle : figuresEle) {
                WebElement profileEle = figureEle.findElement(profileNameSel);
                WebElement profileLinkEle = figureEle.findElement(profileLinkSel);
                // BEFORE mouse hover
                System.out.println(profileEle.getText() + profileEle.isDisplayed());
                System.out.println(profileLinkEle.getText() + profileLinkEle.isDisplayed());

                // Mouse hover
                actions.moveToElement(figureEle).perform();

                // AFTER mouse hover
                System.out.println(profileEle.getText() + ": " + profileEle.isDisplayed());
                System.out.println(profileLinkEle.getText() + ": " + profileLinkEle.isDisplayed());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
