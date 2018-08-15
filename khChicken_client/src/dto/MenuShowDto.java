package dto;

import java.io.Serializable;

public class MenuShowDto extends MenuDto implements Serializable {
<<<<<<< HEAD
	
	private static final long serialVersionUID = 7675418139620414668L;
	
	private String description;
	private double avgScore;
	
	public MenuShowDto() {
	} 

	public MenuShowDto(MenuDto dto) {
		super(dto);
		this.avgScore = 0;
	}

	public MenuShowDto(String name, int price, String type, String description, double avgScore) {
		super(type, name, price);
		this.description = description;
		this.avgScore = avgScore;
	}

	public double getavgScore() {
		return avgScore;
	}

	public void setavgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MenuShowDto [ " + super.toString() + ", description=" + description + ", avgScore=" + avgScore + "]";
	}
=======

	private static final long serialVersionUID = 7675418139620414668L;

	private String type;
	private String description;
	private double avgScore;

	public MenuShowDto() {
	}

	public MenuShowDto(String name, int price) {
		super(name, price);
		this.avgScore = 0;
	}
	
	public MenuShowDto(String name, int price, String type, String description, double avgScore) {
		super(name, price);
		this.type = type;
		this.description = description;
		this.avgScore = avgScore;
	}

	public double getavgScore() {
		return avgScore;
	}

	public void setavgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MenuShowDto [ menuName =" + super.getMenu_name() + ", price = " + super.getPrice() + ", type=" + type + ", description=" + description + ", avgScore=" + avgScore + "]";
	}


>>>>>>> branch 'jinyoung' of https://github.com/gqpibd/kh_semi.git
}
