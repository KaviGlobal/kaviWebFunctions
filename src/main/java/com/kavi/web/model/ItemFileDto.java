package com.kavi.web.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class ItemFileDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private byte[] file;
	
	
	public ItemFileDto(Integer id, byte[] file) {
		this.id = id;
		this.file = file;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public byte[] getFile() {
		return file;
	}


	public void setFile(byte[] file) {
		this.file = file;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
