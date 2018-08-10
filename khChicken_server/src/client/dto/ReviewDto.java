package client.dto;
 
import java.io.Serializable;
import java.sql.Date;

public class ReviewDto implements Serializable{
	
	private static final long serialVersionUID = 4365147598740533512L;
	
	private String userId;
	private String menuName;
	private int count1;
	private int bec_coupon;
	private String orderDate;
	private String review;
	private int score;
	
	
	
	public ReviewDto() {
		
	}
	
	public ReviewDto(String userId, String menuName, int count1, int bec_coupon, String orderDate, String review,
			int score) {
		super();
		this.userId = userId;
		this.menuName = menuName;
		this.count1 = count1;
		this.bec_coupon = bec_coupon;
		this.orderDate = orderDate;
		this.review = review;
		this.score = score;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getMenuName() {
		return menuName;
	}
	
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getCount1() {
		return count1;
	}
	
	public void setCount1(int count1) {
		this.count1 = count1;
	}
	
	public int getBec_coupon() {
		return bec_coupon;
	}
	
	public void setBec_coupon(int bec_coupon) {
		this.bec_coupon = bec_coupon;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getReview() {
		return review;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "ReviewDto [userId=" + userId + ", menuName=" + menuName + ", count1=" + count1 + ", bec_coupon="
				+ bec_coupon + ", orderDate=" + orderDate + ", review=" + review + ", score=" + score + "]";
	}
	
	
	
	
	
	

}
