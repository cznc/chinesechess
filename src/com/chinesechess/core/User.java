package com.chinesechess.core;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	Integer id;
	String name;

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

}
