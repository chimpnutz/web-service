
package com.payexchange.ws.dao;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.payexchange.ws.dao package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReqUploadResponse_QNAME = new QName("http://dao.ws.payexchange.com/", "reqUploadResponse");
    private final static QName _ReqUpload_QNAME = new QName("http://dao.ws.payexchange.com/", "reqUpload");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.payexchange.ws.dao
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReqUpload }
     * 
     */
    public ReqUpload createReqUpload() {
        return new ReqUpload();
    }

    /**
     * Create an instance of {@link ReqUploadResponse }
     * 
     */
    public ReqUploadResponse createReqUploadResponse() {
        return new ReqUploadResponse();
    }

    /**
     * Create an instance of {@link EpinsUploadResponse }
     * 
     */
    public EpinsUploadResponse createEpinsUploadResponse() {
        return new EpinsUploadResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReqUploadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dao.ws.payexchange.com/", name = "reqUploadResponse")
    public JAXBElement<ReqUploadResponse> createReqUploadResponse(ReqUploadResponse value) {
        return new JAXBElement<ReqUploadResponse>(_ReqUploadResponse_QNAME, ReqUploadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReqUpload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dao.ws.payexchange.com/", name = "reqUpload")
    public JAXBElement<ReqUpload> createReqUpload(ReqUpload value) {
        return new JAXBElement<ReqUpload>(_ReqUpload_QNAME, ReqUpload.class, null, value);
    }

}
