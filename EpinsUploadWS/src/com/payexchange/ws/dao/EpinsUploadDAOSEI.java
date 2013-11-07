package com.payexchange.ws.dao;

import javax.jws.WebService;

import com.payexchange.ws.beans.EpinsUploadResponse;

@WebService(name = "EpinsUploadDAOSEI", targetNamespace = "http://dao.ws.payexchange.com/")
public interface EpinsUploadDAOSEI {

	public EpinsUploadResponse reqUpload(String Trace, String ProdCode,
			int Qty, String type, String target, String appname,
			String ipaddress, String trantype, String Denom, String message,
			String username, String password, String transid);

}