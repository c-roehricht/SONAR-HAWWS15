//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2016.01.11 um 10:57:03 PM CET 
//


package de.haw_hamburg.informatik.sonardev.ws15.xmlRawClasses;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gv2 package. 
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

    private final static QName _Output_QNAME = new QName("", "output");
    private final static QName _Opa_QNAME = new QName("", "opa");
    private final static QName _Input_QNAME = new QName("", "input");
    private final static QName _Protocol_QNAME = new QName("", "protocol");
    private final static QName _Role_QNAME = new QName("", "roles");
    private final static QName _Task_QNAME = new QName("", "task");
    private final static QName _Split_QNAME = new QName("", "split");
    private final static QName _Organisation_QNAME = new QName("", "organisation");
    private final static QName _Refine_QNAME = new QName("", "refine");
    private final static QName _Deleg_QNAME = new QName("", "deleg");
    private final static QName _Exec_QNAME = new QName("", "exec");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gv2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Output }
     * 
     */
    public Output createOutput() {
        return new Output();
    }

    /**
     * Create an instance of {@link Opa }
     * 
     */
    public Opa createOpa() {
        return new Opa();
    }

    /**
     * Create an instance of {@link Input }
     * 
     */
    public Input createInput() {
        return new Input();
    }

    /**
     * Create an instance of {@link Protocol }
     * 
     */
    public Protocol createProtocol() {
        return new Protocol();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link Split }
     * 
     */
    public Split createSplit() {
        return new Split();
    }

    /**
     * Create an instance of {@link Organisation }
     * 
     */
    public Organisation createOrganisation() {
        return new Organisation();
    }

    /**
     * Create an instance of {@link Refine }
     * 
     */
    public Refine createRefine() {
        return new Refine();
    }

    /**
     * Create an instance of {@link Deleg }
     * 
     */
    public Deleg createDeleg() {
        return new Deleg();
    }

    /**
     * Create an instance of {@link Exec }
     * 
     */
    public Exec createExec() {
        return new Exec();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Output }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "output")
    public JAXBElement<Output> createOutput(Output value) {
        return new JAXBElement<Output>(_Output_QNAME, Output.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Opa }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "opa")
    public JAXBElement<Opa> createOpa(Opa value) {
        return new JAXBElement<Opa>(_Opa_QNAME, Opa.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Input }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "input")
    public JAXBElement<Input> createInput(Input value) {
        return new JAXBElement<Input>(_Input_QNAME, Input.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Protocol }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "protocol")
    public JAXBElement<Protocol> createProtocol(Protocol value) {
        return new JAXBElement<Protocol>(_Protocol_QNAME, Protocol.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "roles")
    public JAXBElement<String> createRole(String value) {
        return new JAXBElement<String>(_Role_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Task }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task")
    public JAXBElement<Task> createTask(Task value) {
        return new JAXBElement<Task>(_Task_QNAME, Task.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Split }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "split")
    public JAXBElement<Split> createSplit(Split value) {
        return new JAXBElement<Split>(_Split_QNAME, Split.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Organisation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "organisation")
    public JAXBElement<Organisation> createOrganisation(Organisation value) {
        return new JAXBElement<Organisation>(_Organisation_QNAME, Organisation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Refine }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "refine")
    public JAXBElement<Refine> createRefine(Refine value) {
        return new JAXBElement<Refine>(_Refine_QNAME, Refine.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Deleg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "deleg")
    public JAXBElement<Deleg> createDeleg(Deleg value) {
        return new JAXBElement<Deleg>(_Deleg_QNAME, Deleg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "exec")
    public JAXBElement<Exec> createExec(Exec value) {
        return new JAXBElement<Exec>(_Exec_QNAME, Exec.class, null, value);
    }

}
