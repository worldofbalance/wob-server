/**
 * Nodes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Nodes  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private String commonName;

    private String critterId;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Critters critters;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCrittershovVakal crittersReference;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode[] manipulationBiomassNode;

    private Integer modelType;

    private String networkId;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal networksReference;

    private Integer nodeId;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.NodeParameters[] nodeParameters;

    private String TSN;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timeseries[] timeseries;

    private String id;

    public Nodes() {
    }

    public Nodes(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           String commonName,
           String critterId,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Critters critters,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCrittershovVakal crittersReference,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode[] manipulationBiomassNode,
           Integer modelType,
           String networkId,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal networksReference,
           Integer nodeId,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.NodeParameters[] nodeParameters,
           String TSN,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timeseries[] timeseries,
           String id) {
        super(
            _id,
            ref,
            entityKey);
        this.commonName = commonName;
        this.critterId = critterId;
        this.critters = critters;
        this.crittersReference = crittersReference;
        this.manipulationBiomassNode = manipulationBiomassNode;
        this.modelType = modelType;
        this.networkId = networkId;
        this.networks = networks;
        this.networksReference = networksReference;
        this.nodeId = nodeId;
        this.nodeParameters = nodeParameters;
        this.TSN = TSN;
        this.timeseries = timeseries;
        this.id = id;
    }


    /**
     * Gets the commonName value for this Nodes.
     *
     * @return commonName
     */
    public String getCommonName() {
        return commonName;
    }


    /**
     * Sets the commonName value for this Nodes.
     *
     * @param commonName
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }


    /**
     * Gets the critterId value for this Nodes.
     *
     * @return critterId
     */
    public String getCritterId() {
        return critterId;
    }


    /**
     * Sets the critterId value for this Nodes.
     *
     * @param critterId
     */
    public void setCritterId(String critterId) {
        this.critterId = critterId;
    }


    /**
     * Gets the critters value for this Nodes.
     *
     * @return critters
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Critters getCritters() {
        return critters;
    }


    /**
     * Sets the critters value for this Nodes.
     *
     * @param critters
     */
    public void setCritters(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Critters critters) {
        this.critters = critters;
    }


    /**
     * Gets the crittersReference value for this Nodes.
     *
     * @return crittersReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCrittershovVakal getCrittersReference() {
        return crittersReference;
    }


    /**
     * Sets the crittersReference value for this Nodes.
     *
     * @param crittersReference
     */
    public void setCrittersReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCrittershovVakal crittersReference) {
        this.crittersReference = crittersReference;
    }


    /**
     * Gets the manipulationBiomassNode value for this Nodes.
     *
     * @return manipulationBiomassNode
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode[] getManipulationBiomassNode() {
        return manipulationBiomassNode;
    }


    /**
     * Sets the manipulationBiomassNode value for this Nodes.
     *
     * @param manipulationBiomassNode
     */
    public void setManipulationBiomassNode(cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode[] manipulationBiomassNode) {
        this.manipulationBiomassNode = manipulationBiomassNode;
    }


    /**
     * Gets the modelType value for this Nodes.
     *
     * @return modelType
     */
    public Integer getModelType() {
        return modelType;
    }


    /**
     * Sets the modelType value for this Nodes.
     *
     * @param modelType
     */
    public void setModelType(Integer modelType) {
        this.modelType = modelType;
    }


    /**
     * Gets the networkId value for this Nodes.
     *
     * @return networkId
     */
    public String getNetworkId() {
        return networkId;
    }


    /**
     * Sets the networkId value for this Nodes.
     *
     * @param networkId
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }


    /**
     * Gets the networks value for this Nodes.
     *
     * @return networks
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks getNetworks() {
        return networks;
    }


    /**
     * Sets the networks value for this Nodes.
     *
     * @param networks
     */
    public void setNetworks(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks) {
        this.networks = networks;
    }


    /**
     * Gets the networksReference value for this Nodes.
     *
     * @return networksReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal getNetworksReference() {
        return networksReference;
    }


    /**
     * Sets the networksReference value for this Nodes.
     *
     * @param networksReference
     */
    public void setNetworksReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal networksReference) {
        this.networksReference = networksReference;
    }


    /**
     * Gets the nodeId value for this Nodes.
     *
     * @return nodeId
     */
    public Integer getNodeId() {
        return nodeId;
    }


    /**
     * Sets the nodeId value for this Nodes.
     *
     * @param nodeId
     */
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }


    /**
     * Gets the nodeParameters value for this Nodes.
     *
     * @return nodeParameters
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.NodeParameters[] getNodeParameters() {
        return nodeParameters;
    }


    /**
     * Sets the nodeParameters value for this Nodes.
     *
     * @param nodeParameters
     */
    public void setNodeParameters(cos.org.datacontract.schemas._2004._07.LINQ2Entities.NodeParameters[] nodeParameters) {
        this.nodeParameters = nodeParameters;
    }


    /**
     * Gets the TSN value for this Nodes.
     *
     * @return TSN
     */
    public String getTSN() {
        return TSN;
    }


    /**
     * Sets the TSN value for this Nodes.
     *
     * @param TSN
     */
    public void setTSN(String TSN) {
        this.TSN = TSN;
    }


    /**
     * Gets the timeseries value for this Nodes.
     *
     * @return timeseries
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timeseries[] getTimeseries() {
        return timeseries;
    }


    /**
     * Sets the timeseries value for this Nodes.
     *
     * @param timeseries
     */
    public void setTimeseries(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Timeseries[] timeseries) {
        this.timeseries = timeseries;
    }


    /**
     * Gets the id value for this Nodes.
     *
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this Nodes.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Nodes)) return false;
        Nodes other = (Nodes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.commonName==null && other.getCommonName()==null) ||
             (this.commonName!=null &&
              this.commonName.equals(other.getCommonName()))) &&
            ((this.critterId==null && other.getCritterId()==null) ||
             (this.critterId!=null &&
              this.critterId.equals(other.getCritterId()))) &&
            ((this.critters==null && other.getCritters()==null) ||
             (this.critters!=null &&
              this.critters.equals(other.getCritters()))) &&
            ((this.crittersReference==null && other.getCrittersReference()==null) ||
             (this.crittersReference!=null &&
              this.crittersReference.equals(other.getCrittersReference()))) &&
            ((this.manipulationBiomassNode==null && other.getManipulationBiomassNode()==null) ||
             (this.manipulationBiomassNode!=null &&
              java.util.Arrays.equals(this.manipulationBiomassNode, other.getManipulationBiomassNode()))) &&
            ((this.modelType==null && other.getModelType()==null) ||
             (this.modelType!=null &&
              this.modelType.equals(other.getModelType()))) &&
            ((this.networkId==null && other.getNetworkId()==null) ||
             (this.networkId!=null &&
              this.networkId.equals(other.getNetworkId()))) &&
            ((this.networks==null && other.getNetworks()==null) ||
             (this.networks!=null &&
              this.networks.equals(other.getNetworks()))) &&
            ((this.networksReference==null && other.getNetworksReference()==null) ||
             (this.networksReference!=null &&
              this.networksReference.equals(other.getNetworksReference()))) &&
            ((this.nodeId==null && other.getNodeId()==null) ||
             (this.nodeId!=null &&
              this.nodeId.equals(other.getNodeId()))) &&
            ((this.nodeParameters==null && other.getNodeParameters()==null) ||
             (this.nodeParameters!=null &&
              java.util.Arrays.equals(this.nodeParameters, other.getNodeParameters()))) &&
            ((this.TSN==null && other.getTSN()==null) ||
             (this.TSN!=null &&
              this.TSN.equals(other.getTSN()))) &&
            ((this.timeseries==null && other.getTimeseries()==null) ||
             (this.timeseries!=null &&
              java.util.Arrays.equals(this.timeseries, other.getTimeseries()))) &&
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
        if (getCommonName() != null) {
            _hashCode += getCommonName().hashCode();
        }
        if (getCritterId() != null) {
            _hashCode += getCritterId().hashCode();
        }
        if (getCritters() != null) {
            _hashCode += getCritters().hashCode();
        }
        if (getCrittersReference() != null) {
            _hashCode += getCrittersReference().hashCode();
        }
        if (getManipulationBiomassNode() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationBiomassNode());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getManipulationBiomassNode(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getModelType() != null) {
            _hashCode += getModelType().hashCode();
        }
        if (getNetworkId() != null) {
            _hashCode += getNetworkId().hashCode();
        }
        if (getNetworks() != null) {
            _hashCode += getNetworks().hashCode();
        }
        if (getNetworksReference() != null) {
            _hashCode += getNetworksReference().hashCode();
        }
        if (getNodeId() != null) {
            _hashCode += getNodeId().hashCode();
        }
        if (getNodeParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNodeParameters());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getNodeParameters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTSN() != null) {
            _hashCode += getTSN().hashCode();
        }
        if (getTimeseries() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTimeseries());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getTimeseries(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Nodes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Nodes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commonName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "CommonName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("critterId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "CritterId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("critters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Critters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Critters"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crittersReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "CrittersReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfCrittershovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationBiomassNode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationBiomassNode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationBiomassNode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationBiomassNode"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ModelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Networks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Networks"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networksReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworksReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfNetworkshovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeParameters"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeParameters"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TSN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "TSN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeseries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Timeseries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Timeseries"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Timeseries"));
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
