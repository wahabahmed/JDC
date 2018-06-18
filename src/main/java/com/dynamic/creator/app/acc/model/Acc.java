package com.dynamic.creator.app.acc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/***
 * @author wahab
 * @since v1.0
 */
@Entity
@Table(name = "db_acc")
public class Acc {

	@Id
	@Column(unique = true, nullable = false)
	@NotNull
	private int id;
	@Column(length = 255, unique = false, nullable = false)
	@NotNull
	private String name;


	Acc(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}