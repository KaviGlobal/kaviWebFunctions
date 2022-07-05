package com.kavi.web.model;

import java.io.Serializable;

public class MediaDto implements Serializable {

	private Integer id;
	private String name;
	private String description;
	private String typeId;
	public MediaDto(Integer id, String name, String description, String typeId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.typeId = typeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "MediaDto [id=" + id + ", name=" + name + ", description=" + description + ", typeId=" + typeId + "]";
	}
}
