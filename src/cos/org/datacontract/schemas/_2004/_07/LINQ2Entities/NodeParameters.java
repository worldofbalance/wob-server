/**
 * NodeParameters.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class NodeParameters  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private String nodeId;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal nodesReference;

    private String propertyName;

    private Float propertyValue;

    private String id;

    public NodeParameters() {
    }

    public NodeParameters(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           String nodeId,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal nodesReference,
           String propertyName,
           Float propertyValue,
           String id) {
        super(
            _id,
            ref,
            entityKey);
        this.nodeId = nodeId;
        this.nodes = nodes;
        this.nodesReference = nodesReference;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.id = id;
    }


    /**
     * Gets the nodeId value for this NodeParameters.
     *
     * @return nodeId
     */
    public String getNodeId() {
        return nodeId;
    }


    /**
     * Sets the nodeId value for this NodeParameters.
     *
     * @param nodeId
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }


    /**
     * Gets the nodes value for this NodeParameters.
     *
     * @return nodes
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes getNodes() {
        return nodes;
    }


    /**
     * Sets the nodes value for this NodeParameters.
     *
     * @param nodes
     */
    public void setNodes(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes) {
        this.nodes = nodes;
    }


    /**
     * Gets the nodesReference value for this NodeParameters.
     *
     * @return nodesReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal getNodesReference() {
        return nodesReference;
    }


    /**
     * Sets the nodesReference value for this NodeParameters.
     *
     * @param nodesReference
     */
    public void setNodesReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal nodesReference) {
        this.nodesReference = nodesReference;
    }


    /**
     * Gets the propertyName value for this NodeParameters.
     *
     * @return propertyName
     */
    public String getPropertyName() {
        return propertyName;
    }


    /**
     * Sets the propertyName value for this NodeParameters.
     *
     * @param propertyName
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }


    /**
     * Gets the propertyValue value for this NodeParameters.
     *
     * @return propertyValue
     */
    public Float getPropertyValue() {
        return propertyValue;
    }


    /**
     * Sets the propertyValue value for this NodeParameters.
     *
     * @param propertyValue
     */
    public void setPropertyValue(Float propertyValue) {
        this.propertyValue = propertyValue;
    }


    /**
     * Gets the id value for this NodeParameters.
     *
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this NodeParameters.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof NodeParameters)) return false;
        NodeParameters other = (NodeParameters) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.nodeId==null && other.getNodeId()==null) ||
             (this.nodeId!=null &&
              this.nodeId.equals(other.getNodeId()))) &&
            ((this.nodes==null && other.getNodes()==null) ||
             (this.nodes!=null &&
              this.nodes.equals(other.getNodes()))) &&
            ((this.nodesReference==null && other.getNodesReference()==null) ||
             (this.nodesReference!=null &&
              this.nodesReference.equals(other.getNodesReference()))) &&
            ((this.propertyName==null && other.getPropertyName()==null) ||
             (this.propertyName!=null &&
              this.propertyName.equals(other.getPropertyName()))) &&
            ((this.propertyValue==null && other.getPropertyValue()==null) ||
             (this.propertyValue!=null &&
              this.propertyValue.equals(other.getPropertyValue()))) &&
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
        if (getNodeId() != null) {
            _hashCode += getNodeId().hashCode();
        }
        if (getNodes() != null) {
            _hashCode += getNodes().hashCode();
        }
        if (getNodesReference() != null) {
            _hashCode += getNodesReference().hashCode();
        }
        if (getPropertyName() != null) {
            _hashCode += getPropertyName().hashCode();
        }
        if (getPropertyValue() != null) {
            _hashCode += getPropertyValue().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NodeParameters.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeParameters"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
