/**
 * ManipulationTimesteps.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.LINQ2Entities;

public class ManipulationTimesteps  extends shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.Float amount;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.EnergyFlowLink energyFlowLink;

    private shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfEnergyFlowLinkhovVakal energyFlowLinkReference;

    private java.lang.String id;

    private java.lang.Boolean isLink;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode manipulationBiomassNode;

    private shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationBiomassNodehovVakal manipulationBiomassNodeReference;

    private java.lang.String manipulationId;

    private shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference;

    private java.lang.String nodeLinkId;

    private java.lang.Double timestep;

    private java.lang.Integer timestepIdx;

    private java.lang.Boolean userProvided;

    public ManipulationTimesteps() {
    }

    public ManipulationTimesteps(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           shared.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.Float amount,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.EnergyFlowLink energyFlowLink,
           shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfEnergyFlowLinkhovVakal energyFlowLinkReference,
           java.lang.String id,
           java.lang.Boolean isLink,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode manipulationBiomassNode,
           shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationBiomassNodehovVakal manipulationBiomassNodeReference,
           java.lang.String manipulationId,
           shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference,
           java.lang.String nodeLinkId,
           java.lang.Double timestep,
           java.lang.Integer timestepIdx,
           java.lang.Boolean userProvided) {
        super(
            _id,
            ref,
            entityKey);
        this.amount = amount;
        this.energyFlowLink = energyFlowLink;
        this.energyFlowLinkReference = energyFlowLinkReference;
        this.id = id;
        this.isLink = isLink;
        this.manipulation = manipulation;
        this.manipulationBiomassNode = manipulationBiomassNode;
        this.manipulationBiomassNodeReference = manipulationBiomassNodeReference;
        this.manipulationId = manipulationId;
        this.manipulationReference = manipulationReference;
        this.nodeLinkId = nodeLinkId;
        this.timestep = timestep;
        this.timestepIdx = timestepIdx;
        this.userProvided = userProvided;
    }


    /**
     * Gets the amount value for this ManipulationTimesteps.
     * 
     * @return amount
     */
    public java.lang.Float getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this ManipulationTimesteps.
     * 
     * @param amount
     */
    public void setAmount(java.lang.Float amount) {
        this.amount = amount;
    }


    /**
     * Gets the energyFlowLink value for this ManipulationTimesteps.
     * 
     * @return energyFlowLink
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.EnergyFlowLink getEnergyFlowLink() {
        return energyFlowLink;
    }


    /**
     * Sets the energyFlowLink value for this ManipulationTimesteps.
     * 
     * @param energyFlowLink
     */
    public void setEnergyFlowLink(shared.org.datacontract.schemas._2004._07.LINQ2Entities.EnergyFlowLink energyFlowLink) {
        this.energyFlowLink = energyFlowLink;
    }


    /**
     * Gets the energyFlowLinkReference value for this ManipulationTimesteps.
     * 
     * @return energyFlowLinkReference
     */
    public shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfEnergyFlowLinkhovVakal getEnergyFlowLinkReference() {
        return energyFlowLinkReference;
    }


    /**
     * Sets the energyFlowLinkReference value for this ManipulationTimesteps.
     * 
     * @param energyFlowLinkReference
     */
    public void setEnergyFlowLinkReference(shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfEnergyFlowLinkhovVakal energyFlowLinkReference) {
        this.energyFlowLinkReference = energyFlowLinkReference;
    }


    /**
     * Gets the id value for this ManipulationTimesteps.
     * 
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this ManipulationTimesteps.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the isLink value for this ManipulationTimesteps.
     * 
     * @return isLink
     */
    public java.lang.Boolean getIsLink() {
        return isLink;
    }


    /**
     * Sets the isLink value for this ManipulationTimesteps.
     * 
     * @param isLink
     */
    public void setIsLink(java.lang.Boolean isLink) {
        this.isLink = isLink;
    }


    /**
     * Gets the manipulation value for this ManipulationTimesteps.
     * 
     * @return manipulation
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation getManipulation() {
        return manipulation;
    }


    /**
     * Sets the manipulation value for this ManipulationTimesteps.
     * 
     * @param manipulation
     */
    public void setManipulation(shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation) {
        this.manipulation = manipulation;
    }


    /**
     * Gets the manipulationBiomassNode value for this ManipulationTimesteps.
     * 
     * @return manipulationBiomassNode
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode getManipulationBiomassNode() {
        return manipulationBiomassNode;
    }


    /**
     * Sets the manipulationBiomassNode value for this ManipulationTimesteps.
     * 
     * @param manipulationBiomassNode
     */
    public void setManipulationBiomassNode(shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode manipulationBiomassNode) {
        this.manipulationBiomassNode = manipulationBiomassNode;
    }


    /**
     * Gets the manipulationBiomassNodeReference value for this ManipulationTimesteps.
     * 
     * @return manipulationBiomassNodeReference
     */
    public shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationBiomassNodehovVakal getManipulationBiomassNodeReference() {
        return manipulationBiomassNodeReference;
    }


    /**
     * Sets the manipulationBiomassNodeReference value for this ManipulationTimesteps.
     * 
     * @param manipulationBiomassNodeReference
     */
    public void setManipulationBiomassNodeReference(shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationBiomassNodehovVakal manipulationBiomassNodeReference) {
        this.manipulationBiomassNodeReference = manipulationBiomassNodeReference;
    }


    /**
     * Gets the manipulationId value for this ManipulationTimesteps.
     * 
     * @return manipulationId
     */
    public java.lang.String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationTimesteps.
     * 
     * @param manipulationId
     */
    public void setManipulationId(java.lang.String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the manipulationReference value for this ManipulationTimesteps.
     * 
     * @return manipulationReference
     */
    public shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal getManipulationReference() {
        return manipulationReference;
    }


    /**
     * Sets the manipulationReference value for this ManipulationTimesteps.
     * 
     * @param manipulationReference
     */
    public void setManipulationReference(shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference) {
        this.manipulationReference = manipulationReference;
    }


    /**
     * Gets the nodeLinkId value for this ManipulationTimesteps.
     * 
     * @return nodeLinkId
     */
    public java.lang.String getNodeLinkId() {
        return nodeLinkId;
    }


    /**
     * Sets the nodeLinkId value for this ManipulationTimesteps.
     * 
     * @param nodeLinkId
     */
    public void setNodeLinkId(java.lang.String nodeLinkId) {
        this.nodeLinkId = nodeLinkId;
    }


    /**
     * Gets the timestep value for this ManipulationTimesteps.
     * 
     * @return timestep
     */
    public java.lang.Double getTimestep() {
        return timestep;
    }


    /**
     * Sets the timestep value for this ManipulationTimesteps.
     * 
     * @param timestep
     */
    public void setTimestep(java.lang.Double timestep) {
        this.timestep = timestep;
    }


    /**
     * Gets the timestepIdx value for this ManipulationTimesteps.
     * 
     * @return timestepIdx
     */
    public java.lang.Integer getTimestepIdx() {
        return timestepIdx;
    }


    /**
     * Sets the timestepIdx value for this ManipulationTimesteps.
     * 
     * @param timestepIdx
     */
    public void setTimestepIdx(java.lang.Integer timestepIdx) {
        this.timestepIdx = timestepIdx;
    }


    /**
     * Gets the userProvided value for this ManipulationTimesteps.
     * 
     * @return userProvided
     */
    public java.lang.Boolean getUserProvided() {
        return userProvided;
    }


    /**
     * Sets the userProvided value for this ManipulationTimesteps.
     * 
     * @param userProvided
     */
    public void setUserProvided(java.lang.Boolean userProvided) {
        this.userProvided = userProvided;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ManipulationTimesteps)) return false;
        ManipulationTimesteps other = (ManipulationTimesteps) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            ((this.energyFlowLink==null && other.getEnergyFlowLink()==null) || 
             (this.energyFlowLink!=null &&
              this.energyFlowLink.equals(other.getEnergyFlowLink()))) &&
            ((this.energyFlowLinkReference==null && other.getEnergyFlowLinkReference()==null) || 
             (this.energyFlowLinkReference!=null &&
              this.energyFlowLinkReference.equals(other.getEnergyFlowLinkReference()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.isLink==null && other.getIsLink()==null) || 
             (this.isLink!=null &&
              this.isLink.equals(other.getIsLink()))) &&
            ((this.manipulation==null && other.getManipulation()==null) || 
             (this.manipulation!=null &&
              this.manipulation.equals(other.getManipulation()))) &&
            ((this.manipulationBiomassNode==null && other.getManipulationBiomassNode()==null) || 
             (this.manipulationBiomassNode!=null &&
              this.manipulationBiomassNode.equals(other.getManipulationBiomassNode()))) &&
            ((this.manipulationBiomassNodeReference==null && other.getManipulationBiomassNodeReference()==null) || 
             (this.manipulationBiomassNodeReference!=null &&
              this.manipulationBiomassNodeReference.equals(other.getManipulationBiomassNodeReference()))) &&
            ((this.manipulationId==null && other.getManipulationId()==null) || 
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            ((this.manipulationReference==null && other.getManipulationReference()==null) || 
             (this.manipulationReference!=null &&
              this.manipulationReference.equals(other.getManipulationReference()))) &&
            ((this.nodeLinkId==null && other.getNodeLinkId()==null) || 
             (this.nodeLinkId!=null &&
              this.nodeLinkId.equals(other.getNodeLinkId()))) &&
            ((this.timestep==null && other.getTimestep()==null) || 
             (this.timestep!=null &&
              this.timestep.equals(other.getTimestep()))) &&
            ((this.timestepIdx==null && other.getTimestepIdx()==null) || 
             (this.timestepIdx!=null &&
              this.timestepIdx.equals(other.getTimestepIdx()))) &&
            ((this.userProvided==null && other.getUserProvided()==null) || 
             (this.userProvided!=null &&
              this.userProvided.equals(other.getUserProvided())));
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
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        if (getEnergyFlowLink() != null) {
            _hashCode += getEnergyFlowLink().hashCode();
        }
        if (getEnergyFlowLinkReference() != null) {
            _hashCode += getEnergyFlowLinkReference().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIsLink() != null) {
            _hashCode += getIsLink().hashCode();
        }
        if (getManipulation() != null) {
            _hashCode += getManipulation().hashCode();
        }
        if (getManipulationBiomassNode() != null) {
            _hashCode += getManipulationBiomassNode().hashCode();
        }
        if (getManipulationBiomassNodeReference() != null) {
            _hashCode += getManipulationBiomassNodeReference().hashCode();
        }
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        if (getManipulationReference() != null) {
            _hashCode += getManipulationReference().hashCode();
        }
        if (getNodeLinkId() != null) {
            _hashCode += getNodeLinkId().hashCode();
        }
        if (getTimestep() != null) {
            _hashCode += getTimestep().hashCode();
        }
        if (getTimestepIdx() != null) {
            _hashCode += getTimestepIdx().hashCode();
        }
        if (getUserProvided() != null) {
            _hashCode += getUserProvided().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationTimesteps.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationTimesteps"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("energyFlowLink");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "EnergyFlowLink"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "EnergyFlowLink"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("energyFlowLinkReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "EnergyFlowLinkReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfEnergyFlowLinkhovVakal"));
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
        elemField.setFieldName("isLink");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "IsLink"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("nodeLinkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeLinkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userProvided");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserProvided"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
