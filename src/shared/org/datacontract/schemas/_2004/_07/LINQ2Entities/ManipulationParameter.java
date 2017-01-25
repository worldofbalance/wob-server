/**
 * ManipulationParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.LINQ2Entities;

public class ManipulationParameter  extends shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String id;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation;

    private java.lang.String manipulationId;

    private shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference;

    private java.lang.Integer nodeIdx;

    private java.lang.String parameterFullName;

    private java.lang.Integer parameterId;

    private java.lang.String parameterName;

    private java.lang.Integer parameterType;

    private java.lang.Float parameterValue;

    private java.lang.Integer predIdx;

    private java.lang.Integer preyIdx;

    private java.lang.Double timestep;

    private java.lang.Integer timestepIdx;

    public ManipulationParameter() {
    }

    public ManipulationParameter(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           shared.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String id,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation,
           java.lang.String manipulationId,
           shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference,
           java.lang.Integer nodeIdx,
           java.lang.String parameterFullName,
           java.lang.Integer parameterId,
           java.lang.String parameterName,
           java.lang.Integer parameterType,
           java.lang.Float parameterValue,
           java.lang.Integer predIdx,
           java.lang.Integer preyIdx,
           java.lang.Double timestep,
           java.lang.Integer timestepIdx) {
        super(
            _id,
            ref,
            entityKey);
        this.id = id;
        this.manipulation = manipulation;
        this.manipulationId = manipulationId;
        this.manipulationReference = manipulationReference;
        this.nodeIdx = nodeIdx;
        this.parameterFullName = parameterFullName;
        this.parameterId = parameterId;
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.parameterValue = parameterValue;
        this.predIdx = predIdx;
        this.preyIdx = preyIdx;
        this.timestep = timestep;
        this.timestepIdx = timestepIdx;
    }


    /**
     * Gets the id value for this ManipulationParameter.
     * 
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this ManipulationParameter.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the manipulation value for this ManipulationParameter.
     * 
     * @return manipulation
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation getManipulation() {
        return manipulation;
    }


    /**
     * Sets the manipulation value for this ManipulationParameter.
     * 
     * @param manipulation
     */
    public void setManipulation(shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation) {
        this.manipulation = manipulation;
    }


    /**
     * Gets the manipulationId value for this ManipulationParameter.
     * 
     * @return manipulationId
     */
    public java.lang.String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationParameter.
     * 
     * @param manipulationId
     */
    public void setManipulationId(java.lang.String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the manipulationReference value for this ManipulationParameter.
     * 
     * @return manipulationReference
     */
    public shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal getManipulationReference() {
        return manipulationReference;
    }


    /**
     * Sets the manipulationReference value for this ManipulationParameter.
     * 
     * @param manipulationReference
     */
    public void setManipulationReference(shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference) {
        this.manipulationReference = manipulationReference;
    }


    /**
     * Gets the nodeIdx value for this ManipulationParameter.
     * 
     * @return nodeIdx
     */
    public java.lang.Integer getNodeIdx() {
        return nodeIdx;
    }


    /**
     * Sets the nodeIdx value for this ManipulationParameter.
     * 
     * @param nodeIdx
     */
    public void setNodeIdx(java.lang.Integer nodeIdx) {
        this.nodeIdx = nodeIdx;
    }


    /**
     * Gets the parameterFullName value for this ManipulationParameter.
     * 
     * @return parameterFullName
     */
    public java.lang.String getParameterFullName() {
        return parameterFullName;
    }


    /**
     * Sets the parameterFullName value for this ManipulationParameter.
     * 
     * @param parameterFullName
     */
    public void setParameterFullName(java.lang.String parameterFullName) {
        this.parameterFullName = parameterFullName;
    }


    /**
     * Gets the parameterId value for this ManipulationParameter.
     * 
     * @return parameterId
     */
    public java.lang.Integer getParameterId() {
        return parameterId;
    }


    /**
     * Sets the parameterId value for this ManipulationParameter.
     * 
     * @param parameterId
     */
    public void setParameterId(java.lang.Integer parameterId) {
        this.parameterId = parameterId;
    }


    /**
     * Gets the parameterName value for this ManipulationParameter.
     * 
     * @return parameterName
     */
    public java.lang.String getParameterName() {
        return parameterName;
    }


    /**
     * Sets the parameterName value for this ManipulationParameter.
     * 
     * @param parameterName
     */
    public void setParameterName(java.lang.String parameterName) {
        this.parameterName = parameterName;
    }


    /**
     * Gets the parameterType value for this ManipulationParameter.
     * 
     * @return parameterType
     */
    public java.lang.Integer getParameterType() {
        return parameterType;
    }


    /**
     * Sets the parameterType value for this ManipulationParameter.
     * 
     * @param parameterType
     */
    public void setParameterType(java.lang.Integer parameterType) {
        this.parameterType = parameterType;
    }


    /**
     * Gets the parameterValue value for this ManipulationParameter.
     * 
     * @return parameterValue
     */
    public java.lang.Float getParameterValue() {
        return parameterValue;
    }


    /**
     * Sets the parameterValue value for this ManipulationParameter.
     * 
     * @param parameterValue
     */
    public void setParameterValue(java.lang.Float parameterValue) {
        this.parameterValue = parameterValue;
    }


    /**
     * Gets the predIdx value for this ManipulationParameter.
     * 
     * @return predIdx
     */
    public java.lang.Integer getPredIdx() {
        return predIdx;
    }


    /**
     * Sets the predIdx value for this ManipulationParameter.
     * 
     * @param predIdx
     */
    public void setPredIdx(java.lang.Integer predIdx) {
        this.predIdx = predIdx;
    }


    /**
     * Gets the preyIdx value for this ManipulationParameter.
     * 
     * @return preyIdx
     */
    public java.lang.Integer getPreyIdx() {
        return preyIdx;
    }


    /**
     * Sets the preyIdx value for this ManipulationParameter.
     * 
     * @param preyIdx
     */
    public void setPreyIdx(java.lang.Integer preyIdx) {
        this.preyIdx = preyIdx;
    }


    /**
     * Gets the timestep value for this ManipulationParameter.
     * 
     * @return timestep
     */
    public java.lang.Double getTimestep() {
        return timestep;
    }


    /**
     * Sets the timestep value for this ManipulationParameter.
     * 
     * @param timestep
     */
    public void setTimestep(java.lang.Double timestep) {
        this.timestep = timestep;
    }


    /**
     * Gets the timestepIdx value for this ManipulationParameter.
     * 
     * @return timestepIdx
     */
    public java.lang.Integer getTimestepIdx() {
        return timestepIdx;
    }


    /**
     * Sets the timestepIdx value for this ManipulationParameter.
     * 
     * @param timestepIdx
     */
    public void setTimestepIdx(java.lang.Integer timestepIdx) {
        this.timestepIdx = timestepIdx;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ManipulationParameter)) return false;
        ManipulationParameter other = (ManipulationParameter) obj;
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
            ((this.manipulation==null && other.getManipulation()==null) || 
             (this.manipulation!=null &&
              this.manipulation.equals(other.getManipulation()))) &&
            ((this.manipulationId==null && other.getManipulationId()==null) || 
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            ((this.manipulationReference==null && other.getManipulationReference()==null) || 
             (this.manipulationReference!=null &&
              this.manipulationReference.equals(other.getManipulationReference()))) &&
            ((this.nodeIdx==null && other.getNodeIdx()==null) || 
             (this.nodeIdx!=null &&
              this.nodeIdx.equals(other.getNodeIdx()))) &&
            ((this.parameterFullName==null && other.getParameterFullName()==null) || 
             (this.parameterFullName!=null &&
              this.parameterFullName.equals(other.getParameterFullName()))) &&
            ((this.parameterId==null && other.getParameterId()==null) || 
             (this.parameterId!=null &&
              this.parameterId.equals(other.getParameterId()))) &&
            ((this.parameterName==null && other.getParameterName()==null) || 
             (this.parameterName!=null &&
              this.parameterName.equals(other.getParameterName()))) &&
            ((this.parameterType==null && other.getParameterType()==null) || 
             (this.parameterType!=null &&
              this.parameterType.equals(other.getParameterType()))) &&
            ((this.parameterValue==null && other.getParameterValue()==null) || 
             (this.parameterValue!=null &&
              this.parameterValue.equals(other.getParameterValue()))) &&
            ((this.predIdx==null && other.getPredIdx()==null) || 
             (this.predIdx!=null &&
              this.predIdx.equals(other.getPredIdx()))) &&
            ((this.preyIdx==null && other.getPreyIdx()==null) || 
             (this.preyIdx!=null &&
              this.preyIdx.equals(other.getPreyIdx()))) &&
            ((this.timestep==null && other.getTimestep()==null) || 
             (this.timestep!=null &&
              this.timestep.equals(other.getTimestep()))) &&
            ((this.timestepIdx==null && other.getTimestepIdx()==null) || 
             (this.timestepIdx!=null &&
              this.timestepIdx.equals(other.getTimestepIdx())));
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
        if (getManipulation() != null) {
            _hashCode += getManipulation().hashCode();
        }
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        if (getManipulationReference() != null) {
            _hashCode += getManipulationReference().hashCode();
        }
        if (getNodeIdx() != null) {
            _hashCode += getNodeIdx().hashCode();
        }
        if (getParameterFullName() != null) {
            _hashCode += getParameterFullName().hashCode();
        }
        if (getParameterId() != null) {
            _hashCode += getParameterId().hashCode();
        }
        if (getParameterName() != null) {
            _hashCode += getParameterName().hashCode();
        }
        if (getParameterType() != null) {
            _hashCode += getParameterType().hashCode();
        }
        if (getParameterValue() != null) {
            _hashCode += getParameterValue().hashCode();
        }
        if (getPredIdx() != null) {
            _hashCode += getPredIdx().hashCode();
        }
        if (getPreyIdx() != null) {
            _hashCode += getPreyIdx().hashCode();
        }
        if (getTimestep() != null) {
            _hashCode += getTimestep().hashCode();
        }
        if (getTimestepIdx() != null) {
            _hashCode += getTimestepIdx().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Manipulation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Manipulation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfManipulationhovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterFullName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ParameterFullName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ParameterId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ParameterName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ParameterType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ParameterValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("predIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "PredIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preyIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "PreyIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Timestep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestepIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "TimestepIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
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
