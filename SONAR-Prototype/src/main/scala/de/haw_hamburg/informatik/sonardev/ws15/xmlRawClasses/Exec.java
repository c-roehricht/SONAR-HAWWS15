//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2016.01.11 um 10:57:03 PM CET 
//


package de.haw_hamburg.informatik.sonardev.ws15.xmlRawClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r Exec complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Exec">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}input"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Exec", propOrder = {
    "input"
})
public class Exec {

    @XmlElement(required = true)
    protected Input input;

    /**
     * Ruft den Wert der input-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Input }
     *     
     */
    public Input getInput() {
        return input;
    }

    /**
     * Legt den Wert der input-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Input }
     *     
     */
    public void setInput(Input value) {
        this.input = value;
    }

}
