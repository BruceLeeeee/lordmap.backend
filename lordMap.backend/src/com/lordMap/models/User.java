package com.lordMap.models;

import java.util.Date;

public class User {
	private String userId;
	private String userPwd;
	private int atk;
	private Date[] last3Atks;
	private int money;
	private int[] landIds;
	private int[] tasks;
	private int[] friends;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public Date[] getLast3Atks() {
		return last3Atks;
	}
	public void setLast3Atks(Date[] last3Atks) {
		this.last3Atks = last3Atks;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int[] getLandIds() {
		return landIds;
	}
	public void setLandIds(int[] landIds) {
		this.landIds = landIds;
	}
	public int[] getTasks() {
		return tasks;
	}
	public void setTasks(int[] tasks) {
		this.tasks = tasks;
	}
	public int[] getFriends() {
		return friends;
	}
	public void setFriends(int[] friends) {
		this.friends = friends;
	}
}
