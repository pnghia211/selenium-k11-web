package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMod01;
import models.pages.LoginPageMod02;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTestMod02 {

    public static void main(String[] args) {

        // Get a chrome section
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            LoginPageMod02 loginPage = new LoginPageMod02(driver);
            loginPage.inputUsername("taolaobidao");
            loginPage.inputPassword("taolaobidao");
            loginPage.clickLoginBTN();

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
