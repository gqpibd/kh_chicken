package dto;

import java.io.Serializable;

/*	
	CREATE TABLE ORDER_DETAIL(
    ID VARCHAR2(10),
    MENU_NAME VARCHAR2(30),
    COUNTS NUMBER(10) NOT NULL,
    BEV_COUPON NUMBER(3),
    ORDER_DATE DATE NOT NULL,
    REVIEW VARCHAR2(1000),
    SCORE NUMBER(5),
    CONSTRAINT FK_ID FOREIGN KEY(ID)
    REFERENCES MEMBER(ID),
    CONSTRAINT FK_MENU FOREIGN KEY(MENU_NAME)
    REFERENCES MENU(MENU_NAME)
);	
*/

public class ReviewDto implements Serializable{
	
	private static final long serialVersionUID = 4365147598740533512L;
	
	private String menuName;
	private String review;
	private String userId;
	private String orderDate;
	private int score;
	
	public ReviewDto() {
	}

	public ReviewDto(String menuName, String review, String userId, String orderDate, int score) {
		super();
		this.menuName = menuName;
		this.review = review;
		this.userId = userId;
		this.orderDate = orderDate;
		this.score = score;
	}
	
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ReviewDto [menuName=" + menuName + ", review=" + review + ", userId=" + userId + ", orderDate="
				+ orderDate + ", score=" + score + "]";
	}
	
	

}
