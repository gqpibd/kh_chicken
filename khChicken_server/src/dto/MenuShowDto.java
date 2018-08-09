package dto;

import java.awt.Image;
import java.io.Serializable;

public class MenuShowDto extends MenuDto implements Serializable {
	
	private static final long serialVersionUID = 7675418139620414668L;
	
	private String image;
	//private int reviewCount;	best메뉴만들기위해서 해놨었음
	private double avgScore;
	
	public MenuShowDto() {
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


	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	@Override
	public String toString() {
		return "MenuShowDto [image=" + image + ", avgScore=" + avgScore + "]";
	}
	
	
}
