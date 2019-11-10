package com.pra.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CONSTANTS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Constants implements BaseModel<String> {
	
	@Id
	private String constName;
	private String constValue;
	
	@Override
	public String primaryKey() {
		return this.constName;
	}

}
