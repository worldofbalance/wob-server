/**
 * ManipulatingNodeProperty.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.ManipulationParameter;

public class ManipulatingNodeProperty  implements java.io.Serializable {
    private int nodeIdx;

    private String nodePropertyName;

    private double nodePropertyValue;

    public ManipulatingNodeProperty() {
    }

    public ManipulatingNodeProperty(
           int nodeIdx,
           String nodePropertyName,
           double nodePropertyValue) {
           this.nodeIdx = nodeIdx;
           this.nodePropertyName = nodePropertyName;
           this.nodePropertyValue = nodePropertyValue;
    }


    /**
     * Gets the nodeIdx value for this ManipulatingNodeProperty.
     *
     * @return nodeIdx
     */
    public int getNodeIdx() {
        return nodeIdx;
    }


    /**
     * Sets the nodeIdx value for this ManipulatingNodeProperty.
     *
     * @param nodeIdx
     */
    public void setNodeIdx(int nodeIdx) {
        this.nodeIdx = nodeIdx;
    }


    /**
     * Gets the nodePropertyName value for this ManipulatingNodeProperty.
     *
     * @return nodePropertyName
     */
    public String getNodePropertyName() {
        return nodePropertyName;
    }


    /**
     * Sets the nodePropertyName value for this ManipulatingNodeProperty.
     *
     * @param nodePropertyName
     */
    public void setNodePropertyName(String nodePropertyName) {
        this.nodePropertyName = nodePropertyName;
    }


    /**
     * Gets the nodePropertyValue value for this ManipulatingNodeProperty.
     *
     * @return nodePropertyValue
     */
    public double getNodePropertyValue() {
        return nodePropertyValue;
    }


    /**
     * Sets the nodePropertyValue value for this ManipulatingNodeProperty.
     *
     * @param nodePropertyValue
     */
    public void setNodePropertyValue(double nodePropertyValue) {
        this.nodePropertyValue = nodePropertyValue;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulatingNodeProperty)) return false;
        ManipulatingNodeProperty other = (ManipulatingNodeProperty) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.nodeIdx == other.getNodeIdx() &&
            ((this.nodePropertyName==null && other.getNodePropertyName()==null) ||
             (this.nodePropertyName!=null &&
              this.nodePropertyName.equals(other.getNodePropertyName()))) &&
            this.nodePropertyValue == other.getNodePropertyValue();
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
        _hashCode += getNodeIdx();
        if (getNodePropertyName() != null) {
            _hashCode += getNodePropertyName().hashCode();
        }
        _hashCode += new Double(getNodePropertyValue()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulatingNodeProperty.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ManipulatingNodeProperty"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "NodeIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodePropertyName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "NodePropertyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodePropertyValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "NodePropertyValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
