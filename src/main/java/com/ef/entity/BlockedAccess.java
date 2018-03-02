package com.ef.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class BlockedAccess {
	@Id
	@Column
	private String ip;
	@Column
	private String reason;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;
	@Column
	private Integer requestsNumber;
	
	public BlockedAccess(){
		
	}
	
	public BlockedAccess(String ip, String reason, Date startDate, Date finishDate, Integer requestsNumber) {
		super();
		this.ip = ip;
		this.reason = reason;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.requestsNumber = requestsNumber;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public Integer getRequestsNumber() {
		return requestsNumber;
	}
	public void setRequestsNumber(Integer requestsNumber) {
		this.requestsNumber = requestsNumber;
	}
	
	
}
