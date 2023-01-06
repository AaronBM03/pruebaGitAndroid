package com.example.pruebagit.model;

import java.util.List;


public class Nation
{
	private int id;
	private String name;

	private List<Card> cards;

	public Nation(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Nation() { }

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Nation [id=" + id + ", name=" + name + "]";
	}

}
