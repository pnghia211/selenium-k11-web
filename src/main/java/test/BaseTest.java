package test.global;

import driver.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {

    private static final List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String browser;

    protected WebDriver getDriver() {
        return driverThread.get().getDriver(browser);
    }

    @BeforeTest(description = "Init browser session")
    @Parameters({"browser"})
    public void initBrowserSession(String browser) {
        this.browser = browser;
        driverThread = ThreadLocal.withInitial(() -> {
                    DriverFactory webDriverThread = new DriverFactory();
                    webDriverThreadPool.add(webDriverThread);
                    return webDriverThread;
                }
        );
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowserSession() {
        driverThread.get().getDriver(browser).quit();
    }


    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // testMethodName -yyyy-m-d-h-sec.png

            // 1. Get method name
            String methodName = result.getName();

            // 2. Get Taken Time
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String fileName = methodName + "-" + y + "-" + m + "-" + d + "-" + hr + "-" + min + "-" + sec + ".png";

            // 3. Take ScreenShot
            WebDriver driver = driverThread.get().getDriver(browser);
            File screenshotBase64Date = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // 4. Save and attach to allure reporter
            try {
                // 4.1 Save
                String fileLocation = System.getProperty("user.dir") + "/screenshots/" + fileName;
                FileUtils.copyFile(screenshotBase64Date, new File(fileLocation));

                // 4.2 Attach to report
                Path content = Paths.get(fileLocation);
                try (InputStream input = Files.newInputStream(content)) {
                    Allure.addAttachment(methodName, input);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
