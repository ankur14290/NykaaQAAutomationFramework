package PagesNykaa_NykaaMan;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import DataNykaa.CheckoutData;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

/**
 * Created by shail on 10/4/2017.
 */

public class MoreSection extends AppAction {
    public MoreSection(AndroidDriver driver) {
        this.driver = driver;
    }

    Locator ActionBar() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'menu_overflow')]"), "Action Bar Menu");
    }

    Locator MyAccountAction() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_my_account')]"), "My Account Button");
    }

    Locator MyAddress() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'myAddress_btn')]"), "My Addresses");
    }

    Locator AddAddress() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'address_add_btn')] | //android.widget.Button[contains(@resource-id,'btn_add_address')]"), "Add Address Button");
    }

    Locator EnterFullname() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dialog_full_name')]"), "Address Fullname");
    }

    Locator EnterPincode() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dialog_pincode')]"), "Address Pincode");
    }

    Locator EnterAddress() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dialog_address_line_1')]"), "Address Lines");
    }

    Locator EnterMobileNumber() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dialog_mobile_number')]"), "Address Mobile Number");
    }

    Locator AddressSubmit() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'dialog_submit')]"), "Address Submit");
    }

    Locator NoAddress() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'no_address_label')]"), "No Address");
    }

    Locator AddressLayout() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'addressrl')]"), "Address Fullname");
    }

    Locator SavedAddress() {
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'addressTextLL')]"), "Saved Address");
    }

    Locator EditAddress() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'editBtn')]"), "Edit Address");
    }

    Locator RemoveAddress() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'removeBtn')]"), "Remove Address");
    }

    Locator SelectAddress() {
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'addressTextLL')]"), "Select Address");
    }

    /******************************************Services*****************************************************************************/

    public void MyAccountSection() throws IllegalAccessException, InstantiationException {
        click(ActionBar());
        click(MyAccountAction());
    }

    public void NavigateToMyAddresses() throws IllegalAccessException, InstantiationException, InterruptedException {
        bringElementIntoViewDown(MyAddress(), 5);
        click(MyAddress());
    }

    public String fillNewAddress(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, InterruptedException {
        click(AddAddress());
        EnterValue(EnterFullname(), checkoutData.getFirstname());
        EnterValue(EnterPincode(), checkoutData.getPostcode());
        click(EnterPincode());
        bringElementIntoViewDown(EnterAddress(), 3);
        if (!isDisplayed(EnterAddress())) {
            androidBackButton();
        }
        bringElementIntoViewDown(EnterAddress(), 1);
        EnterValue(EnterAddress(), checkoutData.getShipping_address1());
        bringElementIntoViewDown(EnterMobileNumber(), 2);
        if (!isDisplayed(EnterMobileNumber())) {
            androidBackButton();
        }
        bringElementIntoViewDown(EnterMobileNumber(), 2);
        EnterValue(EnterMobileNumber(), checkoutData.getTelephone());
        String telephone = checkoutData.getTelephone();
        click(AddressSubmit());
        return telephone;
    }

    public boolean isAddressAddedorEdited(String telephone) throws InterruptedException {
        boolean telestats = false;
        List<WebElement> SavedAdd = getWebElements(SavedAddress());
        for (WebElement addressIterator : SavedAdd) {
            Locator addedAdd = new Locator(addressIterator, By.xpath("//android.widget.TextView[contains(@resource-id,'address_text')]"), "Added Address");
            String verifytele = getText(addedAdd);
            if (verifytele.contains(telephone)) {
                telestats = true;
                break;
            }
        }
        return telestats;
    }

    public String editSavedAddress() throws IllegalAccessException, InstantiationException, InterruptedException {
        List<WebElement> EditAddress = getWebElements(EditAddress());
        EditAddress.get(0).click();
        Thread.sleep(2000);
        androidBackButton();
        bringElementIntoViewDown(EnterMobileNumber(), 3);
        Random random = new Random();
        String telephone = String.valueOf(1000000000 + (random.nextInt(999999999)));
        ClearValue(EnterMobileNumber());
        EnterValue(EnterMobileNumber(), telephone);
        click(AddressSubmit());
        return telephone;
    }

}