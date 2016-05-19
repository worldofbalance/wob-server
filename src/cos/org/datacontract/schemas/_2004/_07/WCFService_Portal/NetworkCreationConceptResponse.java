/**
 * NetworkCreationConceptResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class NetworkCreationConceptResponse  extends cos.org.datacontract.schemas._2004._07.WCFService_Portal.Response  implements java.io.Serializable {
    private Integer conceptType;

    private Boolean DESC;

    private String message;

    private cos.org.datacontract.schemas._2004._07.WCFService_Portal.NetworkCreationConceptInfo[] networkCreationConcepts;

    private String orderBy;

    private Integer pageAvailable;

    public NetworkCreationConceptResponse() {
    }

    public NetworkCreationConceptResponse(
           String _message,
           Integer conceptType,
           Boolean DESC,
           String message,
           cos.org.datacontract.schemas._2004._07.WCFService_Portal.NetworkCreationConceptInfo[] networkCreationConcepts,
           String orderBy,
           Integer pageAvailable) {
        super(
            _message);
        this.conceptType = conceptType;
        this.DESC = DESC;
        this.message = message;
        this.networkCreationConcepts = networkCreationConcepts;
        this.orderBy = orderBy;
        this.pageAvailable = pageAvailable;
    }


    /**
     * Gets the conceptType value for this NetworkCreationConceptResponse.
     *
     * @return conceptType
     */
    public Integer getConceptType() {
        return conceptType;
    }


    /**
     * Sets the conceptType value for this NetworkCreationConceptResponse.
     *
     * @param conceptType
     */
    public void setConceptType(Integer conceptType) {
        this.conceptType = conceptType;
    }


    /**
     * Gets the DESC value for this NetworkCreationConceptResponse.
     *
     * @return DESC
     */
    public Boolean getDESC() {
        return DESC;
    }


    /**
     * Sets the DESC value for this NetworkCreationConceptResponse.
     *
     * @param DESC
     */
    public void setDESC(Boolean DESC) {
        this.DESC = DESC;
    }


    /**
     * Gets the message value for this NetworkCreationConceptResponse.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this NetworkCreationConceptResponse.
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Gets the networkCreationConcepts value for this NetworkCreationConceptResponse.
     *
     * @return networkCreationConcepts
     */
    public cos.org.datacontract.schemas._2004._07.WCFService_Portal.NetworkCreationConceptInfo[] getNetworkCreationConcepts() {
        return networkCreationConcepts;
    }


    /**
     * Sets the networkCreationConcepts value for this NetworkCreationConceptResponse.
     *
     * @param networkCreationConcepts
     */
    public void setNetworkCreationConcepts(cos.org.datacontract.schemas._2004._07.WCFService_Portal.NetworkCreationConceptInfo[] networkCreationConcepts) {
        this.networkCreationConcepts = networkCreationConcepts;
    }


    /**
     * Gets the orderBy value for this NetworkCreationConceptResponse.
     *
     * @return orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }


    /**
     * Sets the orderBy value for this NetworkCreationConceptResponse.
     *
     * @param orderBy
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


    /**
     * Gets the pageAvailable value for this NetworkCreationConceptResponse.
     *
     * @return pageAvailable
     */
    public Integer getPageAvailable() {
        return pageAvailable;
    }


    /**
     * Sets the pageAvailable value for this NetworkCreationConceptResponse.
     *
     * @param pageAvailable
     */
    public void setPageAvailable(Integer pageAvailable) {
        this.pageAvailable = pageAvailable;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof NetworkCreationConceptResponse)) return false;
        NetworkCreationConceptResponse other = (NetworkCreationConceptResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.conceptType==null && other.getConceptType()==null) ||
             (this.conceptType!=null &&
              this.conceptType.equals(other.getConceptType()))) &&
            ((this.DESC==null && other.getDESC()==null) ||
             (this.DESC!=null &&
              this.DESC.equals(other.getDESC()))) &&
            ((this.message==null && other.getMessage()==null) ||
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.networkCreationConcepts==null && other.getNetworkCreationConcepts()==null) ||
             (this.networkCreationConcepts!=null &&
              java.util.Arrays.equals(this.networkCreationConcepts, other.getNetworkCreationConcepts()))) &&
            ((this.orderBy==null && other.getOrderBy()==null) ||
             (this.orderBy!=null &&
              this.orderBy.equals(other.getOrderBy()))) &&
            ((this.pageAvailable==null && other.getPageAvailable()==null) ||
             (this.pageAvailable!=null &&
              this.pageAvailable.equals(other.getPageAvailable())));
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
        if (getConceptType() != null) {
            _hashCode += getConceptType().hashCode();
        }
        if (getDESC() != null) {
            _hashCode += getDESC().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getNetworkCreationConcepts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNetworkCreationConcepts());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getNetworkCreationConcepts(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOrderBy() != null) {
            _hashCode += getOrderBy().hashCode();
        }
        if (getPageAvailable() != null) {
            _hashCode += getPageAvailable().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NetworkCreationConceptResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkCreationConceptResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conceptType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ConceptType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "DESC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkCreationConcepts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkCreationConcepts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkCreationConceptInfo"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkCreationConceptInfo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "OrderBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageAvailable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "PageAvailable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
