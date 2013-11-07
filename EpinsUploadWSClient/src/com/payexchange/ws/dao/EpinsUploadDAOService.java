package com.payexchange.ws.dao;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.7
 * 2013-11-05T13:55:47.774+08:00
 * Generated source version: 2.7.7
 * 
 */
@WebServiceClient(name = "EpinsUploadDAOService", 
                  wsdlLocation = "http://localhost:8080/EpinsUploadWS/services/EpinsUploadDAOPort?wsdl",
                  targetNamespace = "http://dao.ws.payexchange.com/") 
public class EpinsUploadDAOService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://dao.ws.payexchange.com/", "EpinsUploadDAOService");
    public final static QName EpinsUploadDAOPort = new QName("http://dao.ws.payexchange.com/", "EpinsUploadDAOPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/EpinsUploadWS/services/EpinsUploadDAOPort?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(EpinsUploadDAOService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/EpinsUploadWS/services/EpinsUploadDAOPort?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public EpinsUploadDAOService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EpinsUploadDAOService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EpinsUploadDAOService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EpinsUploadDAOService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EpinsUploadDAOService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EpinsUploadDAOService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns EpinsUploadDAOSEI
     */
    @WebEndpoint(name = "EpinsUploadDAOPort")
    public EpinsUploadDAOSEI getEpinsUploadDAOPort() {
        return super.getPort(EpinsUploadDAOPort, EpinsUploadDAOSEI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EpinsUploadDAOSEI
     */
    @WebEndpoint(name = "EpinsUploadDAOPort")
    public EpinsUploadDAOSEI getEpinsUploadDAOPort(WebServiceFeature... features) {
        return super.getPort(EpinsUploadDAOPort, EpinsUploadDAOSEI.class, features);
    }

}
