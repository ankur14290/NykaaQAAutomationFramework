package PagesNykaa_NykaaMan;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;
import DataNykaa.ProductDetailData;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nevil on 6/26/2017.
 */


public class CartPage extends AppAction {
    public CartPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private boolean isCartEmpty;

    private boolean isShippingChargedApplied;

    private boolean isProductDeleted;

    private boolean isShippingFree;

    private boolean isWishlistEmptyInCart;

    /******************************************Locators*****************************************************************************/


    Locator ProductRowsInBag() {
        //return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvProductTitle')]/ancestor::android.widget.LinearLayout") , "Product Row in Bag");
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvProductTitle')]"), "Product Row in Bag");
    }

    Locator ProductName() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvProductTitle')]"), "Product Name");
    }

    Locator ProductName(String ProductName) {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvProductTitle')][contains(@text,'" + ProductName + "')]"), "Product Name " + ProductName + "");
    }

    Locator ProductsInBagsLocator() {
        //return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'item_total_price')][contains(@text,'₹ ')]/ancestor::android.widget.RelativeLayout[contains(@resource-id,'layout_cart')]") , "Product in Bag");
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'Quantity:')]"), "Product in Bag");
        //return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvFinalPrice')][contains(@text,'₹ ')]/ancestor::android.widget.LinearLayout[contains(@resource-id,'llPrices')]") , "Product in Bag");
    }

    Locator RemovableProductLocator() {
        //return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'/img_trash')]") ,"Removable Product");
        return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'ivIconTrash')]"), "Removable Product");
    }

    Locator RemoveProduct() {
        //return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'/dialog_cancel')]") , "Remove Button");
        return new Locator(By.xpath("//android.widget.TextView[@text='REMOVE']"), "Remove Button");
    }

    Locator BackMenuCart() {
        return new Locator(By.xpath("//*[contains(@resource-id,'/action_bar')]//android.widget.ImageButton"), "Back Button");
    }

    Locator TopTrashLocator() {
        //return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'img_trash')][1]"), "Top Trash Button");
        return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'ivIconTrash')][1]"), "Top Trash Button");
    }

    Locator loadingIcon() {
        return new Locator(By.xpath("//android.widget.ProgressBar[contains(@resource-id,'progressBar')]"), "Loader");
    }

    Locator RemoveLoader() {
        return new Locator(By.xpath("//android.widget.ProgressBar[contains(@resource-id,'progress')]"), "Remove Product Loader");
    }

    Locator UndoButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'sb__action')][@text='UNDO']"), "Undo Button");
    }

    Locator CartIcon() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'/action_shopping_cart')]"), "Cart Button");
    }

    Locator shoppingBagEmptyLocator() {
        return new Locator(By.xpath("//android.widget.TextView[@text='Your Shopping Bag is Empty']"), "Shopping Bag Empty");
    }

    Locator YourWishlist() {
        return new Locator(By.xpath("//android.widget.TextView[@text='Your Wishlist']"), "Your Wishlist text");
    }

    Locator shippingCharge() {
        //return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'item_value')][@text='₹50']"),"Shipping Charge");
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvPaymentItemPrice')][@text='₹50']"), "Shipping Charge");
    }

    Locator shippingFree() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvPaymentItemPrice')][@text='FREE']"), "Free shipping");
    }

    Locator subTotal() {
        //return new Locator(By.xpath("//android.widget.TextView[@text='Subtotal']/ancestor::android.widget.RelativeLayout/following-sibling::android.widget.TextView[contains(@resource-id,'item_value')]"),"Subtotal");
        //return new Locator(By.xpath("//android.widget.RelativeLayout[@index='0']//android.widget.TextView[contains(@resource-id='tvPaymentItemPrice')]"),"Subtotal");
        return new Locator(By.xpath("//android.support.v7.widget.RecyclerView[@index='2']//android.widget.RelativeLayout[@index='0']//android.widget.TextView[@index='1']"), "Subtotal");

    }

    Locator couponCodeTextBox() {
        //return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'promocode_input')]"),"Coupon Code Text Box");
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etCouponCode')]"), "Coupon Code Text Box");
    }

    Locator applyCouponButton() {
        // return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'promocode_apply')]"),"Apply Coupon Button");
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvApplyRemoveBtn')]"), "Apply Coupon Button");
    }

    Locator couponAppliedOKButton() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'dialog_cancel')]"), "Coupon Applied OK");
    }

    Locator discountAmount(String coupon) {

        //return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'item_description')][contains(@text,'"+coupon+"')]/ancestor::android.widget.RelativeLayout/following-sibling::android.widget.TextView[contains(@resource-id,'item_value')][contains(@text,'-')]"),"Discount Amount");
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvPaymentItemTitle')][contains(@text,'" + coupon + "')]"), "Discount Amount");
    }

    Locator CheckOutButton() {
        //return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'checkout_btn')]"),"CheckOut Button");
        return new Locator(By.xpath("//android.widget.TextView[@text='PROCEED']"), "CheckOut Button");
    }

    Locator rewardPoints() {
        //return new Locator(By.xpath("//android.widget.CheckBox[contains(@resource-id,'reward_points_check')]"),"Reward Point CheckBox");
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'rlRewardCardTop')]"), "Reward Point CheckBox");
    }

    Locator rewardPointsAppliedNotification() {
        //return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'sb__text')][@text='Reward Points Applied Successfully.']"),"Rewards point Applied Succefully");
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvRemoveApplyRewards')]"), "Rewards point Applied Succefully");
    }

    Locator spendNowFeature() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'spend_more')][contains(@text,'more and avail free shipping')]"), "Spend More Text");
    }

    Locator calculationOfPrice() {
        //return new Locator(By.xpath("//android.widget.TextView[not(contains(@text,'GRAND TOTAL'))][not(contains(@text,'Subtotal'))]/ancestor::android.widget.RelativeLayout/following-sibling::android.widget.TextView[contains(@resource-id,'item_value')]"),"calculation of prices");
        return new Locator(By.xpath("//android.widget.TextView[not(contains(@text,'GRAND TOTAL'))][not(contains(@text,'Subtotal'))]/ancestor::android.widget.RelativeLayout/following-sibling::android.widget.TextView[contains(@resource-id,'tvPaymentItemPrice')]"), "calculation of prices");
    }

    Locator calculationOfPrice1() {
        //return new Locator(By.xpath("//android.widget.TextView[not(contains(@text,'GRAND TOTAL'))]/ancestor::android.widget.RelativeLayout/following-sibling::android.widget.TextView[contains(@resource-id,'item_value')]"),"calculation of prices");
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvFinalPrice')]"), "calculation of prices");
    }

    Locator priceHeader() {
        // return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'item_description')]"),"Prices Header");
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvFinalPrice')]"), "Prices Header");
    }

    Locator grandTotalPrice() {
        //return new Locator(By.xpath("//android.widget.TextView[contains(@text,'GRAND TOTAL')]/ancestor::android.widget.RelativeLayout/following-sibling::android.widget.TextView[contains(@resource-id,'item_value')]"),"Grand Total");
        return new Locator(By.xpath("//android.widget.RelativeLayout[@index='5']//android.widget.TextView[@index='1']"), "Grand Total");
    }

    Locator deleteFirst() {
        return new Locator(By.xpath("//android.widget.FrameLayout[@index='0']//android.widget.LinearLayout[@index='2']//android.widget.ImageView[@index='0']"), "Delete First Product");
    }

    /******************************************Services*****************************************************************************/

    public boolean applyRewardPoints() throws InterruptedException, IllegalAccessException, InstantiationException {
        if (waitUntilDisplayed(CartIcon(), 7)) {
            click(CartIcon());
        }
        bringElementIntoViewDown(rewardPoints(), 5);
        click(rewardPoints());
        return isRewardPointsApplied();
    }

    public boolean applyCoupon(DataNykaa.CheckoutData checkoutData) throws InterruptedException, IllegalAccessException, InstantiationException {
        if (waitUntilDisplayed(CartIcon(), 4)) {
            click(CartIcon());
        }
        bringElementIntoViewDown(couponCodeTextBox(), 9);
        ClearValue(couponCodeTextBox());
        EnterValue(couponCodeTextBox(), checkoutData.getCouponcode());
        click(applyCouponButton());
        //click(couponAppliedOKButton());
        waitUntilDisplayed(applyCouponButton(), 15);
        //bringElementIntoViewDown(discountAmount(checkoutData.getCouponcode()),5);

        return waitUntilDisplayed(discountAmount(checkoutData.getCouponcode()), 10);

    }

    public boolean isProductPresentInBag(ProductDetailData productDetailData) throws Throwable {
        if (waitUntilDisplayed(CartIcon(), 7)) {
            click(CartIcon());
        }
        boolean isProductPresent = false;
        waitUntilElementDisappear(loadingIcon(), 5);
        //String productName1 = productDetailData.getProductName().split(" ")[0] +" " + productDetailData.getProductName().split(" ")[1]+" " + productDetailData.getProductName().split(" ")[2];
        String productName = productDetailData.getProductName();
        //   String NewProduct = productName.concat(" Fabershoot Shampoo");
        bringElementIntoViewDown(ProductName(productName), 3);
        List<WebElement> productrow = getWebElements(ProductRowsInBag());
        for (WebElement product : productrow) {
            Locator ProductName = new Locator(product, ProductName().getBy(), "Product Name");
            String ProductNameFromUI = getText(ProductName);
            //if (ProductNameFromUI.contains(productName)) {
            if (productName.contains(ProductNameFromUI)) {
                isProductPresent = true;
                break;
            }
        }
        click(BackMenuCart());
        return isProductPresent;
    }

    public ProductDetailData deleteFirstProduct() throws IllegalAccessException, InstantiationException, InterruptedException {
        if (waitUntilDisplayed(CartIcon(), 7)) {
            click(CartIcon());
        }
        List<WebElement> removableProducts = getWebElements(ProductsInBagsLocator());
        ListingPage listingPage = new ListingPage(driver);
        ProductDetailData productDetailData = null;
        for (WebElement removeProduct : removableProducts) {
            Locator removeProductLocator = new Locator(removeProduct, RemovableProductLocator().getBy(), "Product");
            productDetailData = listingPage.getProductDetailFromCart();
            waitUntilDisplayed(removeProductLocator, 10);
            //click(removeProductLocator);
            click(RemovableProductLocator());
            click(RemoveProduct());
            setProductDeleted(waitUntilDisplayed(UndoButton(), 10));
            break;
        }

        return productDetailData;
    }

    public boolean deleteFirstProductFromCart() throws IllegalAccessException, InstantiationException {
        if (waitUntilDisplayed(CartIcon(), 7)) {
            click(CartIcon());
        }
        if (waitUntilDisplayed(deleteFirst(), 15)) {
            click(deleteFirst());
            return true;
        } else {
            return false;
        }
    }

    public void undoDeleteProduct() throws IllegalAccessException, InstantiationException {
        click(UndoButton());
    }

    public void removeAllProduct() throws InterruptedException, IllegalAccessException, InstantiationException {
        List<WebElement> removableProducts;
        bringElementIntoViewDown(TopTrashLocator(), 4);
        isCartEmpty = false;
        while (!isCartEmpty()) {
            removableProducts = getWebElements(ProductsInBagsLocator());
            for (WebElement removeProduct : removableProducts) {
                if (removableProducts.size() > 0) {
                    removeProduct = getWebElements(ProductsInBagsLocator()).get(0);
                    Locator removeProductLocator = new Locator(removeProduct, RemovableProductLocator().getBy(), "Trash");
                    //click(removeProductLocator);
                    click(RemovableProductLocator());
                    click(RemoveProduct());
                    // waitUntilElementDisappear(RemoveLoader(), 6);
                    // waitUntilElementDisappear(UndoButton(), 6);
                    setCartEmpty(waitUntilDisplayed(shoppingBagEmptyLocator(), 2));
                    setWishlistEmptyInCart(waitUntilDisplayed(YourWishlist(), 2));
                } else if (waitUntilDisplayed(shoppingBagEmptyLocator(), 4)) {
                    setCartEmpty(waitUntilDisplayed(shoppingBagEmptyLocator(), 2));
                }
            }
        }
        androidBackButton();
        waitUntilElementDisappear(BackMenuCart(), 5);
    }

    public void checkShippingCharge() throws IllegalAccessException, InstantiationException, InterruptedException {
        click(CartIcon());
        bringElementIntoViewDown(shippingCharge(), 5);
        String subtotal = getText(subTotal());
        System.out.println("SubTotal :: -> " + subtotal);

        String subtotal1 = subtotal.replaceAll("[^0-9]", "");
        String subtotal2 = subtotal1.trim();
        int y = Integer.parseInt(subtotal2);
        if (y > 500) {
            setShippingFree(waitUntilDisplayed(shippingFree(), 10));

        } else {
            setShippingChargedApplied(waitUntilDisplayed(shippingCharge(), 10));
        }
        // click(BackMenuCart());
        //waitUntilElementDisappear(BackMenuCart(), 5);
    }

    public boolean isComboProductsPresentInBag(List<ProductDetailData> comboProductsDetail) throws Throwable {
        boolean isIntheBag = false;
        List<Boolean> isProductPresent = new ArrayList<Boolean>();

        for (ProductDetailData comboProduct : comboProductsDetail) {
            isProductPresent.add(isProductPresentInBag(comboProduct));

        }
        if (isProductPresent.contains(false)) {
            isIntheBag = false;
        } else {

            isIntheBag = true;
        }
        return isIntheBag;
    }

    public boolean isCartEmpty() {
        return isCartEmpty;
    }

    public void setCartEmpty(boolean cartEmpty) {
        this.isCartEmpty = cartEmpty;
    }

    public boolean isWishlistEmptyInCart() {
        return isWishlistEmptyInCart;
    }

    public void setWishlistEmptyInCart(boolean wishlistEmptyInCart) {
        this.isWishlistEmptyInCart = wishlistEmptyInCart;
    }

    public boolean isShippingChargedApplied() {
        return isShippingChargedApplied;
    }

    public void setShippingChargedApplied(boolean shippingChargedApplied) {
        this.isShippingChargedApplied = shippingChargedApplied;
    }

    public boolean isShippingFree() {
        return isShippingFree;
    }

    public void setShippingFree(boolean shippingFree) {
        this.isShippingFree = shippingFree;
    }

    public void checkOut() throws IllegalAccessException, InstantiationException {
        if (waitUntilDisplayed(CartIcon(), 4)) {
            click(CartIcon());
        }
        click(CheckOutButton());

    }

    public ProductDetailData goToProductDetailPage() throws IllegalAccessException, InstantiationException, InterruptedException {
        if (waitUntilDisplayed(CartIcon(), 5)) {
            click(CartIcon());
        }
        ProductPage productPage = new ProductPage(driver);
        ProductDetailData productDetailData = null;
        List<WebElement> productRow = getWebElements(ProductsInBagsLocator());
        for (WebElement product : productRow) {
            productDetailData = productPage.getProductDetail();
            Locator productImage = new Locator(product, By.xpath("//android.widget.ImageView"), "Image Product");

            click(productImage);
            break;
        }
        return productDetailData;
    }

    public boolean isSpendMoreTextVisible() throws InterruptedException, IllegalAccessException, InstantiationException {
        if (waitUntilDisplayed(CartIcon(), 5)) {
            click(CartIcon());
        }
        bringElementIntoViewDown(spendNowFeature(), 3);
        return waitUntilDisplayed(spendNowFeature(), 5);
    }

    private boolean isRewardPointsApplied() {
        /*String txt = getText(rewardPointsAppliedNotification());
        if(txt.equals("Remove")) {
            return true;
        }*/
        return waitUntilDisplayed(rewardPointsAppliedNotification(), 10);
       /* else {
            return false;
        }*/
    }

    public boolean checkPriceCalculation() throws InterruptedException, IllegalAccessException, InstantiationException {
        if (waitUntilDisplayed(CartIcon(), 3)) {
            click(CartIcon());
        }
        bringElementIntoViewDown(grandTotalPrice(), 10);

        ArrayList<String> PriceText = new ArrayList<String>();
        List<WebElement> priceHeader = getWebElements(priceHeader());
        for (WebElement price1 : priceHeader) {
            String getValue = getText(price1);
            PriceText.add(getValue);
        }
        System.out.println(PriceText);
        List<WebElement> differentPrices = null;
        float grandTotal = 0;
        if (PriceText.contains("Bag Total")) {
            differentPrices = getWebElements(calculationOfPrice());
        } else {
            differentPrices = getWebElements(calculationOfPrice1());
        }
        for (WebElement individualPrices : differentPrices) {
            String pricesInString = getText(individualPrices);
            if (pricesInString.contains("-")) {
                float pricesInInt = Float.parseFloat(pricesInString.split("- ₹")[1].replace(",", ""));
                grandTotal = grandTotal - pricesInInt;
            } else if (!pricesInString.contains("-") && pricesInString.contains("₹")) {
                float pricesInInt = Float.parseFloat(pricesInString.split("₹")[1].replace(",", ""));
                grandTotal = grandTotal + pricesInInt;
            }

            System.out.println("grandTotal : --> " + grandTotal);
        }

        int grandTotalFromUI = Integer.parseInt(getText(grandTotalPrice()).split("₹")[1].replace(",", ""));
        System.out.println("grandTotalFromUI  ::-->>" + grandTotalFromUI);
        TestListner.testing.get().log(LogStatus.INFO, "Grand Total From Phone :" + grandTotalFromUI);
        TestListner.testing.get().log(LogStatus.INFO, "Calculated Grand Total :" + grandTotal);
        if ((grandTotalFromUI - 2) < grandTotal && grandTotal > (grandTotalFromUI + 2)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isProductDeleted() {
        return isProductDeleted;
    }

    public void setProductDeleted(boolean productDeleted) {
        isProductDeleted = productDeleted;
    }
}
