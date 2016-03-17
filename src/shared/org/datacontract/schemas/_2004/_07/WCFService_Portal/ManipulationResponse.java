/**
 * ManipulationResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.WCFService_Portal;

public class ManipulationResponse  extends shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response  implements java.io.Serializable {
    private java.lang.String manipulationId;

    private java.lang.String[] manipulationIds;

    private java.lang.String manipulationRequestId;

    private java.lang.String networksId;

    public ManipulationResponse() {
    }

    public ManipulationResponse(
           java.lang.String message,
           java.lang.String manipulationId,
           java.lang.String[] manipulationIds,
           java.lang.String manipulationRequestId,
           java.lang.String networksId) {
        super(
            message);
        this.manipulationId = manipulationId;
        this.manipulationIds = manipulationIds;
        this.manipulationRequestId = manipulationRequestId;
        this.networksId = networksId;
    }


    /**
     * Gets the manipulationId value for this ManipulationResponse.
     * 
     * @return manipulationId
     */
    public java.lang.String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationResponse.
     * 
     * @param manipulationId
     */
    public void setManipulationId(java.lang.String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the manipulationIds value for this ManipulationResponse.
     * 
     * @return manipulationIds
     */
    public java.lang.String[] getManipulationIds() {
        return manipulationIds;
    }


    /**
     * Sets the manipulationIds value for this ManipulationResponse.
     * 
     * @param manipulationIds
     */
    public void setManipulationIds(java.lang.String[] manipulationIds) {
        this.manipulationIds = manipulationIds;
    }


    /**
     * Gets the manipulationRequestId value for this ManipulationResponse.
     * 
     * @return manipulationRequestId
     */
    public java.lang.String getManipulationRequestId() {
        return manipulationRequestId;
    }


    /**
     * Sets the manipulationRequestId value for this ManipulationResponse.
     * 
     * @param manipulationRequestId
     */
    public void setManipulationRequestId(java.lang.String manipulationRequestId) {
        this.manipulationRequestId = manipulationRequestId;
    }


    /**
     * Gets the networksId value for this ManipulationResponse.
     * 
     * @return networksId
     */
    public java.lang.String getNetworksId() {
        return networksId;
    }


    /**
     * Sets the networksId value for this ManipulationResponse.
     * 
     * @param networksId
     */
    public void setNetworksId(java.lang.String networksId) {
        this.networksId = networksId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ManipulationResponse)) return false;
        ManipulationResponse other = (ManipulationResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.manipulationId==null && other.getManipulationId()==null) || 
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            ((this.manipulationIds==null && other.getManipulationIds()==null) || 
             (this.manipulationIds!=null &&
              java.util.Arrays.equals(this.manipulationIds, other.getManipulationIds()))) &&
            ((this.manipulationRequestId==null && other.getManipulationRequestId()==null) || 
             (this.manipulationRequestId!=null &&
              this.manipulationRequestId.equals(other.getManipulationRequestId()))) &&
            ((this.networksId==null && other.getNetworksId()==null) || 
             (this.networksId!=null &&
              this.networksId.equals(other.getNetworksId())));
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
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        if (getManipulationIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManipulationIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getManipulationRequestId() != null) {
            _hashCode += getManipulationRequestId().hashCode();
        }
        if (getNetworksId() != null) {
            _hashCode += getNetworksId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "guid"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationRequestId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationRequestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networksId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworksId"));
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
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
