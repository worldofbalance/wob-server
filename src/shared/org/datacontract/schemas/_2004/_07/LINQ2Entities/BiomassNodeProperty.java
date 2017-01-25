/**
 * BiomassNodeProperty.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.LINQ2Entities;

public class BiomassNodeProperty  extends shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String biomassNodeId;

    private java.lang.String id;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode manipulationBiomassNode;

    private shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationBiomassNodehovVakal manipulationBiomassNodeReference;

    private java.lang.String propertyName;

    private java.lang.Float propertyValue;

    public BiomassNodeProperty() {
    }

    public BiomassNodeProperty(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           shared.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String biomassNodeId,
           java.lang.String id,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode manipulationBiomassNode,
           shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationBiomassNodehovVakal manipulationBiomassNodeReference,
           java.lang.String propertyName,
           java.lang.Float propertyValue) {
        super(
            _id,
            ref,
            entityKey);
        this.biomassNodeId = biomassNodeId;
        this.id = id;
        this.manipulationBiomassNode = manipulationBiomassNode;
        this.manipulationBiomassNodeReference = manipulationBiomassNodeReference;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }


    /**
     * Gets the biomassNodeId value for this BiomassNodeProperty.
     * 
     * @return biomassNodeId
     */
    public java.lang.String getBiomassNodeId() {
        return biomassNodeId;
    }


    /**
     * Sets the biomassNodeId value for this BiomassNodeProperty.
     * 
     * @param biomassNodeId
     */
    public void setBiomassNodeId(java.lang.String biomassNodeId) {
        this.biomassNodeId = biomassNodeId;
    }


    /**
     * Gets the id value for this BiomassNodeProperty.
     * 
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this BiomassNodeProperty.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the manipulationBiomassNode value for this BiomassNodeProperty.
     * 
     * @return manipulationBiomassNode
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode getManipulationBiomassNode() {
        return manipulationBiomassNode;
    }


    /**
     * Sets the manipulationBiomassNode value for this BiomassNodeProperty.
     * 
     * @param manipulationBiomassNode
     */
    public void setManipulationBiomassNode(shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode manipulationBiomassNode) {
        this.manipulationBiomassNode = manipulationBiomassNode;
    }


    /**
     * Gets the manipulationBiomassNodeReference value for this BiomassNodeProperty.
     * 
     * @return manipulationBiomassNodeReference
     */
    public shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationBiomassNodehovVakal getManipulationBiomassNodeReference() {
        return manipulationBiomassNodeReference;
    }


    /**
     * Sets the manipulationBiomassNodeReference value for this BiomassNodeProperty.
     * 
     * @param manipulationBiomassNodeReference
     */
    public void setManipulationBiomassNodeReference(shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationBiomassNodehovVakal manipulationBiomassNodeReference) {
        this.manipulationBiomassNodeReference = manipulationBiomassNodeReference;
    }


    /**
     * Gets the propertyName value for this BiomassNodeProperty.
     * 
     * @return propertyName
     */
    public java.lang.String getPropertyName() {
        return propertyName;
    }


    /**
     * Sets the propertyName value for this BiomassNodeProperty.
     * 
     * @param propertyName
     */
    public void setPropertyName(java.lang.String propertyName) {
        this.propertyName = propertyName;
    }


    /**
     * Gets the propertyValue value for this BiomassNodeProperty.
     * 
     * @return propertyValue
     */
    public java.lang.Float getPropertyValue() {
        return propertyValue;
    }


    /**
     * Sets the propertyValue value for this BiomassNodeProperty.
     * 
     * @param propertyValue
     */
    public void setPropertyValue(java.lang.Float propertyValue) {
        this.propertyValue = propertyValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BiomassNodeProperty)) return false;
        BiomassNodeProperty other = (BiomassNodeProperty) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.biomassNodeId==null && other.getBiomassNodeId()==null) || 
             (this.biomassNodeId!=null &&
              this.biomassNodeId.equals(other.getBiomassNodeId()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.manipulationBiomassNode==null && other.getManipulationBiomassNode()==null) || 
             (this.manipulationBiomassNode!=null &&
              this.manipulationBiomassNode.equals(other.getManipulationBiomassNode()))) &&
            ((this.manipulationBiomassNodeReference==null && other.getManipulationBiomassNodeReference()==null) || 
             (this.manipulationBiomassNodeReference!=null &&
              this.manipulationBiomassNodeReference.equals(other.getManipulationBiomassNodeReference()))) &&
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
        if (getBiomassNodeId() != null) {
            _hashCode += getBiomassNodeId().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getManipulationBiomassNode() != null) {
            _hashCode += getManipulationBiomassNode().hashCode();
        }
        if (getManipulationBiomassNodeReference() != null) {
            _hashCode += getManipulationBiomassNodeReference().hashCode();
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
        new org.apache.axis.description.TypeDesc(BiomassNodeProperty.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "BiomassNodeProperty"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("biomassNodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "BiomassNodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationBiomassNode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationBiomassNode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationBiomassNode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationBiomassNodeReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationBiomassNodeReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfManipulationBiomassNodehovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
