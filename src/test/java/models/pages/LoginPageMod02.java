package models.pages;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pom_tests.LoginTestMod02;

public class LoginPageMod02 {

    private final WebDriver driver;
    private final static By usernameSle = By.id("username");
    private final static By passwordSle = By.cssSelector("#password");
    private final static By loginBtnSle = By.cssSelector("[type='submit']");

    public LoginPageMod02(WebDriver driver) {
        this.driver = driver;
    }

    public void inputUsername (String usernameTxt) {
        driver.findElement(usernameSle).sendKeys(usernameTxt);
    }

    public void inputPassword (String passwordTxt) {
        driver.findElement(passwordSle).sendKeys(passwordTxt);
    }

    public void clickLoginBTN () {
        driver.findElement(loginBtnSle).click();
    }
}

