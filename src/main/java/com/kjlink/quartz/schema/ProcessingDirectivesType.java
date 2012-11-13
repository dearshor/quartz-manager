//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.07 at 09:36:48 上午 CST 
//


package com.kjlink.quartz.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for processing-directivesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="processing-directivesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="overwrite-existing-data" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ignore-duplicates" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "processing-directivesType", propOrder = {
    "overwriteExistingData",
    "ignoreDuplicates"
})
public class ProcessingDirectivesType {

    @XmlElement(name = "overwrite-existing-data", defaultValue = "true")
    protected Boolean overwriteExistingData;
    @XmlElement(name = "ignore-duplicates", defaultValue = "false")
    protected Boolean ignoreDuplicates;

    /**
     * Gets the value of the overwriteExistingData property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOverwriteExistingData() {
        return overwriteExistingData;
    }

    /**
     * Sets the value of the overwriteExistingData property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOverwriteExistingData(Boolean value) {
        this.overwriteExistingData = value;
    }

    /**
     * Gets the value of the ignoreDuplicates property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIgnoreDuplicates() {
        return ignoreDuplicates;
    }

    /**
     * Sets the value of the ignoreDuplicates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIgnoreDuplicates(Boolean value) {
        this.ignoreDuplicates = value;
    }

}
