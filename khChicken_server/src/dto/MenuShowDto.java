package dto;

import java.io.Serializable;

public class MenuShowDto extends MenuDto implements Serializable {
	
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
}
