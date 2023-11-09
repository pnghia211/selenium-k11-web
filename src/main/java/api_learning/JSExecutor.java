package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class JSExecutor implements Urls {
    public static void main(String[] args) {

        // Get chrome section:
        WebDriver driver = DriverFactory.getChromeDriver();

        //Navigate to target base:
        try {
            driver.get(baseUrl.concat(floatingMenuSlug));

            // Scroll to bottom
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000);

            // Scroll to top
            javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight,0);");
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
