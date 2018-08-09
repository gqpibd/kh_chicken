package server.dto;


/*
CREATE TABLE MEMBER(
    NAME VARCHAR2(20) NOT NULL,
    ID VARCHAR2(10) PRIMARY KEY,
    PW VARCHAR2(20) NOT NULL,
    COUPON NUMBER(1),
    AUTH NUMBER(1) NOT NULL,
    ADR VARCHAR2(50) NOT NULL,
    PHONE VARCHAR2(20) NOT NULL
);

CREATE TABLE MENU(
    MENU_NAME VARCHAR2(50) PRIMARY KEY,
    PRICE NUMBER(5) NOT NULL
);

CREATE TABLE ORDER_DETAIL(
    ID VARCHAR2(10),
    MENU_NAME VARCHAR2(50),
    COUNT NUMBER(10) NOT NULL,
    BEV_COUPON NUMBER(10),
    ORDER_DATE DATE NOT NULL,
    REVIEW VARCHAR2(1000),
    SCORE NUMBER(5),
    CONSTRAINT FK_ID FOREIGN KEY(ID)
    REFERENCES MEMBER(ID),
    CONSTRAINT FK_MENU FOREIGN KEY(MENU_NAME)
    REFERENCES MENU(MENU_NAME)
);
 */

import java.io.Serializable;
import java.sql.Date;

public class OrderedMenuDto extends MenuDto implements Serializable {
	
	private static final long serialVersionUID = 8611197365865197331L;
	
	private Date order_date;
	private int coupon;
	private int count;
	
	
	public OrderedMenuDto() {
	}


	public OrderedMenuDto(Date order_date, String menu_name, int count, int coupon, int price) {
		this.order_date = order_date;
		super.setMenu_name(menu_name);
		super.setPrice(price);
		this.coupon = coupon;
		this.count = count;
	}


	public Date getOrder_date() {
		return order_date;
	}


	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}


	public int getCoupon() {
		return coupon;
	}


	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return super.toString() + " OrderedMenuDto [order_date=" + order_date + ", coupon=" + coupon + ", count=" + count + "]";
	}


	


	
	

}
