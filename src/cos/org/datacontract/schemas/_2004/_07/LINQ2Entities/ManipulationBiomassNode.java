/**
 * ManipulationBiomassNode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class ManipulationBiomassNode  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.BiomassNodeProperty[] biomassNodeProperty;

    private java.util.Calendar createdDate;

    private String description;

    private String id;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation;

    private String manipulationId;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] manipulationTimesteps;

    private String name;

    private String nodeId;

    private Integer nodeIdx;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal nodesReference;

    public ManipulationBiomassNode() {
    }

    public ManipulationBiomassNode(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.BiomassNodeProperty[] biomassNodeProperty,
           java.util.Calendar createdDate,
           String description,
           String id,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation,
           String manipulationId,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] manipulationTimesteps,
           String name,
           String nodeId,
           Integer nodeIdx,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal nodesReference) {
        super(
            _id,
            ref,
            entityKey);
        this.biomassNodeProperty = biomassNodeProperty;
        this.createdDate = createdDate;
        this.description = description;
        this.id = id;
        this.manipulation = manipulation;
        this.manipulationId = manipulationId;
        this.manipulationReference = manipulationReference;
        this.manipulationTimesteps = manipulationTimesteps;
        this.name = name;
        this.nodeId = nodeId;
        this.nodeIdx = nodeIdx;
        this.nodes = nodes;
        this.nodesReference = nodesReference;
    }


    /**
     * Gets the biomassNodeProperty value for this ManipulationBiomassNode.
     *
     * @return biomassNodeProperty
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.BiomassNodeProperty[] getBiomassNodeProperty() {
        return biomassNodeProperty;
    }


    /**
     * Sets the biomassNodeProperty value for this ManipulationBiomassNode.
     *
     * @param biomassNodeProperty
     */
    public void setBiomassNodeProperty(cos.org.datacontract.schemas._2004._07.LINQ2Entities.BiomassNodeProperty[] biomassNodeProperty) {
        this.biomassNodeProperty = biomassNodeProperty;
    }


    /**
     * Gets the createdDate value for this ManipulationBiomassNode.
     *
     * @return createdDate
     */
    public java.util.Calendar getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this ManipulationBiomassNode.
     *
     * @param createdDate
     */
    public void setCreatedDate(java.util.Calendar createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the description value for this ManipulationBiomassNode.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ManipulationBiomassNode.
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the id value for this ManipulationBiomassNode.
     *
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this ManipulationBiomassNode.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets the manipulation value for this ManipulationBiomassNode.
     *
     * @return manipulation
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation getManipulation() {
        return manipulation;
    }


    /**
     * Sets the manipulation value for this ManipulationBiomassNode.
     *
     * @param manipulation
     */
    public void setManipulation(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation) {
        this.manipulation = manipulation;
    }


    /**
     * Gets the manipulationId value for this ManipulationBiomassNode.
     *
     * @return manipulationId
     */
    public String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationBiomassNode.
     *
     * @param manipulationId
     */
    public void setManipulationId(String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the manipulationReference value for this ManipulationBiomassNode.
     *
     * @return manipulationReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal getManipulationReference() {
        return manipulationReference;
    }


    /**
     * Sets the manipulationReference value for this ManipulationBiomassNode.
     *
     * @param manipulationReference
     */
    public void setManipulationReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference) {
        this.manipulationReference = manipulationReference;
    }


    /**
     * Gets the manipulationTimesteps value for this ManipulationBiomassNode.
     *
     * @return manipulationTimesteps
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] getManipulationTimesteps() {
        return manipulationTimesteps;
    }


    /**
     * Sets the manipulationTimesteps value for this ManipulationBiomassNode.
     *
     * @param manipulationTimesteps
     */
    public void setManipulationTimesteps(cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] manipulationTimesteps) {
        this.manipulationTimesteps = manipulationTimesteps;
    }


    /**
     * Gets the name value for this ManipulationBiomassNode.
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this ManipulationBiomassNode.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the nodeId value for this ManipulationBiomassNode.
     *
     * @return nodeId
     */
    public String getNodeId() {
        return nodeId;
    }


    /**
     * Sets the nodeId value for this ManipulationBiomassNode.
     *
     * @param nodeId
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }


    /**
     * Gets the nodeIdx value for this ManipulationBiomassNode.
     *
     * @return nodeIdx
     */
    public Integer getNodeIdx() {
        return nodeIdx;
    }


    /**
     * Sets the nodeIdx value for this ManipulationBiomassNode.
     *
     * @param nodeIdx
     */
    public void setNodeIdx(Integer nodeIdx) {
        this.nodeIdx = nodeIdx;
    }


    /**
     * Gets the nodes value for this ManipulationBiomassNode.
     *
     * @return nodes
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes getNodes() {
        return nodes;
    }


    /**
     * Sets the nodes value for this ManipulationBiomassNode.
     *
     * @param nodes
     */
    public void setNodes(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes) {
        this.nodes = nodes;
    }


    /**
     * Gets the nodesReference value for this ManipulationBiomassNode.
     *
     * @return nodesReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal getNodesReference() {
        return nodesReference;
    }


    /**
     * Sets the nodesReference value for this ManipulationBiomassNode.
     *
     * @param nodesReference
     */
    public void setNodesReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal nodesReference) {
        this.nodesReference = nodesReference;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulationBiomassNode)) return false;
        ManipulationBiomassNode other = (ManipulationBiomassNode) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.biomassNodeProperty==null && other.getBiomassNodeProperty()==null) ||
             (this.biomassNodeProperty!=null &&
              java.util.Arrays.equals(this.biomassNodeProperty, other.getBiomassNodeProperty()))) &&
            ((this.createdDate==null && other.getCreatedDate()==null) ||
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.description==null && other.getDescription()==null) ||
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
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
            ((this.nodeId==null && other.getNodeId()==null) ||
             (this.nodeId!=null &&
              this.nodeId.equals(other.getNodeId()))) &&
            ((this.nodeIdx==null && other.getNodeIdx()==null) ||
             (this.nodeIdx!=null &&
              this.nodeIdx.equals(other.getNodeIdx()))) &&
            ((this.nodes==null && other.getNodes()==null) ||
             (this.nodes!=null &&
              this.nodes.equals(other.getNodes()))) &&
            ((this.nodesReference==null && other.getNodesReference()==null) ||
             (this.nodesReference!=null &&
              this.nodesReference.equals(other.getNodesReference())));
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
        if (getBiomassNodeProperty() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBiomassNodeProperty());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getBiomassNodeProperty(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
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
                Object obj = java.lang.reflect.Array.get(getManipulationTimesteps(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getNodeId() != null) {
            _hashCode += getNodeId().hashCode();
        }
        if (getNodeIdx() != null) {
            _hashCode += getNodeIdx().hashCode();
        }
        if (getNodes() != null) {
            _hashCode += getNodes().hashCode();
        }
        if (getNodesReference() != null) {
            _hashCode += getNodesReference().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationBiomassNode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationBiomassNode"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("biomassNodeProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "BiomassNodeProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "BiomassNodeProperty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "BiomassNodeProperty"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "CreatedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Description"));
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
        elemField.setFieldName("nodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Nodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Nodes"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodesReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodesReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfNodeshovVakal"));
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
