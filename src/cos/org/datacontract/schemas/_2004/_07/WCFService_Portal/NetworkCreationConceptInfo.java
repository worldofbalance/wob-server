/**
 * NetworkCreationConceptInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class NetworkCreationConceptInfo  implements java.io.Serializable {
    private Integer conceptIdx;

    private String conceptName;

    private Integer conceptType;

    public NetworkCreationConceptInfo() {
    }

    public NetworkCreationConceptInfo(
           Integer conceptIdx,
           String conceptName,
           Integer conceptType) {
           this.conceptIdx = conceptIdx;
           this.conceptName = conceptName;
           this.conceptType = conceptType;
    }


    /**
     * Gets the conceptIdx value for this NetworkCreationConceptInfo.
     *
     * @return conceptIdx
     */
    public Integer getConceptIdx() {
        return conceptIdx;
    }


    /**
     * Sets the conceptIdx value for this NetworkCreationConceptInfo.
     *
     * @param conceptIdx
     */
    public void setConceptIdx(Integer conceptIdx) {
        this.conceptIdx = conceptIdx;
    }


    /**
     * Gets the conceptName value for this NetworkCreationConceptInfo.
     *
     * @return conceptName
     */
    public String getConceptName() {
        return conceptName;
    }


    /**
     * Sets the conceptName value for this NetworkCreationConceptInfo.
     *
     * @param conceptName
     */
    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }


    /**
     * Gets the conceptType value for this NetworkCreationConceptInfo.
     *
     * @return conceptType
     */
    public Integer getConceptType() {
        return conceptType;
    }


    /**
     * Sets the conceptType value for this NetworkCreationConceptInfo.
     *
     * @param conceptType
     */
    public void setConceptType(Integer conceptType) {
        this.conceptType = conceptType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof NetworkCreationConceptInfo)) return false;
        NetworkCreationConceptInfo other = (NetworkCreationConceptInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.conceptIdx==null && other.getConceptIdx()==null) ||
             (this.conceptIdx!=null &&
              this.conceptIdx.equals(other.getConceptIdx()))) &&
            ((this.conceptName==null && other.getConceptName()==null) ||
             (this.conceptName!=null &&
              this.conceptName.equals(other.getConceptName()))) &&
            ((this.conceptType==null && other.getConceptType()==null) ||
             (this.conceptType!=null &&
              this.conceptType.equals(other.getConceptType())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getConceptIdx() != null) {
            _hashCode += getConceptIdx().hashCode();
        }
        if (getConceptName() != null) {
            _hashCode += getConceptName().hashCode();
        }
        if (getConceptType() != null) {
            _hashCode += getConceptType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NetworkCreationConceptInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkCreationConceptInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conceptIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ConceptIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conceptName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ConceptName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conceptType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ConceptType"));
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
