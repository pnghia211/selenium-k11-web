package models.components.order;

import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@ComponentCssSelector(value = ".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent {

    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    private static final By productAttributeSel = By.cssSelector("select[id^=\"product_attribute\"]");

    @Override
    public String selectProcessorType(String type) {
        final int PROCESSOR_DROPDOWN_INDEX = 0;
        WebElement processorEle = component.findElements(productAttributeSel).get(PROCESSOR_DROPDOWN_INDEX);
        return selectType(processorEle,type);
    }

    @Override
    public String selectRAMType(String type) {
        final int RAM_DROPDOWN_INDEX = 1;
        WebElement RAMEle = component.findElements(productAttributeSel).get(RAM_DROPDOWN_INDEX);
        return selectType(RAMEle,type);
    }

    private String selectType (WebElement dropdownEle, String type) {
        String fullOptionString = null;
        Select select = new Select(dropdownEle);
        List<WebElement> allOptions = select.getOptions();
        for (WebElement option : allOptions) {
            String currentOptionText = option.getText();
            String textWithoutSpaces = currentOptionText.trim().replace(" ","");
            if(textWithoutSpaces.startsWith(type)) {
                fullOptionString = currentOptionText;
            }
        }
        if(fullOptionString == null) {
            throw new RuntimeException("[ERR] the option " + type + " is not exist!!");
        }

        select.selectByVisibleText(fullOptionString);
        return fullOptionString;
    }
}
