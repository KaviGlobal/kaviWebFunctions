package com.kavi.web.entity;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.kavi.web.model.DimensionDto;

@EntityScan
@Table
public class Dimension implements Serializable{
    
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String name;
	private String description;

    public Dimension(){

    }

    public Dimension(DimensionDto in){
        this.setId(in.getId());
        this.setName(in.getName());
        this.setDescription(in.getDescription());
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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
   
    @Override
	public String toString() {
		return "ItemDto [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
