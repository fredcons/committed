package com.fullsix.committed.core.model;

public class SelectableField {
	
	String value;
	long count;
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public long getCount() {
		return count;
	}
	
	public void setCount(long count) {
		this.count = count;
	}	
	
	public String getDisplayLabel() {
		return value + " (" + count + ")";
	} 

}
