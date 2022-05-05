package com.kavi.web.model;

import java.io.Serializable;

public class ItemDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private String typeId;
	private String tagId;
	private String createdBy;

	public ItemDto(String title, String typeId, String tagId ,String createdBy) {
		this.title = title;
		this.typeId = typeId;
		this.typeId = tagId;
		this.createdBy = createdBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	
	

}
