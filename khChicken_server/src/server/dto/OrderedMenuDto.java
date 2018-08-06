package server.dto;

import java.io.Serializable;

public class OrderedMenuDto extends MenuDto implements Serializable {
	
	private static final long serialVersionUID = 8611197365865197331L;
	
	private int coupon;
	private int count;
	
	
	public OrderedMenuDto() {
	}


	public OrderedMenuDto(String menu_name, int price, int coupon, int count) {
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


	@Override
	public String toString() {
		return "OrderedMenuDto [coupon=" + coupon + ", count=" + count + "]";
	}


	
	

}
