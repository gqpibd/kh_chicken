package server.dto;

import java.awt.Image;
import java.io.Serializable;

public class MenuShowDto extends MenuDto implements Serializable {
	
	private static final long serialVersionUID = 7675418139620414668L;
	
	private String image;
	private double avgRate;
	private int reviewCount;
	
	public MenuShowDto() {
	}

	public MenuShowDto(String image, double avgRate, int reviewCount) {
		super();
		this.image = image;
		this.avgRate = avgRate;
		this.reviewCount = reviewCount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getAvgRate() {
		return avgRate;
	}

	public void setAvgRate(double avgRate) {
		this.avgRate = avgRate;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	@Override
	public String toString() {
		return "MenuShowDto [image=" + image + ", avgRate=" + avgRate + ", reviewCount=" + reviewCount + "]";
	}
	
	

}
