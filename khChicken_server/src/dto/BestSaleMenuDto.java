package dto;

import java.io.Serializable;

public class BestSaleMenuDto implements Serializable {

	private static final long serialVersionUID = -2798385730859068125L;

	private String menu_type;
	private String menu_name;
	private int total_sale;
	private int total_coupon;
	private int total_price;

	public BestSaleMenuDto(String menu_type, String menu_name, int total_sale, int total_coupon, int total_price) {
		this.menu_type = menu_type;
		this.menu_name = menu_name;
		this.total_sale = total_sale;
		this.total_coupon = total_coupon;
		this.total_price = total_price;
	}

	public String getMenu_type() {
		return menu_type;
	}

	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getTotal_sale() {
		return total_sale;
	}

	public void setTotal_sale(int total_sale) {
		this.total_sale = total_sale;
	}

	public int getTotal_coupon() {
		return total_coupon;
	}

	public void setTotal_coupon(int total_coupon) {
		this.total_coupon = total_coupon;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	@Override
	public String toString() {
		return "BestSaleMenuDto [menu_type=" + menu_type + ", menu_name=" + menu_name + ", total_sale=" + total_sale
				+ ", total_coupon=" + total_coupon + ", total_price=" + total_price + "]";
	}

}
