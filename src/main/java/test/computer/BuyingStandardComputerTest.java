package test.computer;

import models.components.order.StandardComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.BuilderDataObject;
import test_data.CreditCard;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {
    @Test(dataProvider = "computerData")
    public void testStandardComputerBuying(ComputerData computerData) {
        WebDriver driver = getDriver();
        driver.get(demoPageUrl + "/build-your-own-computer");
        OrderComputerFlow<StandardComputerComponent> standardComputerComp =
                new OrderComputerFlow<>(driver, StandardComputerComponent.class, computerData, 1);

        standardComputerComp.buildComputerAndAddToCart();
        standardComputerComp.verifyCartItemRow();
        standardComputerComp.agreeTOSAndClickCheckout();
        standardComputerComp.inputBillingAddress();
        standardComputerComp.inputShippingAddress();
        standardComputerComp.inputShippingMethod();
        standardComputerComp.selectPaymentMethod(PaymentMethod.CHECK_MONEY_ORDER);
        standardComputerComp.inputPaymentInformation(CreditCard.AMEX);
        standardComputerComp.verifyBillingAddress();
        standardComputerComp.verifyTotalItem();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLoc = "/src/main/java/test_data/computer/StandardComputerDataList.json";
        return BuilderDataObject.buildDataObject(fileLoc, ComputerData[].class);
    }

}
