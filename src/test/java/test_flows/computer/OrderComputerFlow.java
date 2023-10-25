package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.checkout.ShippingAddressComponent;
import models.components.checkout.ShippingMethodComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckOutOptionPage;
import models.pages.CheckOutPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.BuilderDataObject;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {
    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;
    private final int quantity;
    private double totalPrice;
    private double checkoutSubToTal;
    private double checkoutTotal;
    private double checkoutFees;
    private UserDataObject userDataObject;


    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent,
                             ComputerData computerData, Integer quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildComputerAndAddToCart() {
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerEssentialComp(computerEssentialComponent);

        // Un select default option
        computerEssentialComp.unSelectDefaultOption();

        String selectProcessor = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double additionalProcessorPrice = getAdditionalPrice(selectProcessor);
        String selectRAM = computerEssentialComp.selectRAMType(computerData.getRam());
        double additionalRAMPrice = getAdditionalPrice(selectRAM);
        String selectHDD = computerEssentialComp.selectCompOption(computerData.getHdd());
        double additionalHDDPrice = getAdditionalPrice(selectHDD);
        String selectSoftware = computerEssentialComp.selectCompOption(computerData.getSoftware());
        double additionalSoftwarePrice = getAdditionalPrice(selectSoftware);
        double additionalOSPrice = 0;
        if (computerData.getOs() != null) {
            String selectOS = computerEssentialComp.selectCompOption(computerData.getOs());
            additionalOSPrice = getAdditionalPrice(selectOS);
        }

        double basePrice = computerEssentialComp.productPrice();
        double totalAdditionalPrice = additionalProcessorPrice + additionalRAMPrice + additionalHDDPrice
                + additionalOSPrice + additionalSoftwarePrice;
        totalPrice = basePrice + totalAdditionalPrice;

        // Add to cart
        computerEssentialComp.clickAddToCart();
        // Wait for notification bar
        computerEssentialComp.waitForNotificationBar();
        // Navigate and click
        computerItemDetailsPage.headerComponent().clickShoppingCartBTN();
        try {
            Thread.sleep(3000);
        } catch (Exception ignored) {
        }
    }


    private double getAdditionalPrice(String itemType) {
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemType);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1));
        }
        return price;
    }

    public void verifyCartItemRow() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowComps = shoppingCartPage.cartItemRowComponentComp();
        if (cartItemRowComps.isEmpty()) {
            Assert.fail("[ERR] No item to display!!!");
        }
        double unitPrice = 0;
        double unitSubTotal = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemRowComps) {
            unitPrice += cartItemRowComp.getUnitPrice();
            unitSubTotal += cartItemRowComp.getUnitSubTotal();

            Assert.assertEquals(unitPrice * cartItemRowComp.getUnitQuantity(), unitSubTotal,
                    "[ERR] Unit Sub Total is not correct!!!");
        }

        Map<String, Double> totalMapComps = shoppingCartPage.totalComp().getMapTableRowComp();
        double checkoutSubTotal = 0;
        double additionalFees = 0;
        double checkoutTotal = 0;
        for (String key : totalMapComps.keySet()) {
            if (key.startsWith("Sub-Total")) {
                checkoutSubTotal += totalMapComps.get(key);
            } else if (key.startsWith("Total")) {
                checkoutTotal += totalMapComps.get(key);
            } else {
                additionalFees += totalMapComps.get(key);
            }
        }
        Assert.assertEquals(unitSubTotal, checkoutSubTotal,
                "[ERR] Check out sub total is not correct!!!");
        Assert.assertEquals(checkoutSubTotal + additionalFees, checkoutTotal,
                "[ERR] Check out total is not correct!!!");
    }

    public void agreeTOSAndClickCheckout() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComp().agreeTOS();
        shoppingCartPage.totalComp().clickCheckoutBTN();
        new CheckOutOptionPage(driver).checkoutAsGuess();
    }

    public void inputBillingAddress() {
        String defaultUserCheckoutJSONLoc = "/src/test/java/test_data/DefaultCheckoutUser.json";
        userDataObject = BuilderDataObject.buildDataObject(defaultUserCheckoutJSONLoc, UserDataObject.class);
        BillingAddressComponent billingAddressComp = new CheckOutPage(driver).billingAddressComp();
        billingAddressComp.selectNewAddress()
                .inputFirstName(userDataObject.getFirstName())
                .inputLastName(userDataObject.getLastName())
                .inputEmail(userDataObject.getEmail())
                .selectCountry(userDataObject.getCountry())
                .selectState(userDataObject.getState())
                .inputCity(userDataObject.getCity())
                .inputAddress1(userDataObject.getAdd1())
                .inputAddress2(userDataObject.getAdd2())
                .inputZIPCode(userDataObject.getZipCode())
                .inputPhone(userDataObject.getPhoneNum())
                .clickContinueBtn();
    }

    public void inputShippingAddress() {
        ShippingAddressComponent shippingAddressComp = new CheckOutPage(driver).shippingAddressComp();
        shippingAddressComp.clickContinueBtn();
    }

    public void inputShippingMethod() {
        ShippingMethodComponent shippingAddressComp = new CheckOutPage(driver).shippingMethodComp();
        List<String> methodNameList = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        String methodNameStr = methodNameList.get(new SecureRandom().nextInt(methodNameList.size()));
        shippingAddressComp.selectMethod(methodNameStr);
        shippingAddressComp.clickContinueBtn();
    }



}



