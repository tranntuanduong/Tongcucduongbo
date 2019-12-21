package com.jun.model;

public class Record {
	private Long id;
	private Integer vehicleId;
	private Integer totalKm;
	private Integer violateKm;
	private Integer totalTime;
	private Integer violateTime;
	private Integer createdDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Integer getTotalKm() {
		return totalKm;
	}
	public void setTotalKm(Integer totalKm) {
		this.totalKm = totalKm;
	}
	public Integer getViolateKm() {
		return violateKm;
	}
	public void setViolateKm(Integer violateKm) {
		this.violateKm = violateKm;
	}
	public Integer getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	public Integer getViolateTime() {
		return violateTime;
	}
	public void setViolateTime(Integer violateTime) {
		this.violateTime = violateTime;
	}
	public Integer getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Integer createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
