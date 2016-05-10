/**
 * ManipulationRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class ManipulationRequest  extends cos.org.datacontract.schemas._2004._07.WCFService_Portal.Request  implements java.io.Serializable {
    private java.util.Calendar date;

    private String manipulationId;

    private Object parameters;

    private Integer timestepIdx;

    private Integer timestepsToRun;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    public ManipulationRequest() {
    }

    public ManipulationRequest(
           java.util.Calendar date,
           String manipulationId,
           Object parameters,
           Integer timestepIdx,
           Integer timestepsToRun,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.date = date;
        this.manipulationId = manipulationId;
        this.parameters = parameters;
        this.timestepIdx = timestepIdx;
        this.timestepsToRun = timestepsToRun;
        this.user = user;
    }


    /**
     * Gets the date value for this ManipulationRequest.
     *
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this ManipulationRequest.
     *
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the manipulationId value for this ManipulationRequest.
     *
     * @return manipulationId
     */
    public String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationRequest.
     *
     * @param manipulationId
     */
    public void setManipulationId(String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the parameters value for this ManipulationRequest.
     *
     * @return parameters
     */
    public Object getParameters() {
        return parameters;
    }


    /**
     * Sets the parameters value for this ManipulationRequest.
     *
     * @param parameters
     */
    public void setParameters(Object parameters) {
        this.parameters = parameters;
    }


    /**
     * Gets the timestepIdx value for this ManipulationRequest.
     *
     * @return timestepIdx
     */
    public Integer getTimestepIdx() {
        return timestepIdx;
    }


    /**
     * Sets the timestepIdx value for this ManipulationRequest.
     *
     * @param timestepIdx
     */
    public void setTimestepIdx(Integer timestepIdx) {
        this.timestepIdx = timestepIdx;
    }


    /**
     * Gets the timestepsToRun value for this ManipulationRequest.
     *
     * @return timestepsToRun
     */
    public Integer getTimestepsToRun() {
        return timestepsToRun;
    }


    /**
     * Sets the timestepsToRun value for this ManipulationRequest.
     *
     * @param timestepsToRun
     */
    public void setTimestepsToRun(Integer timestepsToRun) {
        this.timestepsToRun = timestepsToRun;
    }


    /**
     * Gets the user value for this ManipulationRequest.
     *
     * @return user
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this ManipulationRequest.
     *
     * @param user
     */
    public void setUser(cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulationRequest)) return false;
        ManipulationRequest other = (ManipulationRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.date==null && other.getDate()==null) ||
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.manipulationId==null && other.getManipulationId()==null) ||
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            ((this.parameters==null && other.getParameters()==null) ||
             (this.parameters!=null &&
              this.parameters.equals(other.getParameters()))) &&
            ((this.timestepIdx==null && other.getTimestepIdx()==null) ||
             (this.timestepIdx!=null &&
              this.timestepIdx.equals(other.getTimestepIdx()))) &&
            ((this.timestepsToRun==null && other.getTimestepsToRun()==null) ||
             (this.timestepsToRun!=null &&
              this.timestepsToRun.equals(other.getTimestepsToRun()))) &&
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
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        if (getParameters() != null) {
            _hashCode += getParameters().hashCode();
        }
        if (getTimestepIdx() != null) {
            _hashCode += getTimestepIdx().hashCode();
        }
        if (getTimestepsToRun() != null) {
            _hashCode += getTimestepsToRun().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Parameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestepIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TimestepIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestepsToRun");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TimestepsToRun"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
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
