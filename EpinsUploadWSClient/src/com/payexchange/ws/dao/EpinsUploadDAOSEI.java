package com.payexchange.ws.dao;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7
 * 2013-11-15T11:12:23.435+08:00
 * Generated source version: 2.7.7
 * 
 */
@WebService(targetNamespace = "http://dao.ws.payexchange.com/", name = "EpinsUploadDAOSEI")
@XmlSeeAlso({ObjectFactory.class})
public interface EpinsUploadDAOSEI {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "reqUpload", targetNamespace = "http://dao.ws.payexchange.com/", className = "com.payexchange.ws.dao.ReqUpload")
    @WebMethod
    @ResponseWrapper(localName = "reqUploadResponse", targetNamespace = "http://dao.ws.payexchange.com/", className = "com.payexchange.ws.dao.ReqUploadResponse")
    public com.payexchange.ws.dao.EpinsUploadResponse reqUpload(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        java.lang.String arg6,
        @WebParam(name = "arg7", targetNamespace = "")
        java.lang.String arg7,
        @WebParam(name = "arg8", targetNamespace = "")
        java.lang.String arg8,
        @WebParam(name = "arg9", targetNamespace = "")
        java.lang.String arg9,
        @WebParam(name = "arg10", targetNamespace = "")
        java.lang.String arg10,
        @WebParam(name = "arg11", targetNamespace = "")
        java.lang.String arg11,
        @WebParam(name = "arg12", targetNamespace = "")
        java.lang.String arg12,
        @WebParam(name = "arg13", targetNamespace = "")
        java.lang.String arg13
    );
}
