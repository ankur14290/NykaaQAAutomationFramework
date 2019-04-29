package DataNykaa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BeautyServiceData {
	@Id
	@Column
	private String rowId;
	@Column
	private String testcaseid;
	@Column
	private String Menu;
	@Column
	private String mainmenu;
	@Column
	private String submenu;
	@Column
	private String searchkeyword;
	@Column
	private String itrtestcaseid;
	@Column
	private String city;
	@Column
	private String location;
	@Column
	private String quicklinks;
	@Column
	private String servicename;
	@Column
	private String servicedetail;



	
	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getTestcaseid() {
		return testcaseid;
	}

	public void setTestcaseid(String testcaseid) {
		this.testcaseid = testcaseid;
	}

	public String getMenu() {
		return Menu;
	}

	public void setMenu(String menu) {
		Menu = menu;
	}

	public String getMainmenu() {
		return mainmenu;
	}

	public void setMainmenu(String mainmenu) {
		this.mainmenu = mainmenu;
	}

	public String getSubmenu() {
		return submenu;
	}

	public void setSubmenu(String submenu) {
		this.submenu = submenu;
	}

	public String getSearchkeyword() {
		return searchkeyword;
	}

	public void setSearchkeyword(String searchkeyword) {
		this.searchkeyword = searchkeyword;
	}

	public String getItrtestcaseid() {
		return itrtestcaseid;
	}

	public void setItrtestcaseid(String itrtestcaseid) {
		this.itrtestcaseid = itrtestcaseid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getServicedetail() {
		return servicedetail;
	}

	public void setServicedetail(String servicedetail) {
		this.servicedetail = servicedetail;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getQuicklinks() {
		return quicklinks;
	}

	public void setQuicklinks(String quicklinks) {
		this.quicklinks = quicklinks;
	}
}
