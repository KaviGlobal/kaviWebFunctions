package com.kavi.web.model;

import java.io.Serializable;
import java.util.List;

public class ItemTagDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String typeId;
	private List<String> tagIds;
	private String createdBy;

	public ItemTagDto(Integer id, String title, String typeId, List<String> tagIds, String createdBy) {
		super();
		this.id = id;
		this.title = title;
		this.typeId = typeId;
		this.tagIds = tagIds;
		this.createdBy = createdBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<String> getTagIds() {
		return tagIds;
	}

	public void setTagIds(List<String> tagIds) {
		this.tagIds = tagIds;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ItemDto [id=" + id + ", title=" + title + ", typeId=" + typeId + ", tagIds=" + tagIds + ", createdBy="
				+ createdBy + "]";
	}

}
