/**
 * ManipulationParam.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.ManipulationParameter;

public class ManipulationParam  extends shared.org.datacontract.schemas._2004._07.ManipulationParameter.TimestepEvent  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String manipulationId;

    private int nodeIdx;

    private java.lang.String parameterFullName;

    private int parameterId;

    private java.lang.String parameterName;

    private int parameterType;

    private double parameterValue;

    private int predIdx;

    private int preyIdx;

    private boolean saved;

    public ManipulationParam() {
    }

    public ManipulationParam(
           boolean processed,
           float timestep,
           int timestepIdx,
           java.lang.String id,
           java.lang.String manipulationId,
           int nodeIdx,
           java.lang.String parameterFullName,
           int parameterId,
           java.lang.String parameterName,
           int parameterType,
           double parameterValue,
           int predIdx,
           int preyIdx,
           boolean saved) {
        super(
            processed,
            timestep,
            timestepIdx);
        this.id = id;
        this.manipulationId = manipulationId;
        this.nodeIdx = nodeIdx;
        this.parameterFullName = parameterFullName;
        this.parameterId = parameterId;
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.parameterValue = parameterValue;
        this.predIdx = predIdx;
        this.preyIdx = preyIdx;
        this.saved = saved;
    }


    /**
     * Gets the id value for this ManipulationParam.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this ManipulationParam.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the manipulationId value for this ManipulationParam.
     * 
     * @return manipulationId
     */
    public java.lang.String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationParam.
     * 
     * @param manipulationId
     */
    public void setManipulationId(java.lang.String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the nodeIdx value for this ManipulationParam.
     * 
     * @return nodeIdx
     */
    public int getNodeIdx() {
        return nodeIdx;
    }


    /**
     * Sets the nodeIdx value for this ManipulationParam.
     * 
     * @param nodeIdx
     */
    public void setNodeIdx(int nodeIdx) {
        this.nodeIdx = nodeIdx;
    }


    /**
     * Gets the parameterFullName value for this ManipulationParam.
     * 
     * @return parameterFullName
     */
    public java.lang.String getParameterFullName() {
        return parameterFullName;
    }


    /**
     * Sets the parameterFullName value for this ManipulationParam.
     * 
     * @param parameterFullName
     */
    public void setParameterFullName(java.lang.String parameterFullName) {
        this.parameterFullName = parameterFullName;
    }


    /**
     * Gets the parameterId value for this ManipulationParam.
     * 
     * @return parameterId
     */
    public int getParameterId() {
        return parameterId;
    }


    /**
     * Sets the parameterId value for this ManipulationParam.
     * 
     * @param parameterId
     */
    public void setParameterId(int parameterId) {
        this.parameterId = parameterId;
    }


    /**
     * Gets the parameterName value for this ManipulationParam.
     * 
     * @return parameterName
     */
    public java.lang.String getParameterName() {
        return parameterName;
    }


    /**
     * Sets the parameterName value for this ManipulationParam.
     * 
     * @param parameterName
     */
    public void setParameterName(java.lang.String parameterName) {
        this.parameterName = parameterName;
    }


    /**
     * Gets the parameterType value for this ManipulationParam.
     * 
     * @return parameterType
     */
    public int getParameterType() {
        return parameterType;
    }


    /**
     * Sets the parameterType value for this ManipulationParam.
     * 
     * @param parameterType
     */
    public void setParameterType(int parameterType) {
        this.parameterType = parameterType;
    }


    /**
     * Gets the parameterValue value for this ManipulationParam.
     * 
     * @return parameterValue
     */
    public double getParameterValue() {
        return parameterValue;
    }


    /**
     * Sets the parameterValue value for this ManipulationParam.
     * 
     * @param parameterValue
     */
    public void setParameterValue(double parameterValue) {
        this.parameterValue = parameterValue;
    }


    /**
     * Gets the predIdx value for this ManipulationParam.
     * 
     * @return predIdx
     */
    public int getPredIdx() {
        return predIdx;
    }


    /**
     * Sets the predIdx value for this ManipulationParam.
     * 
     * @param predIdx
     */
    public void setPredIdx(int predIdx) {
        this.predIdx = predIdx;
    }


    /**
     * Gets the preyIdx value for this ManipulationParam.
     * 
     * @return preyIdx
     */
    public int getPreyIdx() {
        return preyIdx;
    }


    /**
     * Sets the preyIdx value for this ManipulationParam.
     * 
     * @param preyIdx
     */
    public void setPreyIdx(int preyIdx) {
        this.preyIdx = preyIdx;
    }


    /**
     * Gets the saved value for this ManipulationParam.
     * 
     * @return saved
     */
    public boolean isSaved() {
        return saved;
    }


    /**
     * Sets the saved value for this ManipulationParam.
     * 
     * @param saved
     */
    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ManipulationParam)) return false;
        ManipulationParam other = (ManipulationParam) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.manipulationId==null && other.getManipulationId()==null) || 
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            this.nodeIdx == other.getNodeIdx() &&
            ((this.parameterFullName==null && other.getParameterFullName()==null) || 
             (this.parameterFullName!=null &&
              this.parameterFullName.equals(other.getParameterFullName()))) &&
            this.parameterId == other.getParameterId() &&
            ((this.parameterName==null && other.getParameterName()==null) || 
             (this.parameterName!=null &&
              this.parameterName.equals(other.getParameterName()))) &&
            this.parameterType == other.getParameterType() &&
            this.parameterValue == other.getParameterValue() &&
            this.predIdx == other.getPredIdx() &&
            this.preyIdx == other.getPreyIdx() &&
            this.saved == other.isSaved();
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        _hashCode += getNodeIdx();
        if (getParameterFullName() != null) {
            _hashCode += getParameterFullName().hashCode();
        }
        _hashCode += getParameterId();
        if (getParameterName() != null) {
            _hashCode += getParameterName().hashCode();
        }
        _hashCode += getParameterType();
        _hashCode += new Double(getParameterValue()).hashCode();
        _hashCode += getPredIdx();
        _hashCode += getPreyIdx();
        _hashCode += (isSaved() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationParam.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ManipulationParam"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "manipulationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "nodeIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterFullName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "parameterFullName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "parameterId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "parameterName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "parameterType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "parameterValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("predIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "predIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preyIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "preyIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saved");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "saved"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
