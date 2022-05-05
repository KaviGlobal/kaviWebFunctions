package com.kavi.web.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.kavi.web.model.ItemDto;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String title;
	private String typeId;
	private LocalDateTime createdAt;

	private String createdBy;

	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Item(ItemDto item) {
		this.title = item.getTitle();
		this.typeId = item.getTypeId();
		this.createdAt = LocalDateTime.now() ;
		this.createdBy = item.getCreatedBy();
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", typeId=" + typeId + ", createdBy=" + createdBy + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
