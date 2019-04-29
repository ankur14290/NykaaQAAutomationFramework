package DataNykaa;


import javax.persistence.*;

@Entity
@Table
public class ProductDetailData {
	
	@Id
	@Column
	String  rowid;
	@Column
	String testcaseid;
	@Column
	String productname;
	@Column
	String producttype;
	@Column
	String productprize;
	@Column
	String productquantity;
	@Column
	String reviewtitle;
	@Column
	String reviews;
	@Column
	String rating;
	@Column
	String question;
	@Column
	String location;
	@Transient
	String Discount;
	@Transient
	String ReviewCount;
	@Transient
	String productID;
	
	
	
	
	
	/**
	 * @return the productID
	 */
	public String getProductID() {
		return productID;
	}
	/**
	 * @param productID the productID to set
	 */
	public void setProductID(String productID) {
		this.productID = productID;
	}
	/**
	 * @return the reviewCount
	 */
	public String getReviewCount() {
		return ReviewCount;
	}
	/**
	 * @param reviewCount the reviewCount to set
	 */
	public void setReviewCount(String reviewCount) {
		ReviewCount = reviewCount;
	}
	/**
	 * @return the discount
	 */
	public String getDiscount() {
		return Discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(String discount) {
		Discount = discount;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the reviewtitle
	 */
	public String getReviewtitle() {
		return reviewtitle;
	}
	/**
	 * @param reviewtitle the reviewtitle to set
	 */
	public void setReviewtitle(String reviewtitle) {
		this.reviewtitle = reviewtitle;
	}
	/**
	 * @return the reviews
	 */
	
	
	public String getReviews() {
		return reviews;
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
	 * @param reviews the reviews to set
	 */
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getProductName() {
		return productname;
	}
	public void setProductName(String productName) {
		this.productname = productName;
	}
	public String getProductType() {
		return producttype;
	}
	public void setProductType(String productType) {
		producttype = productType;
	}
	public String getProductPrize() {
		return productprize;
	}
	public void setProductPrize(String productPrize) {
		productprize = productPrize;
	}
	public String getProductQuantity() {
		return productquantity;
	}
	public void setProductQuantity(String productQuantity) {
		this.productquantity = productQuantity;
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return "ProductDetailData [rowid=" + rowid + ", testcaseid=" + testcaseid + ", productname=" + productname
				+ ", producttype=" + producttype + ", productprize=" + productprize + ", productquantity="
				+ productquantity + ", reviewtitle=" + reviewtitle + ", reviews=" + reviews + ", rating=" + rating
				+ ", question=" + question + ", location=" + location + ", Discount=" + Discount + ", ReviewCount="
				+ ReviewCount + ", productID=" + productID + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Discount == null) ? 0 : Discount.hashCode());
		result = prime * result + ((ReviewCount == null) ? 0 : ReviewCount.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((productID == null) ? 0 : productID.hashCode());
		result = prime * result + ((productname == null) ? 0 : productname.hashCode());
		result = prime * result + ((productprize == null) ? 0 : productprize.hashCode());
		result = prime * result + ((productquantity == null) ? 0 : productquantity.hashCode());
		result = prime * result + ((producttype == null) ? 0 : producttype.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((reviews == null) ? 0 : reviews.hashCode());
		result = prime * result + ((reviewtitle == null) ? 0 : reviewtitle.hashCode());
		result = prime * result + ((rowid == null) ? 0 : rowid.hashCode());
		result = prime * result + ((testcaseid == null) ? 0 : testcaseid.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDetailData other = (ProductDetailData) obj;
		if (Discount == null) {
			if (other.Discount != null)
				return false;
		} else if (!Discount.equals(other.Discount))
			return false;
		if (ReviewCount == null) {
			if (other.ReviewCount != null)
				return false;
		} else if (!ReviewCount.equals(other.ReviewCount))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
			return false;
		if (productname == null) {
			if (other.productname != null)
				return false;
		} else if (!productname.equals(other.productname))
			return false;
		if (productprize == null) {
			if (other.productprize != null)
				return false;
		} else if (!productprize.equals(other.productprize))
			return false;
		if (productquantity == null) {
			if (other.productquantity != null)
				return false;
		} else if (!productquantity.equals(other.productquantity))
			return false;
		if (producttype == null) {
			if (other.producttype != null)
				return false;
		} else if (!producttype.equals(other.producttype))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (reviews == null) {
			if (other.reviews != null)
				return false;
		} else if (!reviews.equals(other.reviews))
			return false;
		if (reviewtitle == null) {
			if (other.reviewtitle != null)
				return false;
		} else if (!reviewtitle.equals(other.reviewtitle))
			return false;
		if (rowid == null) {
			if (other.rowid != null)
				return false;
		} else if (!rowid.equals(other.rowid))
			return false;
		if (testcaseid == null) {
			if (other.testcaseid != null)
				return false;
		} else if (!testcaseid.equals(other.testcaseid))
			return false;
		return true;
	}
	
	
	  



}
