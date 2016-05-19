/**
 * ManipulationLinkTimestepInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class ManipulationLinkTimestepInfo  implements java.io.Serializable {
    private int dstIdx;

    private double energyFlow;

    private int srcIdx;

    private float timestep;

    private int timestepIdx;

    public ManipulationLinkTimestepInfo() {
    }

    public ManipulationLinkTimestepInfo(
           int dstIdx,
           double energyFlow,
           int srcIdx,
           float timestep,
           int timestepIdx) {
           this.dstIdx = dstIdx;
           this.energyFlow = energyFlow;
           this.srcIdx = srcIdx;
           this.timestep = timestep;
           this.timestepIdx = timestepIdx;
    }


    /**
     * Gets the dstIdx value for this ManipulationLinkTimestepInfo.
     * 
     * @return dstIdx
     */
    public int getDstIdx() {
        return dstIdx;
    }


    /**
     * Sets the dstIdx value for this ManipulationLinkTimestepInfo.
     * 
     * @param dstIdx
     */
    public void setDstIdx(int dstIdx) {
        this.dstIdx = dstIdx;
    }


    /**
     * Gets the energyFlow value for this ManipulationLinkTimestepInfo.
     * 
     * @return energyFlow
     */
    public double getEnergyFlow() {
        return energyFlow;
    }


    /**
     * Sets the energyFlow value for this ManipulationLinkTimestepInfo.
     * 
     * @param energyFlow
     */
    public void setEnergyFlow(double energyFlow) {
        this.energyFlow = energyFlow;
    }


    /**
     * Gets the srcIdx value for this ManipulationLinkTimestepInfo.
     * 
     * @return srcIdx
     */
    public int getSrcIdx() {
        return srcIdx;
    }


    /**
     * Sets the srcIdx value for this ManipulationLinkTimestepInfo.
     * 
     * @param srcIdx
     */
    public void setSrcIdx(int srcIdx) {
        this.srcIdx = srcIdx;
    }


    /**
     * Gets the timestep value for this ManipulationLinkTimestepInfo.
     * 
     * @return timestep
     */
    public float getTimestep() {
        return timestep;
    }


    /**
     * Sets the timestep value for this ManipulationLinkTimestepInfo.
     * 
     * @param timestep
     */
    public void setTimestep(float timestep) {
        this.timestep = timestep;
    }


    /**
     * Gets the timestepIdx value for this ManipulationLinkTimestepInfo.
     * 
     * @return timestepIdx
     */
    public int getTimestepIdx() {
        return timestepIdx;
    }


    /**
     * Sets the timestepIdx value for this ManipulationLinkTimestepInfo.
     * 
     * @param timestepIdx
     */
    public void setTimestepIdx(int timestepIdx) {
        this.timestepIdx = timestepIdx;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulationLinkTimestepInfo)) return false;
        ManipulationLinkTimestepInfo other = (ManipulationLinkTimestepInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.dstIdx == other.getDstIdx() &&
            this.energyFlow == other.getEnergyFlow() &&
            this.srcIdx == other.getSrcIdx() &&
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
        _hashCode += getDstIdx();
        _hashCode += new Double(getEnergyFlow()).hashCode();
        _hashCode += getSrcIdx();
        _hashCode += new Float(getTimestep()).hashCode();
        _hashCode += getTimestepIdx();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationLinkTimestepInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationLinkTimestepInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dstIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "DstIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("energyFlow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "EnergyFlow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srcIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "SrcIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Timestep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestepIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TimestepIdx"));
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
