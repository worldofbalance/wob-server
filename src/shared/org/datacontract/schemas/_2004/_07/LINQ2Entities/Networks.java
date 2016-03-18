/**
 * Networks.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Networks  extends shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.util.Calendar creationDate;

    private java.lang.Long download;

    private java.lang.Integer foodwebType;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.Links[] links;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationNetwork[] manipulationNetwork;

    private java.lang.String manipulationNetworkId;

    private java.lang.Integer modelType;

    private java.util.Calendar modifiedDate;

    private java.lang.String name;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkACL[] networkACL;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkParameters[] networkParameters;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes[] nodes;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.UserVote[] userVote;

    private java.lang.String version;

    private java.lang.String id;

    public Networks() {
    }

    public Networks(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           shared.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.util.Calendar creationDate,
           java.lang.Long download,
           java.lang.Integer foodwebType,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.Links[] links,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationNetwork[] manipulationNetwork,
           java.lang.String manipulationNetworkId,
           java.lang.Integer modelType,
           java.util.Calendar modifiedDate,
           java.lang.String name,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkACL[] networkACL,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkParameters[] networkParameters,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes[] nodes,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.UserVote[] userVote,
           java.lang.String version,
           java.lang.String id) {
        super(
            _id,
            ref,
            entityKey);
        this.creationDate = creationDate;
        this.download = download;
        this.foodwebType = foodwebType;
        this.links = links;
        this.manipulationNetwork = manipulationNetwork;
        this.manipulationNetworkId = manipulationNetworkId;
        this.modelType = modelType;
        this.modifiedDate = modifiedDate;
        this.name = name;
        this.networkACL = networkACL;
        this.networkParameters = networkParameters;
        this.nodes = nodes;
        this.userVote = userVote;
        this.version = version;
        this.id = id;
    }


    /**
     * Gets the creationDate value for this Networks.
     * 
     * @return creationDate
     */
    public java.util.Calendar getCreationDate() {
        return creationDate;
    }


    /**
     * Sets the creationDate value for this Networks.
     * 
     * @param creationDate
     */
    public void setCreationDate(java.util.Calendar creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Gets the download value for this Networks.
     * 
     * @return download
     */
    public java.lang.Long getDownload() {
        return download;
    }


    /**
     * Sets the download value for this Networks.
     * 
     * @param download
     */
    public void setDownload(java.lang.Long download) {
        this.download = download;
    }


    /**
     * Gets the foodwebType value for this Networks.
     * 
     * @return foodwebType
     */
    public java.lang.Integer getFoodwebType() {
        return foodwebType;
    }


    /**
     * Sets the foodwebType value for this Networks.
     * 
     * @param foodwebType
     */
    public void setFoodwebType(java.lang.Integer foodwebType) {
        this.foodwebType = foodwebType;
    }


    /**
     * Gets the links value for this Networks.
     * 
     * @return links
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.Links[] getLinks() {
        return links;
    }


    /**
     * Sets the links value for this Networks.
     * 
     * @param links
     */
    public void setLinks(shared.org.datacontract.schemas._2004._07.LINQ2Entities.Links[] links) {
        this.links = links;
    }


    /**
     * Gets the manipulationNetwork value for this Networks.
     * 
     * @return manipulationNetwork
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationNetwork[] getManipulationNetwork() {
        return manipulationNetwork;
    }


    /**
     * Sets the manipulationNetwork value for this Networks.
     * 
     * @param manipulationNetwork
     */
    public void setManipulationNetwork(shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationNetwork[] manipulationNetwork) {
        this.manipulationNetwork = manipulationNetwork;
    }


    /**
     * Gets the manipulationNetworkId value for this Networks.
     * 
     * @return manipulationNetworkId
     */
    public java.lang.String getManipulationNetworkId() {
        return manipulationNetworkId;
    }


    /**
     * Sets the manipulationNetworkId value for this Networks.
     * 
     * @param manipulationNetworkId
     */
    public void setManipulationNetworkId(java.lang.String manipulationNetworkId) {
        this.manipulationNetworkId = manipulationNetworkId;
    }


    /**
     * Gets the modelType value for this Networks.
     * 
     * @return modelType
     */
    public java.lang.Integer getModelType() {
        return modelType;
    }


    /**
     * Sets the modelType value for this Networks.
     * 
     * @param modelType
     */
    public void setModelType(java.lang.Integer modelType) {
        this.modelType = modelType;
    }


    /**
     * Gets the modifiedDate value for this Networks.
     * 
     * @return modifiedDate
     */
    public java.util.Calendar getModifiedDate() {
        return modifiedDate;
    }


    /**
     * Sets the modifiedDate value for this Networks.
     * 
     * @param modifiedDate
     */
    public void setModifiedDate(java.util.Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    /**
     * Gets the name value for this Networks.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Networks.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the networkACL value for this Networks.
     * 
     * @return networkACL
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkACL[] getNetworkACL() {
        return networkACL;
    }


    /**
     * Sets the networkACL value for this Networks.
     * 
     * @param networkACL
     */
    public void setNetworkACL(shared.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkACL[] networkACL) {
        this.networkACL = networkACL;
    }


    /**
     * Gets the networkParameters value for this Networks.
     * 
     * @return networkParameters
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkParameters[] getNetworkParameters() {
        return networkParameters;
    }


    /**
     * Sets the networkParameters value for this Networks.
     * 
     * @param networkParameters
     */
    public void setNetworkParameters(shared.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkParameters[] networkParameters) {
        this.networkParameters = networkParameters;
    }


    /**
     * Gets the nodes value for this Networks.
     * 
     * @return nodes
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes[] getNodes() {
        return nodes;
    }


    /**
     * Sets the nodes value for this Networks.
     * 
     * @param nodes
     */
    public void setNodes(shared.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes[] nodes) {
        this.nodes = nodes;
    }


    /**
     * Gets the userVote value for this Networks.
     * 
     * @return userVote
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.UserVote[] getUserVote() {
        return userVote;
    }


    /**
     * Sets the userVote value for this Networks.
     * 
     * @param userVote
     */
    public void setUserVote(shared.org.datacontract.schemas._2004._07.LINQ2Entities.UserVote[] userVote) {
        this.userVote = userVote;
    }


    /**
     * Gets the version value for this Networks.
     * 
     * @return version
     */
    public java.lang.String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this Networks.
     * 
     * @param version
     */
    public void setVersion(java.lang.String version) {
        this.version = version;
    }


    /**
     * Gets the id value for this Networks.
     * 
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this Networks.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Networks)) return false;
        Networks other = (Networks) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.creationDate==null && other.getCreationDate()==null) || 
             (this.creationDate!=null &&
              this.creationDate.equals(other.getCreationDate()))) &&
            ((this.download==null && other.getDownload()==null) || 
             (this.download!=null &&
              this.download.equals(other.getDownload()))) &&
            ((this.foodwebType==null && other.getFoodwebType()==null) || 
             (this.foodwebType!=null &&
              this.foodwebType.equals(other.getFoodwebType()))) &&
            ((this.links==null && other.getLinks()==null) || 
             (this.links!=null &&
              java.util.Arrays.equals(this.links, other.getLinks()))) &&
            ((this.manipulationNetwork==null && other.getManipulationNetwork()==null) || 
             (this.manipulationNetwork!=null &&
              java.util.Arrays.equals(this.manipulationNetwork, other.getManipulationNetwork()))) &&
            ((this.manipulationNetworkId==null && other.getManipulationNetworkId()==null) || 
             (this.manipulationNetworkId!=null &&
              this.manipulationNetworkId.equals(other.getManipulationNetworkId()))) &&
            ((this.modelType==null && other.getModelType()==null) || 
             (this.modelType!=null &&
              this.modelType.equals(other.getModelType()))) &&
            ((this.modifiedDate==null && other.getModifiedDate()==null) || 
             (this.modifiedDate!=null &&
              this.modifiedDate.equals(other.getModifiedDate()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.networkACL==null && other.getNetworkACL()==null) || 
             (this.networkACL!=null &&
              java.util.Arrays.equals(this.networkACL, other.getNetworkACL()))) &&
            ((this.networkParameters==null && other.getNetworkParameters()==null) || 
             (this.networkParameters!=null &&
              java.util.Arrays.equals(this.networkParameters, other.getNetworkParameters()))) &&
            ((this.nodes==null && other.getNodes()==null) || 
             (this.nodes!=null &&
              java.util.Arrays.equals(this.nodes, other.getNodes()))) &&
            ((this.userVote==null && other.getUserVote()==null) || 
             (this.userVote!=null &&
              java.util.Arrays.equals(this.userVote, other.getUserVote()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion()))) &&
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
        if (getCreationDate() != null) {
            _hashCode += getCreationDate().hashCode();
        }
        if (getDownload() != null) {
            _hashCode += getDownload().hashCode();
        }
        if (getFoodwebType() != null) {
            _hashCode += getFoodwebType().hashCode();
        }
        if (getLinks() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLinks());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLinks(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getManipulationNetwork() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationNetwork());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManipulationNetwork(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getManipulationNetworkId() != null) {
            _hashCode += getManipulationNetworkId().hashCode();
        }
        if (getModelType() != null) {
            _hashCode += getModelType().hashCode();
        }
        if (getModifiedDate() != null) {
            _hashCode += getModifiedDate().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getNetworkACL() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNetworkACL());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNetworkACL(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNetworkParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNetworkParameters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNetworkParameters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNodes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNodes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUserVote() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUserVote());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUserVote(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Networks.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Networks"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "CreationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("download");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Download"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foodwebType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "FoodwebType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("links");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Links"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Links"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Links"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationNetwork");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationNetwork"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationNetwork"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationNetwork"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationNetworkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationNetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ModelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ModifiedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("networkACL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworkACL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworkACL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworkACL"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworkParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworkParameters"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworkParameters"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Nodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Nodes"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Nodes"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userVote");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserVote"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserVote"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserVote"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Version"));
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
