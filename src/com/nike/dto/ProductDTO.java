package com.nike.dto;

public class ProductDTO {
	@Override
	public String toString() {
		return "ProductDTO [pnum=" + pnum + ", type=" + type + ", name=" + name + ", price=" + price + ", explain="
				+ explain + ", detailexplain=" + detailexplain + ", img=" + img + ", gender=" + gender + "]";
	}
	private int pnum;
	private String type;
	private String name;
	private int price;
	private String explain;
	private String detailexplain;
	private String img;
	private String gender;
	
	public ProductDTO() {}
	public ProductDTO(int pnum, String type, String name, int price, String explain, String detailexplain, String img,
			String gender) {
		super();
		this.pnum = pnum;
		this.type = type;
		this.name = name;
		this.price = price;
		this.explain = explain;
		this.detailexplain = detailexplain;
		this.img = img;
		this.gender = gender;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getDetailexplain() {
		return detailexplain;
	}
	public void setDetailexplain(String detailexplain) {
		this.detailexplain = detailexplain;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
