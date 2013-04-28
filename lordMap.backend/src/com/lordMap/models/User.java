package com.lordMap.models;

import java.util.Date;

public class User {
	private String userId;
	private String userPwd;
	private long atk;
	private long money;
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
	public long getAtk() {
		return atk;
	}
	public void setAtk(long atk) {
		this.atk = atk;
	}
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
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
