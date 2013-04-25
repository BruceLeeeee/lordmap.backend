package com.lordMap.models;

public class Land {
	private int id;
	private int price;
	private int[] lats;
	private int[] longs;
	private int defence;
	private String owner;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int[] getLats() {
		return lats;
	}
	public void setLats(int[] lats) {
		this.lats = lats;
	}
	public int[] getLongs() {
		return longs;
	}
	public void setLongis(int[] longs) {
		this.longs = longs;
	}
	public int getDefence() {
		return defence;
	}
	public void setDefence(int defence) {
		this.defence = defence;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
