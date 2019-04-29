package PagesNykaaApp;


import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.PartialStringList;
import FrameWorkNykaa.TestListner;

import DataNykaa.ProductDetailData;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListingPage extends AppAction
{


    public ListingPage(AndroidDriver driver){
        this.driver = driver;
    }

    private String filtername;

    private String selectionCriteria;

    private String count;

    Locator productList(){
        return new Locator(By.xpath("//android.support.v7.widget.RecyclerView//android.widget.FrameLayout//android.widget.RelativeLayout[contains(@resource-id,'layout_product')]"), "ProductList");
    }

    Locator configurableSizeProductList(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_option_name')][contains(@text,'Sizes')]/ancestor::android.widget.RelativeLayout[contains(@resource-id,'layout_product')]"), "Configurable Size Product List");
    }

    Locator simpleProductList(){
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'btn_add_to_bag')]/ancestor::android.widget.RelativeLayout[contains(@resource-id,'layout_product')] | //android.widget.Button[contains(@resource-id,'btn_add_to_bag')]/ancestor::android.widget.LinearLayout[contains(@resource-id,'layout_product')]"), "Simple Product");
    }

    Locator configurableShadeProductList(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_option_name')][contains(@text,'Shades')]/ancestor::android.widget.RelativeLayout[contains(@resource-id,'layout_product')]"),"ConfigurablePrductList");
    }

    Locator shadeLocator(){
        return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'img_shade')]/ancestor::android.widget.FrameLayout[contains(@resource-id,'layout_container')]"),"shades");
    }

    Locator NewShadeLocator(int i){
        return new Locator(By.xpath("//android.widget.FrameLayout//android.widget.LinearLayout[@index=" +i+"]//android.widget.ImageView[contains(@resource-id,'img_shade')]"),"Product shades");
    }

    Locator NewSizeLocator(int i){
        return new Locator(By.xpath("//android.widget.FrameLayout//android.widget.LinearLayout[@index=" +i+"]//android.widget.TextView[contains(@resource-id,'txt_size')]"), "Product Size");
    }

    Locator Applicable_shades_In_PDP(){
        return new Locator(By.xpath("//android.widget.FrameLayout//android.widget.LinearLayout//android.widget.ImageView[contains(@resource-id,'img_shade')]"),"All Available Shades in Product");
    }

    Locator Apllicable_size_In_PDP(){
        return new Locator(By.xpath("//android.widget.FrameLayout//android.widget.LinearLayout//android.widget.TextView[contains(@resource-id,'txt_size')]"),"All Available Size in Product Page");
    }

    Locator sizeLocator(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_size')]/ancestor::android.widget.LinearLayout"),"size");
    }

    Locator addToBagButton() {
        return new Locator(By.xpath("//*[@text='ADD TO BAG']"),"Add to Bag Button");
    }

    Locator faltu() {
        return new Locator(By.id("com.fsn.nykaaewrygwehjdwdgwgdvwgdvwfcaf"),"faltu");
    }

    Locator productNameInListing(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_product_name')] | //android.widget.TextView[contains(@resource-id,'/product_title_text')]"),"Product NameText");
    }

    Locator productNameInListing(String productname){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_product_name')][@text='"+productname+"']"),"Dynamic Product");
    }

    Locator NotifyMeList(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_quantity')]/ancestor::android.widget.RelativeLayout[contains(@resource-id,'layout_product')]//android.widget.Button[@text='NOTIFY ME']/ancestor::android.widget.RelativeLayout[contains(@resource-id,'layout_product')]"),"OOS Products");
    }

    Locator NotifyMeButton(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_btn_title')][@text='NOTIFY ME']"),"Notify Me Button");
    }

    Locator WishlistButton(){
        return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'wish_img')]/ancestor::android.widget.RelativeLayout"), "Wishlist Button");
    }

    Locator WishlistButonofWishlistPage(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'wishlist_parent_layout')]//android.widget.ImageView[contains(@resource-id,'wish_img')]"),"Wishlist Button");
    }

    Locator WistlistText(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'empty_wishList_view')]//android.widget.TextView[contains(@resource-id,'add_product_wish_text')]"),"Empty Wistlist");
    }

    Locator AddToBagWishlistButton(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'wishlist_parent_layout')]//android.widget.Button[contains(@resource-id,'btn_add_to_bag')]"),"Add to bag");
    }

    Locator WishlistAddToBag(String productname){
        return new Locator(By.xpath("//android.widget.TextView[@text='"+productname+"']/ancestor::android.widget.LinearLayout/following-sibling::android.widget.LinearLayout//android.widget.Button[@text='ADD TO BAG']"),"Add to Bag");
    }

    Locator NotifyMeText(){
        return new Locator(By.xpath("//android.widget.TextView[@text='You will be notified when product will be in stock']"),"Notify Me Notification");
    }

    Locator WishlistAddNotification(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'sb__text')][@text='Added to wishlist']"),"Added to wishlist");
    }

    Locator changeTheView(){
        return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'toggle_image_view')]"),"change the view");
    }

    Locator listViewLocator(){
        return new Locator(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'r_product_list')]//android.widget.FrameLayout/android.widget.LinearLayout"),"list view");
    }

    Locator sorting(){
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'sort_btn_layout')]"),"Sorting");
    }

    Locator SortBy(String sort){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'category_name')][@text='"+sort+"']"),"sort by");
    }

    Locator fullPageLoadingIcon(){
        return new Locator(By.xpath("//android.widget.ProgressBar[contains(@resource-id,'progressBar')]"),"Loader");
    }

    Locator discountPercent(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_discount')]"),"discount percent");
    }

    Locator priceOfTheProduct(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_price')]"),"Price Of The Product");
    }

    Locator customerRating(){
        return new Locator(By.xpath("//android.widget.RatingBar[contains(@resource-id,'rating_bar')]"),"Rating Bar");
    }

    Locator filterButton(){
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'filter_btn_layout')]"),"Filter Button");
    }

    Locator filterMethod(String filter){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_filter_label')][@text='"+filter+"']"),"Filter Method -- "+filter+"");
    }

    Locator filterCheckBox(String filterCheckBox){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_filter_label')][contains(@text,'"+filterCheckBox+"')]"),"Filter check box "+filterCheckBox+"");
    }

    Locator filterCheckBox() {
        return new Locator(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'filter_list')]//android.widget.TextView[contains(@resource-id,'txt_filter_label')][contains(@text,'(')]"), "Filter CheckBox");
    }

    Locator filterApply(){
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'btn_apply_filters')]"),"Apply Filter Button");
    }

    Locator title(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'header_text')]"),"Title of Product List");
    }

    Locator addingToBagLoader(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'Adding to bag...')]"),"Adding To Bag");
    }

    Locator shopAllProducts(){
        return new Locator(By.xpath("//android.widget.Image[@content-desc='shoppall'][@clickable='true'] | //android.widget.Image[@text='shoppall'][@clickable='true']"),"Shop All Products");
    }

    Locator RateButton(){
        return new Locator(By.xpath("//android.widget.RadioButton[@resource-id = 'five_star']"),"Rate Button");
    }

    Locator priceDiscountLayout() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_price')]/ancestor::android.widget.LinearLayout//android.widget.LinearLayout[contains(@resource-id,'layout_discount')]"), "Price And Discount Layout");
    }

    Locator noMoreProducts(){
        return new Locator(By.xpath("//android.widget.TextView[@text='No More Products']"),"No More Products");
    }


    /******************************************Services*****************************************************************************/

    public String getBreadcrumbsValue(){
        return getText(title());

    }

    public String applyFilter(String filterName) throws InterruptedException, IllegalAccessException, InstantiationException {
        return applyFilter(filterName, null);
    }

    public String applyFilter(String filtername, String criteria) throws InterruptedException, IllegalAccessException, InstantiationException {
        if(!isDisplayed(filterButton())){
            bringElementIntoViewUp(filterButton(),1);
        }
        bringElementIntoViewDown(filterButton(), 5);
        click(filterButton());
        click(filterMethod(filtername));
        String criteriaName;
        if (criteria != null) {
            criteriaName = getText(filterCheckBox(criteria));
        } else {
            criteriaName = selectRandomlyOnWebElements(filterCheckBox());
        }
        setSelectionCriteria(criteriaName.split(" ")[0]);
        Matcher count = Pattern.compile("\\(([^)]+)\\)").matcher(criteriaName);
        String exactCount = null;
        while (count.find()) {
            exactCount = count.group(1);
            System.out.println(count.group(1));
        }
        Thread.sleep(2000);
        setCount(exactCount);
        setSelectionCriteria(criteriaName);
        click(filterCheckBox(criteriaName));
        //    click(filterCheckBox(criteria));
        click(filterApply());
        setFiltername(filtername);
        waitUntilElementDisappear(fullPageLoadingIcon(), 15);
        return criteriaName;
    }

    public boolean isFilterApplied(String criteria) throws InterruptedException, InstantiationException, IllegalAccessException {
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        boolean list = false;
        if (getFiltername().equalsIgnoreCase("Price")) {
            list = isPriceFilterApplied(criteria);
        } else if (getFiltername().equalsIgnoreCase("Brand")) {
            list = isBrandFilterApplied();
        } else if (getFiltername().equalsIgnoreCase("Discount")) {
            list = isDiscountFilterApplied();
        }
        return list;
    }

    public String applySort(String sortname) throws Throwable {

        bringElementIntoViewDown(sorting(), 10);
        click(sorting());
        click(SortBy(sortname));
        waitUntilElementDisappear(fullPageLoadingIcon(), 15);
        return sortname;
    }

    public boolean isSortApplied(String sortname) throws InterruptedException, InstantiationException, IllegalAccessException {
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        List <WebElement> elementList = null;
        ArrayList<String> sortedList = new ArrayList<>();
        Set<String> ObtainNameList = new LinkedHashSet<>();
        for(int i=1;i<7;i++) {
            if (sortname.equals("Brands A-Z")) {
                elementList = getWebElements(productNameInListing());
            }
            else if(sortname.equals("Discount: High to Low")){
                elementList = getWebElements(discountPercent());
            }
            else if(sortname.equals("Price: Low to High")){
                elementList=getWebElements(priceOfTheProduct());
            }
            else if(sortname.equals("Price: High to Low")){
                elementList=getWebElements(priceOfTheProduct());
            }
            else if(sortname.equals("Customer Top Rated")){
                elementList=getWebElements(customerRating());
            }
            for(WebElement element : elementList) {
                String text = element.getText().trim();
                if(text.contains("₹")) {
                    if(text.contains(" ")){
                        ObtainNameList.add(text.split(" ")[1]);
                    }
                    else {
                        ObtainNameList.add(text);
                    }
                }
                else {
                    ObtainNameList.add(text);
                }
            }
            bringElementIntoViewDown(faltu(), 2);
        }
        for (String s : ObtainNameList) {
            sortedList.add(s);
        }
        if(sortname.equals("Discount: High to Low") | sortname.equals("Price: High to Low")) {
            Collections.sort(sortedList, Collections.<String>reverseOrder());
        }
        else {
            Collections.sort(sortedList);
        }
        TestListner.testing.get().log(LogStatus.INFO,ObtainNameList.toString());
        TestListner.testing.get().log(LogStatus.INFO,sortedList.toString());
        System.out.println(ObtainNameList);
        System.out.println(sortedList);

        return sortedList.toString().equals(ObtainNameList.toString());
    }

    public ProductDetailData addToBagAnySimpleProduct() throws InterruptedException, IllegalAccessException, InstantiationException {
        ProductDetailData productDetail = new ProductDetailData();
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        bringElementIntoViewDown(simpleProductList(),20);
        List<WebElement> webElements = getWebElements(simpleProductList());
        for (WebElement element:webElements)
        {
            Locator SimpleLocator = new Locator(element,By.xpath("//android.widget.Button[contains(@resource-id,'btn_add_to_bag')]"),"simple");
            bringElementIntoViewDown(SimpleLocator,7);
            productDetail= getProductDetail(element);
            click(SimpleLocator);
            break;
        }
        waitUntilElementDisappear(addingToBagLoader(),10);
        return productDetail;
    }

    public ProductDetailData addToBagAnySizeConfigurableProduct() throws InterruptedException, InstantiationException, IllegalAccessException {
        ProductDetailData productDetail = new ProductDetailData();
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        bringElementIntoViewDown(configurableSizeProductList(),20);
        List<WebElement> webElements = getWebElements(configurableSizeProductList());
        for (WebElement element:webElements)
        {
            Locator PreviewSizeLocator = new Locator(element, By.xpath("//android.widget.Button[contains(@resource-id,'btn_add_to_bag')][@text='PREVIEW SIZES']"),"product");
            bringElementIntoViewDown(PreviewSizeLocator,7);
            productDetail= getProductDetail(element);
            click(PreviewSizeLocator);
            boolean isadded = false;
            List<WebElement>  sizeElements = getWebElements(Apllicable_size_In_PDP());
            int size = sizeElements.size();
            int i = 0;
            for(WebElement shadeElement:sizeElements){
                //Locator shadeLocator = new Locator(shadeElement,By.xpath("//android.widget.ImageView[contains(@resource-id,'img_shade')]/ancestor::android.widget.LinearLayout"),"shade");
                //Locator test = new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'img_shade')]/ancestor::android.widget.LinearLayout"), "ShadeSelector");
                click(NewSizeLocator(i));
                //checking shades is not OOS
                if(isDisplayed(addToBagButton())) {
                    click(addToBagButton());
                    isadded = true;
                    break;
                }
                i++;

            }
            if(isadded==true){
                break;
            }
        }
        return productDetail;
    }

    public ProductDetailData addToBagAnyShadeConfigurableProduct() throws IllegalAccessException, InstantiationException, InterruptedException {
        ProductDetailData productDetail = new ProductDetailData();
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        bringElementIntoViewDown(configurableShadeProductList(),37);
        List<WebElement> webelements = getWebElements(configurableShadeProductList());
        boolean isadded = false;
        for(WebElement element : webelements)
        {
            Locator PreviewShadeLocator = new Locator(element, By.xpath("//android.widget.Button[contains(@resource-id,'btn_add_to_bag')][@text='PREVIEW SHADES']"), "product");
            bringElementIntoViewDown(PreviewShadeLocator, 7);
            productDetail = getProductDetail(element);
            click(PreviewShadeLocator);
            Thread.sleep(2000);
            List<WebElement>  shadeElements = getWebElements(Applicable_shades_In_PDP());
            int size = shadeElements.size();
            int i = 0;
            for(WebElement shadeElement:shadeElements){
                //Locator shadeLocator = new Locator(shadeElement,By.xpath("//android.widget.ImageView[contains(@resource-id,'img_shade')]/ancestor::android.widget.LinearLayout"),"shade");
                //Locator test = new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'img_shade')]/ancestor::android.widget.LinearLayout"), "ShadeSelector");
                click(NewShadeLocator(i));
                //checking shades is not OOS
                if(isDisplayed(addToBagButton())) {
                    click(addToBagButton());
                    isadded = true;
                    break;
                }
                i++;

            }
            if(isadded==true){
                break;
            }
        }
        return productDetail;
    }

    public ProductDetailData addToBagAnyProduct() throws IllegalAccessException, InstantiationException, InterruptedException {
        ProductDetailData productDetail = new ProductDetailData();
        if (waitUntilDisplayed(listViewLocator(), 5)) {
            click(changeTheView());
        }
        List<WebElement> elementList = getWebElements(productList());
        for (WebElement element : elementList) {
            Locator productType = new Locator(element, By.xpath("//android.widget.Button[contains(@resource-id,'btn_add_to_bag')]"), "Product Type");
            bringElementIntoViewDown(productType, 5);
            productDetail = getProductDetail(element);
            String type = getText(productType);
            click(productType);
            if (type.equals("PREVIEW SHADES")) {
                clickOnFirstShade();
                click(addToBagButton());
            } else if (type.equals("PREVIEW SIZES")) {
                clickOnFirstSize();
                click(addToBagButton());
            }
            break;
        }
        return productDetail;
    }

    public ProductDetailData addToBagFromWishlist() throws IllegalAccessException, InstantiationException {
        ProductDetailData productDetailData = new ProductDetailData();
        productDetailData = getProductDetail();
        click(AddToBagWishlistButton());
        //   click(WishlistAddToBag(productDetailData.getProductName()));
       /* TouchAction action = new TouchAction(driver);
        action.longPress(driver.findElement(addToBagButton().getBy()));*/

        return productDetailData;
    }

    public boolean removeFromWishlist() throws IllegalAccessException, InstantiationException, InterruptedException {
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        click(WishlistButonofWishlistPage());
        return isWishlistEmpty();

    }

    public boolean addToWishlist() throws IllegalAccessException, InstantiationException, InterruptedException {
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        List<WebElement> webElements = getWebElements(WishlistButton());
        for(WebElement element:webElements){
            Locator WishlistClick = new Locator(element, By.xpath("//android.widget.ImageView[contains(@resource-id,'wish_img')]"),"click wishlist");
            click(WishlistClick);
            break;
        }
        return isProductAddedToWishlist();
    }

    private void clickOnFirstShade() throws InterruptedException, IllegalAccessException, InstantiationException {
        List<WebElement>  shadeElements = getWebElements(shadeLocator());
        for(WebElement shadeElement:shadeElements){
            Locator shadeLocator = new Locator(shadeElement,By.xpath("//android.widget.ImageView[contains(@resource-id,'img_shade')]"),"shade");
            click(shadeLocator);
            break;

        }
    }

    private void clickOnFirstSize() throws InterruptedException, IllegalAccessException, InstantiationException {
        List<WebElement> sizeElements = getWebElements(sizeLocator());
        for(WebElement sizeElement:sizeElements){
            Locator sizeLocator = new Locator(sizeElement,By.xpath("//android.widget.TextView[contains(@resource-id,'txt_size')]"),"size");
            click(sizeLocator);
            break;
        }
    }

    public  void navigateToAnyShadeProductDetailPage() throws InterruptedException, InstantiationException, IllegalAccessException {
        ProductDetailData productDetailData = new ProductDetailData();
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        bringElementIntoViewDown(configurableShadeProductList(),20);
        List<WebElement> webElements = getWebElements(configurableShadeProductList());
        for (WebElement element : webElements) {
            Locator ProductImage = new Locator(element, By.xpath("//android.widget.ImageView[contains(@resource-id,'img_product')]"), "Product");
            bringElementIntoViewDown(ProductImage, 7);
            click(ProductImage);
            break;

        }
    }

    public  void navigateToAnySizeProduct() throws Throwable {
        ProductDetailData productDetailData = new ProductDetailData();
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        bringElementIntoViewDown(configurableSizeProductList(),20);
        // bringElementIntoView(configurableSizeProductList());
        getWebElements(configurableSizeProductList());
        List<WebElement> webElements = getWebElements(configurableSizeProductList());
        for(WebElement element:webElements){
            Locator ProductImage = new Locator(element, By.xpath("//android.widget.ImageView[contains(@resource-id,'img_product')]") , "Product Image");
            bringElementIntoViewDown(ProductImage, 7);
            click(ProductImage);
            break;
        }
    }

    public  void navigateToAnySimpleProduct() throws InterruptedException, InstantiationException, IllegalAccessException {
        bringElementIntoViewDown(simpleProductList(),20);
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        bringElementIntoViewDown(simpleProductList(),5);
        List<WebElement> webElements = getWebElements(simpleProductList());
        for(WebElement element:webElements){
            Locator ProductImage = new Locator(element, By.xpath("//android.widget.ImageView[contains(@resource-id,'img_product')]"), "Product");
            bringElementIntoViewDown(ProductImage, 7);
            click(ProductImage);
            break;
        }
    }

    public  void navigateToSubscribeProduct() throws InterruptedException, InstantiationException, IllegalAccessException {
        bringElementIntoViewDown(simpleProductList(),20);
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        ProductPage productPage = new ProductPage(driver);
        HeaderBar headerBar = new HeaderBar(driver);
        // bringElementIntoViewDown(simpleProductList(),5);
        ScrollDown(2);
        List<WebElement> webElements = getWebElements(simpleProductList());
        for(WebElement element:webElements){
            Locator ProductImage = new Locator(element, By.xpath("//android.widget.ImageView[contains(@resource-id,'img_product')]"), "Product");
            bringElementIntoViewDown(ProductImage, 7);
            click(ProductImage);
            bringElementIntoViewDown(productPage.subscribeBar(), 5);
            if(isDisplayed(productPage.subscribeBar())){
                break;
            }
            headerBar.BackButtonClick();
            Thread.sleep(2000);
        }
    }

    public void navigateToOutofStockProduct() throws InterruptedException, InstantiationException, IllegalAccessException {
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        while (!isDisplayed(noMoreProducts()) && !isDisplayed(NotifyMeList())) {
            bringElementIntoViewDown(NotifyMeList(), 2);
        }
        List<WebElement> webElements = getWebElements(NotifyMeList());
        for(WebElement element: webElements){
            Locator NotifyMeButton  = new Locator(element,By.xpath("//android.widget.Button[contains(@resource-id,'btn_add_to_bag')]"),"OOS Product");
            click(NotifyMeButton);
            break;
        }
    }

    public void navigateToDynamicCombo(ProductDetailData productDetailData) throws InterruptedException, InstantiationException, IllegalAccessException {
        if(waitUntilDisplayed(listViewLocator(),5)){
            click(changeTheView());
        }
        bringElementIntoViewDown(productNameInListing(productDetailData.getProductName()),20);
        click(productNameInListing(productDetailData.getProductName()));
    }

    public boolean changeTheViewOfProduct() throws IllegalAccessException, InstantiationException {
        click(changeTheView());
        return isViewChanged();
    }

    public boolean isNotifyMeClicked(){
        return waitUntilDisplayed(NotifyMeText(),5);
    }

    public boolean isWishlistEmpty(){
        return waitUntilDisplayed(WistlistText(), 5);
    }

    private boolean isViewChanged(){
        return waitUntilDisplayed(listViewLocator(),5);
    }

    private boolean isProductAddedToWishlist(){
        return waitUntilDisplayed(WishlistAddNotification(),6);
    }

    private ProductDetailData getProductDetail(WebElement productLayoutElement) {
        ProductDetailData product = new ProductDetailData();
        Locator ProductNameLocator = new Locator(productLayoutElement,productNameInListing().getBy(),"ProductName Text");
        String productName = getText(ProductNameLocator);
        product.setProductName(productName);
        return product;
    }

    protected ProductDetailData getProductDetail(){
        ProductDetailData product = new ProductDetailData();
        String productName = getText(productNameInListing());
        product.setProductName(productName);
        return product;
    }

    public PartialStringList getSearchedResult() throws Throwable {
        List<WebElement> ProductList = getWebElements(productNameInListing());
        PartialStringList productValueList = new PartialStringList();
        for(WebElement product: ProductList){
            // Locator ProductLocator = new Locator(product, By.xpath("//android.widget.TextView[contains(@resource-id,'txt_product_name')]"),"Product Name");
            String TitleProduct = getAttribute(productNameInListing(),"text");
            productValueList.add(TitleProduct.toLowerCase());
        }
        return productValueList;
    }

    public boolean isTopCategorySearched(String searchedProduct) throws IllegalAccessException, InterruptedException, InstantiationException {
        if(!waitUntilDisplayed(title(),3)){
            bringElementIntoViewDown(shopAllProducts(),35);
            click(shopAllProducts());
        }
        boolean isTopCategorySearched = false;
        if(getBreadcrumbsValue().contains(searchedProduct)){
            isTopCategorySearched = true;
        }
        return isTopCategorySearched;
    }

    public boolean isPriceFilterApplied(String criteria) throws InterruptedException, InstantiationException, IllegalAccessException {
        ArrayList<String> sortedList = new ArrayList<>();
        Set<String> ObtainNameList = new LinkedHashSet<>();
        List<WebElement> elementList;
        for (int i = 1; i < 7; i++) {
            elementList = getWebElements(priceOfTheProduct());
            for (WebElement element : elementList) {
                String text = element.getText().trim();
                if (text.contains("₹")) {
                    if (text.contains(" ")) {
                        String add = text.split(" ₹")[1];
                        if (add.contains(",")) {
                            add = add.replace(",", "");
                        }
                        ObtainNameList.add(add);
                    } else {
                        String add = text.split("₹")[1];
                        if (add.contains(",")) {
                            add = add.replace(",", "");
                        }
                        ObtainNameList.add(add);
                    }
                } else {
                    ObtainNameList.add(text);
                }
            }
            if(isDisplayed(noMoreProducts())){
                break;
            }
            bringElementIntoViewDown(faltu(), 3);
        }
        criteria = criteria.replaceAll("\\(.*\\)", "");
        String criteria1 = criteria.replace("₹", " ");
        String criteria2 = criteria1.trim();
        for (String s : ObtainNameList) {
            int y = Integer.parseInt(s);
            if (criteria2.contains("Above")) {
                if (y >= Integer.parseInt(criteria2.split(" -  ")[0]) && y <= 21474836) {
                    sortedList.add(s);
                }
            } else {
                if (y >= Integer.parseInt(criteria2.split(" -  ")[0]) && y <= Integer.parseInt(criteria2.split(" -  ")[1])) {
                    sortedList.add(s);
                }
            }
        }
        TestListner.testing.get().log(LogStatus.INFO,ObtainNameList.toString());
        TestListner.testing.get().log(LogStatus.INFO,sortedList.toString());
        System.out.println(ObtainNameList);
        System.out.println(sortedList);
        return sortedList.toString().equals(ObtainNameList.toString());
    }

    private boolean isBrandFilterApplied() throws InterruptedException, InstantiationException, IllegalAccessException {
        ArrayList<String> sortedList = new ArrayList<>();
        Set<String> ObtainNameList = new LinkedHashSet<>();
        List<WebElement> elementList;
        for (int i = 1; i < 7; i++) {
            elementList = getWebElements(productNameInListing());
            for (WebElement element : elementList) {
                String productName = getText(element).trim().toLowerCase();
                ObtainNameList.add(productName);
            }
            if(isDisplayed(noMoreProducts())){
                break;
            }
            bringElementIntoViewDown(faltu(), 2);
        }

        for (String s : ObtainNameList) {
            if (s.toLowerCase().contains(getSelectionCriteria().split(" ")[0].toLowerCase())) {
                sortedList.add(s);
            }
        }
        TestListner.testing.get().log(LogStatus.INFO,ObtainNameList.toString());
        TestListner.testing.get().log(LogStatus.INFO,sortedList.toString());
        System.out.println(ObtainNameList);
        System.out.println(sortedList);

        return sortedList.toString().equals(ObtainNameList.toString());
    }

    private boolean isDiscountFilterApplied() throws InterruptedException, InstantiationException, IllegalAccessException {
        ArrayList<String> sortedList = new ArrayList<>();
        Set<String> ObtainNameList = new LinkedHashSet<>();
        String productDiscount;
        List<WebElement> elementList;
        for (int i = 1; i < 7; i++) {
            elementList = getWebElements(discountPercent());
            for (WebElement element : elementList) {
                productDiscount = getText(element).split("%")[0];
                ObtainNameList.add(productDiscount);
            }
            if(isDisplayed(noMoreProducts())){
                break;
            }
            bringElementIntoViewDown(faltu(), 2);
        }
        String criteria = getSelectionCriteria();
        for (String s : ObtainNameList) {
            if (criteria.contains("Upto")) {
                criteria = criteria.replace("Upto", "").trim().split("%")[0];
                if (Integer.parseInt(s) <= Integer.parseInt(criteria)) {
                    sortedList.add(s);
                }
            } else {
                criteria = criteria.split("%")[0];
                if (Integer.parseInt(s) >= Integer.parseInt(criteria)) {
                    sortedList.add(s);
                }
            }
        }
        TestListner.testing.get().log(LogStatus.INFO,ObtainNameList.toString());
        TestListner.testing.get().log(LogStatus.INFO,sortedList.toString());
        System.out.println(ObtainNameList.toString());
        System.out.println(sortedList.toString());
        return ObtainNameList.toString().equals(sortedList.toString());
    }

    public boolean isCountOfProductSame() throws InterruptedException {
        String breadCrumb = getBreadcrumbsValue();
        Matcher count = Pattern.compile("\\(([^)]+)\\)").matcher(breadCrumb);
        String countInsideBracket = null;
        while (count.find()) {
            countInsideBracket = count.group(1);
            System.out.println(countInsideBracket);
        }

        String exactCount = countInsideBracket.split(" ")[0];
        Thread.sleep(2000);
        String countFromFilter = getCount();
        if (exactCount.equals(countFromFilter)) {
            return true;
        } else {
            return false;
        }

    }

    public String getFiltername() {
        return filtername;
    }

    public void setFiltername(String filtername) {
        this.filtername = filtername;
    }

    public String getSelectionCriteria() {
        return selectionCriteria;
    }

    public void setSelectionCriteria(String selectionCriteria) {
        this.selectionCriteria = selectionCriteria;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
