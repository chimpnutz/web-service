package com.pc2mweb.model;

public class ResponseString {

	private String responseCode;
	
	private String traceNumber;
	
	private String responseMsg;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getTraceNumber() {
		return traceNumber;
	}

	public void setTraceNumber(String traceNumber) {
		this.traceNumber = traceNumber;
	}

	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("&RESPONSE_CODE=").append(getResponseCode())
		.append("&TRACE_NO=").append(getTraceNumber())
		.append("&RESPONSE_MSG=").append(getResponseMsg());
		
		return str.toString();
	}
}
