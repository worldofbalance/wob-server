/**
 * TimestepsSaveRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.WCFService_Portal;

public class TimestepsSaveRequest  extends shared.org.datacontract.schemas._2004._07.WCFService_Portal.Request  implements java.io.Serializable {
    private java.lang.String timeseriesId;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.Timesteps timestep;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    public TimestepsSaveRequest() {
    }

    public TimestepsSaveRequest(
           java.lang.String timeseriesId,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.Timesteps timestep,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.timeseriesId = timeseriesId;
        this.timestep = timestep;
        this.user = user;
    }


    /**
     * Gets the timeseriesId value for this TimestepsSaveRequest.
     * 
     * @return timeseriesId
     */
    public java.lang.String getTimeseriesId() {
        return timeseriesId;
    }


    /**
     * Sets the timeseriesId value for this TimestepsSaveRequest.
     * 
     * @param timeseriesId
     */
    public void setTimeseriesId(java.lang.String timeseriesId) {
        this.timeseriesId = timeseriesId;
    }


    /**
     * Gets the timestep value for this TimestepsSaveRequest.
     * 
     * @return timestep
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.Timesteps getTimestep() {
        return timestep;
    }


    /**
     * Sets the timestep value for this TimestepsSaveRequest.
     * 
     * @param timestep
     */
    public void setTimestep(shared.org.datacontract.schemas._2004._07.LINQ2Entities.Timesteps timestep) {
        this.timestep = timestep;
    }


    /**
     * Gets the user value for this TimestepsSaveRequest.
     * 
     * @return user
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this TimestepsSaveRequest.
     * 
     * @param user
     */
    public void setUser(shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TimestepsSaveRequest)) return false;
        TimestepsSaveRequest other = (TimestepsSaveRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.timeseriesId==null && other.getTimeseriesId()==null) || 
             (this.timeseriesId!=null &&
              this.timeseriesId.equals(other.getTimeseriesId()))) &&
            ((this.timestep==null && other.getTimestep()==null) || 
             (this.timestep!=null &&
              this.timestep.equals(other.getTimestep()))) &&
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
        if (getTimeseriesId() != null) {
            _hashCode += getTimeseriesId().hashCode();
        }
        if (getTimestep() != null) {
            _hashCode += getTimestep().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TimestepsSaveRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TimestepsSaveRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeseriesId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TimeseriesId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Timestep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Timesteps"));
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
