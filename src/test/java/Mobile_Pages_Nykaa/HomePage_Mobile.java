package Mobile_Pages_Nykaa;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;



public class HomePage_Mobile extends BrowserAction {
	
	public HomePage_Mobile(WebDriver driver) {
		this.driver = driver;
	}
	
	By BestSellers_Tiles = By.xpath("//a[text()='Bestsellers']/ancestor::div[@class='add-tiles']/descendant::img");
	
	Locator TreadingSearchModule(){
		return new Locator(By.xpath("//div[@class='trendingSearchesWrapper']"),"Treading search Module");
	}
	
	Locator SelectiveTreadingSearch(int ran){
		 String xpath ="//div[@class='trendingList']//a["+ran+"]";
		return new Locator(By.xpath(xpath),"Treading search option");
	}
	Locator SlickSlidesCount(){
		return new Locator(By.xpath("//ul[contains(@class,'slick-dots')]/li"),"slic slides");
	}
	Locator SlickButton(int index){
		int buttonid = index+1;
		return new Locator(By.xpath("//ul[@class='slick-dots']/li/button[contains(text(),"+buttonid+")]"),"Slick Button");
	}
	
	Locator widgetLinks(){
		return new Locator(By.xpath("//section[@class='Homepage-Banners-Wrapper']//a[@href]"),"Widget links redirections");
	}

	Locator ActiveSlide(int index){

		return new Locator(By.xpath("//div[@class='slick-slide slick-active'][@index='"+index+"']"),"Active Slide");
	}

	Locator redirectedToFilter(){
		return new Locator(By.xpath("//ul[@class='category_filt_sort_ul']"),"Filter to Redirected Area");
	}
	Locator bannerLinks(){
		return new Locator(By.xpath("//div[@id='Homepage-Slider']//a[@href]"),"banner Links");
	}
	
	Locator StickDots(){
		return new Locator(By.xpath("//ul[@class='slick-dots']//li//button"),"Stick dots");
	}
	
	Locator bannerImagActive(){
		return new Locator(By.xpath("//div[@class='nykaa-drag-drop nykka-editor-view nw_carousel_img nw_sliding_image slick-slide slick-active']//img"),"banner Images");
	}
	
	
	Locator SlickSlid(String Sliderindex)
	{
		return new Locator(By.xpath("//div[@class='slick-list draggable']/div[@class='slick-slide slick-active'][@index='"+Sliderindex+"']"),"Slick Slide Banner "+Sliderindex +" On HomePage");
	}
	
	
	


@SuppressWarnings("deprecation")
public Map BrokenImagesList() throws Exception, IOException
{
	List<WebElement> StickDots = getWebElements(StickDots());
	Map <Integer ,Boolean> BrokenImagesCount = new HashMap<Integer ,Boolean>();
	  final HttpParams httpParams = new BasicHttpParams();
	    HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
	

	int count =1;
	for(WebElement StickDot : StickDots)
	{
		click(StickDot , "click stick dot" + count);
		WebElement bannerImage = driver.findElement(bannerImagActive().getBy());
		String src= bannerImage.getAttribute("src");
		System.out.println("on image :" + src);
		try {
			HttpClient client = new DefaultHttpClient(httpParams);
		HttpGet request = new HttpGet(src);
		HttpResponse response = client.execute(request);
		if (response.getStatusLine().getStatusCode() == 200)
		{
			BrokenImagesCount.put(count,false);
		}
		else
		{
			BrokenImagesCount.put(count ,true);
		}
		} 
		 catch(SocketTimeoutException se) {
		        Long endTime = System.currentTimeMillis();
		        System.out.println("SocketTimeoutException " );
		        se.printStackTrace();
		        BrokenImagesCount.put(count,true);
		        
		      }          
		      catch(ConnectTimeoutException cte) {
		        Long endTime = System.currentTimeMillis();
		        System.out.println("ConnectTimeoutException ");
		        cte.printStackTrace();
		        BrokenImagesCount.put(count,true);
		      }
		
		
			count++;
	}
	return BrokenImagesCount;
}


public Map BannerImagesRedirectingList() throws Exception, IOException
{
	List<WebElement> StickDots = getWebElements(StickDots());
	Map<Integer , Boolean> BannerImagesRedirectingCount = new HashMap<Integer , Boolean>();
	int count=1;
	for(int i = 1 ;i <= StickDots.size();i++)
	{
		Thread.sleep(2000);
		String xpath ="//ul[@class='slick-dots']//li["+i+"]//button";
		WebElement StickDot = driver.findElement(By.xpath(xpath));
		click(StickDot , " stick dot" + count);
		String presentUrl = driver.getCurrentUrl();
		WebElement bannerImage = driver.findElement(bannerImagActive().getBy());	
		click(bannerImage , "banner image at " + count );
		waitForPagetoLoad();
		String newUrl = driver.getCurrentUrl();
		if(!presentUrl.equals(newUrl))
		{
		BannerImagesRedirectingCount.put(count, true);
		driver.navigate().back();
		waitForPagetoLoad();
		}
		else
		{
		BannerImagesRedirectingCount.put(count, false);
		}
		
		System.out.println("map value" +BannerImagesRedirectingCount );
		count++;
		Thread.sleep(2000);
	}
		
			
	
	return BannerImagesRedirectingCount;
}


