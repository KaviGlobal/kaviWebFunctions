package com.kavi.web.entity;

import java.io.Serializable;

public class ItemTag implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer itemId;
	private String tagId;
	
	public ItemTag(Integer itemId, String tagId) {
		this.itemId = itemId;
		this.tagId = tagId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
