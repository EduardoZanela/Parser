package com.ef.entity;

import java.io.Serializable;

public class AccessId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dateString;
	private String ip;
	public String getAccessDate() {
		return dateString;
	}
	public void setAccessDate(String dateString) {
		this.dateString = dateString;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateString == null) ? 0 : dateString.hashCode());
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
		AccessId other = (AccessId) obj;
		if (dateString == null) {
			if (other.dateString != null)
				return false;
		} else if (!dateString.equals(other.dateString))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		return true;
	}
	
}
