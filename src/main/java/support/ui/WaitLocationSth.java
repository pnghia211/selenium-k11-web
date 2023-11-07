package support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitLocationSth implements ExpectedCondition<Boolean> {
    private By eleSel;

    public WaitLocationSth(By eleSel) {
        this.eleSel = eleSel;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        return driver.findElement(eleSel).getText().equals("123");
    }
}
