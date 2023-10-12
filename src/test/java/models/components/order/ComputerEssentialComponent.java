package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ComputerEssentialComponent extends Component {
    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);

    public abstract String selectRAMType(String type);

    public String selectCompOption(String type) {
        String selectorString = "//label[contains(text(),\"" + type + "\")]";
        By optionSel = By.xpath(selectorString);
        WebElement optionEle = null;
        try {
            optionEle = component.findElement(optionSel);
        } catch (Exception ignored) {}

        if (optionEle != null) {
            optionEle.click();
            return optionEle.getText();
        } else {
            throw new RuntimeException("[ERR] the option " + type + " is not exist!!");
        }


//        List<WebElement> optionsEle = component.findElements(optionSel);
//        if (!optionsEle.isEmpty()) {
//            optionsEle.get(0).click();
//            return optionsEle.get(0).getText();
//        } else {
//            throw new RuntimeException("[ERR]");
//        }
    }
}
