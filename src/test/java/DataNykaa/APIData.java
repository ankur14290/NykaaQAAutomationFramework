/**
 * 
 */
package DataNykaa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nevil
 *
 */
@Entity
@Table
public class APIData {
	@Id
	@Column
	String rowid;
	@Column
	String itrtestcaseid;
	@Column
	String testcaseid;
	@Column
	String path;
	@Column
	String parameter ;
	@Column
	String status;
	@Column
	String result;
	@Column
	String response;
	@Column
	String expected;
	@Column
	String method;
	@Column
	String name;
	@Column
	String expectedstatus;
	
	
	
	/**
	 * @return the rowid
	 */
	public String getRowid() {
		return rowid;
	}
	/**
	 * @param rowid the rowid to set
	 */
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the itrtestcaseid
	 */
	public String getItrtestcaseid() {
		return itrtestcaseid;
	}
	/**
	 * @param itrtestcaseid the itrtestcaseid to set
	 */
	public void setItrtestcaseid(String itrtestcaseid) {
		this.itrtestcaseid = itrtestcaseid;
	}
	/**
	 * @return the row
	 */
	public String getRow() {
		return rowid;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(String row) {
		this.rowid = row;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the parameter
	 */
	public String getParameter() {
		return parameter;
	}
	/**
	 * @param parameter the parameter to set
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	/**
	 * @return the expected
	 */
	public String getExpected() {
		return expected;
	}
	/**
	 * @param expected the expected to set
	 */
	public void setExpected(String expected) {
		this.expected = expected;
	}
	
	public String getName() {
		return name;
	}
	/**
	 * @param rowid the rowid to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getExpectedStatus() {
		return expectedstatus;
	}
	/**
	 * @param rowid the rowid to set
	 */
	public void setExpectedStatus(String expectedStatus) {
		this.expectedstatus = expectedStatus;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "APIData [testcaseid=" + testcaseid + ", path="
				+ path + ", parameter=" + parameter + ", status=" + status + ", result=" + result + ", response="
				+ response + ", expected=" + expected + "]";
	}
	
	
	

}
