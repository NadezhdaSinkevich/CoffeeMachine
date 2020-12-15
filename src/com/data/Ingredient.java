package com.data;

public class Ingredient {
	private int id ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public Ingredient() {
	}
	public Ingredient(String name, int count, int price) {
		this.name = name;
		this.count = count;
		this.price = price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	private String name;
	private int count;
	private int price;
}
