package com.kavi.web.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class ItemTag implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private Integer itemId;
	private String tagId;
	private Integer score;

	public ItemTag(Integer itemId, String tagId, Integer score) {
		this.itemId = itemId;
		this.tagId = tagId;
		this.score = score;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ItemTag [id=" + id + ", itemId=" + itemId + ", tagId=" + tagId + ", score=" + score + "]";
	}

}
