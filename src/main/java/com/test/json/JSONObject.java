package com.test.json;

/*
 * CLass to de-serialize JSON file
 * */
public class JSONObject {
	private String entityId;
	private String name;
	private String description;
	private String productType;
	private String category;
	private String sdmproductName;
	
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSdmproductName() {
		return sdmproductName;
	}
	public void setSdmproductName(String sdmproductName) {
		this.sdmproductName = sdmproductName;
	}
	
	public String toString() {
	    return "[NAME: " + name + ", DESCRIPTION:" + description + ", PRODUCTTYPE: " +productType +
		       ", SDMPRODUCTNAME: " + sdmproductName +"]";
	}
}
