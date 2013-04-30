package com.lordMap.models;

public class Land {
	private long id;
	private long price;
	private double[] lats;
	private double[] longs;
	private long defence;
	private String owner;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
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
	public long getDefence() {
		return defence;
	}
	public void setDefence(long defence) {
		this.defence = defence;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
