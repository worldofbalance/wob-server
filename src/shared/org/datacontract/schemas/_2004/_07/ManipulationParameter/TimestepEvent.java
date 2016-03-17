/**
 * TimestepEvent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.ManipulationParameter;

public class TimestepEvent  implements java.io.Serializable {
    private boolean processed;

    private float timestep;

    private int timestepIdx;

    public TimestepEvent() {
    }

    public TimestepEvent(
           boolean processed,
           float timestep,
           int timestepIdx) {
           this.processed = processed;
           this.timestep = timestep;
           this.timestepIdx = timestepIdx;
    }


    /**
     * Gets the processed value for this TimestepEvent.
     * 
     * @return processed
     */
    public boolean isProcessed() {
        return processed;
    }


    /**
     * Sets the processed value for this TimestepEvent.
     * 
     * @param processed
     */
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }


    /**
     * Gets the timestep value for this TimestepEvent.
     * 
     * @return timestep
     */
    public float getTimestep() {
        return timestep;
    }


    /**
     * Sets the timestep value for this TimestepEvent.
     * 
     * @param timestep
     */
    public void setTimestep(float timestep) {
        this.timestep = timestep;
    }


    /**
     * Gets the timestepIdx value for this TimestepEvent.
     * 
     * @return timestepIdx
     */
    public int getTimestepIdx() {
        return timestepIdx;
    }


    /**
     * Sets the timestepIdx value for this TimestepEvent.
     * 
     * @param timestepIdx
     */
    public void setTimestepIdx(int timestepIdx) {
        this.timestepIdx = timestepIdx;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TimestepEvent)) return false;
        TimestepEvent other = (TimestepEvent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.processed == other.isProcessed() &&
            this.timestep == other.getTimestep() &&
            this.timestepIdx == other.getTimestepIdx();
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
        _hashCode += (isProcessed() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Float(getTimestep()).hashCode();
        _hashCode += getTimestepIdx();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TimestepEvent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "TimestepEvent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "Processed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "timestep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestepIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "timestepIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
