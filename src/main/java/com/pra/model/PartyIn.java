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
@Table(name="PARTYIN")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyIn implements BaseModel<Integer> {
	
	@Id
	@TableCol(displayName = "Party Inward Id" , width=10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@TableCol(displayName = "Party Inward Name" , width=500)
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
