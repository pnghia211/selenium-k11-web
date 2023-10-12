package test.computer;

import models.components.order.StandardComputerComponent;
import org.testng.annotations.Test;
import test.global.BaseTest;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {
    @Test
    public void testStandardComputerBuying() {
        driver.get(demoPageUrl + "/build-your-own-computer");
        OrderComputerFlow<StandardComputerComponent> standardComputerComp = new OrderComputerFlow<>(driver, StandardComputerComponent.class);
        standardComputerComp.buildComputerAndAddToCart();
        try {
            Thread.sleep(3000);
        } catch (Exception ignored) {
        }
    }
}
