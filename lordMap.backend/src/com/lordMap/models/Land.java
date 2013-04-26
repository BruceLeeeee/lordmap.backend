package com.lordMap.models;

public class Land {
	private int id;
	private int price;
	private double[] lats;
	private double[] longs;
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
	public double[] getLats() {
		return lats;
	}
	public void setLats(double[] lats) {
		this.lats = lats;
	}
	public double[] getLongs() {
		return longs;
	}
	public void setLongs(double[] longs) {
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
