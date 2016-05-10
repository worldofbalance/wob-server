/**
 * LinkParameters.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class LinkParameters  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private String linkId;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Links links;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfLinkshovVakal linksReference;

    private String propertyName;

    private Float propertyValue;

    private String id;

    public LinkParameters() {
    }

    public LinkParameters(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           String linkId,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Links links,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfLinkshovVakal linksReference,
           String propertyName,
           Float propertyValue,
           String id) {
        super(
            _id,
            ref,
            entityKey);
        this.linkId = linkId;
        this.links = links;
        this.linksReference = linksReference;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.id = id;
    }


    /**
     * Gets the linkId value for this LinkParameters.
     *
     * @return linkId
     */
    public String getLinkId() {
        return linkId;
    }


    /**
     * Sets the linkId value for this LinkParameters.
     *
     * @param linkId
     */
    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }


    /**
     * Gets the links value for this LinkParameters.
     *
     * @return links
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Links getLinks() {
        return links;
    }


    /**
     * Sets the links value for this LinkParameters.
     *
     * @param links
     */
    public void setLinks(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Links links) {
        this.links = links;
    }


    /**
     * Gets the linksReference value for this LinkParameters.
     *
     * @return linksReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfLinkshovVakal getLinksReference() {
        return linksReference;
    }


    /**
     * Sets the linksReference value for this LinkParameters.
     *
     * @param linksReference
     */
    public void setLinksReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfLinkshovVakal linksReference) {
        this.linksReference = linksReference;
    }


    /**
     * Gets the propertyName value for this LinkParameters.
     *
     * @return propertyName
     */
    public String getPropertyName() {
        return propertyName;
    }


    /**
     * Sets the propertyName value for this LinkParameters.
     *
     * @param propertyName
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }


    /**
     * Gets the propertyValue value for this LinkParameters.
     *
     * @return propertyValue
     */
    public Float getPropertyValue() {
        return propertyValue;
    }


    /**
     * Sets the propertyValue value for this LinkParameters.
     *
     * @param propertyValue
     */
    public void setPropertyValue(Float propertyValue) {
        this.propertyValue = propertyValue;
    }


    /**
     * Gets the id value for this LinkParameters.
     *
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this LinkParameters.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LinkParameters)) return false;
        LinkParameters other = (LinkParameters) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.linkId==null && other.getLinkId()==null) ||
             (this.linkId!=null &&
              this.linkId.equals(other.getLinkId()))) &&
            ((this.links==null && other.getLinks()==null) ||
             (this.links!=null &&
              this.links.equals(other.getLinks()))) &&
            ((this.linksReference==null && other.getLinksReference()==null) ||
             (this.linksReference!=null &&
              this.linksReference.equals(other.getLinksReference()))) &&
            ((this.propertyName==null && other.getPropertyName()==null) ||
             (this.propertyName!=null &&
              this.propertyName.equals(other.getPropertyName()))) &&
            ((this.propertyValue==null && other.getPropertyValue()==null) ||
             (this.propertyValue!=null &&
              this.propertyValue.equals(other.getPropertyValue()))) &&
            ((this.id==null && other.getId()==null) ||
             (this.id!=null &&
              this.id.equals(other.getId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getLinkId() != null) {
            _hashCode += getLinkId().hashCode();
        }
        if (getLinks() != null) {
            _hashCode += getLinks().hashCode();
        }
        if (getLinksReference() != null) {
            _hashCode += getLinksReference().hashCode();
        }
        if (getPropertyName() != null) {
            _hashCode += getPropertyName().hashCode();
        }
        if (getPropertyValue() != null) {
            _hashCode += getPropertyValue().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LinkParameters.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LinkParameters"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LinkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("links");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Links"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Links"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linksReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LinksReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfLinkshovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "PropertyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "PropertyValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
