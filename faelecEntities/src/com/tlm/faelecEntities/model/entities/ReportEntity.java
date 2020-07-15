package com.tlm.faelecEntities.model.entities;

import java.util.HashMap;

public class ReportEntity {
	HashMap<String, Object> data;
	HashMap<String, Mparpd00> mparpd;
	public HashMap<String, Object> getData() {
		return data;
	}
	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
	public HashMap<String, Mparpd00> getMparpd() {
		return mparpd;
	}
	public void setMparpd(HashMap<String, Mparpd00> mparpd) {
		this.mparpd = mparpd;
	}
	
}
