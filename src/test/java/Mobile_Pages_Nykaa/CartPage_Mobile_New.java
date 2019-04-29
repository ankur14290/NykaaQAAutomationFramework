package Mobile_Pages_Nykaa;

import DataNykaa.BeautyServiceData;
import DataNykaa.CheckoutData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;
import org.apache.regexp.RE;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage_Mobile_New extends BrowserAction {
    public CartPage_Mobile_New(WebDriver driver) {
        this.driver = driver;
    }

    Locator CartLocator() {
        return new Locator(By.xpath("//span[contains(@class,'mkr-New-Shopping-Bag')]"), "Shoping Cart Icon");
    }

    Locator CartProductLocator(int index) {
        return new Locator(By.xpath("//div[@class='container-box ']/div[1]/div[2]/div[@class='product-name']"),
                "Product name");
    }
    
    Locator CartProductLocatorNykaaMan(int index) {
        return new Locator(By.xpath("//form[@id='mobile-checkout-item']//div[@class='head_text']"),
                "Product name");
    }


    Locator EmptyCartLocator() {
        return new Locator(By.xpath("//div[@class='empty-bag-img']"), "Empty cart");
    }

    Locator EmptyCartHomeButtonLocator() {
        return new Locator(By.xpath("//button[@class='btn fill shadow proceed mb30' and contains(text(),'START SHOPPING')]"),
                "Home Button on Empty Cart Page");
    }

    Locator ShoppingCartTableRightColumn() {
        return new Locator(By.xpath("/following-sibling::div[@class='value']"),
                "Right columns");
    }

    Locator ShoppingCartTableLeftColumn() {
        return new Locator(By.xpath("//div[@class='payment-tbl-data']/div/div[@class='name']"),
                "Left columns");
    }

    Locator extendedProductNameLocator() {

        return new Locator(By.xpath("//td/a/span[@class='product-name']"), "ProductName ");
    }

    Locator inStockMessage() {
        return new Locator(By.xpath(
                "//span[@class='product_qty_blck'][1]/ancestor::tr[@id='product_block']//span[contains(@style,'color:red')][contains(text(),'in stock')]"),
                "in stock message");
    }

    Locator CouponCodeTextBox() {
        return new Locator(By.xpath("//div[@class='coupon-btn-wrap']"), "Apply Coupen Text");
    }
    
    Locator CouponCodeTextBoxNykaaMan() {
        return new Locator(By.xpath("//input[@id='coupon_code']"), "Apply Coupen Text");
    }
    Locator CouponCodeTextBox_text(){
        return new Locator(By.xpath("//div[@class='coupon-btn-wrap']//input"),"Apply coupon Textbox");
    }

    Locator ApplyCouponButton() {
        return new Locator(By.xpath("//div[@class='coupon-btn-wrap']//button[@type='submit']"), "Apply Coupon Button");
    }
    
    Locator ApplyCouponButtonNykaaMan() {
        return new Locator(By.xpath("//button[@value='Apply Coupon']"), "Apply Coupon Button");
    }
    
  

    Locator CancelCouponbutton() {

        return new Locator(By.xpath("//div[@class='coupon-btn-wrap']//button[@type='button']"), "Cancel Coupon Button");
    }
    
    Locator CancelCouponbuttonNykaaMan() {

        return new Locator(By.xpath("//button[@title='Cancel Coupon']"), "Cancel Coupon Button");
    }

    Locator OutOfStockInbagMessage() {
        return new Locator(By.xpath(
                "//p[@class='item-msg error'][contains(text(),'currently out of stock')] | //span[@class='error-msg'] [contains(text(),'The requested quantity is not available')]"),
                "Out Of Stock Message");
    }

    Locator closelabel() {
        return new Locator(By.id("closelb"), "Close lb popup");
    }

    Locator Checkout_Button() {
        return new Locator(By.xpath("//span[text()='CHECKOUT ']"), "Checkout Button");
    }

    Locator ProceedWithoutFreeBies(){
        return new Locator(By.xpath("//button[@type='button']/span[contains(text(), 'Proceed')]"), "Proceed Without FreeBies");
    }

    Locator ProceedToPayButton() {
        return new Locator(By.xpath("//button[@type='button'][contains(text(), 'PROCEED TO PAY')]"), "Proceed To Pay Button");
    }

    Locator LoaderIcon() {
        return new Locator(By.className("update_loader"), "Update Icon");
    }

    Locator LoadingBar() {
        return new Locator(By.xpath("//span[@id='loading']"), "Loading Bar");
    }

    Locator exceedQuantityMessageinBag() {
        return new Locator(By.xpath("//p[@id='exceed_message'][contains(text(),'5 quantities')]"),
                "Exceed Quantity Message");
    }

    Locator productInBag() {
        return new Locator(By.id("product_block"), "product in bag");

    }

    Locator ProductRowsInBag() {

        return new Locator(By.xpath("//div[@class='flip-container    ']"),
                "Product Row in Bag ");
    }
    
    Locator ProductRowsInBagNykaaMn() {

        return new Locator(By.xpath("//form[@id='mobile-checkout-item']//div[@class='content_wrapper']"),
                "Product Row in Bag ");
    }

    Locator ServiceRowsInBag() {
        return new Locator(By.xpath("//tr[@id='product_block']"), "Services In Bag");
    }

    public Locator BackMenuCart() {
        return new Locator(By.xpath("//button[@class='back-btn']"), "Back Menu Of Cart");
    }

    public Locator BackMenuCartNykaaMan() {
        return new Locator(By.id("back_button"), "Back Menu Of Cart");
    }
    
    public Locator BackMenuCartEmptyNykaaMan() {
        return new Locator(By.id("back_img"), "Back Menu Of Cart");
    }
    
   
    Locator ProductsInBagsLocator() {
        return new Locator(
                By.xpath("//div[@class='bottom-detail clearfix']/div//div[@class='price']/span[@class='actual-price']/ancestor::div//i[@class='remove-product']"),
                "products in bag");
		/*
		 * return new Locator( By.xpath(
		 * "//td[@class='last_col']/span/img[@title='Remove item'][not(contains(@style,'display:none'))]"
		 * ), "Remove Icons");
		 */
    }
    
    Locator DeleteItemFromCartButtonNykaaMan() {
        return new Locator(
                By.xpath("//form[@id='mobile-checkout-item']//div[@class='del_btn']/img"),
                "delete item from cart button");
		/*
		 * return new Locator( By.xpath(
		 * "//td[@class='last_col']/span/img[@title='Remove item'][not(contains(@style,'display:none'))]"
		 * ), "Remove Icons");
		 */
    }
    
    Locator ProductsInBagsLocatorNykaaMan() {
        return new Locator(
                By.xpath("//form[@id='mobile-checkout-item']//div[@class='product_txt']"),
                "products in bag");
		/*
		 * return new Locator( By.xpath(
		 * "//td[@class='last_col']/span/img[@title='Remove item'][not(contains(@style,'display:none'))]"
		 * ), "Remove Icons");
		 */
    }

    Locator RemoveProduct_From_cart() {
        return new Locator(By.xpath("//div[@class='flip-container flipped   ']//button[text()='Remove']"),"Remove Button in Cart");
    }

    Locator ExtendedRemoveProdutFromBagLocator() {
        return new Locator(By.xpath("//i[@class='remove-product']"), "Remove Button Locator");

    }

    Locator QuantityUpdateButton() {

        return new Locator(By.xpath("//div[@class='bottom-detail clearfix']/div[@class='qty']"),
                "Update Button");
    }


    Locator FreeShippingLable() {
        return new Locator(By.xpath("//div[text()='Shipping Charge']"), "Free Shiping lable");

    }

    Locator SlidingCartLogo() {
        return new Locator(By.id("sliding_cart_bag_logo"), "Sliding Logo");

    }
    Locator EmptyCartLocatorReact() {
        return new Locator(By.xpath("//*[@class='remove-product']"), "Remove cart");
    }

    Locator ApplyReedemButton() {
        return new Locator(By.xpath("//button[@class='text-btn medium-strong']"), "Apply Reedem Button");
    }

    Locator ApplyRemoveButtonofRewardPoint() {
        return new Locator(By.xpath("[contains(text(),'')]"),"Apply Remove Button of Reward Point");
    }

    Locator checkoutLoader() {
        return new Locator(By.xpath("//span[@id='loading']"), "CheckOut Loader");
    }

    Locator FooterPopUp(){
        return new
                Locator(By.xpath("//div[@class='close']"),"Footer Pop-up");
    }

    Locator Maximum_quantity_reached(int index) {
        return new Locator(By.xpath("//div[@class='container-box unavailable']/div[1]/div[2]/div[@class='curious-msg']"),
                "Product name");
    }

    Locator removeproduct(){
        return new Locator(By.xpath("/ancestor::*//i[@class='remove-product']"), "Remove products");
    }

    Locator shiptoThisAddress(){
        return new Locator(By.xpath("//button[contains(text(),'SHIP TO THIS ADDRESS')]"), "Ship to this address Button");
    }


    public void removeAllProductNykaaMan()
            throws InstantiationException, IllegalAccessException, InterruptedException, Throwable {
        waitUntilDisplayed(BackMenuCartNykaaMan(), 10);

        int count = getWebElements(ProductsInBagsLocatorNykaaMan()).size();

        for (int i = 0; i < count; i++) {
           Thread.sleep(5000);
           click(DeleteItemFromCartButtonNykaaMan());
           Thread.sleep(5000);
          
               
            }
        click(BackMenuCartEmptyNykaaMan());
        }
    
    
    public void removeAllProduct()
            throws InstantiationException, IllegalAccessException, InterruptedException, Throwable {
        waitUntilDisplayed(BackMenuCart(), 10);

        int count = getWebElements(ProductsInBagsLocator()).size();

        for (int i = 0; i < count; i++) {
            waitUntilDisappear(LoadingBar());
            List<WebElement> removableProducts = getWebElements(ProductsInBagsLocator());
            for (WebElement removeProduct : removableProducts) {
                click(removeProduct, "Remove button of item : " + i);
                Thread.sleep(4000);
                bringElementIntoView(RemoveProduct_From_cart());
                click(RemoveProduct_From_cart());
                Thread.sleep(5000);
                waitUntilDisplayed(EmptyCartLocator(), 10);
                break;
            }
        }
        exitEmptyCart();
    }

    private void exitEmptyCart() throws Throwable {
        waitUntilDisplayed(EmptyCartLocator(), 10);
        bringElementIntoView(EmptyCartHomeButtonLocator());
        click(EmptyCartHomeButtonLocator());
        waitUntilDisappear(EmptyCartHomeButtonLocator());
    }

    public boolean isSlidingCartOpen() {

        return waitUntilDisplayed(SlidingCartLogo(), 4);

    }

    public boolean IncreaseQuantityOfProduct(WebDriver browser)
            throws InterruptedException, InstantiationException, IllegalAccessException {
        boolean isIncreasedSuccessfully = true;

        while (waitUntilDisplayed(FreeShippingLable(), 5)) {
            click(QuantityUpdateButton());
			/*
			 * WebElement quantityPlus = browser .findElement(By.xpath(
			 * "//img[@id='Sym'][@class='update_p_qty'][not(contains(@style,'hidden'))]" ));
			 * quantityPlus.click();
			 */
            if (waitUntilDisplayed(OutOfStockInbagMessage(), 5)) {

                click(BackMenuCart());
                waitUntilDisappear(BackMenuCart());
                // browser.findElement(By.id("back_menu")).click();
                isIncreasedSuccessfully = false;

                break;
            }
            if (waitUntilDisplayed(exceedQuantityMessageinBag(), 4)) {
                click(closelabel());
                // browser.findElement(By.id("closelb")).click();
                Thread.sleep(4000);
                click(BackMenuCart());
                waitUntilDisappear(BackMenuCart());
                // browser.findElement(By.id("back_menu")).click();
                isIncreasedSuccessfully = false;
                break;
            }

            if (isAlertPresent(browser)) {
                dismissAlert();
                isIncreasedSuccessfully = false;
                break;

            } else
                waitUntilDisappear(LoaderIcon());

            // waitForJQuery(browser);

        }
        return isIncreasedSuccessfully;
    }

    public boolean isProductPresentInBag(ProductDetailData productDetailData) throws Throwable, Throwable {
        boolean isProductPresent = false;
        closeGetAppPopUp();
        goToCart();
        closeGetAppPopUp();
        if (isPresent(EmptyCartLocator())) {
            exitEmptyCart();
            return false;
        } else {
            String productName = productDetailData.getProductName();
            System.out.println(productName.replaceAll("\\(.*?\\)", ""));
            int itemsInCart = getWebElements(ProductRowsInBag()).size();
            for (int i = 1; i <= itemsInCart; i++) {
                Locator productNameLocator = CartProductLocator(i);
                if(isDisplayed(Maximum_quantity_reached(i))){
                    Locator removeproduct = Maximum_quantity_reached(i).concatLocator(removeproduct());
                    click(removeproduct);
                    click(RemoveProduct_From_cart());
                    Thread.sleep(3000);
                }
                String ProductNameFromUI = getText(productNameLocator).split("-")[0];
                System.out.println(ProductNameFromUI.replaceAll("\\(.*?\\)", ""));
                String name1= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
                System.out.println(productName.replaceAll("\\(.*?\\)", ""));
                String name2= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
                System.out.println(productName.replaceAll("\\(.*?\\)", ""));
                String name3= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
                System.out.println(ProductNameFromUI.replaceAll("\\(.*?\\)", ""));
                String name4= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
                if (productName.contains("...")) {
                    String test = productName.replace("...", "");
                    System.out.println(test);
                    String test1=ProductNameFromUI.replace("...", "");
                    System.out.println(test1);
                    if (ProductNameFromUI.contains(test) || productName.contains(test1)) {
                        isProductPresent = true;
                        break;
                    }
                } else if (name1.contains(name2)||name3.contains(name4)) {
                    isProductPresent = true;
                    break;

                }

            }
        }

        if (waitUntilDisplayed(BackMenuCart(), 3)) {
            click(BackMenuCart());
            waitUntilDisappear(BackMenuCart());
        }
        return isProductPresent;
    }

    
    public boolean isProductPresentInBagNykaaMan(ProductDetailData productDetailData) throws Throwable, Throwable {
        boolean isProductPresent = false;
        try {
        closeGetAppPopUp();
        }
        catch(Exception e)
        {
        	
        }
        goToCart();
        try {
            closeGetAppPopUp();
            }
            catch(Exception e)
            {
            	
            }
       
        if (isPresent(EmptyCartLocator())) {
            exitEmptyCart();
            return false;
        } else {
            String productName = productDetailData.getProductName();
            System.out.println(productName.replaceAll("\\(.*?\\)", ""));
            int itemsInCart = getWebElements(ProductRowsInBagNykaaMn()).size();
            for (int i = 1; i <= itemsInCart; i++) {
                Locator productNameLocator = CartProductLocatorNykaaMan(i);

                String ProductNameFromUI = getText(productNameLocator).split("-")[0];
                System.out.println(ProductNameFromUI.replaceAll("\\(.*?\\)", ""));
                String name1= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
                System.out.println(productName.replaceAll("\\(.*?\\)", ""));
                String name2= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
                System.out.println(productName.replaceAll("\\(.*?\\)", ""));
                String name3= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
                System.out.println(ProductNameFromUI.replaceAll("\\(.*?\\)", ""));
                String name4= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
                if (productName.contains("...")) {
                    String test = productName.replace("...", "");
                    System.out.println(test);
                    String test1=ProductNameFromUI.replace("...", "");
                    System.out.println(test1);
                    if (ProductNameFromUI.contains(test) || productName.contains(test1)) {
                        isProductPresent = true;
                        break;
                    }
                } else if (name1.contains(name2)||name3.contains(name4)) {
                    isProductPresent = true;
                    break;

                }

            }
        }

        if (waitUntilDisplayed(BackMenuCartNykaaMan(), 3)) {
            click(BackMenuCartNykaaMan());
            waitUntilDisappear(BackMenuCartNykaaMan());
        }
        return isProductPresent;
    }

    public boolean isServicePresentInBag(BeautyServiceData beautyServiceData) throws Throwable, Throwable {
        boolean isServicePresent = false;
        String serviceName = beautyServiceData.getServicename();
        List<WebElement> servicerow = getWebElements(ServiceRowsInBag());
        for (WebElement service : servicerow) {
            Locator ServieListBagLocator = new Locator(By.xpath(getAbsoluteXPath(service)), "service List ");
            Locator serviceNameLocator = ServieListBagLocator.concatLocator(extendedProductNameLocator());

            String ServiceNameFromUI = getText(serviceNameLocator);
            if (ServiceNameFromUI.contains("...")) {
                if (serviceName.contains(ServiceNameFromUI.replace("...", ""))) {
                    isServicePresent = true;
                    break;
                }
            } else if (serviceName.equalsIgnoreCase(ServiceNameFromUI)) {
                isServicePresent = true;
                break;

            }

        }

        if (waitUntilDisplayed(BackMenuCart(), 3)) {
            click(BackMenuCart());
            waitUntilDisappear(BackMenuCart());
        }
        return isServicePresent;
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

    public boolean isInstockMessageDisplay() throws Throwable, IllegalAccessException {
        HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(driver);
        if (waitUntilDisplayed(HeaderBar_Mobile.CartLocator(), 4)) {
            click(HeaderBar_Mobile.CartLocator());
            waitUntilDisplayed(productInBag(), 5);
            return waitUntilDisplayed(inStockMessage(), 3);
        } else {
            return waitUntilDisplayed(inStockMessage(), 3);
        }

    }

    public void checkOut() throws InterruptedException, InstantiationException, IllegalAccessException {

        if (!waitUntilDisplayed(BackMenuCart(), 3)) {
            goToCart();
        }
        if (isPresent(ProceedWithoutFreeBies())) {
            click(ProceedWithoutFreeBies());
        } else {
            click(Checkout_Button());

            CheckOutPage_Mobile checkoutPage = new CheckOutPage_Mobile(driver);
            checkoutPage.waitUntilDisappear(checkoutPage.checkoutPageLoader());
        }
    }
    
    
    public void checkOutNykaaMan() throws InterruptedException, InstantiationException, IllegalAccessException {

        if (!waitUntilDisplayed(BackMenuCartNykaaMan(), 3)) {
            goToCart();
        }
        if (isPresent(ProceedWithoutFreeBies())) {
            click(ProceedWithoutFreeBies());
        } else {
            click(Checkout_Button());

            CheckOutPage_Mobile checkoutPage = new CheckOutPage_Mobile(driver);
            checkoutPage.waitUntilDisappear(checkoutPage.checkoutPageLoader());
        }
    }

    public boolean ApplyCoupon(CheckoutData checkoutData) throws Throwable, Throwable {
       // bringElementIntoView(CouponCodeTextBox());
        System.out.println(checkoutData.getCouponcode());
        System.out.println(CouponCodeTextBox().getBy());
        bringElementIntoView(CouponCodeTextBox());
        click(CouponCodeTextBox().getBy());
        //JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
        //myExecutor.executeScript("arguments[0].value='Kirtesh';", CouponCodeTextBox());
    //   driver.findElement(CouponCodeTextBox().getBy()).sendKeys("Sourabh");
        EnterValue(CouponCodeTextBox(), checkoutData.getCouponcode());
        click(ApplyCouponButton());
        return waitUntilDisplayed(CancelCouponbutton(), 20);

        // return getShoppingTableData("Coupon Discount");

    }

    public boolean ApplyCouponNykaaMan(CheckoutData checkoutData) throws Throwable, Throwable {
        // bringElementIntoView(CouponCodeTextBox());
         System.out.println(checkoutData.getCouponcode());
         System.out.println(CouponCodeTextBoxNykaaMan().getBy());
         bringElementIntoView(CouponCodeTextBoxNykaaMan());
         click(CouponCodeTextBoxNykaaMan().getBy());
         //JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
         //myExecutor.executeScript("arguments[0].value='Kirtesh';", CouponCodeTextBox());
     //   driver.findElement(CouponCodeTextBox().getBy()).sendKeys("Sourabh");
         EnterValue(CouponCodeTextBoxNykaaMan(), checkoutData.getCouponcode());
         click(ApplyCouponButtonNykaaMan());
         return waitUntilDisplayed(CancelCouponbuttonNykaaMan(), 20);

         // return getShoppingTableData("Coupon Discount");

     }
    public boolean CancelCoupon() throws Throwable, Throwable {
        click(CancelCouponbutton());
        Thread.sleep(5000);
        return waitUntilDisplayed(CouponCodeTextBox(), 30);
    }
    
    public boolean CancelCouponNykaaMan() throws Throwable, Throwable {
        click(CancelCouponbuttonNykaaMan());
        Thread.sleep(5000);
        return waitUntilDisplayed(ApplyCouponButtonNykaaMan(), 30);
    }

    public String getShoppingTableData(String Rowname) throws Throwable, Throwable {
        waitUntilDisappear(LoaderIcon());
        List<WebElement> LeftColumns = getWebElements(ShoppingCartTableLeftColumn());
        Locator rightColumn = null;
        for (WebElement column : LeftColumns) {
            Locator leftColumnLocator = new Locator(By.xpath(getAbsoluteXPath(column))," Column value" );
            String value = getText(leftColumnLocator);
            rightColumn = leftColumnLocator.concatLocator(ShoppingCartTableRightColumn());
            if(value.contains(Rowname))
                break;
        }
        return getText(rightColumn);
    }

    /**
     * @throws Throwable
     * @throws InstantiationException
     *
     */
    public void removeFirstProduct() throws InstantiationException, Throwable {
        waitUntilDisplayed(BackMenuCart(), 5);
        List<WebElement> removableProducts = getWebElements(ProductsInBagsLocator());

        Locator removeProductLocator = new Locator(By.xpath(getAbsoluteXPath(removableProducts.get(0))), "Product");
        Locator removeLocator = removeProductLocator.concatLocator(ExtendedRemoveProdutFromBagLocator());
        click(removeLocator);
        // waitUntilDisappear(removeLocator);
        removableProducts = getWebElements(ProductsInBagsLocator());

        click(BackMenuCart());
        waitUntilDisappear(BackMenuCart());

    }

    public void goToCart() {
        try {
            if(waitUntilDisplayed(CartLocator(), 5))
            {
                bringElementIntoView(CartLocator());
                click(CartLocator());
            }
        } catch (Throwable e) {
        }

    }

    public void gotoProccedtWithoutFreeBies() {
        try{
            if(waitUntilDisplayed(ProceedWithoutFreeBies(), 5)){
                bringElementIntoView(ProceedWithoutFreeBies());
                click(ProceedWithoutFreeBies());
            }
            Thread.sleep(3000);
            if(isDisplayed(shiptoThisAddress())){
                click(shiptoThisAddress());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void gotoProceedToPayButton() {
        try{
            if(waitUntilDisplayed(ProceedToPayButton(), 5)){
                bringElementIntoView(ProceedToPayButton());
                click(ProceedToPayButton());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void gotoHomeNykaaMan() throws InstantiationException, IllegalAccessException{
        try{
            if(waitUntilDisplayed(BackMenuCartNykaaMan(),5)) {
                click(BackMenuCartNykaaMan());
            }
            if(waitUntilDisplayed(BackMenuCartEmptyNykaaMan(),5)) {
                click(BackMenuCartEmptyNykaaMan());
            }
        }catch (Throwable e){
        	
        }
    }

    
    public void gotoHome(){
        try{
            if(waitUntilDisplayed(BackMenuCart(),5)) {
                click(BackMenuCart());
            }
        }catch (Throwable e){

        }
    }
    public void closefooterPopup() throws IllegalAccessException, InstantiationException {

                click(FooterPopUp());

    }

    public boolean isProductPresentInBag() throws Throwable, Throwable {
        try {
            goToCart();
            if (isPresent(ProductsInBagsLocator())) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable e) {
            return false;
        }

    }

    public boolean isProductPresentInBagReact() throws Throwable, Throwable {
        try {

            if (isPresent(EmptyCartLocatorReact())) {

                return true;
            } else {
                return false;
            }
        } catch (Throwable e) {
            return false;
        }

    }


    public boolean applyRewardPoint() throws Throwable {
        try {
            String ButtonName = getText(ApplyReedemButton());
            Locator Applybutton = ApplyReedemButton().concatLocator(ApplyRemoveButtonofRewardPoint());
            if(ButtonName.contains("Remove"))
            {
                click(Applybutton);
            }
            click(ApplyReedemButton());
            Wait(2);
            waitUntilDisappear(checkoutLoader());
            Locator RemoveButton = ApplyReedemButton().concatLocator(ApplyRemoveButtonofRewardPoint());
            waitUntilDisplayed(RemoveButton, 5);
            return driver.findElement(RemoveButton.getBy()).isDisplayed();
        }catch (Exception e){
            System.out.println("No Reward Point applicable to user");
            return true;
        }
    }


}
