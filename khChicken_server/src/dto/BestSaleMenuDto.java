package dto;

import java.io.Serializable;

public class BestSaleMenuDto extends MenuDto implements Serializable {

	private static final long serialVersionUID = -2798385730859068125L;

	private int total_sale;
	private int total_coupon;
	private int total_price;
	private double score;

	public BestSaleMenuDto() {
	
	}
	
	public BestSaleMenuDto(String menu_type, String menu_name, int price, int total_sale, int total_coupon, int total_price, double score) {
		super(menu_type,menu_name, price);
		this.total_sale = total_sale;
		this.total_coupon = total_coupon;
		this.total_price = total_price;
		this.score = score;
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
	
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "BestSaleMenuDto [" + super.toString() + " total_sale=" + total_sale
				+ ", total_coupon=" + total_coupon + ", total_price=" + total_price + "]";
	}

}
