/**
 * ModelNodeProperty.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.LINQ2Entities;

public class ModelNodeProperty  extends shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String id;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationModel manipulationModel;

    private shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationModelhovVakal manipulationModelReference;

    private java.lang.String modelNodeId;

    private java.lang.String propertyName;

    private java.lang.Float propertyValue;

    public ModelNodeProperty() {
    }

    public ModelNodeProperty(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           shared.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String id,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationModel manipulationModel,
           shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationModelhovVakal manipulationModelReference,
           java.lang.String modelNodeId,
           java.lang.String propertyName,
           java.lang.Float propertyValue) {
        super(
            _id,
            ref,
            entityKey);
        this.id = id;
        this.manipulationModel = manipulationModel;
        this.manipulationModelReference = manipulationModelReference;
        this.modelNodeId = modelNodeId;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }


    /**
     * Gets the id value for this ModelNodeProperty.
     * 
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this ModelNodeProperty.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the manipulationModel value for this ModelNodeProperty.
     * 
     * @return manipulationModel
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationModel getManipulationModel() {
        return manipulationModel;
    }


    /**
     * Sets the manipulationModel value for this ModelNodeProperty.
     * 
     * @param manipulationModel
     */
    public void setManipulationModel(shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationModel manipulationModel) {
        this.manipulationModel = manipulationModel;
    }


    /**
     * Gets the manipulationModelReference value for this ModelNodeProperty.
     * 
     * @return manipulationModelReference
     */
    public shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationModelhovVakal getManipulationModelReference() {
        return manipulationModelReference;
    }


    /**
     * Sets the manipulationModelReference value for this ModelNodeProperty.
     * 
     * @param manipulationModelReference
     */
    public void setManipulationModelReference(shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationModelhovVakal manipulationModelReference) {
        this.manipulationModelReference = manipulationModelReference;
    }


    /**
     * Gets the modelNodeId value for this ModelNodeProperty.
     * 
     * @return modelNodeId
     */
    public java.lang.String getModelNodeId() {
        return modelNodeId;
    }


    /**
     * Sets the modelNodeId value for this ModelNodeProperty.
     * 
     * @param modelNodeId
     */
    public void setModelNodeId(java.lang.String modelNodeId) {
        this.modelNodeId = modelNodeId;
    }


    /**
     * Gets the propertyName value for this ModelNodeProperty.
     * 
     * @return propertyName
     */
    public java.lang.String getPropertyName() {
        return propertyName;
    }


    /**
     * Sets the propertyName value for this ModelNodeProperty.
     * 
     * @param propertyName
     */
    public void setPropertyName(java.lang.String propertyName) {
        this.propertyName = propertyName;
    }


    /**
     * Gets the propertyValue value for this ModelNodeProperty.
     * 
     * @return propertyValue
     */
    public java.lang.Float getPropertyValue() {
        return propertyValue;
    }


    /**
     * Sets the propertyValue value for this ModelNodeProperty.
     * 
     * @param propertyValue
     */
    public void setPropertyValue(java.lang.Float propertyValue) {
        this.propertyValue = propertyValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ModelNodeProperty)) return false;
        ModelNodeProperty other = (ModelNodeProperty) obj;
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
            ((this.manipulationModel==null && other.getManipulationModel()==null) || 
             (this.manipulationModel!=null &&
              this.manipulationModel.equals(other.getManipulationModel()))) &&
            ((this.manipulationModelReference==null && other.getManipulationModelReference()==null) || 
             (this.manipulationModelReference!=null &&
              this.manipulationModelReference.equals(other.getManipulationModelReference()))) &&
            ((this.modelNodeId==null && other.getModelNodeId()==null) || 
             (this.modelNodeId!=null &&
              this.modelNodeId.equals(other.getModelNodeId()))) &&
            ((this.propertyName==null && other.getPropertyName()==null) || 
             (this.propertyName!=null &&
              this.propertyName.equals(other.getPropertyName()))) &&
            ((this.propertyValue==null && other.getPropertyValue()==null) || 
             (this.propertyValue!=null &&
              this.propertyValue.equals(other.getPropertyValue())));
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
        if (getManipulationModel() != null) {
            _hashCode += getManipulationModel().hashCode();
        }
        if (getManipulationModelReference() != null) {
            _hashCode += getManipulationModelReference().hashCode();
        }
        if (getModelNodeId() != null) {
            _hashCode += getModelNodeId().hashCode();
        }
        if (getPropertyName() != null) {
            _hashCode += getPropertyName().hashCode();
        }
        if (getPropertyValue() != null) {
            _hashCode += getPropertyValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ModelNodeProperty.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ModelNodeProperty"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationModel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationModel"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationModelReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationModelReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfManipulationModelhovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelNodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ModelNodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "PropertyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "PropertyValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
