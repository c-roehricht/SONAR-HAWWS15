//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2016.01.11 um 10:57:03 PM CET 
//


package de.haw_hamburg.informatik.sonardev.ws15.xmlRawClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java-Klasse f�r Eckiges complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Eckiges">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}exec" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}split" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}refine" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}deleg" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConcreteTask", propOrder = {
    "exec",
    "split",
    "refine",
    "deleg"
})
public class Task {

    protected List<Exec> exec;
    protected List<Split> split;
    protected List<Refine> refine;
    protected List<Deleg> deleg;

    /**
     * Gets the value of the exec property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the exec property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExec().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Exec }
     * 
     * 
     */
    public List<Exec> getExec() {
        if (exec == null) {
            exec = new ArrayList<Exec>();
        }
        return this.exec;
    }

    /**
     * Gets the value of the split property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the split property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSplit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Split }
     * 
     * 
     */
    public List<Split> getSplit() {
        if (split == null) {
            split = new ArrayList<Split>();
        }
        return this.split;
    }

    /**
     * Gets the value of the refine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Refine }
     * 
     * 
     */
    public List<Refine> getRefine() {
        if (refine == null) {
            refine = new ArrayList<Refine>();
        }
        return this.refine;
    }

    /**
     * Gets the value of the deleg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deleg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeleg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Deleg }
     * 
     * 
     */
    public List<Deleg> getDeleg() {
        if (deleg == null) {
            deleg = new ArrayList<Deleg>();
        }
        return this.deleg;
    }

}
