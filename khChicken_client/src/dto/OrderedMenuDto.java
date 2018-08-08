package dto;

import java.io.Serializable;

public class OrderedMenuDto extends MenuDto implements Serializable {
	
	private static final long serialVersionUID = 8611197365865197331L;
	
	private int coupon;
	private int count;
	private int totalPrice;
	
	
	public OrderedMenuDto() {
	}


	public OrderedMenuDto(int coupon, int count, int totalPrice) {
		super();
		this.coupon = coupon;
		this.count = count;
		this.totalPrice = totalPrice;
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


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	@Override
	public String toString() {
		return "OrderedMenuDto [coupon=" + coupon + ", count=" + count + ", totalPrice=" + totalPrice + "]";
	}
	
}
