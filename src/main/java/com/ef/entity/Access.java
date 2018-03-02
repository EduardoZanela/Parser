package com.ef.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(AccessId.class)
public class Access {
	//Date, IP, Request, Status, User Agent
	@Id
	private String dateString;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date accessDate;
	@Id
	@Column
	private String ip;
	@Column
	private String request;
	@Column
	private String status;
	@Column
	private String userAgent;
	
	public Access(){
		
	}
	
	public Access(String dateString, Date accessDate, String ip, String request, String status, String userAgent) {
		this.dateString = dateString;
		this.accessDate = accessDate;
		this.ip = ip;
		this.request = request;
		this.status = status;
		this.userAgent = userAgent;
	}
	
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString= dateString;
	}
	public Date getAccessDate() {
		return accessDate;
	}
	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Access other = (Access) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		return true;
	}
}
