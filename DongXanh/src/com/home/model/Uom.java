package com.home.model;

// Generated Mar 1, 2016 10:27:23 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Uom generated by hbm2java
 */
public class Uom implements java.io.Serializable {

	private Integer id;
	private String uomName;
	private String description;

	public Uom() {
	}

	public Uom(String uomName, String description) {
		this.uomName = uomName;
		this.description = description;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUomName() {
		return this.uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
