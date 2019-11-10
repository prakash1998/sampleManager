package com.pra.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="USERS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements BaseModel<String>{
	
	@Id
	private String userName;
	private String password;
	
	
	@Override
	public String primaryKey() {
		return this.userName;
	}	

}
