/**
 * ManipulationNetwork.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class ManipulationNetwork  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private String id;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal networksReference;

    private java.util.Calendar createdDate;

    private String manipulationId;

    private Integer manipulationType;

    private String networkId;

    private String username;

    public ManipulationNetwork() {
    }

    public ManipulationNetwork(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           String id,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal networksReference,
           java.util.Calendar createdDate,
           String manipulationId,
           Integer manipulationType,
           String networkId,
           String username) {
        super(
            _id,
            ref,
            entityKey);
        this.id = id;
        this.manipulation = manipulation;
        this.manipulationReference = manipulationReference;
        this.networks = networks;
        this.networksReference = networksReference;
        this.createdDate = createdDate;
        this.manipulationId = manipulationId;
        this.manipulationType = manipulationType;
        this.networkId = networkId;
        this.username = username;
    }


    /**
     * Gets the id value for this ManipulationNetwork.
     *
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this ManipulationNetwork.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets the manipulation value for this ManipulationNetwork.
     *
     * @return manipulation
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation getManipulation() {
        return manipulation;
    }


    /**
     * Sets the manipulation value for this ManipulationNetwork.
     *
     * @param manipulation
     */
    public void setManipulation(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation manipulation) {
        this.manipulation = manipulation;
    }


    /**
     * Gets the manipulationReference value for this ManipulationNetwork.
     *
     * @return manipulationReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal getManipulationReference() {
        return manipulationReference;
    }


    /**
     * Sets the manipulationReference value for this ManipulationNetwork.
     *
     * @param manipulationReference
     */
    public void setManipulationReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationhovVakal manipulationReference) {
        this.manipulationReference = manipulationReference;
    }


    /**
     * Gets the networks value for this ManipulationNetwork.
     *
     * @return networks
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks getNetworks() {
        return networks;
    }


    /**
     * Sets the networks value for this ManipulationNetwork.
     *
     * @param networks
     */
    public void setNetworks(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks) {
        this.networks = networks;
    }


    /**
     * Gets the networksReference value for this ManipulationNetwork.
     *
     * @return networksReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal getNetworksReference() {
        return networksReference;
    }


    /**
     * Sets the networksReference value for this ManipulationNetwork.
     *
     * @param networksReference
     */
    public void setNetworksReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal networksReference) {
        this.networksReference = networksReference;
    }


    /**
     * Gets the createdDate value for this ManipulationNetwork.
     *
     * @return createdDate
     */
    public java.util.Calendar getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this ManipulationNetwork.
     *
     * @param createdDate
     */
    public void setCreatedDate(java.util.Calendar createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the manipulationId value for this ManipulationNetwork.
     *
     * @return manipulationId
     */
    public String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationNetwork.
     *
     * @param manipulationId
     */
    public void setManipulationId(String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the manipulationType value for this ManipulationNetwork.
     *
     * @return manipulationType
     */
    public Integer getManipulationType() {
        return manipulationType;
    }


    /**
     * Sets the manipulationType value for this ManipulationNetwork.
     *
     * @param manipulationType
     */
    public void setManipulationType(Integer manipulationType) {
        this.manipulationType = manipulationType;
    }


    /**
     * Gets the networkId value for this ManipulationNetwork.
     *
     * @return networkId
     */
    public String getNetworkId() {
        return networkId;
    }


    /**
     * Sets the networkId value for this ManipulationNetwork.
     *
     * @param networkId
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }


    /**
     * Gets the username value for this ManipulationNetwork.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this ManipulationNetwork.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulationNetwork)) return false;
        ManipulationNetwork other = (ManipulationNetwork) obj;
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
            ((this.manipulation==null && other.getManipulation()==null) ||
             (this.manipulation!=null &&
              this.manipulation.equals(other.getManipulation()))) &&
            ((this.manipulationReference==null && other.getManipulationReference()==null) ||
             (this.manipulationReference!=null &&
              this.manipulationReference.equals(other.getManipulationReference()))) &&
            ((this.networks==null && other.getNetworks()==null) ||
             (this.networks!=null &&
              this.networks.equals(other.getNetworks()))) &&
            ((this.networksReference==null && other.getNetworksReference()==null) ||
             (this.networksReference!=null &&
              this.networksReference.equals(other.getNetworksReference()))) &&
            ((this.createdDate==null && other.getCreatedDate()==null) ||
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.manipulationId==null && other.getManipulationId()==null) ||
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            ((this.manipulationType==null && other.getManipulationType()==null) ||
             (this.manipulationType!=null &&
              this.manipulationType.equals(other.getManipulationType()))) &&
            ((this.networkId==null && other.getNetworkId()==null) ||
             (this.networkId!=null &&
              this.networkId.equals(other.getNetworkId()))) &&
            ((this.username==null && other.getUsername()==null) ||
             (this.username!=null &&
              this.username.equals(other.getUsername())));
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
        if (getManipulation() != null) {
            _hashCode += getManipulation().hashCode();
        }
        if (getManipulationReference() != null) {
            _hashCode += getManipulationReference().hashCode();
        }
        if (getNetworks() != null) {
            _hashCode += getNetworks().hashCode();
        }
        if (getNetworksReference() != null) {
            _hashCode += getNetworksReference().hashCode();
        }
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        if (getManipulationType() != null) {
            _hashCode += getManipulationType().hashCode();
        }
        if (getNetworkId() != null) {
            _hashCode += getNetworkId().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationNetwork.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationNetwork"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("manipulationReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfManipulationhovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
        elemField.setFieldName("createdDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "createdDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "manipulationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "manipulationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "networkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
