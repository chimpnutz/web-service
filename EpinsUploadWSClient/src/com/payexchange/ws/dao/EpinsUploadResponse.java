
package com.payexchange.ws.dao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for epinsUploadResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="epinsUploadResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resultcode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tracenumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "epinsUploadResponse", propOrder = {
    "password",
    "resultcode",
    "tracenumber"
})
public class EpinsUploadResponse {

    protected String password;
    protected int resultcode;
    protected String tracenumber;

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the resultcode property.
     * 
     */
    public int getResultcode() {
        return resultcode;
    }

    /**
     * Sets the value of the resultcode property.
     * 
     */
    public void setResultcode(int value) {
        this.resultcode = value;
    }

    /**
     * Gets the value of the tracenumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTracenumber() {
        return tracenumber;
    }

    /**
     * Sets the value of the tracenumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTracenumber(String value) {
        this.tracenumber = value;
    }

}
