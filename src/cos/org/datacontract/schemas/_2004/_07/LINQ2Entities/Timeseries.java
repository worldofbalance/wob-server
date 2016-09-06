/**
 * Timeseries.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Timeseries  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private Float deltaT;

    private String description;

    private Integer length;

    private String name;

    private String nodeId;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal nodesReference;

    private java.util.Calendar samplingDate;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timesteps[] timesteps;

    private String type;

    private String id;

    public Timeseries() {
    }

    public Timeseries(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           Float deltaT,
           String description,
           Integer length,
           String name,
           String nodeId,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal nodesReference,
           java.util.Calendar samplingDate,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timesteps[] timesteps,
           String type,
           String id) {
        super(
            _id,
            ref,
            entityKey);
        this.deltaT = deltaT;
        this.description = description;
        this.length = length;
        this.name = name;
        this.nodeId = nodeId;
        this.nodes = nodes;
        this.nodesReference = nodesReference;
        this.samplingDate = samplingDate;
        this.timesteps = timesteps;
        this.type = type;
        this.id = id;
    }


    /**
     * Gets the deltaT value for this Timeseries.
     *
     * @return deltaT
     */
    public Float getDeltaT() {
        return deltaT;
    }


    /**
     * Sets the deltaT value for this Timeseries.
     *
     * @param deltaT
     */
    public void setDeltaT(Float deltaT) {
        this.deltaT = deltaT;
    }


    /**
     * Gets the description value for this Timeseries.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Timeseries.
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the length value for this Timeseries.
     *
     * @return length
     */
    public Integer getLength() {
        return length;
    }


    /**
     * Sets the length value for this Timeseries.
     *
     * @param length
     */
    public void setLength(Integer length) {
        this.length = length;
    }


    /**
     * Gets the name value for this Timeseries.
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this Timeseries.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the nodeId value for this Timeseries.
     *
     * @return nodeId
     */
    public String getNodeId() {
        return nodeId;
    }


    /**
     * Sets the nodeId value for this Timeseries.
     *
     * @param nodeId
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }


    /**
     * Gets the nodes value for this Timeseries.
     *
     * @return nodes
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes getNodes() {
        return nodes;
    }


    /**
     * Sets the nodes value for this Timeseries.
     *
     * @param nodes
     */
    public void setNodes(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes) {
        this.nodes = nodes;
    }


    /**
     * Gets the nodesReference value for this Timeseries.
     *
     * @return nodesReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal getNodesReference() {
        return nodesReference;
    }


    /**
     * Sets the nodesReference value for this Timeseries.
     *
     * @param nodesReference
     */
    public void setNodesReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNodeshovVakal nodesReference) {
        this.nodesReference = nodesReference;
    }


    /**
     * Gets the samplingDate value for this Timeseries.
     *
     * @return samplingDate
     */
    public java.util.Calendar getSamplingDate() {
        return samplingDate;
    }


    /**
     * Sets the samplingDate value for this Timeseries.
     *
     * @param samplingDate
     */
    public void setSamplingDate(java.util.Calendar samplingDate) {
        this.samplingDate = samplingDate;
    }


    /**
     * Gets the timesteps value for this Timeseries.
     *
     * @return timesteps
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timesteps[] getTimesteps() {
        return timesteps;
    }


    /**
     * Sets the timesteps value for this Timeseries.
     *
     * @param timesteps
     */
    public void setTimesteps(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timesteps[] timesteps) {
        this.timesteps = timesteps;
    }


    /**
     * Gets the type value for this Timeseries.
     *
     * @return type
     */
    public String getType() {
        return type;
    }


    /**
     * Sets the type value for this Timeseries.
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Gets the id value for this Timeseries.
     *
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this Timeseries.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Timeseries)) return false;
        Timeseries other = (Timeseries) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.deltaT==null && other.getDeltaT()==null) ||
             (this.deltaT!=null &&
              this.deltaT.equals(other.getDeltaT()))) &&
            ((this.description==null && other.getDescription()==null) ||
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.length==null && other.getLength()==null) ||
             (this.length!=null &&
              this.length.equals(other.getLength()))) &&
            ((this.name==null && other.getName()==null) ||
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.nodeId==null && other.getNodeId()==null) ||
             (this.nodeId!=null &&
              this.nodeId.equals(other.getNodeId()))) &&
            ((this.nodes==null && other.getNodes()==null) ||
             (this.nodes!=null &&
              this.nodes.equals(other.getNodes()))) &&
            ((this.nodesReference==null && other.getNodesReference()==null) ||
             (this.nodesReference!=null &&
              this.nodesReference.equals(other.getNodesReference()))) &&
            ((this.samplingDate==null && other.getSamplingDate()==null) ||
             (this.samplingDate!=null &&
              this.samplingDate.equals(other.getSamplingDate()))) &&
            ((this.timesteps==null && other.getTimesteps()==null) ||
             (this.timesteps!=null &&
              java.util.Arrays.equals(this.timesteps, other.getTimesteps()))) &&
            ((this.type==null && other.getType()==null) ||
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
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
        if (getDeltaT() != null) {
            _hashCode += getDeltaT().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getLength() != null) {
            _hashCode += getLength().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getNodeId() != null) {
            _hashCode += getNodeId().hashCode();
        }
        if (getNodes() != null) {
            _hashCode += getNodes().hashCode();
        }
        if (getNodesReference() != null) {
            _hashCode += getNodesReference().hashCode();
        }
        if (getSamplingDate() != null) {
            _hashCode += getSamplingDate().hashCode();
        }
        if (getTimesteps() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTimesteps());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getTimesteps(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Timeseries.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Timeseries"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deltaT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "DeltaT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
        elemField.setFieldName("length");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Length"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
        elemField.setFieldName("samplingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "SamplingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timesteps");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Timesteps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Timesteps"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Timesteps"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
