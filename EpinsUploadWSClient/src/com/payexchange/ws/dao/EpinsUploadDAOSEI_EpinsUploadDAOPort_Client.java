
package com.payexchange.ws.dao;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7
 * 2013-10-22T15:46:10.211+08:00
 * Generated source version: 2.7.7
 * 
 */
public final class EpinsUploadDAOSEI_EpinsUploadDAOPort_Client {

    private static final QName SERVICE_NAME = new QName("http://dao.ws.payexchange.com/", "EpinsUploadDAOService");

    private EpinsUploadDAOSEI_EpinsUploadDAOPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = EpinsUploadDAOService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        EpinsUploadDAOService ss = new EpinsUploadDAOService(wsdlURL, SERVICE_NAME);
        EpinsUploadDAOSEI port = ss.getEpinsUploadDAOPort();  
        
        {
        System.out.println("Invoking reqUpload...");
        java.lang.String _reqUpload_arg0 = "";
        java.lang.String _reqUpload_arg1 = "GT";
        int _reqUpload_arg2 = 10;
        java.lang.String _reqUpload_arg3 = "";
        java.lang.String _reqUpload_arg4 = "_reqUpload_arg4610554814";
        java.lang.String _reqUpload_arg5 = "PC2MWEB";
        java.lang.String _reqUpload_arg6 = "_reqUpload_arg6-1373198287";
        java.lang.String _reqUpload_arg7 = "topup";
        java.lang.String _reqUpload_arg8 = "100";
        java.lang.String _reqUpload_arg9 = "_reqUpload_arg91055428606";
        java.lang.String _reqUpload_arg10 = "Loadcentral";
        java.lang.String _reqUpload_arg11 = "admin";
        java.lang.String _reqUpload_arg12 = "1";
        com.payexchange.ws.dao.EpinsUploadResponse _reqUpload__return = port.reqUpload(_reqUpload_arg0, _reqUpload_arg1, _reqUpload_arg2, _reqUpload_arg3, _reqUpload_arg4, _reqUpload_arg5, _reqUpload_arg6, _reqUpload_arg7, _reqUpload_arg8, _reqUpload_arg9, _reqUpload_arg10, _reqUpload_arg11, _reqUpload_arg12);
        System.out.println("reqUpload.result=" + _reqUpload__return.getPassword());
        System.out.println("reqUpload.result=" + _reqUpload__return.getResultcode());
        System.out.println("reqUpload.result=" + _reqUpload__return.getTracenumber());


        }

        System.exit(0);
    }

}
