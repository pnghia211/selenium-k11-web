package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageMod01 {

    private final WebDriver driver;
    private final static By usernameSle = By.id("username");
    private final static By passwordSle = By.cssSelector("#password");
    private final static By loginBtnSle = By.cssSelector("[type='submit']");

    public LoginPageMod01(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement username() {
        return driver.findElement(usernameSle);
    }

    public WebElement password() {
        return driver.findElement(passwordSle);
    }

    public void loginBTN() {
        driver.findElement(loginBtnSle).click();
    }
}

