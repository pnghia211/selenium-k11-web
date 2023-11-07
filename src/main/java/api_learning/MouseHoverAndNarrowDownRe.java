package api_learning;

import driver.DriverFactory;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import url.Urls;

import java.util.List;

public class MouseHoverAndNarrowDownRe implements Urls {
    private final static By figureSel = By.cssSelector(".figure");
    private final static By profileNameSel = By.cssSelector(".figcaption h5");
    private final static By profileLinkSel = By.cssSelector(".figcaption a");

    public static void main(String[] args) {

        // Get chrome section:
        WebDriver driver = DriverFactory.getChromeDriver();
        //Navigate to target base:
        try {
            driver.get(baseUrl.concat(hoverSlug));
            List<WebElement> figureListEle = driver.findElements(figureSel);

            // Define actions
            Actions actions = new Actions(driver);

            for (WebElement figureEle : figureListEle) {
                WebElement figureName = figureEle.findElement(profileNameSel);
                WebElement figureLink = figureEle.findElement(profileLinkSel);

                // Before move hover
                System.out.println(figureName.getText() + figureName.isDisplayed());
                System.out.println(figureLink.getText() + figureLink.isDisplayed());

                // Move hover
                actions.moveToElement(figureEle).perform();

                // After move hover
                System.out.println(figureName.getText() + " : " + figureEle.isDisplayed());
                System.out.println(figureLink.getText() + " : " + figureLink.isDisplayed());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
