
package com.payexchange.ws.dao.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.7
 * Tue Nov 05 13:53:48 SGT 2013
 * Generated source version: 2.7.7
 */

@XmlRootElement(name = "reqUploadResponse", namespace = "http://dao.ws.payexchange.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reqUploadResponse", namespace = "http://dao.ws.payexchange.com/")

public class ReqUploadResponse {

    @XmlElement(name = "return")
    private com.payexchange.ws.beans.EpinsUploadResponse _return;

    public com.payexchange.ws.beans.EpinsUploadResponse getReturn() {
        return this._return;
    }

    public void setReturn(com.payexchange.ws.beans.EpinsUploadResponse new_return)  {
        this._return = new_return;
    }

}

