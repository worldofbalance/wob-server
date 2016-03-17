/**
 * NetworkModelTypeInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.ManipulationParameter;

public class NetworkModelTypeInfo  implements java.io.Serializable {
    private java.lang.Integer idx;

    private java.lang.String modelClassName;

    private java.lang.String modelName;

    public NetworkModelTypeInfo() {
    }

    public NetworkModelTypeInfo(
           java.lang.Integer idx,
           java.lang.String modelClassName,
           java.lang.String modelName) {
           this.idx = idx;
           this.modelClassName = modelClassName;
           this.modelName = modelName;
    }


    /**
     * Gets the idx value for this NetworkModelTypeInfo.
     * 
     * @return idx
     */
    public java.lang.Integer getIdx() {
        return idx;
    }


    /**
     * Sets the idx value for this NetworkModelTypeInfo.
     * 
     * @param idx
     */
    public void setIdx(java.lang.Integer idx) {
        this.idx = idx;
    }


    /**
     * Gets the modelClassName value for this NetworkModelTypeInfo.
     * 
     * @return modelClassName
     */
    public java.lang.String getModelClassName() {
        return modelClassName;
    }


    /**
     * Sets the modelClassName value for this NetworkModelTypeInfo.
     * 
     * @param modelClassName
     */
    public void setModelClassName(java.lang.String modelClassName) {
        this.modelClassName = modelClassName;
    }


    /**
     * Gets the modelName value for this NetworkModelTypeInfo.
     * 
     * @return modelName
     */
    public java.lang.String getModelName() {
        return modelName;
    }


    /**
     * Sets the modelName value for this NetworkModelTypeInfo.
     * 
     * @param modelName
     */
    public void setModelName(java.lang.String modelName) {
        this.modelName = modelName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NetworkModelTypeInfo)) return false;
        NetworkModelTypeInfo other = (NetworkModelTypeInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idx==null && other.getIdx()==null) || 
             (this.idx!=null &&
              this.idx.equals(other.getIdx()))) &&
            ((this.modelClassName==null && other.getModelClassName()==null) || 
             (this.modelClassName!=null &&
              this.modelClassName.equals(other.getModelClassName()))) &&
            ((this.modelName==null && other.getModelName()==null) || 
             (this.modelName!=null &&
              this.modelName.equals(other.getModelName())));
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
        if (getIdx() != null) {
            _hashCode += getIdx().hashCode();
        }
        if (getModelClassName() != null) {
            _hashCode += getModelClassName().hashCode();
        }
        if (getModelName() != null) {
            _hashCode += getModelName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NetworkModelTypeInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "NetworkModelTypeInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "Idx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelClassName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ModelClassName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ModelName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
