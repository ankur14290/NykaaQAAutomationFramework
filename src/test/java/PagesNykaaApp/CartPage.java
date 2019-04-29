package PagesNykaaApp;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;
import DataNykaa.CheckoutData;
import DataNykaa.ProductDetailData;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alwira on 6/26/2018.
 */



public class CartPage extends AppAction
{
    public CartPage(AndroidDriver driver){
        this.driver = driver;
    }

    private  boolean isCartEmpty;

    private boolean isShippingChargedApplied;

    private boolean isProductDeleted;

    private boolean isShippingFree;

    private boolean isWishlistEmptyInCart;

    /******************************************Locators*****************************************************************************/


    Locator ProductRowsInBag(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'/product_title_text')]/ancestor::android.widget.LinearLayout") , "Product Row in Bag");
    }

    Locator ProductName() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'/product_title_text')]"),"Product Name");
    }

    Locator ProductName(String ProductName){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvProductTitle')][contains(@text,'"+ProductName+"')]"),"Product Name "+ProductName+"");
    }


    Locator ProductsInBagsLocator(){
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'llPrices')]/ancestor::android.widget.RelativeLayout/ancestor::android.widget.LinearLayout[contains(@resource-id,'ProductCard')]"),"Product Row");
    }


    Locator RemovableProductLocator(){
        return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'/ivIconTrash')]") ,"Removable Product");
    }

    Locator RemoveProduct(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_ok')]") , "Remove Button");
    }

    Locator BackMenuCart(){
        return new Locator(By.xpath("//*[contains(@resource-id,'toolbarCart')]//child::android.widget.ImageButton"), "Back Button");
    }

    Locator TopTrashLocator(){
        return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'ivIconTrash')][1]"), "Top Trash Button");
    }

    Locator loadingIcon(){
        return new Locator(By.xpath("//android.widget.ProgressBar[contains(@resource-id,'progressBar')]"), "Loader");
    }

    Locator RemoveLoader(){
        return new Locator(By.xpath("//android.widget.ProgressBar[contains(@resource-id,'progress')]"), "Remove Product Loader");
    }

    Locator UndoButton(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'sb__action')][@text='UNDO']"), "Undo Button");
    }

    Locator CartIcon(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'/action_shopping_cart')]") ,"Cart Button");
    }

    Locator shoppingBagEmptyLocator(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'/tvStartShop')][@text='START SHOPPING'][2] | //android.widget.TextView[contains(@resource-id,'/tvEmptyBagMsg')] "),"Shopping Bag Empty");
    }

    Locator shoppingBagEmptyLocator1(){
        return new Locator(By.id("/tvStartShop") ,"Shopping Bag Empty");
    }


    Locator YourWishlist(){
        return new Locator(By.xpath("//android.widget.TextView[@text='Your Wishlist']"),"Your Wishlist text");
    }

    Locator shippingText(){
        return new Locator(By.xpath("//android.widget.TextView[@text='Shipping Charges']"), "Check shipping charges");
    }

    Locator shippingCharge(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'/tvPaymentItemPrice')][@text='₹50']"),"Shipping Charge applied");
    }

    Locator shippingFree(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'/tvPaymentItemPrice')][@text='FREE']"),"Free shipping");
    }

    Locator subTotal(){
        return new Locator(By.xpath("//android.widget.TextView[@text='Subtotal']/following-sibling::android.widget.TextView[contains(@resource-id,'/tvPaymentItemPrice')]"),"Subtotal");
    }

    Locator couponCodeTextBox(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etCouponCode')]"),"Coupon Code Text Box");
    }

    Locator applyCouponButton(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvApplyRemoveBtn')]"),"Apply Coupon Button");
    }

    Locator couponApplied(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'IS APPLIED')]"),"Coupon Applied");
    }

    Locator discountAmount(String coupon){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvPaymentItemTitle')][contains(@text,'"+coupon+"')]/following-sibling::android.widget.TextView[contains(@resource-id,'tvPaymentItemPrice')][contains(@text,'-')]"),"Discount Amount");
    }

    Locator CheckOutButton(){
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'llProceedBtn')]"),"CheckOut Button");
    }

    Locator rewardPoints(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvRemoveApplyRewards')]"),"Reward Point Box");
    }

    Locator rewardPointsAppliedNotification(){
        return new Locator(By.xpath("//android.widget.TextView[@text='REMOVE']"),"Rewards point Applied Successfully");
    }

    Locator spendNowFeature(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'spend_more')][contains(@text,'more and avail free shipping')]"),"Spend More Text");
    }

    Locator calculationOfPrice(){
        return new Locator(By.xpath("//android.widget.TextView[not(contains(@text,'GRAND TOTAL'))][not(contains(@text,'Subtotal'))]/following-sibling::android.widget.TextView[contains(@resource-id,'PaymentItemPrice')]"),"calculation of prices");
    }

    Locator calculationOfPrice1(){
        return new Locator(By.xpath("//android.widget.TextView[not(contains(@text,'GRAND TOTAL'))]/following-sibling::android.widget.TextView[contains(@resource-id,'PaymentItemPrice')]"),"calculation of prices");
    }

    Locator priceHeader(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'FinalPrice')]"),"Prices Header");
    }

    Locator NewPriceHeaderName(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'PaymentItemTitle')]"), "Price Header");
    }

    Locator NewPriceHeaderValue(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'PaymentItemPrice')]"),"Price Header Value");
    }

    Locator grandTotalPrice(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'GRAND TOTAL')]/ancestor::android.widget.RelativeLayout/following-sibling::android.widget.TextView[contains(@resource-id,'tvPaymentItemPrice')]"),"Grand Total");
    }

    Locator GrandTotalPrice_New(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'GRAND TOTAL')]"),"Grand Total");
    }

    Locator GrandTotalPrice_NewValue(){
        return new Locator(By.xpath("//android.widget.TextView[(contains(@text,'GRAND TOTAL'))]/following-sibling::android.widget.TextView[contains(@resource-id,'PaymentItemPrice')]"), "Grand Total Value");
    }



    /******************************************Services*****************************************************************************/

    public boolean applyRewardPoints() throws InterruptedException, IllegalAccessException, InstantiationException {
        if(waitUntilDisplayed(CartIcon(), 7)) {
            click(CartIcon());
        }
        bringElementIntoViewDown(rewardPoints(),5);
        click(rewardPoints());
        return isRewardPointsApplied();
    }

    public boolean applyCoupon(CheckoutData checkoutData) throws InterruptedException, IllegalAccessException, InstantiationException {
        if(waitUntilDisplayed(CartIcon(), 4)) {
            click(CartIcon());
        }
        bringElementIntoViewDown(couponCodeTextBox(),9);
        click(couponCodeTextBox());
        EnterValue(couponCodeTextBox(), checkoutData.getCouponcode());
        bringElementIntoViewDown(applyCouponButton(),2);
        click(applyCouponButton());
        return waitUntilDisplayed(couponApplied(),5);

        //bringElementIntoViewUp(discountAmount(checkoutData.getCouponcode()),5);
        //return waitUntilDisplayed(discountAmount(checkoutData.getCouponcode()),10);

    }

    public boolean isProductPresentInBag(ProductDetailData productDetailData) throws  Throwable {
        if(waitUntilDisplayed(CartIcon(), 7)) {
            click(CartIcon());
        }
        //To overcome Marketting Pop - system will click on centre of screen so...pop get closed.
        //    boolean isProductPresent = false;
        waitUntilElementDisappear(loadingIcon() , 5);
        String productName = productDetailData.getProductName().split(" ")[0] +" " + productDetailData.getProductName().split(" ")[1]+" " + productDetailData.getProductName().split(" ")[2];
        boolean avialable = bringElementIntoViewDown(ProductName(productName),5);
        /*List<WebElement> productrow = getWebElements(ProductRowsInBag());
        for (WebElement product : productrow) {
            Locator ProductName = new Locator(product, ProductName().getBy(), "Product Name");
            String ProductNameFromUI = getText(ProductName);
            if (ProductNameFromUI.contains(productName)) {
                isProductPresent = true;
                break;
            }
        }*/
        click(BackMenuCart());
        return avialable;
    }

    public ProductDetailData deleteFirstProduct() throws IllegalAccessException, InstantiationException, InterruptedException {
        if(waitUntilDisplayed(CartIcon(),7)){
            click(CartIcon());
        }
        List<WebElement> removableProducts = getWebElements(ProductsInBagsLocator());
        ProductDetailData productDetailData = new ProductDetailData();
        for (WebElement removeProduct:removableProducts) {
            Locator removeProductLocator = new Locator(removeProduct, RemovableProductLocator().getBy(), "Product");
            Locator productDetail = new Locator(removeProduct, By.xpath("//android.widget.LinearLayout[contains(@resource-id,'ProductDetails')]//android.widget.TextView[contains(@resource-id,'tvProductTitle')]"),"product name");
            productDetailData.setProductName(getText(productDetail));
            click(removeProductLocator);
            click(RemoveProduct());
            setProductDeleted(!waitUntilDisplayed(productDetail,5));
            break;
        }

        return productDetailData;
    }

    public void removeAllProduct() throws InterruptedException, IllegalAccessException, InstantiationException {
        List<WebElement> removableProducts;
        bringElementIntoViewDown(TopTrashLocator(), 4);
        isCartEmpty =false;
        while(!isCartEmpty()){
            removableProducts = getWebElements(ProductsInBagsLocator());
            for (WebElement removeProduct : removableProducts) {
                if(removableProducts.size() > 0) {
                    removeProduct = getWebElements(ProductsInBagsLocator()).get(0);
                    Locator removeProductLocator = new Locator(removeProduct, RemovableProductLocator().getBy(), "Trash");
                    click(removeProductLocator);
                    click(RemoveProduct());
                    Thread.sleep(3000);
                    waitUntilElementDisappear(RemoveLoader(), 60);
                    setCartEmpty(waitUntilDisplayed(shoppingBagEmptyLocator(), 2));
                    //setWishlistEmptyInCart(waitUntilDisplayed(YourWishlist(), 2));
                }else if (waitUntilDisplayed(shoppingBagEmptyLocator(),4)){
                    setCartEmpty(waitUntilDisplayed(shoppingBagEmptyLocator(),2));
                }
            }
        }
        androidBackButton();
        waitUntilElementDisappear(BackMenuCart(), 5);
    }

    public void checkShippingCharge() throws IllegalAccessException, InstantiationException, InterruptedException {
        click(CartIcon());
        bringElementIntoViewDown(shippingText(),5);
        String subtotal = getText(subTotal());
        String subtotal1 = subtotal.replaceAll("[^0-9]", "");
        String subtotal2 = subtotal1.trim();
        int y = Integer.parseInt(subtotal2);
        if(y>500){
            setShippingFree(waitUntilDisplayed(shippingFree(),10));

        }else {
            setShippingChargedApplied(waitUntilDisplayed(shippingCharge(),10));
        }
        click(BackMenuCart());
        waitUntilElementDisappear(BackMenuCart(), 5);
    }

    public boolean isComboProductsPresentInBag(List<ProductDetailData> comboProductsDetail) throws Throwable{
        boolean isIntheBag = false;
        List<Boolean> isProductPresent = new ArrayList<Boolean>();

        for (ProductDetailData comboProduct:comboProductsDetail)
        {
            isProductPresent.add(isProductPresentInBag(comboProduct));

        }
        if(isProductPresent.contains(false))
        {
            isIntheBag = false;
        }
        else{

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
        if(waitUntilDisplayed(CartIcon(),5)){
            click(CartIcon());
        }
        ProductPage productPage = new ProductPage(driver);
        ProductDetailData productDetailData = new ProductDetailData();
        List<WebElement> productRow = getWebElements(ProductsInBagsLocator());
        for (WebElement product:productRow) {
            Locator productDetail = new Locator(product, By.xpath("//android.widget.LinearLayout[contains(@resource-id,'ProductDetails')]//android.widget.TextView[contains(@resource-id,'tvProductTitle')]"),"product name");
            productDetailData.setProductName(getText(productDetail));
            Locator productImage = new Locator(product, By.xpath("//android.widget.ImageView"),"Image Product");
            click(productImage);
            break;
        }
        return productDetailData;
    }

    public boolean isSpendMoreTextVisible() throws InterruptedException, IllegalAccessException, InstantiationException {
        if(waitUntilDisplayed(CartIcon(),5)){
            click(CartIcon());
        }
        bringElementIntoViewDown(spendNowFeature(),3);
        return waitUntilDisplayed(spendNowFeature(),5);
    }

    private boolean isRewardPointsApplied(){
        return waitUntilDisplayed(rewardPointsAppliedNotification(),10);
    }

    public boolean checkPriceCalculation() throws InterruptedException, IllegalAccessException, InstantiationException {
        if(waitUntilDisplayed(CartIcon(),3)){
            click(CartIcon());
        }
        bringElementIntoViewDown(GrandTotalPrice_New(),10);

        ArrayList<String> PriceText = new ArrayList<String>();
        List<WebElement> priceHeader = getWebElements(NewPriceHeaderValue());
        for(WebElement price1:priceHeader){
            String getValue = getText(price1);
            PriceText.add(getValue);
        }
        ArrayList<String> PriceName = new ArrayList<String>();
        List<WebElement> priceHeader1 = getWebElements(NewPriceHeaderName());
        for(WebElement price1:priceHeader1){
            String getValue = getText(price1);
            PriceName.add(getValue);
        }
        System.out.println(PriceText);
        System.out.println(PriceName);
        List<WebElement> differentPrices = null;
        float grandTotal = 0;
        if(PriceName.contains("Bag Total")) {
            differentPrices = getWebElements(calculationOfPrice());
        }else {
            differentPrices = getWebElements(calculationOfPrice1());
        }
        for(WebElement individualPrices:differentPrices) {
            String pricesInString = getText(individualPrices);
            if (pricesInString.contains("-")) {
                float pricesInInt = Float.parseFloat(pricesInString.split("- ₹")[1].replace(",", ""));
                grandTotal = grandTotal - pricesInInt;
            } else if (!pricesInString.contains("-") && pricesInString.contains("₹")) {
                float pricesInInt = Float.parseFloat(pricesInString.split("₹")[1].replace(",", ""));
                grandTotal = grandTotal + pricesInInt;
            }
        }

        int grandTotalFromUI = Integer.parseInt(getText(GrandTotalPrice_NewValue()).split("₹")[1].replace(",",""));
        TestListner.testing.get().log(LogStatus.INFO,"Grand Total From Phone :"+grandTotalFromUI);
        TestListner.testing.get().log(LogStatus.INFO, "Calculated Grand Total :"+grandTotal);
        if ((grandTotalFromUI - 2) < grandTotal && grandTotal < (grandTotalFromUI + 2)){
            return true;
        }else {
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