package test.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.global.BaseTest;
import test_data.BuilderDataObject;
import test_data.computer.ComputerData;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTest implements Urls {
    @Test(dataProvider = "computerData")
    public void testCheapComputerBuying(ComputerData computerData) {
        driver.get(demoPageUrl + "/build-your-cheap-own-computer");
        OrderComputerFlow<CheapComputerComponent> cheapComputerComp =
                new OrderComputerFlow<>(driver, CheapComputerComponent.class, computerData,1);

        cheapComputerComp.buildComputerAndAddToCart();
        cheapComputerComp.verifyCartItemRow();
        cheapComputerComp.agreeTOSAndClickCheckout();
        cheapComputerComp.inputBillingAddress();
        cheapComputerComp.inputShippingAddress();
        cheapComputerComp.inputShippingMethod();
    }



    @DataProvider
    public ComputerData[] computerData () {
        String fileLoc = "/src/test/java/test_data/computer/CheapComputerDataList.json";
        return BuilderDataObject.buildDataObject(fileLoc,ComputerData[].class);
    }

}
