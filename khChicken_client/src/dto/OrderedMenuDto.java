package dto;

import java.io.Serializable;
import java.sql.Date;

public class OrderedMenuDto extends MenuDto implements Serializable {

	private static final long serialVersionUID = 8611197365865197331L;

	private Date order_date;
	private String id;
	private int coupon;
	private int count;
	private int totalPrice;

	public OrderedMenuDto() {
	}

	public OrderedMenuDto(MenuDto dto) {
		super(dto);
		count = 1;
	}
	
	public OrderedMenuDto(Date order_date, String id, String type, String menu_name, int count, int coupon, int totalPrice) {
		this.order_date = order_date;
		this.id = id;
		super.setMenu_name(menu_name);
		super.setType(type);
		this.coupon = coupon;
		this.count = count;
		this.totalPrice = totalPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setPrice(int price) {
		super.setPrice(price);
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return  "OrderedMenuDto [ " + super.toString() + ", order_date=" + order_date + ", id=" + id + ", coupon=" + coupon
				+ ", count=" + count + ", totalPrice=" + totalPrice + "]";
	}
}
