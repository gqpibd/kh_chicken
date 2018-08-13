package dto;

import java.io.Serializable;

public class CustomerManageDto implements Serializable{

	private static final long serialVersionUID = 3518330222275236249L;
	
	private String id;
	private String name;
	private String adr;
	private String phone;
	private int OrderCount;
	
	public CustomerManageDto() {
	}
	
	public CustomerManageDto(String id, String name, String adr, String phone, int orderCount) {
		this.id = id;
		this.name = name;
		this.adr = adr;
		this.phone = phone;
		OrderCount = orderCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getOrderCount() {
		return OrderCount;
	}

	public void setOrderCount(int orderCount) {
		OrderCount = orderCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CustomerManageDto [id=" + id + ", name=" + name + ", adr=" + adr + ", phone=" + phone + ", OrderCount="
				+ OrderCount + "]";
	} 
	
	
	
	

}
