package DataNykaa;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AnalyticsDataApp {
	@Id
	@Column
	private String rowId;
	@Column
	private String testcaseid;
	@Column
	private String tracking;
	@Column
	private String events;
	@Column
	private String comments;
	@Column
	private String trackingresult;

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

	public String getTracking() {
		return tracking;
	}

	public void setTracking(String tracking) {
		this.tracking = tracking;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTrackingresult() {
		return trackingresult;
	}

	public void setTrackingresult(String trackingresult) {
		this.trackingresult = trackingresult;
	}

}
