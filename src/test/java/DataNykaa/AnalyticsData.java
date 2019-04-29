/**
 * 
 */
package DataNykaa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class AnalyticsData {
	
	@Id
	@Column
	private String rowId;
	@Column
	private String testcaseid;
	@Column
	private String pageloadorevents;
	@Column
	private String evars;
	@Column
	private String evarresult;
	@Column
	private String evarcomments;
	@Column
	private String events;
	@Column
	private String eventresult;
	@Column 
	private String eventscomments;
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the evar_comments
	 */
	public String getEvar_comments() {
		return evarcomments;
	}
	/**
	 * @param evar_comments the evar_comments to set
	 */
	public void setEvar_comments(String evar_comments) {
		this.evarcomments = evar_comments;
	}
	/**
	 * @return the events_comments
	 */
	public String getEvents_comments() {
		return eventscomments;
	}
	/**
	 * @param events_comments the events_comments to set
	 */
	public void setEvents_comments(String events_comments) {
		this.eventscomments = events_comments;
	}
	/**
	 * @return the evar_result
	 */
	public String getEvar_result() {
		return evarresult;
	}
	/**
	 * @param evar_result the evar_result to set
	 */
	public void setEvar_result(String evar_result) {
		this.evarresult = evar_result;
	}
	/**
	 * @return the event_result
	 */
	public String getEvent_result() {
		return eventresult;
	}
	/**
	 * @param event_result the event_result to set
	 */
	public void setEvent_result(String event_result) {
		this.eventresult = event_result;
	}
	/**
	 * @return the rowId
	 */
	public String getRowId() {
		return rowId;
	}
	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	/**
	 * @return the testcaseid
	 */
	public String getTestcaseid() {
		return testcaseid;
	}
	/**
	 * @param testcaseid the testcaseid to set
	 */
	public void setTestcaseid(String testcaseid) {
		this.testcaseid = testcaseid;
	}
	/**
	 * @return the pageload_or_events
	 */
	public String getPageload_or_events() {
		return pageloadorevents;
	}
	/**
	 * @param pageload_or_events the pageload_or_events to set
	 */
	public void setPageload_or_events(String pageload_or_events) {
		this.pageloadorevents = pageload_or_events;
	}
	/**
	 * @return the evar
	 */
	public String getEvars() {
		return evars;
	}
	/**
	 * @param evar the evar to set
	 */
	public void setEvars(String evar) {
		this.evars = evar;
	}

	/**
	 * @return the events
	 */
	public String getEvents() {
		return events;
	}
	/**
	 * @param events the events to set
	 */
	public void setEvents(String events) {
		this.events = events;
	}
	
	
	
	
	

}
