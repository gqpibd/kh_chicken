package dto;

<<<<<<< HEAD
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
	
	

=======
import java.io.Serializable;

public class MenuShowDto extends MenuDto implements Serializable {

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
>>>>>>> refs/remotes/origin/도현+다슬+승지
}
