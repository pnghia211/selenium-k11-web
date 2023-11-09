package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.checkout.*;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckOutOptionPage;
import models.pages.CheckOutPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.BuilderDataObject;
import test_data.CreditCard;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.*;
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
    private PaymentMethod paymentMethod;
    private CreditCard creditCard;


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
        String defaultUserCheckoutJSONLoc = "/src/main/java/test_data/DefaultCheckoutUser.json";
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

    public void selectPaymentMethod() {
        this.paymentMethod = PaymentMethod.COD;
    }

    public void selectPaymentMethod(PaymentMethod paymentMethod) {
        PaymentMethodComponent paymentMethodComp = new CheckOutPage(driver).paymentMethodComp();
        this.paymentMethod = paymentMethod;

        switch (paymentMethod) {
            case COD -> paymentMethodComp.selectCODMethod();
            case CHECK_MONEY_ORDER -> paymentMethodComp.selectMoneyOrderMethod();
            case CREDIT_CARD -> paymentMethodComp.selectCreditCardMethod();
            default -> paymentMethodComp.selectPurchaseOrderMethod();
        }
        if (this.paymentMethod == null) {
            throw new IllegalArgumentException("[ERR] Payment method cant be null!!!");
        }
        paymentMethodComp.clickContinueBTN();
    }

    public void inputPaymentInformation(CreditCard creditCard) {
        PaymentInformationComponent paymentInformationComp = new CheckOutPage(driver).paymentInformationComp();
        this.creditCard = creditCard;
        switch (this.paymentMethod) {
            case CREDIT_CARD -> {
                paymentInformationComp.selectCreditCard(creditCard);
                String cardHolderName = this.userDataObject.getFirstName();
                paymentInformationComp.inputCardHolderName(cardHolderName);
                String cardNumber;
                switch (creditCard) {
                    case VISA -> {
                        cardNumber = "4001919257537193";
                        paymentInformationComp.inputCardNumber(cardNumber);
                    }
                    case AMEX -> {
                        cardNumber = "374245455400126";
                        paymentInformationComp.inputCardNumber(cardNumber);
                    }
                    case DISCOVER -> {
                        cardNumber = "6011000991300009";
                        paymentInformationComp.inputCardNumber(cardNumber);
                    }
                    case MASTER_CARD -> {
                        cardNumber = "5425233430109903";
                        paymentInformationComp.inputCardNumber(cardNumber);
                    }
                }
                Calendar calendar = new GregorianCalendar();
                paymentInformationComp.inputMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
                paymentInformationComp.inputMYear(String.valueOf(calendar.get(Calendar.YEAR) + 1));
                paymentInformationComp.inputMCardCode("123");
            }
            case PURCHASE_ORDER -> paymentInformationComp.inputPONumber("123");
            case COD -> paymentInformationComp.verifyCOD();
            case CHECK_MONEY_ORDER -> paymentInformationComp.verifyMoneyOrder();
        }
        paymentInformationComp.clickContinueBTN();
    }

    public void verifyBillingAddress() {
        String defaultUserCheckoutJSONLoc = "/src/main/java/test_data/DefaultCheckoutUser.json";
        userDataObject = BuilderDataObject.buildDataObject(defaultUserCheckoutJSONLoc, UserDataObject.class);
        ConfirmOrderComponent confirmOrderComp = new CheckOutPage(driver).confirmOrderComp();
        String fullName = userDataObject.getFirstName() + " " + userDataObject.getLastName();
        if (!fullName.equals(confirmOrderComp.getActualFullName())) {
            Assert.fail("[ERR] First name and last name are not correct!!!");
        }

        if (!("Email: " + userDataObject.getEmail()).equals(confirmOrderComp.getActualEmail())) {
            Assert.fail("[ERR] Email is not correct!!!");
        }

        if (!("Phone: " + userDataObject.getPhoneNum()).equals(confirmOrderComp.getActualPhone())) {
            Assert.fail("[ERR] Phone is not correct!!!");
        }

        Pattern patternCity = Pattern.compile(userDataObject.getCity(), Pattern.CASE_INSENSITIVE);
        Matcher matcherCity = patternCity.matcher(confirmOrderComp.getActualCityStateZip());
        if (!matcherCity.find()) Assert.fail("[ERR] City is not correct!!!");

        Pattern patternState = Pattern.compile(userDataObject.getState(), Pattern.CASE_INSENSITIVE);
        Matcher matcherState = patternState.matcher(confirmOrderComp.getActualCityStateZip());
        if (!matcherState.find()) Assert.fail("[ERR] City is not correct!!!");

        Pattern patternZip = Pattern.compile(userDataObject.getZipCode(), Pattern.CASE_INSENSITIVE);
        Matcher matcherZip = patternZip.matcher(confirmOrderComp.getActualCityStateZip());
        if (!matcherZip.find()) Assert.fail("[ERR] City is not correct!!!");

        if (!(userDataObject.getCountry()).equals(confirmOrderComp.getActualCountry())) {
            Assert.fail("[ERR] Country is not correct!!!");
        }

        Pattern patternPaymentMethod = Pattern.compile(paymentMethod.toString().replaceAll("_", " "), Pattern.CASE_INSENSITIVE);
        Matcher matcherPaymentMethod = patternPaymentMethod.matcher(confirmOrderComp.getActualPayment().replaceAll(" /", ""));
        if (!matcherPaymentMethod.find()) Assert.fail("[ERR] Payment method is not correct!!!");
    }

    public void verifyTotalItem() {
        Map<String, Double> totalComps = new HashMap<>();
        double checkoutSubTotal = 0;
        double checkoutOtherFees = 0;
        double checkoutTotal = 0;

        for (String key : totalComps.keySet()) {
            if (key.startsWith("Sub Total")) {
                checkoutSubTotal += totalComps.get(key);
            } else if (key.startsWith("Total")) {
                checkoutTotal += totalComps.get(key);
            } else checkoutOtherFees += totalComps.get(key);
        }
        Assert.assertEquals(checkoutSubTotal + checkoutOtherFees, checkoutTotal, "[ERR] Total is not correct!!!");
        ConfirmOrderComponent confirmOrderComp = new CheckOutPage(driver).confirmOrderComp();
        confirmOrderComp.clickContinueBTN();
    }
}



