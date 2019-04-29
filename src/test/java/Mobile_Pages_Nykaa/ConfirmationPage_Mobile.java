/**
 * 
 */
package Mobile_Pages_Nykaa;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import DataNykaa.CheckoutData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;

/**
 * @author nevil
 *
 */
public class ConfirmationPage_Mobile extends BrowserAction {
	
	public ConfirmationPage_Mobile(WebDriver browser) {
		this.driver = browser;
	}
	
	Locator PaymentDetailText(){
		return new Locator(By.xpath("//header[@class='header extra-adjustment']"),"Payment Box");
		
		
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

	Locator Subtotal(){
		return new Locator(By.xpath("//div[@class='name'][contains(text(),'Subtotal')]/ancestor::div/div[@class='value']"), "Subtotal Value");
	}


	public void getPaymentData() throws Throwable, Throwable{
	      System.out.println(getText(PaymentDetailText()));
		}
		
		public boolean verifyPaymentDetailWithCheckoutData(CheckoutData checkoutData) throws Throwable, Throwable
		{
		String PaymentDetail = getText(PaymentDetailText());
		boolean isSubtotal = Subtotal().equals(checkoutData.getSubtotal());
		//boolean isSubtotal = PaymentDetail.contains("Subtotal: Rs. "+NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(checkoutData.getSubtotal())));
		boolean isDescount;
		if(checkoutData.getRewardpoint()==null)
		{
			isDescount = PaymentDetail.contains("Discount: Rs. 0");
		}else{
		isDescount = PaymentDetail.contains("Discount: "+checkoutData.getRewardpoint());
		}
		
	    boolean isShipping = PaymentDetail.contains("Shipping: "+checkoutData.getShipping());
	   
		boolean isFinalTotal  = PaymentDetail.contains("Final Total: Rs. "+ NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(checkoutData.getOrdertotal())));
		return (isSubtotal & isDescount & isShipping & isFinalTotal);
		                     
			
			
		}
		
		public boolean verifyDeliveryDetailWithCheckoutData(CheckoutData checkoutData) throws Throwable, Throwable
		{         String DeliveryDetail = getText(DeliveryDetailTextview());
		
		               boolean isFirstname = DeliveryDetail.contains(checkoutData.getFirstname());
		                          boolean ispostCode = DeliveryDetail.contains(checkoutData.getPostcode());
		                          boolean isShippingAddress1 = DeliveryDetail.contains(checkoutData.getShipping_address1());
//		                         boolean isShippingAddress2 = DeliveryDetail.contains(checkoutData.getShipping_address2());
		                         boolean isTelephone = DeliveryDetail.contains(checkoutData.getTelephone());
		                         return (isFirstname & ispostCode & isShippingAddress1&isTelephone);
		                     
		                            
		
		
	     
			
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
	
	

}
