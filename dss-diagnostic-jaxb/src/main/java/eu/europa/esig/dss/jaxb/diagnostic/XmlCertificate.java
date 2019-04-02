//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.02 at 03:56:12 PM CEST 
//


package eu.europa.esig.dss.jaxb.diagnostic;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import eu.europa.esig.dss.validation.XmlCertificateSourceType;


/**
 * <p>Java class for Certificate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Certificate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SubjectDistinguishedName" type="{http://dss.esig.europa.eu/validation/diagnostic}DistinguishedName" maxOccurs="unbounded"/&gt;
 *         &lt;element name="IssuerDistinguishedName" type="{http://dss.esig.europa.eu/validation/diagnostic}DistinguishedName" maxOccurs="unbounded"/&gt;
 *         &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="CommonName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Locality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CountryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OrganizationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="GivenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OrganizationalUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Pseudonym" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AuthorityInformationAccessUrls" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CRLDistributionPoints" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="OCSPAccessUrls" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Sources"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Source" type="{http://dss.esig.europa.eu/validation/diagnostic}CertificateSourceType" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NotAfter" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="NotBefore" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="PublicKeySize" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="PublicKeyEncryptionAlgo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="KeyUsageBits" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="KeyUsage" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ExtendedKeyUsages" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="oid" type="{http://dss.esig.europa.eu/validation/diagnostic}OID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="IdPkixOcspNoCheck" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="BasicSignature" type="{http://dss.esig.europa.eu/validation/diagnostic}BasicSignature"/&gt;
 *         &lt;element name="SigningCertificate" type="{http://dss.esig.europa.eu/validation/diagnostic}SigningCertificate" minOccurs="0"/&gt;
 *         &lt;element name="CertificateChain" type="{http://dss.esig.europa.eu/validation/diagnostic}CertificateChain" minOccurs="0"/&gt;
 *         &lt;element name="Trusted" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="SelfSigned" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="CertificatePolicies" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="certificatePolicy" type="{http://dss.esig.europa.eu/validation/diagnostic}CertificatePolicy" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="QCStatementIds" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="oid" type="{http://dss.esig.europa.eu/validation/diagnostic}OID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="QCTypes" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="oid" type="{http://dss.esig.europa.eu/validation/diagnostic}OID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TrustedServiceProviders" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="TrustedServiceProvider" type="{http://dss.esig.europa.eu/validation/diagnostic}TrustedServiceProvider" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Revocations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="CertificateRevocation" type="{http://dss.esig.europa.eu/validation/diagnostic}CertificateRevocation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Base64Encoded" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *           &lt;element name="DigestAlgoAndValue" type="{http://dss.esig.europa.eu/validation/diagnostic}DigestAlgoAndValue"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Certificate", propOrder = {
    "subjectDistinguishedName",
    "issuerDistinguishedName",
    "serialNumber",
    "commonName",
    "locality",
    "state",
    "countryName",
    "organizationName",
    "givenName",
    "organizationalUnit",
    "surname",
    "pseudonym",
    "email",
    "authorityInformationAccessUrls",
    "crlDistributionPoints",
    "ocspAccessUrls",
    "sources",
    "notAfter",
    "notBefore",
    "publicKeySize",
    "publicKeyEncryptionAlgo",
    "keyUsageBits",
    "extendedKeyUsages",
    "idPkixOcspNoCheck",
    "basicSignature",
    "signingCertificate",
    "certificateChain",
    "trusted",
    "selfSigned",
    "certificatePolicies",
    "qcStatementIds",
    "qcTypes",
    "trustedServiceProviders",
    "revocations",
    "base64Encoded",
    "digestAlgoAndValue"
})
public class XmlCertificate implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "SubjectDistinguishedName", required = true)
    protected List<XmlDistinguishedName> subjectDistinguishedName;
    @XmlElement(name = "IssuerDistinguishedName", required = true)
    protected List<XmlDistinguishedName> issuerDistinguishedName;
    @XmlElement(name = "SerialNumber", required = true)
    protected BigInteger serialNumber;
    @XmlElement(name = "CommonName")
    protected String commonName;
    @XmlElement(name = "Locality")
    protected String locality;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "CountryName")
    protected String countryName;
    @XmlElement(name = "OrganizationName")
    protected String organizationName;
    @XmlElement(name = "GivenName")
    protected String givenName;
    @XmlElement(name = "OrganizationalUnit")
    protected String organizationalUnit;
    @XmlElement(name = "Surname")
    protected String surname;
    @XmlElement(name = "Pseudonym")
    protected String pseudonym;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElementWrapper(name = "AuthorityInformationAccessUrls")
    @XmlElement(name = "Url", namespace = "http://dss.esig.europa.eu/validation/diagnostic")
    protected List<String> authorityInformationAccessUrls;
    @XmlElementWrapper(name = "CRLDistributionPoints")
    @XmlElement(name = "Url", namespace = "http://dss.esig.europa.eu/validation/diagnostic")
    protected List<String> crlDistributionPoints;
    @XmlElementWrapper(name = "OCSPAccessUrls")
    @XmlElement(name = "Url", namespace = "http://dss.esig.europa.eu/validation/diagnostic")
    protected List<String> ocspAccessUrls;
    @XmlElementWrapper(name = "Sources", required = true)
    @XmlElement(name = "Source", namespace = "http://dss.esig.europa.eu/validation/diagnostic", type = String.class)
    @XmlJavaTypeAdapter(Adapter4 .class)
    protected List<XmlCertificateSourceType> sources;
    @XmlElement(name = "NotAfter", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date notAfter;
    @XmlElement(name = "NotBefore", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date notBefore;
    @XmlElement(name = "PublicKeySize")
    protected int publicKeySize;
    @XmlElement(name = "PublicKeyEncryptionAlgo", required = true)
    protected String publicKeyEncryptionAlgo;
    @XmlElementWrapper(name = "KeyUsageBits")
    @XmlElement(name = "KeyUsage", namespace = "http://dss.esig.europa.eu/validation/diagnostic")
    protected List<String> keyUsageBits;
    @XmlElementWrapper(name = "ExtendedKeyUsages")
    @XmlElement(name = "oid", namespace = "http://dss.esig.europa.eu/validation/diagnostic")
    protected List<XmlOID> extendedKeyUsages;
    @XmlElement(name = "IdPkixOcspNoCheck")
    protected Boolean idPkixOcspNoCheck;
    @XmlElement(name = "BasicSignature", required = true)
    protected XmlBasicSignature basicSignature;
    @XmlElement(name = "SigningCertificate")
    protected XmlSigningCertificate signingCertificate;
    @XmlElementWrapper(name = "CertificateChain")
    @XmlElement(name = "ChainItem", namespace = "http://dss.esig.europa.eu/validation/diagnostic")
    protected List<XmlChainItem> certificateChain;
    @XmlElement(name = "Trusted")
    protected boolean trusted;
    @XmlElement(name = "SelfSigned")
    protected boolean selfSigned;
    @XmlElementWrapper(name = "CertificatePolicies")
    @XmlElement(name = "certificatePolicy", namespace = "http://dss.esig.europa.eu/validation/diagnostic")
    protected List<XmlCertificatePolicy> certificatePolicies;
    @XmlElementWrapper(name = "QCStatementIds")
    @XmlElement(name = "oid", namespace = "http://dss.esig.europa.eu/validation/diagnostic")
    protected List<XmlOID> qcStatementIds;
    @XmlElementWrapper(name = "QCTypes")
    @XmlElement(name = "oid", namespace = "http://dss.esig.europa.eu/validation/diagnostic")
    protected List<XmlOID> qcTypes;
    @XmlElementWrapper(name = "TrustedServiceProviders")
    @XmlElement(name = "TrustedServiceProvider", namespace = "http://dss.esig.europa.eu/validation/diagnostic")
    protected List<XmlTrustedServiceProvider> trustedServiceProviders;
    @XmlElementWrapper(name = "Revocations")
    @XmlElement(name = "CertificateRevocation", namespace = "http://dss.esig.europa.eu/validation/diagnostic")
    protected List<XmlCertificateRevocation> revocations;
    @XmlElement(name = "Base64Encoded")
    protected byte[] base64Encoded;
    @XmlElement(name = "DigestAlgoAndValue")
    protected XmlDigestAlgoAndValue digestAlgoAndValue;
    @XmlAttribute(name = "Id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the subjectDistinguishedName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subjectDistinguishedName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubjectDistinguishedName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlDistinguishedName }
     * 
     * 
     */
    public List<XmlDistinguishedName> getSubjectDistinguishedName() {
        if (subjectDistinguishedName == null) {
            subjectDistinguishedName = new ArrayList<XmlDistinguishedName>();
        }
        return this.subjectDistinguishedName;
    }

    /**
     * Gets the value of the issuerDistinguishedName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the issuerDistinguishedName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIssuerDistinguishedName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlDistinguishedName }
     * 
     * 
     */
    public List<XmlDistinguishedName> getIssuerDistinguishedName() {
        if (issuerDistinguishedName == null) {
            issuerDistinguishedName = new ArrayList<XmlDistinguishedName>();
        }
        return this.issuerDistinguishedName;
    }

    /**
     * Gets the value of the serialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the value of the serialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSerialNumber(BigInteger value) {
        this.serialNumber = value;
    }

    /**
     * Gets the value of the commonName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * Sets the value of the commonName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommonName(String value) {
        this.commonName = value;
    }

    /**
     * Gets the value of the locality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocality() {
        return locality;
    }

    /**
     * Sets the value of the locality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocality(String value) {
        this.locality = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the countryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the value of the countryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryName(String value) {
        this.countryName = value;
    }

    /**
     * Gets the value of the organizationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * Sets the value of the organizationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationName(String value) {
        this.organizationName = value;
    }

    /**
     * Gets the value of the givenName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the value of the givenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenName(String value) {
        this.givenName = value;
    }

    /**
     * Gets the value of the organizationalUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationalUnit() {
        return organizationalUnit;
    }

    /**
     * Sets the value of the organizationalUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationalUnit(String value) {
        this.organizationalUnit = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the pseudonym property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPseudonym() {
        return pseudonym;
    }

    /**
     * Sets the value of the pseudonym property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPseudonym(String value) {
        this.pseudonym = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the notAfter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getNotAfter() {
        return notAfter;
    }

    /**
     * Sets the value of the notAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotAfter(Date value) {
        this.notAfter = value;
    }

    /**
     * Gets the value of the notBefore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getNotBefore() {
        return notBefore;
    }

    /**
     * Sets the value of the notBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotBefore(Date value) {
        this.notBefore = value;
    }

    /**
     * Gets the value of the publicKeySize property.
     * 
     */
    public int getPublicKeySize() {
        return publicKeySize;
    }

    /**
     * Sets the value of the publicKeySize property.
     * 
     */
    public void setPublicKeySize(int value) {
        this.publicKeySize = value;
    }

    /**
     * Gets the value of the publicKeyEncryptionAlgo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicKeyEncryptionAlgo() {
        return publicKeyEncryptionAlgo;
    }

    /**
     * Sets the value of the publicKeyEncryptionAlgo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicKeyEncryptionAlgo(String value) {
        this.publicKeyEncryptionAlgo = value;
    }

    /**
     * Gets the value of the idPkixOcspNoCheck property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIdPkixOcspNoCheck() {
        return idPkixOcspNoCheck;
    }

    /**
     * Sets the value of the idPkixOcspNoCheck property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIdPkixOcspNoCheck(Boolean value) {
        this.idPkixOcspNoCheck = value;
    }

    /**
     * Gets the value of the basicSignature property.
     * 
     * @return
     *     possible object is
     *     {@link XmlBasicSignature }
     *     
     */
    public XmlBasicSignature getBasicSignature() {
        return basicSignature;
    }

    /**
     * Sets the value of the basicSignature property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlBasicSignature }
     *     
     */
    public void setBasicSignature(XmlBasicSignature value) {
        this.basicSignature = value;
    }

    /**
     * Gets the value of the signingCertificate property.
     * 
     * @return
     *     possible object is
     *     {@link XmlSigningCertificate }
     *     
     */
    public XmlSigningCertificate getSigningCertificate() {
        return signingCertificate;
    }

    /**
     * Sets the value of the signingCertificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlSigningCertificate }
     *     
     */
    public void setSigningCertificate(XmlSigningCertificate value) {
        this.signingCertificate = value;
    }

    /**
     * Gets the value of the trusted property.
     * 
     */
    public boolean isTrusted() {
        return trusted;
    }

    /**
     * Sets the value of the trusted property.
     * 
     */
    public void setTrusted(boolean value) {
        this.trusted = value;
    }

    /**
     * Gets the value of the selfSigned property.
     * 
     */
    public boolean isSelfSigned() {
        return selfSigned;
    }

    /**
     * Sets the value of the selfSigned property.
     * 
     */
    public void setSelfSigned(boolean value) {
        this.selfSigned = value;
    }

    /**
     * Gets the value of the base64Encoded property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBase64Encoded() {
        return base64Encoded;
    }

    /**
     * Sets the value of the base64Encoded property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBase64Encoded(byte[] value) {
        this.base64Encoded = value;
    }

    /**
     * Gets the value of the digestAlgoAndValue property.
     * 
     * @return
     *     possible object is
     *     {@link XmlDigestAlgoAndValue }
     *     
     */
    public XmlDigestAlgoAndValue getDigestAlgoAndValue() {
        return digestAlgoAndValue;
    }

    /**
     * Sets the value of the digestAlgoAndValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDigestAlgoAndValue }
     *     
     */
    public void setDigestAlgoAndValue(XmlDigestAlgoAndValue value) {
        this.digestAlgoAndValue = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    public List<String> getAuthorityInformationAccessUrls() {
        if (authorityInformationAccessUrls == null) {
            authorityInformationAccessUrls = new ArrayList<String>();
        }
        return authorityInformationAccessUrls;
    }

    public void setAuthorityInformationAccessUrls(List<String> authorityInformationAccessUrls) {
        this.authorityInformationAccessUrls = authorityInformationAccessUrls;
    }

    public List<String> getCRLDistributionPoints() {
        if (crlDistributionPoints == null) {
            crlDistributionPoints = new ArrayList<String>();
        }
        return crlDistributionPoints;
    }

    public void setCRLDistributionPoints(List<String> crlDistributionPoints) {
        this.crlDistributionPoints = crlDistributionPoints;
    }

    public List<String> getOCSPAccessUrls() {
        if (ocspAccessUrls == null) {
            ocspAccessUrls = new ArrayList<String>();
        }
        return ocspAccessUrls;
    }

    public void setOCSPAccessUrls(List<String> ocspAccessUrls) {
        this.ocspAccessUrls = ocspAccessUrls;
    }

    public List<XmlCertificateSourceType> getSources() {
        if (sources == null) {
            sources = new ArrayList<XmlCertificateSourceType>();
        }
        return sources;
    }

    public void setSources(List<XmlCertificateSourceType> sources) {
        this.sources = sources;
    }

    public List<String> getKeyUsageBits() {
        if (keyUsageBits == null) {
            keyUsageBits = new ArrayList<String>();
        }
        return keyUsageBits;
    }

    public void setKeyUsageBits(List<String> keyUsageBits) {
        this.keyUsageBits = keyUsageBits;
    }

    public List<XmlOID> getExtendedKeyUsages() {
        if (extendedKeyUsages == null) {
            extendedKeyUsages = new ArrayList<XmlOID>();
        }
        return extendedKeyUsages;
    }

    public void setExtendedKeyUsages(List<XmlOID> extendedKeyUsages) {
        this.extendedKeyUsages = extendedKeyUsages;
    }

    public List<XmlChainItem> getCertificateChain() {
        if (certificateChain == null) {
            certificateChain = new ArrayList<XmlChainItem>();
        }
        return certificateChain;
    }

    public void setCertificateChain(List<XmlChainItem> certificateChain) {
        this.certificateChain = certificateChain;
    }

    public List<XmlCertificatePolicy> getCertificatePolicies() {
        if (certificatePolicies == null) {
            certificatePolicies = new ArrayList<XmlCertificatePolicy>();
        }
        return certificatePolicies;
    }

    public void setCertificatePolicies(List<XmlCertificatePolicy> certificatePolicies) {
        this.certificatePolicies = certificatePolicies;
    }

    public List<XmlOID> getQCStatementIds() {
        if (qcStatementIds == null) {
            qcStatementIds = new ArrayList<XmlOID>();
        }
        return qcStatementIds;
    }

    public void setQCStatementIds(List<XmlOID> qcStatementIds) {
        this.qcStatementIds = qcStatementIds;
    }

    public List<XmlOID> getQCTypes() {
        if (qcTypes == null) {
            qcTypes = new ArrayList<XmlOID>();
        }
        return qcTypes;
    }

    public void setQCTypes(List<XmlOID> qcTypes) {
        this.qcTypes = qcTypes;
    }

    public List<XmlTrustedServiceProvider> getTrustedServiceProviders() {
        if (trustedServiceProviders == null) {
            trustedServiceProviders = new ArrayList<XmlTrustedServiceProvider>();
        }
        return trustedServiceProviders;
    }

    public void setTrustedServiceProviders(List<XmlTrustedServiceProvider> trustedServiceProviders) {
        this.trustedServiceProviders = trustedServiceProviders;
    }

    public List<XmlCertificateRevocation> getRevocations() {
        if (revocations == null) {
            revocations = new ArrayList<XmlCertificateRevocation>();
        }
        return revocations;
    }

    public void setRevocations(List<XmlCertificateRevocation> revocations) {
        this.revocations = revocations;
    }

}
