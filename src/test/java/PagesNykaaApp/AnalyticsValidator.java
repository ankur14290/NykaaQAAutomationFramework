package PagesNykaaApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import DataNykaa.AnalyticsDataApp;
import FrameWorkNykaa.Framework;
import net.lightbody.bmp.core.har.HarPostDataParam;

public class AnalyticsValidator {
	
	
	Framework frameWork = new Framework();

	public Map<String, Object> validateAnalyticsData(String testcaseId, List<HarPostDataParam> harPostData) {
		//Get Analytics Data 
		Map mapEventPageCheckResult = new HashMap<String, Object>();
		
		List<String> ignoreList = new ArrayList<String>();
		
		
		AnalyticsDataApp analyticsDataApp = frameWork.getData(AnalyticsDataApp.class, testcaseId);

		//Change the Event Data Into Map asd
		Map<String, Object> mapDataAnalyticsExpected = mapDataAnalytics(analyticsDataApp);
		
		//Get the Map of all the variable Data which we need to replace
		Map<String, Object> mapActionVarData = getVariableDataAction(testcaseId);
		
		//Replace the variables which are not static and Assign back to mapAnalyticsData
		mapDataAnalyticsExpected = replaceVarAnalyticsDataMap(mapDataAnalyticsExpected,mapActionVarData);
		
		//Get the Map of harPostData
		Map<String, Object> mapDataAnalyticsActual = mapDataAnalyticsActual(harPostData);
		
		
		//Compare the Expected and ActualMap
		 mapEventPageCheckResult = compareMapAnalyticsData(mapDataAnalyticsExpected,mapDataAnalyticsActual);
		
		

		return mapEventPageCheckResult;
	}
	

	private Map compareMapAnalyticsData(Map<String, Object> mapDataAnalyticsExpected,Map<String, Object> mapDataAnalyticsActual) {
		Map mapEventPageCheckResult = new HashMap<String, Object>();
		List<String> passList = new ArrayList<String>();
		List<String> failList = new ArrayList<String>();
		List<String> missingList = new ArrayList<String>();
		List<String> errorList = new ArrayList<String>();
		
		
		try {
			for (Entry<String, Object> entry : mapDataAnalyticsExpected.entrySet())  
				if(mapDataAnalyticsActual.containsKey(entry.getKey())) {
					String expectedValue =mapDataAnalyticsExpected.get(entry.getKey()).toString();
					String actualValue= mapDataAnalyticsActual.get(entry.getKey()).toString();
					if(expectedValue.equalsIgnoreCase(actualValue)){
						passList.add("Variable Name: " + entry.getKey() + "=" + entry.getValue());
					}else {
						failList.add("Variable Name: " + entry.getKey() + "Expected Evar Value : " + entry.getValue() + " Actual Evar Value: " + actualValue);
					}
				}else {
						missingList.add(entry.getKey());
				}
		} catch (Exception e) {
			
						errorList.add(e.getMessage());
		}
   
		mapEventPageCheckResult.put("PassList", passList);
		mapEventPageCheckResult.put("FailList", failList);
		mapEventPageCheckResult.put("ErrorList", errorList);
		mapEventPageCheckResult.put("MissingList", errorList);
		
		return mapEventPageCheckResult;
		
		
	}


	private Map<String, Object> mapDataAnalyticsActual(List<HarPostDataParam> harPostData) {
		Map<String, Object> mapDataAnalyticsActual = new HashMap<String,Object>();
		
		try {
			for (HarPostDataParam nameValue : harPostData) {
				mapDataAnalyticsActual.put(nameValue.getName(),nameValue.getValue());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapDataAnalyticsActual;
	}


	private Map<String, Object> replaceVarAnalyticsDataMap(Map<String, Object> mapDataAnalytics,Map<String, Object> mapActionVarData) {
		
		try {
			for (Entry<String, Object> entry : mapDataAnalytics.entrySet())  
					if(mapActionVarData.containsKey(entry.getKey())) {
						mapDataAnalytics.put(entry.getKey(), mapActionVarData.get(entry.getKey()));
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		return mapDataAnalytics;
	}

	public Map<String, Object> getVariableDataAction(String testcaseId) {
		Map<String, Object> mapActionVarData = new HashMap<String, Object>();
		if(testcaseId.equals("addtocart")) {
			mapActionVarData.put("itemcount", 1);
			mapActionVarData.put("loginStatus", "logged_in");
			mapActionVarData.put("couponcode", "TESTQA987");
			mapActionVarData.put("hasfreeproduct", "1");
			mapActionVarData.put("cartsubtotal", "1971.0");
			mapActionVarData.put("customerID", "3841703");
			mapActionVarData.put("pageName", "App:ShoppingBag");
			
		}
		return mapActionVarData;
	}

	
	
	public Map<String, Object> mapDataAnalytics(AnalyticsDataApp analyticsAddToCart) {

		Map<String, Object> mapDataAnalytics = new HashMap<String, Object>();
		String eventsData = analyticsAddToCart.getEvents();
		String[] eventDataTemp = eventsData.split(",");

		try {
			for (String event : eventDataTemp) {
				String[] eventNameValuePair = event.split("=");
				String eventName = eventNameValuePair[0];
				String eventValue = eventNameValuePair[1];
				mapDataAnalytics.put(eventName, eventValue);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mapDataAnalytics;
	}

	
	
}
