package dto;

import java.io.Serializable;

public class MenuShowDto extends MenuDto implements Serializable {
	
	private static final long serialVersionUID = 7675418139620414668L;
	
	private String image;
	private double avgScore;
	
	public MenuShowDto() {
	}
	

	public MenuShowDto(String name, int price, String image, double avgScore) {
		super(name, price);
		this.image = image;
		this.avgScore = avgScore;
	}


	public MenuShowDto(String image, double avgScore) {
		super();
		this.image = image;
		this.avgScore = avgScore;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public double getavgScore() {
		return avgScore;
	}

	public void setavgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	@Override
	public String toString() {
		
		return super.toString() + "MenuShowDto [image=" + image + ", avgScore=" + avgScore + "]";
	}
	
	

}
