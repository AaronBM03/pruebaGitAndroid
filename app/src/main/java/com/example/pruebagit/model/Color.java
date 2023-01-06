package com.example.pruebagit.model;


/**
 * This enum denotes card color. It's usually referenced as rarity, but rarity
 * actually depends on how often the card is pulled from packs. Color is the
 * name used in the API to reference card color
 *
 * @author Miguel Rubio
 *
 */
public enum Color {
	ICON("icon"),
	GOLD("gold"),
	SILVER("silver"),
	BRONZE("bronze");

	private final String value;

	private Color (String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
