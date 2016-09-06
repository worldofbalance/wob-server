/**
 * NodeInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class NodeInfo  implements java.io.Serializable {
    private int nodeIdx;

    private String nodeName;

    private float trophicLevel;

    public NodeInfo() {
    }

    public NodeInfo(
           int nodeIdx,
           String nodeName,
           float trophicLevel) {
           this.nodeIdx = nodeIdx;
           this.nodeName = nodeName;
           this.trophicLevel = trophicLevel;
    }


    /**
     * Gets the nodeIdx value for this NodeInfo.
     *
     * @return nodeIdx
     */
    public int getNodeIdx() {
        return nodeIdx;
    }


    /**
     * Sets the nodeIdx value for this NodeInfo.
     *
     * @param nodeIdx
     */
    public void setNodeIdx(int nodeIdx) {
        this.nodeIdx = nodeIdx;
    }


    /**
     * Gets the nodeName value for this NodeInfo.
     *
     * @return nodeName
     */
    public String getNodeName() {
        return nodeName;
    }


    /**
     * Sets the nodeName value for this NodeInfo.
     *
     * @param nodeName
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }


    /**
     * Gets the trophicLevel value for this NodeInfo.
     *
     * @return trophicLevel
     */
    public float getTrophicLevel() {
        return trophicLevel;
    }


    /**
     * Sets the trophicLevel value for this NodeInfo.
     *
     * @param trophicLevel
     */
    public void setTrophicLevel(float trophicLevel) {
        this.trophicLevel = trophicLevel;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof NodeInfo)) return false;
        NodeInfo other = (NodeInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.nodeIdx == other.getNodeIdx() &&
            ((this.nodeName==null && other.getNodeName()==null) ||
             (this.nodeName!=null &&
              this.nodeName.equals(other.getNodeName()))) &&
            this.trophicLevel == other.getTrophicLevel();
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
        if (getNodeName() != null) {
            _hashCode += getNodeName().hashCode();
        }
        _hashCode += new Float(getTrophicLevel()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NodeInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trophicLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TrophicLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
