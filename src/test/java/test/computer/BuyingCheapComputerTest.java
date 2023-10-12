package test.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.Test;
import test.global.BaseTest;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTest implements Urls {
    @Test
    public void testCheapComputerBuying() {
        driver.get(demoPageUrl + "/build-your-cheap-own-computer");
        OrderComputerFlow<CheapComputerComponent> cheapComputerComp = new OrderComputerFlow<>(driver, CheapComputerComponent.class);
        cheapComputerComp.buildComputerAndAddToCart();

        try {
            Thread.sleep(3000);
        } catch (Exception ignored) {
        }
    }
}
