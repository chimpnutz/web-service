package com.pc2mweb.model;

import java.sql.Timestamp;

public class OutgoingSMSModel {
	private long smsID;
	private Timestamp smsDate;
	private String sender;
	private String recipient;
	private String smsData;
	private String gatewayID;
	private String tariffClass;
	private String serviceType;
	private int status = 0;

	public OutgoingSMSModel() {}

	public OutgoingSMSModel(String sender,
			String recipient, String smsData) {
		this.sender = sender;
		this.recipient = recipient;
		this.smsData = smsData;
	}

	// SETTER, GETTER METHODS
	public String getGatewayID() {
		return gatewayID;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getSender() {
		return sender;
	}

	public String getServiceType() {
		return serviceType;
	}

	public String getSmsData() {
		return smsData;
	}

	public Timestamp getSmsDate() {
		return smsDate;
	}

	public long getSmsID() {
		return smsID;
	}

	public int getStatus() {
		return status;
	}

	public String getTariffClass() {
		return tariffClass;
	}

	public void setGatewayID(String string) {
		gatewayID = string;
	}

	public void setRecipient(String string) {
		recipient = string;
	}

	public void setSender(String string) {
		sender = string;
	}

	public void setServiceType(String string) {
		serviceType = string;
	}

	public void setSmsData(String string) {
		smsData = string;
	}

	public void setSmsDate(Timestamp timestamp) {
		smsDate = timestamp;
	}

	public void setSmsID(long l) {
		smsID = l;
	}

	public void setStatus(int i) {
		status = i;
	}

	public void setTariffClass(String string) {
		tariffClass = string;
	}
}