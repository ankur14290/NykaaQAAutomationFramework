package DataNykaa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class CheckoutData {
	@Id
	@Column
	private String rowId;
	@Column
	private String testcaseid;
	@Column
	private String username;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String telephone;
	@Column
	private String postcode;
	@Column
	private String shippingaddress1;
	@Column
	private String shippingaddress2;
	@Column
	private String cardname;
	@Column 
	private String cardno;
	@Column
	private String expDate;
	@Column 
	private String ccv;
	@Column
	private String couponcode;
	@Transient
	private String subtotal;
	@Transient
	private String shipping;
	@Transient
	private String rewardpoint;
	@Transient
	private String ordertotal;
	
	
	
	
	
	
	
	/**
	 * @return the subtotal
	 */
	public String getSubtotal() {
		return subtotal;
	}
	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	/**
	 * @return the shipping
	 */
	public String getShipping() {
		return shipping;
	}
	/**
	 * @param shipping the shipping to set
	 */
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
	/**
	 * @return the rewardpoint
	 */
	public String getRewardpoint() {
		return rewardpoint;
	}
	/**
	 * @param rewardpoint the rewardpoint to set
	 */
	public void setRewardpoint(String rewardpoint) {
		this.rewardpoint = rewardpoint;
	}
	/**
	 * @return the ordertotal
	 */
	public String getOrdertotal() {
		return ordertotal;
	}
	/**
	 * @param ordertotal the ordertotal to set
	 */
	public void setOrdertotal(String ordertotal) {
		this.ordertotal = ordertotal;
	}
	/**
	 * @return the couponcode
	 */
	public String getCouponcode() {
		return couponcode;
	}
	/**
	 * @param couponcode the couponcode to set
	 */
	public void setCouponcode(String couponcode) {
		this.couponcode = couponcode;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getShipping_address1() {
		return shippingaddress1;
	}
	public void setShipping_address1(String shipping_address1) {
		this.shippingaddress1 = shipping_address1;
	}
	public String getShipping_address2() {
		return shippingaddress2;
	}
	public void setShipping_address2(String shipping_address2) {
		this.shippingaddress2 = shipping_address2;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getCcv() {
		return ccv;
	}
	public void setCcv(String ccv) {
		this.ccv = ccv;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getTestcaseid() {
		return testcaseid;
	}
	public void setTestcaseid(String testcaseid) {
		this.testcaseid = testcaseid;
	}
	
		

}
