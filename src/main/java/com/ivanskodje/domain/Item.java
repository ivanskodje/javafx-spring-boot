package com.ivanskodje.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "item")
@EqualsAndHashCode(exclude = {"id"})
public @Data
class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "value")
	private Long value;

	@SuppressWarnings("unused")
	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}
}
