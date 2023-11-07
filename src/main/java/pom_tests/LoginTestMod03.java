package pom_tests;

import driver.DriverFactory;
import models.components.LoginFormComponents;
import models.pages.LoginPageMod02;
import models.pages.LoginPageMod03;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestMod03 implements Urls {



    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            LoginPageMod03 loginPage = new LoginPageMod03(driver);
            LoginFormComponents loginFormComponents = new LoginFormComponents(driver);
            loginFormComponents.inputUsername("taolaobidao");
            loginFormComponents.inputPassword("taolaobidao");
            loginFormComponents.clickLoginBTN();

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

