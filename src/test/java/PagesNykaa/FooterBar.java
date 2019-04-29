package PagesNykaa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;



public class FooterBar extends BrowserAction {

	public FooterBar(WebDriver driver) {
		this.driver = driver;

	}

	Locator AdverismentPopup() {
		return new Locator(
				By.xpath(
						"//iframe[@id='__ta_notif_frame_0' or @id='webklipper-publisher-widget-container-notification-frame']"),
				"Advertisment popup");
	}

	Locator DeleteIcon() {
		return new Locator(By.xpath("//div[@class='close']|//a[@class='close']"), "advertisment Close button");
	}

	public void CloseAdvertismentPopup() throws InstantiationException, IllegalAccessException {

		if (SwitchToFrameIFAvaialble(AdverismentPopup(),5)) {
			click(DeleteIcon());
			switchToDefaultContent();

		}
	}
	
	public void refreshAndCloseFrame(WebDriver browser) throws InstantiationException, IllegalAccessException {
		browser.navigate().refresh();
		CloseAdvertismentPopup();
	}

}
