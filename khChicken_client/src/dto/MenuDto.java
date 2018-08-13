package dto;

import java.io.Serializable;

public class MenuDto implements Serializable {

	private static final long serialVersionUID = -513161406750932290L;

	private String type;
	private String menu_name;
	private int price;

	
	public MenuDto() {
	}

	public MenuDto(String menu_type, String menu_name, int price) {
		this.type = menu_type;
		this.menu_name = menu_name;
		this.price = price;
	}

	public MenuDto(MenuDto dto) {
		this.menu_name = dto.menu_name;
		this.type = dto.type;
		this.price = dto.price;
	}

	public String getType() {
		return type;
	}

	public void setType(String menu_type) {
		this.type = menu_type;
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

	@Override
	public String toString() {
		return "type=" + type + ", menu_name=" + menu_name + ", price=" + price;
	}
	
	
}
