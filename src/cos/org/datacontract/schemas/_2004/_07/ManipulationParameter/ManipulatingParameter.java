/**
 * ManipulatingParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.ManipulationParameter;

public class ManipulatingParameter  implements java.io.Serializable {
    private String fullname;

    private int nodeIdx;

    private int paramIdx;

    private String paramName;

    private int paramType;

    private double paramValue;

    private int predIdx;

    private int preyIdx;

    private int timestepIdx;

    public ManipulatingParameter() {
    }

    public ManipulatingParameter(
           String fullname,
           int nodeIdx,
           int paramIdx,
           String paramName,
           int paramType,
           double paramValue,
           int predIdx,
           int preyIdx,
           int timestepIdx) {
           this.fullname = fullname;
           this.nodeIdx = nodeIdx;
           this.paramIdx = paramIdx;
           this.paramName = paramName;
           this.paramType = paramType;
           this.paramValue = paramValue;
           this.predIdx = predIdx;
           this.preyIdx = preyIdx;
           this.timestepIdx = timestepIdx;
    }


    /**
     * Gets the fullname value for this ManipulatingParameter.
     *
     * @return fullname
     */
    public String getFullname() {
        return fullname;
    }


    /**
     * Sets the fullname value for this ManipulatingParameter.
     *
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    /**
     * Gets the nodeIdx value for this ManipulatingParameter.
     *
     * @return nodeIdx
     */
    public int getNodeIdx() {
        return nodeIdx;
    }


    /**
     * Sets the nodeIdx value for this ManipulatingParameter.
     *
     * @param nodeIdx
     */
    public void setNodeIdx(int nodeIdx) {
        this.nodeIdx = nodeIdx;
    }


    /**
     * Gets the paramIdx value for this ManipulatingParameter.
     *
     * @return paramIdx
     */
    public int getParamIdx() {
        return paramIdx;
    }


    /**
     * Sets the paramIdx value for this ManipulatingParameter.
     *
     * @param paramIdx
     */
    public void setParamIdx(int paramIdx) {
        this.paramIdx = paramIdx;
    }


    /**
     * Gets the paramName value for this ManipulatingParameter.
     *
     * @return paramName
     */
    public String getParamName() {
        return paramName;
    }


    /**
     * Sets the paramName value for this ManipulatingParameter.
     *
     * @param paramName
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }


    /**
     * Gets the paramType value for this ManipulatingParameter.
     *
     * @return paramType
     */
    public int getParamType() {
        return paramType;
    }


    /**
     * Sets the paramType value for this ManipulatingParameter.
     *
     * @param paramType
     */
    public void setParamType(int paramType) {
        this.paramType = paramType;
    }


    /**
     * Gets the paramValue value for this ManipulatingParameter.
     *
     * @return paramValue
     */
    public double getParamValue() {
        return paramValue;
    }


    /**
     * Sets the paramValue value for this ManipulatingParameter.
     *
     * @param paramValue
     */
    public void setParamValue(double paramValue) {
        this.paramValue = paramValue;
    }


    /**
     * Gets the predIdx value for this ManipulatingParameter.
     *
     * @return predIdx
     */
    public int getPredIdx() {
        return predIdx;
    }


    /**
     * Sets the predIdx value for this ManipulatingParameter.
     *
     * @param predIdx
     */
    public void setPredIdx(int predIdx) {
        this.predIdx = predIdx;
    }


    /**
     * Gets the preyIdx value for this ManipulatingParameter.
     *
     * @return preyIdx
     */
    public int getPreyIdx() {
        return preyIdx;
    }


    /**
     * Sets the preyIdx value for this ManipulatingParameter.
     *
     * @param preyIdx
     */
    public void setPreyIdx(int preyIdx) {
        this.preyIdx = preyIdx;
    }


    /**
     * Gets the timestepIdx value for this ManipulatingParameter.
     *
     * @return timestepIdx
     */
    public int getTimestepIdx() {
        return timestepIdx;
    }


    /**
     * Sets the timestepIdx value for this ManipulatingParameter.
     *
     * @param timestepIdx
     */
    public void setTimestepIdx(int timestepIdx) {
        this.timestepIdx = timestepIdx;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulatingParameter)) return false;
        ManipulatingParameter other = (ManipulatingParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.fullname==null && other.getFullname()==null) ||
             (this.fullname!=null &&
              this.fullname.equals(other.getFullname()))) &&
            this.nodeIdx == other.getNodeIdx() &&
            this.paramIdx == other.getParamIdx() &&
            ((this.paramName==null && other.getParamName()==null) ||
             (this.paramName!=null &&
              this.paramName.equals(other.getParamName()))) &&
            this.paramType == other.getParamType() &&
            this.paramValue == other.getParamValue() &&
            this.predIdx == other.getPredIdx() &&
            this.preyIdx == other.getPreyIdx() &&
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
        if (getFullname() != null) {
            _hashCode += getFullname().hashCode();
        }
        _hashCode += getNodeIdx();
        _hashCode += getParamIdx();
        if (getParamName() != null) {
            _hashCode += getParamName().hashCode();
        }
        _hashCode += getParamType();
        _hashCode += new Double(getParamValue()).hashCode();
        _hashCode += getPredIdx();
        _hashCode += getPreyIdx();
        _hashCode += getTimestepIdx();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulatingParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ManipulatingParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "Fullname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "NodeIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ParamIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ParamName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ParamType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ParamValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("predIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "PredIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preyIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "PreyIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestepIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "TimestepIdx"));
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
