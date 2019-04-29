package FrameWorkNykaa;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.openqa.selenium.Proxy;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarPage;
import net.lightbody.bmp.core.har.HarPostDataParam;
import net.lightbody.bmp.proxy.CaptureType;


public class AnalyticsLogAnalyser {
	static BrowserMobProxy server;
	public static Proxy proxy;
	static Har har;
	static HarLog log;
	static List<HarEntry>  entries;

	private static volatile AnalyticsLogAnalyser analyticsLogAnalyser= new AnalyticsLogAnalyser();

	//private constructor.
	AnalyticsLogAnalyser(){
		server = new BrowserMobProxyServer();
		server.start(5555);
		HashSet<CaptureType> enable = new HashSet<CaptureType>();
		enable.add(CaptureType.REQUEST_HEADERS);
		enable.add(CaptureType.REQUEST_CONTENT);
		//enable.add(CaptureType.RESPONSE_HEADERS);
		//enable.add(CaptureType.RESPONSE_CONTENT);
		server.enableHarCaptureTypes(enable);
		proxy = ClientUtil.createSeleniumProxy(server);

	}

	public static AnalyticsLogAnalyser getInstance() {


		return analyticsLogAnalyser;
	}

	public Har getHarObject() {
		return server.getHar();
	}

	public void setHarDataObject() {
		har = server.getHar();
	}

	public Proxy getProxyObect() {
		return proxy;
	}

	public HarLog getHarLog() {
		return server.getHar().getLog();
	}

	public void clearHarLog() {

	}

	public HarLog getHarLogObject() {
		return har.getLog();
	}

	public void setHarLogObject() {
		log = har.getLog();
	}

	public void createNewHar(String harName) {
		har = server.newHar(harName);
	}

	public void clearHar() {
		har = null;
	}

	public void clearLogObject() {
		log = null;
	}

	public void clearEntries() {
		entries.clear();
	}

	public List<HarPostDataParam> getHarPostDataParam(String analyticstype,String actionName){
		//asd
		File harFile = null;
		try {
			analyticstype = "omtrdc";
			// System.out.println("asd");
			har = server.newHar("NewHar");
			log = har.getLog();

			harFile = new File("D:/newHar.har");
			har.writeTo(harFile);
		} catch (IOException ex) {
			System.out.println(ex.toString());
			System.out.println("Could not find file " + harFile);
		}
		entries = new CopyOnWriteArrayList<HarEntry>(log.getEntries());

		System.out.println(entries);
		for (HarEntry entry : entries) {
			// System.out.println(entry.getRequest().getUrl());
			// System.out.println(entry.getResponse().getContent().getText());
			try {
				if ((entry.getRequest().getUrl().contains(analyticstype)) && (entry.getRequest().getMethod().equalsIgnoreCase("POST"))) {
					if (!(entry.getRequest().getPostData().getText() == null)|| !(entry.getRequest().getPostData().getText() == "null")) {
						List<HarPostDataParam> listOfParams = entry.getRequest().getPostData().getParams();
						if (!(listOfParams == null)) {
							for (HarPostDataParam nameValue : listOfParams) {

								System.out.println(nameValue.getName() + "=" + nameValue.getValue());

							}
							System.out.println("asd");
							for (HarPostDataParam nameValue : listOfParams) {
								if(nameValue.getValue().contains(actionName)) {
									return listOfParams;
									//System.out.println(nameValue.getName() + "Value" + nameValue.getValue());
								}
							}
						}
					}
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return null;
	}




}
