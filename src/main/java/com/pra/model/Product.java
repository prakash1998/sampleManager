package com.pra.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pra.annotations.TableCol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="PRODUCT")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements BaseModel<Integer>{
	
	@Id
	@TableCol(displayName = "Product id" , width=10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@TableCol(displayName = "Product Name" , width=500)
	private String name;

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public Integer primaryKey() {
		return this.id;
	}
	
	
}
