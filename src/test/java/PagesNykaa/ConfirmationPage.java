/**
 * 
 */
package PagesNykaa;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.CheckoutData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;

/**
 * @author nevil
 *
 */
public class ConfirmationPage extends BrowserAction {

	public ConfirmationPage(WebDriver browser) {
		this.driver = browser;
	}

	Locator PaymentDetailText(){
		return new Locator(By.xpath("//div[@class='payment-d']"),"Payment Box");
	}

	Locator ConfirmationMessageText(){
		return new Locator(By.xpath("//div[@class='confirmed-information']/div[@class='order-detail-info']"),"Confirmation Cod Page");
	}

	Locator extendedProductName(){
		return new Locator(By.xpath("//p[@class='p']/b"),"Extended ProductName");
	}

	Locator extendedProductPrize(){
		return new Locator(By.xpath("/descendant::a/span[@class='price']"),"product size");
	}

	Locator extendedProductQty(){
		return new Locator(By.xpath("//li[@class='two-item font-size'][contains(text(),'Quantity:')]"),"Product Quantity");
	}

	Locator OrderItems(){

		return new Locator(By.xpath("//div[@class='order-item']")," Order Item");
	}

	Locator DeliveryDetailTextview(){

		return new Locator(By.xpath("//div[@class='delevery-d']"),"Delivery Detail Text");
	}
	public void getPaymentData() throws Throwable, Throwable{
		System.out.println(getText(PaymentDetailText()));
	}

	public boolean verifyPaymentDetailWithCheckoutData(CheckoutData checkoutData) throws Throwable, Throwable
	{
		String PaymentDetail = getText(PaymentDetailText());
		String ExpectedDiscount="0";

		boolean isSubtotal = PaymentDetail.contains("Subtotal: Rs. "+NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(checkoutData.getSubtotal())));
		boolean isDescount;
		if(checkoutData.getRewardpoint()==null && checkoutData.getCouponcode()==null)
		{
			isDescount = PaymentDetail.contains("Discount: Rs. 0");
		}
		else if(checkoutData.getCouponcode()!=null)
		{
			isDescount = PaymentDetail.contains("Discount: -Rs. "+checkoutData.getCouponcode().trim());
			ExpectedDiscount=checkoutData.getCouponcode();
		}
		else
		{
			isDescount = PaymentDetail.contains("Discount: -Rs. "+checkoutData.getRewardpoint());
			ExpectedDiscount=checkoutData.getRewardpoint();
		}

		boolean isShipping = PaymentDetail.contains("Shipping: "+checkoutData.getShipping());

		boolean isFinalTotal  = PaymentDetail.contains("Final Total: Rs. "+ NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(checkoutData.getOrdertotal())));
		
		TestListner.testing.get().log(LogStatus.INFO, "Expected Payment Details ::  SubTotal: Rs. "+checkoutData.getSubtotal()+" Discount: -Rs. "+ExpectedDiscount+" Shipping: Rs.  "+checkoutData.getShipping()+" Final Total: Rs.  "+checkoutData.getOrdertotal());
		TestListner.testing.get().log(LogStatus.INFO, "Actual "+PaymentDetail);

		return (isSubtotal & isDescount & isShipping & isFinalTotal);



	}

	public boolean verifyDeliveryDetailWithCheckoutData(CheckoutData checkoutData) throws Throwable, Throwable
	{         String DeliveryDetail = getText(DeliveryDetailTextview());

	boolean isFirstname = DeliveryDetail.contains(checkoutData.getFirstname());
	boolean ispostCode = DeliveryDetail.contains(checkoutData.getPostcode());
	boolean isShippingAddress1 = DeliveryDetail.contains(checkoutData.getShipping_address1());
	boolean isShippingAddress2 = DeliveryDetail.contains(checkoutData.getShipping_address2());
	boolean isTelephone = DeliveryDetail.contains(checkoutData.getTelephone());
	
	TestListner.testing.get().log(LogStatus.INFO, "Expected Delivery Details : "+checkoutData.getFirstname()+" "+checkoutData.getPostcode()+" "+checkoutData.getShipping_address1()+", "+checkoutData.getShipping_address2()+" "+checkoutData.getTelephone());
	TestListner.testing.get().log(LogStatus.INFO, "Actual "+DeliveryDetail);
	return (isFirstname & ispostCode & isShippingAddress1&isShippingAddress2&isTelephone);






	}
	public List<ProductDetailData> verifyOrderItemDetail() throws IllegalArgumentException, IllegalAccessException{

		List<ProductDetailData> ProductItemsFromConfirmationPageUI = new ArrayList<ProductDetailData>();
		
		List<WebElement> orderItem = getWebElements(OrderItems());
		for(int x=1;x<=orderItem.size()-1;x++)
		{      ProductDetailData  ProductDetailData = new ProductDetailData();
		Locator ItemDetail = new Locator(By.xpath(getAbsoluteXPath(orderItem.get(x))),"Item from ConfiPage UI");
		Locator ProductNameLocator = ItemDetail.concatLocator(extendedProductName());
		Locator ProductPrizeLocator = ItemDetail.concatLocator(extendedProductPrize());
		Locator ProductQtyLocator = ItemDetail.concatLocator(extendedProductQty());
		ProductDetailData.setProductName(getText(ProductNameLocator));
		ProductDetailData.setProductPrize((getText(ProductPrizeLocator).replaceAll("\\D+", "")));
		ProductDetailData.setProductQuantity(getText(ProductQtyLocator).replaceAll("\\D+", ""));
		ProductItemsFromConfirmationPageUI.add(ProductDetailData) ;

		}

		return ProductItemsFromConfirmationPageUI;


	}

public String getConfirmationMessage() throws IllegalArgumentException, IllegalAccessException
{
	waitUntilDisplayed(ConfirmationMessageText(), 10);
	TestListner.testing.get().log(LogStatus.INFO, "Thank You Message:::: "+getText(ConfirmationMessageText()));
	return getText(ConfirmationMessageText());
}

}
