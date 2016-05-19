/**
 * TimeseriesSaveRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class TimeseriesSaveRequest  extends cos.org.datacontract.schemas._2004._07.WCFService_Portal.Request  implements java.io.Serializable {
    private String networkId;

    private int nodeId;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timeseries timeseries;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    public TimeseriesSaveRequest() {
    }

    public TimeseriesSaveRequest(
           String networkId,
           int nodeId,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timeseries timeseries,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.networkId = networkId;
        this.nodeId = nodeId;
        this.timeseries = timeseries;
        this.user = user;
    }


    /**
     * Gets the networkId value for this TimeseriesSaveRequest.
     *
     * @return networkId
     */
    public String getNetworkId() {
        return networkId;
    }


    /**
     * Sets the networkId value for this TimeseriesSaveRequest.
     *
     * @param networkId
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }


    /**
     * Gets the nodeId value for this TimeseriesSaveRequest.
     *
     * @return nodeId
     */
    public int getNodeId() {
        return nodeId;
    }


    /**
     * Sets the nodeId value for this TimeseriesSaveRequest.
     *
     * @param nodeId
     */
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }


    /**
     * Gets the timeseries value for this TimeseriesSaveRequest.
     *
     * @return timeseries
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timeseries getTimeseries() {
        return timeseries;
    }


    /**
     * Sets the timeseries value for this TimeseriesSaveRequest.
     *
     * @param timeseries
     */
    public void setTimeseries(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timeseries timeseries) {
        this.timeseries = timeseries;
    }


    /**
     * Gets the user value for this TimeseriesSaveRequest.
     *
     * @return user
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this TimeseriesSaveRequest.
     *
     * @param user
     */
    public void setUser(cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TimeseriesSaveRequest)) return false;
        TimeseriesSaveRequest other = (TimeseriesSaveRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.networkId==null && other.getNetworkId()==null) ||
             (this.networkId!=null &&
              this.networkId.equals(other.getNetworkId()))) &&
            this.nodeId == other.getNodeId() &&
            ((this.timeseries==null && other.getTimeseries()==null) ||
             (this.timeseries!=null &&
              this.timeseries.equals(other.getTimeseries()))) &&
            ((this.user==null && other.getUser()==null) ||
             (this.user!=null &&
              this.user.equals(other.getUser())));
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
        if (getNetworkId() != null) {
            _hashCode += getNetworkId().hashCode();
        }
        _hashCode += getNodeId();
        if (getTimeseries() != null) {
            _hashCode += getTimeseries().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TimeseriesSaveRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TimeseriesSaveRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeseries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Timeseries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Timeseries"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
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
