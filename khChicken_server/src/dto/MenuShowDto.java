package server.dto;

import java.awt.Image;
import java.io.Serializable;

public class MenuShowDto extends MenuDto implements Serializable {
	
	private static final long serialVersionUID = 7675418139620414668L;
	
	private String image;
	private int reviewCount;
	
	public MenuShowDto() {
	}

	public MenuShowDto(String image, int reviewCount) {
		super();
		this.image = image;
		this.reviewCount = reviewCount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	@Override
	public String toString() {
		return "MenuShowDto [image=" + image + ", reviewCount=" + reviewCount + "]";
	}
	
	

}
