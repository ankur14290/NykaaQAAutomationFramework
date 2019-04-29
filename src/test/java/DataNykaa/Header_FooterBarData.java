package DataNykaa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class Header_FooterBarData {
	
	@Id
	@Column
	private String rowId;
	@Column
	private String testcaseid;
	@Column
	private String mainmenu;
	@Column
	private String submenu;
	@Column
	private String submenuCategory;
	@Column
	private String searchkeyword;
	@Column
	private String itrtestcaseid;
	
	
	
	
	
	/**
	 * @return the itr_testcaseid
	 */
	public String getItr_testcaseid() {
		return itrtestcaseid;
	}
	/**
	 * @param itr_testcaseid the itr_testcaseid to set
	 */
	public void setItr_testcaseid(String itr_testcaseid) {
		this.itrtestcaseid = itr_testcaseid;
	}
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
	public String get_search_keyword() {
		return searchkeyword;
	}
	public String getSubmenuCategory() {
		return submenuCategory;
	}
	public void setSubmenuCategory(String submenuCategory) {
		this.submenuCategory = submenuCategory;
	}
	
	
	

}
