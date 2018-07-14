package com.mazurak.cinema.entity;

public abstract class BaseEntityCommonFieldsAbstract implements BaseEntity {

	private Long id;
	
	public BaseEntityCommonFieldsAbstract(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ID [id=" + id + "]";
	}
}
