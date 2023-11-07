package support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitForBylocatortEnable implements ExpectedCondition<Boolean> {

    By eleSel;

    public WaitForBylocatortEnable(By eleSel) {
        this.eleSel = eleSel;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        return driver.findElement(eleSel).isEnabled();
    }

    @Override
    public String toString() {
        return "Element located by" + this.eleSel.toString();
    }
}
