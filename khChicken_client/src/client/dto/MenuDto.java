package client.dto;

import java.io.Serializable;

public class MenuDto implements Serializable {

	private static final long serialVersionUID = -513161406750932290L;

	private String menu_name;
	private int price;

	public MenuDto() {
	}

	public MenuDto(String menu_name, int price) {
		this.menu_name = menu_name;
		this.price = price;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MenuDto [menu_name=" + menu_name + ", price=" + price + "]";
	}

}
