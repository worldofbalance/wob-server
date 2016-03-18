/**
 * EnergyFlowLink.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.LINQ2Entities;

public class EnergyFlowLink  extends shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String description;

    private java.lang.String destinNodeId;

    private java.lang.Integer destinNodeIdx;

    private java.lang.String energyFlowUnit;

    private java.lang.String id;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation;

    private java.lang.String manipulationId;

    private shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] manipulationTimesteps;

    private java.lang.String name;

    private java.lang.String sourceNodeId;

    private java.lang.Integer sourceNodeIdx;

    public EnergyFlowLink() {
    }

    public EnergyFlowLink(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           shared.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String description,
           java.lang.String destinNodeId,
           java.lang.Integer destinNodeIdx,
           java.lang.String energyFlowUnit,
           java.lang.String id,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation,
           java.lang.String manipulationId,
           shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] manipulationTimesteps,
           java.lang.String name,
           java.lang.String sourceNodeId,
           java.lang.Integer sourceNodeIdx) {
        super(
            _id,
            ref,
            entityKey);
        this.description = description;
        this.destinNodeId = destinNodeId;
        this.destinNodeIdx = destinNodeIdx;
        this.energyFlowUnit = energyFlowUnit;
        this.id = id;
        this.manipulation = manipulation;
        this.manipulationId = manipulationId;
        this.manipulationReference = manipulationReference;
        this.manipulationTimesteps = manipulationTimesteps;
        this.name = name;
        this.sourceNodeId = sourceNodeId;
        this.sourceNodeIdx = sourceNodeIdx;
    }


    /**
     * Gets the description value for this EnergyFlowLink.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this EnergyFlowLink.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the destinNodeId value for this EnergyFlowLink.
     * 
     * @return destinNodeId
     */
    public java.lang.String getDestinNodeId() {
        return destinNodeId;
    }


    /**
     * Sets the destinNodeId value for this EnergyFlowLink.
     * 
     * @param destinNodeId
     */
    public void setDestinNodeId(java.lang.String destinNodeId) {
        this.destinNodeId = destinNodeId;
    }


    /**
     * Gets the destinNodeIdx value for this EnergyFlowLink.
     * 
     * @return destinNodeIdx
     */
    public java.lang.Integer getDestinNodeIdx() {
        return destinNodeIdx;
    }


    /**
     * Sets the destinNodeIdx value for this EnergyFlowLink.
     * 
     * @param destinNodeIdx
     */
    public void setDestinNodeIdx(java.lang.Integer destinNodeIdx) {
        this.destinNodeIdx = destinNodeIdx;
    }


    /**
     * Gets the energyFlowUnit value for this EnergyFlowLink.
     * 
     * @return energyFlowUnit
     */
    public java.lang.String getEnergyFlowUnit() {
        return energyFlowUnit;
    }


    /**
     * Sets the energyFlowUnit value for this EnergyFlowLink.
     * 
     * @param energyFlowUnit
     */
    public void setEnergyFlowUnit(java.lang.String energyFlowUnit) {
        this.energyFlowUnit = energyFlowUnit;
    }


    /**
     * Gets the id value for this EnergyFlowLink.
     * 
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this EnergyFlowLink.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the manipulation value for this EnergyFlowLink.
     * 
     * @return manipulation
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation getManipulation() {
        return manipulation;
    }


    /**
     * Sets the manipulation value for this EnergyFlowLink.
     * 
     * @param manipulation
     */
    public void setManipulation(shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation) {
        this.manipulation = manipulation;
    }


    /**
     * Gets the manipulationId value for this EnergyFlowLink.
     * 
     * @return manipulationId
     */
    public java.lang.String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this EnergyFlowLink.
     * 
     * @param manipulationId
     */
    public void setManipulationId(java.lang.String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the manipulationReference value for this EnergyFlowLink.
     * 
     * @return manipulationReference
     */
    public shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal getManipulationReference() {
        return manipulationReference;
    }


    /**
     * Sets the manipulationReference value for this EnergyFlowLink.
     * 
     * @param manipulationReference
     */
    public void setManipulationReference(shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference) {
        this.manipulationReference = manipulationReference;
    }


    /**
     * Gets the manipulationTimesteps value for this EnergyFlowLink.
     * 
     * @return manipulationTimesteps
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] getManipulationTimesteps() {
        return manipulationTimesteps;
    }


    /**
     * Sets the manipulationTimesteps value for this EnergyFlowLink.
     * 
     * @param manipulationTimesteps
     */
    public void setManipulationTimesteps(shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] manipulationTimesteps) {
        this.manipulationTimesteps = manipulationTimesteps;
    }


    /**
     * Gets the name value for this EnergyFlowLink.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this EnergyFlowLink.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the sourceNodeId value for this EnergyFlowLink.
     * 
     * @return sourceNodeId
     */
    public java.lang.String getSourceNodeId() {
        return sourceNodeId;
    }


    /**
     * Sets the sourceNodeId value for this EnergyFlowLink.
     * 
     * @param sourceNodeId
     */
    public void setSourceNodeId(java.lang.String sourceNodeId) {
        this.sourceNodeId = sourceNodeId;
    }


    /**
     * Gets the sourceNodeIdx value for this EnergyFlowLink.
     * 
     * @return sourceNodeIdx
     */
    public java.lang.Integer getSourceNodeIdx() {
        return sourceNodeIdx;
    }


    /**
     * Sets the sourceNodeIdx value for this EnergyFlowLink.
     * 
     * @param sourceNodeIdx
     */
    public void setSourceNodeIdx(java.lang.Integer sourceNodeIdx) {
        this.sourceNodeIdx = sourceNodeIdx;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EnergyFlowLink)) return false;
        EnergyFlowLink other = (EnergyFlowLink) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.destinNodeId==null && other.getDestinNodeId()==null) || 
             (this.destinNodeId!=null &&
              this.destinNodeId.equals(other.getDestinNodeId()))) &&
            ((this.destinNodeIdx==null && other.getDestinNodeIdx()==null) || 
             (this.destinNodeIdx!=null &&
              this.destinNodeIdx.equals(other.getDestinNodeIdx()))) &&
            ((this.energyFlowUnit==null && other.getEnergyFlowUnit()==null) || 
             (this.energyFlowUnit!=null &&
              this.energyFlowUnit.equals(other.getEnergyFlowUnit()))) &&
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
            ((this.manipulationTimesteps==null && other.getManipulationTimesteps()==null) || 
             (this.manipulationTimesteps!=null &&
              java.util.Arrays.equals(this.manipulationTimesteps, other.getManipulationTimesteps()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.sourceNodeId==null && other.getSourceNodeId()==null) || 
             (this.sourceNodeId!=null &&
              this.sourceNodeId.equals(other.getSourceNodeId()))) &&
            ((this.sourceNodeIdx==null && other.getSourceNodeIdx()==null) || 
             (this.sourceNodeIdx!=null &&
              this.sourceNodeIdx.equals(other.getSourceNodeIdx())));
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getDestinNodeId() != null) {
            _hashCode += getDestinNodeId().hashCode();
        }
        if (getDestinNodeIdx() != null) {
            _hashCode += getDestinNodeIdx().hashCode();
        }
        if (getEnergyFlowUnit() != null) {
            _hashCode += getEnergyFlowUnit().hashCode();
        }
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
        if (getManipulationTimesteps() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationTimesteps());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManipulationTimesteps(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getSourceNodeId() != null) {
            _hashCode += getSourceNodeId().hashCode();
        }
        if (getSourceNodeIdx() != null) {
            _hashCode += getSourceNodeIdx().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnergyFlowLink.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "EnergyFlowLink"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinNodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "DestinNodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinNodeIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "DestinNodeIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("energyFlowUnit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "EnergyFlowUnit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("manipulationTimesteps");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationTimesteps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationTimesteps"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationTimesteps"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceNodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "SourceNodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceNodeIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "SourceNodeIdx"));
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
