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
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java-Klasse f�r Split complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Split">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}input"/>
 *         &lt;element ref="{}output" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Split", propOrder = {
    "input",
    "output"
})
public class Split {

    @XmlElement(required = true)
    protected Input input;
    @XmlElement(required = true)
    protected List<Output> output;

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

    /**
     * Gets the value of the output property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the output property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutput().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Output }
     * 
     * 
     */
    public List<Output> getOutput() {
        if (output == null) {
            output = new ArrayList<Output>();
        }
        return this.output;
    }

}