	public List getBannerLink(){
		
		List<WebElement> bannerlinks = getWebElements(bannerLinks());
		ArrayList<String> bannerlinkList = new ArrayList<String>();
		for(WebElement bannerlink : bannerlinks)
		{
		Locator bannerlinklocator = new Locator(By.xpath(getAbsoluteXPath(bannerlink)), "banner link");
		   String link = getAttribute(bannerlinklocator, "href");
		   bannerlinkList.add(link);
		}

		return bannerlinkList;
		
	}
	public List getHomePagewidgetLinks(){
		List<WebElement> widgetlinks = getWebElements(widgetLinks());
		ArrayList<String> widgetlinkList = new ArrayList<String>();
		for(WebElement widgetlink : widgetlinks)
		{
		Locator widgetlinklocator = new Locator(By.xpath(getAbsoluteXPath(widgetlink)), "banner link");
		   String link = getAttribute(widgetlinklocator, "href");
		   widgetlinkList.add(link);
		}

		return widgetlinkList;
		
	}
	
	public  void goToBestSeller() throws InterruptedException{
		
		List<WebElement> tiles = getAllElements(BestSellers_Tiles);
		
		for(WebElement tile:tiles)
		{   
		    String MainPage = driver.getWindowHandle();
			tile.click();
			Thread.sleep(4000);
			Set<String> MultipleTabs = driver.getWindowHandles();
			if(MultipleTabs.size()>1){
				for (String producttab : MultipleTabs) {
					if (!MainPage.equalsIgnoreCase(producttab)) {
						driver.switchTo().window(producttab);
							break;
						}
					}
				break;
			}
			
		}
		
	}
	
	
public  Boolean NaviagetoTrendingSearch() throws IllegalArgumentException, IllegalAccessException, Throwable{
	
	try {
	waitUntilDisplayed(TreadingSearchModule(), 10);
	bringElementIntoView(TreadingSearchModule());
	return true;
	}
	catch(Exception e)
	{
		return false;
	}
	
		
	}

public  String SelectRandomTrendingSerach() throws IllegalArgumentException, IllegalAccessException, Throwable{
	NaviagetoTrendingSearch();
	String SelectedTrendingsearch;
	int ran ;
	 Random t = new Random();
	 ran = t.nextInt(7)+1;
	 SelectedTrendingsearch = driver.findElement(By.xpath("//div[@class='trendingList']//a["+ran+"]//li")).getText();
	 click(SelectiveTreadingSearch(ran));
	 waitForPagetoLoad();
	 return SelectedTrendingsearch;
	
		
	}


	
	public void redirectToActiveBannerPage() throws Throwable, Throwable{
		 String mainpage = driver.getWindowHandle();
		click(SlickButton(1));
		click(ActiveSlide(1));
		Set<String> multiwindow = driver.getWindowHandles();
		for(String window :multiwindow)
		{
			if(!window.equalsIgnoreCase(mainpage))
			{
				driver.switchTo().window(window);
				break;
			}
			
		}
		
	}
	
	public List<Boolean> verifyBannerRedirction() throws Throwable, Throwable
	{
		String mainpage = driver.getWindowHandle();
	       List<WebElement> slickslidesCount = getWebElements(SlickSlidesCount());
	       int index = 0;
	       List<Boolean> redirectList = new ArrayList<Boolean>();
	       for(WebElement slicslide:slickslidesCount)
	       {
	    bringElementIntoView(SlickButton(index));
		click(SlickButton(index));
		waitUntilDisplayed(ActiveSlide(index), 30);
		click(ActiveSlide(index));
		index++;
		Set<String> multiwindow = driver.getWindowHandles();
		for(String window :multiwindow)
		{
			if(!window.equalsIgnoreCase(mainpage))
			{
				driver.switchTo().window(window);
				boolean pageLoaded = waitUntilDisplayed(redirectedToFilter(), 30);
				
				redirectList.add(pageLoaded);
				driver.close();
				driver.switchTo().window(mainpage);
				
			}
			
		}
		
	       }
		return redirectList;
		
		
		
	}
	
	public Boolean validatesearchKeywordIsInProduct(String productname , String searchKeyword)
	{
		String productName = productname;
		BrowserAction ba = new BrowserAction();
		String newProductName2=ba.removeSpaces(productname);
		String newProductName=ba.removeSpecialChar(newProductName2);
		
		String[] temp = searchKeyword.split(" ");
		Boolean bol=true;
		if(temp.length==2)
		{
	    String stemp1 = ba.removeSpaces(temp[0]);
	    String stemp2 = ba.removeSpaces(temp[1]).substring(0, temp[1].length()-2);
	    
		bol= newProductName.contains(stemp1)&&newProductName.contains(stemp2)||newProductName.contains(stemp1)&&newProductName.contains("enamel");
	}
		else
		{
		
			String stemp1 = ba.removeSpaces(searchKeyword);
			if(stemp1.length()>6)
			bol= newProductName.contains(stemp1.substring(0, stemp1.length()-2));
			else
			{
				bol= newProductName.contains(stemp1);
			}
		}
		
		return bol;
		
	}
	
	
}
