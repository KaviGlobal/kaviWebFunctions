package com.kavi.web.model;

import java.io.Serializable;
import java.util.List;

public class ItemTypeTagRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> itemTypes;
	private List<String> tagIds;

	public ItemTypeTagRequest(List<String> itemTypes, List<String> tagIds) {
		super();
		this.itemTypes = itemTypes;
		this.tagIds = tagIds;
	}

	public List<String> getItemTypes() {
		return itemTypes;
	}

	public void setItemTypes(List<String> itemTypes) {
		this.itemTypes = itemTypes;
	}

	public List<String> getTagIds() {
		return tagIds;
	}

	public void setTagIds(List<String> tagIds) {
		this.tagIds = tagIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
