/**
 * ManipulationModel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class ManipulationModel  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private Float initialBiomass;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation;

    private Integer manipulationActionType;

    private String manipulationId;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.ModelNodeProperty[] modelNodeProperty;

    private Integer modelType;

    private String nodeId;

    private Integer nodeIdx;

    private Double timestep;

    private Integer timestepIdx;

    private String id;

    public ManipulationModel() {
    }

    public ManipulationModel(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           Float initialBiomass,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation,
           Integer manipulationActionType,
           String manipulationId,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.ModelNodeProperty[] modelNodeProperty,
           Integer modelType,
           String nodeId,
           Integer nodeIdx,
           Double timestep,
           Integer timestepIdx,
           String id) {
        super(
            _id,
            ref,
            entityKey);
        this.initialBiomass = initialBiomass;
        this.manipulation = manipulation;
        this.manipulationActionType = manipulationActionType;
        this.manipulationId = manipulationId;
        this.manipulationReference = manipulationReference;
        this.modelNodeProperty = modelNodeProperty;
        this.modelType = modelType;
        this.nodeId = nodeId;
        this.nodeIdx = nodeIdx;
        this.timestep = timestep;
        this.timestepIdx = timestepIdx;
        this.id = id;
    }


    /**
     * Gets the initialBiomass value for this ManipulationModel.
     *
     * @return initialBiomass
     */
    public Float getInitialBiomass() {
        return initialBiomass;
    }


    /**
     * Sets the initialBiomass value for this ManipulationModel.
     *
     * @param initialBiomass
     */
    public void setInitialBiomass(Float initialBiomass) {
        this.initialBiomass = initialBiomass;
    }


    /**
     * Gets the manipulation value for this ManipulationModel.
     *
     * @return manipulation
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation getManipulation() {
        return manipulation;
    }


    /**
     * Sets the manipulation value for this ManipulationModel.
     *
     * @param manipulation
     */
    public void setManipulation(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation) {
        this.manipulation = manipulation;
    }


    /**
     * Gets the manipulationActionType value for this ManipulationModel.
     *
     * @return manipulationActionType
     */
    public Integer getManipulationActionType() {
        return manipulationActionType;
    }


    /**
     * Sets the manipulationActionType value for this ManipulationModel.
     *
     * @param manipulationActionType
     */
    public void setManipulationActionType(Integer manipulationActionType) {
        this.manipulationActionType = manipulationActionType;
    }


    /**
     * Gets the manipulationId value for this ManipulationModel.
     *
     * @return manipulationId
     */
    public String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationModel.
     *
     * @param manipulationId
     */
    public void setManipulationId(String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the manipulationReference value for this ManipulationModel.
     *
     * @return manipulationReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal getManipulationReference() {
        return manipulationReference;
    }


    /**
     * Sets the manipulationReference value for this ManipulationModel.
     *
     * @param manipulationReference
     */
    public void setManipulationReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference) {
        this.manipulationReference = manipulationReference;
    }


    /**
     * Gets the modelNodeProperty value for this ManipulationModel.
     *
     * @return modelNodeProperty
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.ModelNodeProperty[] getModelNodeProperty() {
        return modelNodeProperty;
    }


    /**
     * Sets the modelNodeProperty value for this ManipulationModel.
     *
     * @param modelNodeProperty
     */
    public void setModelNodeProperty(cos.org.datacontract.schemas._2004._07.LINQ2Entities.ModelNodeProperty[] modelNodeProperty) {
        this.modelNodeProperty = modelNodeProperty;
    }


    /**
     * Gets the modelType value for this ManipulationModel.
     *
     * @return modelType
     */
    public Integer getModelType() {
        return modelType;
    }


    /**
     * Sets the modelType value for this ManipulationModel.
     *
     * @param modelType
     */
    public void setModelType(Integer modelType) {
        this.modelType = modelType;
    }


    /**
     * Gets the nodeId value for this ManipulationModel.
     *
     * @return nodeId
     */
    public String getNodeId() {
        return nodeId;
    }


    /**
     * Sets the nodeId value for this ManipulationModel.
     *
     * @param nodeId
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }


    /**
     * Gets the nodeIdx value for this ManipulationModel.
     *
     * @return nodeIdx
     */
    public Integer getNodeIdx() {
        return nodeIdx;
    }


    /**
     * Sets the nodeIdx value for this ManipulationModel.
     *
     * @param nodeIdx
     */
    public void setNodeIdx(Integer nodeIdx) {
        this.nodeIdx = nodeIdx;
    }


    /**
     * Gets the timestep value for this ManipulationModel.
     *
     * @return timestep
     */
    public Double getTimestep() {
        return timestep;
    }


    /**
     * Sets the timestep value for this ManipulationModel.
     *
     * @param timestep
     */
    public void setTimestep(Double timestep) {
        this.timestep = timestep;
    }


    /**
     * Gets the timestepIdx value for this ManipulationModel.
     *
     * @return timestepIdx
     */
    public Integer getTimestepIdx() {
        return timestepIdx;
    }


    /**
     * Sets the timestepIdx value for this ManipulationModel.
     *
     * @param timestepIdx
     */
    public void setTimestepIdx(Integer timestepIdx) {
        this.timestepIdx = timestepIdx;
    }


    /**
     * Gets the id value for this ManipulationModel.
     *
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this ManipulationModel.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulationModel)) return false;
        ManipulationModel other = (ManipulationModel) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.initialBiomass==null && other.getInitialBiomass()==null) ||
             (this.initialBiomass!=null &&
              this.initialBiomass.equals(other.getInitialBiomass()))) &&
            ((this.manipulation==null && other.getManipulation()==null) ||
             (this.manipulation!=null &&
              this.manipulation.equals(other.getManipulation()))) &&
            ((this.manipulationActionType==null && other.getManipulationActionType()==null) ||
             (this.manipulationActionType!=null &&
              this.manipulationActionType.equals(other.getManipulationActionType()))) &&
            ((this.manipulationId==null && other.getManipulationId()==null) ||
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            ((this.manipulationReference==null && other.getManipulationReference()==null) ||
             (this.manipulationReference!=null &&
              this.manipulationReference.equals(other.getManipulationReference()))) &&
            ((this.modelNodeProperty==null && other.getModelNodeProperty()==null) ||
             (this.modelNodeProperty!=null &&
              java.util.Arrays.equals(this.modelNodeProperty, other.getModelNodeProperty()))) &&
            ((this.modelType==null && other.getModelType()==null) ||
             (this.modelType!=null &&
              this.modelType.equals(other.getModelType()))) &&
            ((this.nodeId==null && other.getNodeId()==null) ||
             (this.nodeId!=null &&
              this.nodeId.equals(other.getNodeId()))) &&
            ((this.nodeIdx==null && other.getNodeIdx()==null) ||
             (this.nodeIdx!=null &&
              this.nodeIdx.equals(other.getNodeIdx()))) &&
            ((this.timestep==null && other.getTimestep()==null) ||
             (this.timestep!=null &&
              this.timestep.equals(other.getTimestep()))) &&
            ((this.timestepIdx==null && other.getTimestepIdx()==null) ||
             (this.timestepIdx!=null &&
              this.timestepIdx.equals(other.getTimestepIdx()))) &&
            ((this.id==null && other.getId()==null) ||
             (this.id!=null &&
              this.id.equals(other.getId())));
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
        if (getInitialBiomass() != null) {
            _hashCode += getInitialBiomass().hashCode();
        }
        if (getManipulation() != null) {
            _hashCode += getManipulation().hashCode();
        }
        if (getManipulationActionType() != null) {
            _hashCode += getManipulationActionType().hashCode();
        }
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        if (getManipulationReference() != null) {
            _hashCode += getManipulationReference().hashCode();
        }
        if (getModelNodeProperty() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getModelNodeProperty());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getModelNodeProperty(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getModelType() != null) {
            _hashCode += getModelType().hashCode();
        }
        if (getNodeId() != null) {
            _hashCode += getNodeId().hashCode();
        }
        if (getNodeIdx() != null) {
            _hashCode += getNodeIdx().hashCode();
        }
        if (getTimestep() != null) {
            _hashCode += getTimestep().hashCode();
        }
        if (getTimestepIdx() != null) {
            _hashCode += getTimestepIdx().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationModel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationModel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("initialBiomass");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "InitialBiomass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Manipulation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Manipulation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationActionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationActionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
        elemField.setFieldName("modelNodeProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ModelNodeProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ModelNodeProperty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ModelNodeProperty"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ModelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Timestep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestepIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "TimestepIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
