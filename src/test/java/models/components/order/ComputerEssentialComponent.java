package models.components.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ComputerEssentialComponent extends BaseItemDetailsComponent {

    private static final By inputOptionSel = By.cssSelector(".option-list input");

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);

    public abstract String selectRAMType(String type);

    public void unSelectDefaultOption () {
        List<WebElement> optionEle = component.findElements(inputOptionSel);
        optionEle.forEach(option -> {
            if(option.getAttribute("checked") != null) {
                option.click();
            }
        });
    }

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
