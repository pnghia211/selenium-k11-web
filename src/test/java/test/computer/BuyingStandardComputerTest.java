package test.computer;

import models.components.order.StandardComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.global.BaseTest;
import test_data.BuilderDataObject;
import test_data.computer.ComputerData;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {
    @Test(dataProvider = "computerData")
    public void testStandardComputerBuying(ComputerData computerData) {
        driver.get(demoPageUrl + "/build-your-own-computer");
        OrderComputerFlow<StandardComputerComponent> standardComputerComp =
                new OrderComputerFlow<>(driver, StandardComputerComponent.class, computerData,1);

        standardComputerComp.buildComputerAndAddToCart();
        standardComputerComp.verifyCartItemRow();
        standardComputerComp.agreeTOSAndClickCheckout();

    }

    @DataProvider
    public ComputerData[] computerData () {
        String fileLoc = "/src/test/java/test_data/computer/StandardComputerDataList.json";
        return new BuilderDataObject().buildDataObject(fileLoc, ComputerData[].class);
    }

}
