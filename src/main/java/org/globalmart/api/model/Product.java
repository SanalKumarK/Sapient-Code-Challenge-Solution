package org.globalmart.api.model;

public class Product {
	public static final String COL_NAME = "name";
	public static final String COL_ID = "Id";
	public static final String COL_TYPE = "type";
	public static final String COL_PRICE = "price";
	public static final String COL_MANUF = "manufacturer";
	
	
	private String name ;
	private Integer Id;
	private String type;
	private Long price;
	private String manufacturer;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
}
