package dto;

import java.io.Serializable;

public class MemberDto implements Serializable {
	
	private static final long serialVersionUID = -2270144856528113975L;
	
	public static final int MANAGER = 1;
	public static final int MEMBER = 3;
	
	private String id;
	private String pw;
	private String name;
	private int auth;
	private int coupon;
	private String address;
	private String phone;
	
	public MemberDto() {
	}

	public MemberDto(String id, String pw, String name, int coupon, String address, String phone) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.coupon = coupon;
		this.address = address;
		this.phone = phone;
		this.auth = MEMBER;
	}

	public int getAuth() {
		return auth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoupon() {
		return coupon;
	}

	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pw=" + pw + ", name=" + name + ", coupon=" + coupon + ", address=" + address + ", phone=" + phone + "]";
	}
	
	

}
