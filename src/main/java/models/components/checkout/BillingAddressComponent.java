package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@ComponentCssSelector("#opc-billing")
public class BillingAddressComponent extends Component {

    private final static By addressDropdownSel = By.cssSelector("#billing-address-select");
    private final static By firstNameSel = By.cssSelector("#BillingNewAddress_FirstName");
    private final static By lastNameSel = By.cssSelector("#BillingNewAddress_LastName");
    private final static By emailSel = By.cssSelector("#BillingNewAddress_Email");
    private final static By countrySel = By.cssSelector("#BillingNewAddress_CountryId");
    private final static By stateSel = By.cssSelector("#BillingNewAddress_StateProvinceId");
    private final static By waitCountrySel = By.cssSelector("#states-loading-progress");
    private final static By citySel = By.cssSelector("#BillingNewAddress_City");
    private final static By address1Sel = By.cssSelector("#BillingNewAddress_Address1");
    private final static By address2Sel = By.cssSelector("#BillingNewAddress_Address2");
    private final static By zipSel = By.cssSelector("#BillingNewAddress_ZipPostalCode");
    private final static By phoneSel = By.cssSelector("#BillingNewAddress_PhoneNumber");
    private final static By continueBtnSel = By.cssSelector(".new-address-next-step-button");

    public BillingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public BillingAddressComponent selectNewAddress () {
        if(!component.findElements(addressDropdownSel).isEmpty()) {
            Select select = new Select(component.findElement(addressDropdownSel));
            select.selectByVisibleText("New Address");
        }
        return this;
    }

    public BillingAddressComponent inputFirstName (String firstName) {
        component.findElement(firstNameSel).sendKeys(firstName);
        return this;
    }

    public BillingAddressComponent inputLastName (String lastName) {
        component.findElement(lastNameSel).sendKeys(lastName);
        return this;
    }

    public BillingAddressComponent inputEmail (String email) {
        component.findElement(emailSel).sendKeys(email);
        return this;
    }

    public BillingAddressComponent selectCountry (String country) {
        Select select = new Select(component.findElement(countrySel));
        select.selectByVisibleText(country);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(waitCountrySel));
        return this;
    }

    public BillingAddressComponent selectState (String state) {
        Select select = new Select(component.findElement(stateSel));
        select.selectByVisibleText(state);
        return this;
    }

    public BillingAddressComponent inputCity (String city) {
        component.findElement(citySel).sendKeys(city);
        return this;
    }

    public BillingAddressComponent inputAddress1 (String address1) {
        component.findElement(address1Sel).sendKeys(address1);
        return this;
    }
    public BillingAddressComponent inputAddress2 (String address2) {
        component.findElement(address2Sel).sendKeys(address2);
        return this;
    }

    public BillingAddressComponent inputZIPCode (String Zipcode) {
        component.findElement(zipSel).sendKeys(Zipcode);
        return this;
    }

    public BillingAddressComponent inputPhone (String phone) {
        component.findElement(phoneSel).sendKeys(phone);
        return this;
    }

    public BillingAddressComponent clickContinueBtn () {
        WebElement continueBTN = component.findElement(continueBtnSel);
        continueBTN.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBTN));
        return this;
    }





}
