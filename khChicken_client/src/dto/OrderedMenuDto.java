package dto;

import java.io.Serializable;
import java.util.Date;


/*
CREATE TABLE MEMBER(
    NAME VARCHAR2(20) NOT NULL,
    ID VARCHAR2(10) PRIMARY KEY,
    PW VARCHAR2(20) NOT NULL,
    USEDCOUPON NUMBER(2),
    AUTH NUMBER(1) NOT NULL,
    ADR VARCHAR2(50) NOT NULL,
    PHONE VARCHAR2(20) NOT NULL
);

CREATE TABLE MENU(
    MENU_NAME VARCHAR2(30) PRIMARY KEY,
    PRICE NUMBER(5) NOT NULL,
    MENU_TYPE VARCHAR2(10) NOT NULL,
    DESCRIPTION VARCHAR2(1000) NOT NULL,
    AVG_RATE NUMBER(2)
);


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

public class OrderedMenuDto extends MenuDto implements Serializable {
   
   private static final long serialVersionUID = 8611197365865197331L;
   
   private Date order_date;
   private String id;
   private String menu_type;
   private int coupon = 0;
   private int count;
   private int price;
   
   public OrderedMenuDto() {
   }


   public OrderedMenuDto(Date today, String id, String menu_type, String menu_name, int count, int coupon, int price) {
     
	   super.setMenu_name(menu_name);
      super.setPrice(price);
      
      this.order_date = today;
      this.id = id;
      this.menu_type = menu_type;      
      this.coupon = coupon;
      this.count = count;
      this.price = price;
      
   }
   
   public String getMenu_type() {
      return menu_type;
   }


   public void setMenu_type(String menu_type) {
      this.menu_type = menu_type;
   }


   public String getId() {
      return id;
   }


   public void setId(String id) {
      this.id = id;
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


   public int getPrice() {
      return price;
   }


   public void setPrice(int price) {
      this.price = price;
   }


   @Override
   public String toString() {
      return super.toString() + "OrderedMenuDto [order_date=" + order_date + ", id=" + id + ", menu_type=" + menu_type + ", coupon="
            + coupon + ", count=" + count + ", price=" + price + "]";
   }


}