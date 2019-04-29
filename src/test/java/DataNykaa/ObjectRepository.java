package DataNykaa;

import org.openqa.selenium.By;

import javax.persistence.*;

@Entity
@Table
public class ObjectRepository {


    @Column
    private String screenName;
    
    @Id
    @Column
    private String objectName;
    
    @Column
    private String locatorType;
    
    @Column
    private String locatorValue;
    
    @Column
    private String locatorNameForReporting;
    @Transient
    private By byLocator;

	public void setByLocator(By byLocator) {
		this.byLocator = byLocator;
	}

	public By getByLocator(){
		return byLocator;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getLocatorType() {
		return locatorType;
	}

	public void setLocatorType(String locatorType) {
		this.locatorType = locatorType;
	}

	public String getLocatorValue() {
		return locatorValue;
	}

	public void setLocatorValue(String locatorValue) {
		this.locatorValue = locatorValue;
	}

	public String getLocatorNameForReporting() {
		return locatorNameForReporting;
	}

	public void setLocatorNameForReporting(String locatorNameForReporting) {
		this.locatorNameForReporting = locatorNameForReporting;
	}

}
