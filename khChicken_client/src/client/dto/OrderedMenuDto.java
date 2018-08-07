package client.dto;

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

	public Date getOrder_date() {
			return order_date;
		}
	
	
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	
	
	@Override
	public String toString() {
		return "OrderedMenuDto [coupon=" + coupon + ", count=" + count + "]";
	}


	


	

}
