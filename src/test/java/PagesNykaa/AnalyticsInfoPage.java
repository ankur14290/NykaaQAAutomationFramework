/**
 * 
 */
package PagesNykaa;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import DataNykaa.AnalyticsData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Framework;

/**
 * @author nevil
 *
 */
public class AnalyticsInfoPage extends BrowserAction {
	Framework framework = new Framework();
	public AnalyticsInfoPage(WebDriver driver) {
		this.driver = driver;

	}

	public String getEvar(AnalyticsData analyticsData) {
		waitForPagetoLoad();
		Map map = new HashMap<String, String>();
		String evars = analyticsData.getEvars();
		String[] evarList = evars.split(",");
		for (String evar : evarList) {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@style='overflow:
			// auto;']")));
			
			String evarvalue = (String) ((JavascriptExecutor) driver).executeScript("return s." + evar + ";");
			map.put(evar, evarvalue);
			
		}
		if (map.containsValue(null)) {
			analyticsData.setEvar_result("Fail");
			
			
			analyticsData.setEvar_comments(map.toString());
			framework.updateData(analyticsData);
			return map.toString();

		} else {
			analyticsData.setEvar_result("Pass");
			

		
			analyticsData.setEvar_comments(map.toString());
			framework.updateData(analyticsData);
			
			
			return map.toString();
		}

	}

	
	
	public String getEvarJSLoad(AnalyticsData analyticsData) {
		waitForJstoLoad();
		Map map = new HashMap<String, String>();
		String evars = analyticsData.getEvars();
		String[] evarList = evars.split(",");	
		for (String evar : evarList) {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@style='overflow:
			// auto;']")));
			
			String evarvalue = (String) ((JavascriptExecutor) driver).executeScript("return s." + evar + ";");
			map.put(evar, evarvalue);
			
		}
		if (map.containsValue(null)) {
			analyticsData.setEvar_result("Fail");
			
			
			analyticsData.setEvar_comments(map.toString());
			framework.updateData(analyticsData);
			return map.toString();

		} else {
			analyticsData.setEvar_result("Pass");
			

		
			analyticsData.setEvar_comments(map.toString());
			framework.updateData(analyticsData);
			
			
			return map.toString();
		}

	}
	
	
	
	
	
	
	public String getEvent(AnalyticsData analyticsData) {
		waitForPagetoLoad();
		Map map = new HashMap<String, String>();
		String evars = analyticsData.getEvents();
		String[] eventList = evars.split(",");
		String evarvalue = (String) ((JavascriptExecutor) driver).executeScript("return s.events;");
		boolean isEventPresent = false;
		List<String> listOfEvents =new ArrayList<String>();
		for (String event : eventList) {
			if(evarvalue.contains(event))
			{isEventPresent=true;}else{
				isEventPresent = false;
				break;
			}
		}
		if (isEventPresent==false) {
			analyticsData.setEvent_result("Fail");
			analyticsData.setEvents_comments(evarvalue);
			framework.updateData(analyticsData);
		

		} else {
			analyticsData.setEvent_result("Pass");
			analyticsData.setEvents_comments(evarvalue);
			framework.updateData(analyticsData);
		
		}
		return evarvalue;
	}

	
	
	public String getEventJsload(AnalyticsData analyticsData) {
		waitForJstoLoad();
		Map map = new HashMap<String, String>();
		String evars = analyticsData.getEvents();
		String[] eventList = evars.split(",");
		String evarvalue = (String) ((JavascriptExecutor) driver).executeScript("return s.events;");
		boolean isEventPresent = false;
		List<String> listOfEvents =new ArrayList<String>();
		for (String event : eventList) {
			if(evarvalue.contains(event))
			{isEventPresent=true;}else{
				isEventPresent = false;
				break;
			}
		}
		if (isEventPresent==false) {
			analyticsData.setEvent_result("Fail");
			analyticsData.setEvents_comments(evarvalue);
			framework.updateData(analyticsData);
		

		} else {
			analyticsData.setEvent_result("Pass");
			analyticsData.setEvents_comments(evarvalue);
			framework.updateData(analyticsData);
		
		}
		return evarvalue;
	}

	


}
