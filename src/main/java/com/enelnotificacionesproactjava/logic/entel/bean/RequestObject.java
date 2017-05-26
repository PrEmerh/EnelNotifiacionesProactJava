package com.enelnotificacionesproactjava.logic.entel.bean;


public class RequestObject{
	
	private InArguments[] inArguments;
	private String activityObjectID;
	public InArguments[] getInArguments() {
		return inArguments;
	}
	public void setInArguments(InArguments[] inArguments) {
		this.inArguments = inArguments;
	}
	public String getActivityObjectID() {
		return activityObjectID;
	}
	public void setActivityObjectID(String activityObjectID) {
		this.activityObjectID = activityObjectID;
	}
	

}

