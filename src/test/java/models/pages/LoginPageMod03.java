package models.pages;

import models.components.LoginFormComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageMod03 {

    private final WebDriver driver;

    public LoginPageMod03(WebDriver driver) {
        this.driver = driver;
    }
    public LoginFormComponents loginFormComp () {
        return new LoginFormComponents(driver);
    }
}

