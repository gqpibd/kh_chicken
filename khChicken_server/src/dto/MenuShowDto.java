package dto;

import java.io.Serializable;

public class MenuShowDto extends MenuDto implements Serializable {

	private static final long serialVersionUID = 7675418139620414668L;
	private double avgScore;

	public MenuShowDto() {
	}

	public MenuShowDto(String name, int price) {
		super(name, price);
		this.avgScore = 0;
	}
	
	public MenuShowDto(String name, int price, double avgScore) {
		super(name, price);
		this.avgScore = avgScore;
	}

	public double getavgScore() {
		return avgScore;
	}

	public void setavgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	@Override
	public String toString() {
		return super.toString() + "MenuShowDto [avgScore=" + avgScore + "]";
	}

}
