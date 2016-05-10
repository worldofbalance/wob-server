/**
 * Links.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Links  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.LinkParameters[] linkParameters;

    private String networkId;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal networksReference;

    private Integer predatorID;

    private Integer preyID;

    private String id;

    public Links() {
    }

    public Links(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.LinkParameters[] linkParameters,
           String networkId,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal networksReference,
           Integer predatorID,
           Integer preyID,
           String id) {
        super(
            _id,
            ref,
            entityKey);
        this.linkParameters = linkParameters;
        this.networkId = networkId;
        this.networks = networks;
        this.networksReference = networksReference;
        this.predatorID = predatorID;
        this.preyID = preyID;
        this.id = id;
    }


    /**
     * Gets the linkParameters value for this Links.
     *
     * @return linkParameters
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.LinkParameters[] getLinkParameters() {
        return linkParameters;
    }


    /**
     * Sets the linkParameters value for this Links.
     *
     * @param linkParameters
     */
    public void setLinkParameters(cos.org.datacontract.schemas._2004._07.LINQ2Entities.LinkParameters[] linkParameters) {
        this.linkParameters = linkParameters;
    }


    /**
     * Gets the networkId value for this Links.
     *
     * @return networkId
     */
    public String getNetworkId() {
        return networkId;
    }


    /**
     * Sets the networkId value for this Links.
     *
     * @param networkId
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }


    /**
     * Gets the networks value for this Links.
     *
     * @return networks
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks getNetworks() {
        return networks;
    }


    /**
     * Sets the networks value for this Links.
     *
     * @param networks
     */
    public void setNetworks(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks) {
        this.networks = networks;
    }


    /**
     * Gets the networksReference value for this Links.
     *
     * @return networksReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal getNetworksReference() {
        return networksReference;
    }


    /**
     * Sets the networksReference value for this Links.
     *
     * @param networksReference
     */
    public void setNetworksReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetworkshovVakal networksReference) {
        this.networksReference = networksReference;
    }


    /**
     * Gets the predatorID value for this Links.
     *
     * @return predatorID
     */
    public Integer getPredatorID() {
        return predatorID;
    }


    /**
     * Sets the predatorID value for this Links.
     *
     * @param predatorID
     */
    public void setPredatorID(Integer predatorID) {
        this.predatorID = predatorID;
    }


    /**
     * Gets the preyID value for this Links.
     *
     * @return preyID
     */
    public Integer getPreyID() {
        return preyID;
    }


    /**
     * Sets the preyID value for this Links.
     *
     * @param preyID
     */
    public void setPreyID(Integer preyID) {
        this.preyID = preyID;
    }


    /**
     * Gets the id value for this Links.
     *
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this Links.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Links)) return false;
        Links other = (Links) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.linkParameters==null && other.getLinkParameters()==null) ||
             (this.linkParameters!=null &&
              java.util.Arrays.equals(this.linkParameters, other.getLinkParameters()))) &&
            ((this.networkId==null && other.getNetworkId()==null) ||
             (this.networkId!=null &&
              this.networkId.equals(other.getNetworkId()))) &&
            ((this.networks==null && other.getNetworks()==null) ||
             (this.networks!=null &&
              this.networks.equals(other.getNetworks()))) &&
            ((this.networksReference==null && other.getNetworksReference()==null) ||
             (this.networksReference!=null &&
              this.networksReference.equals(other.getNetworksReference()))) &&
            ((this.predatorID==null && other.getPredatorID()==null) ||
             (this.predatorID!=null &&
              this.predatorID.equals(other.getPredatorID()))) &&
            ((this.preyID==null && other.getPreyID()==null) ||
             (this.preyID!=null &&
              this.preyID.equals(other.getPreyID()))) &&
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
        if (getLinkParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLinkParameters());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getLinkParameters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        if (getPredatorID() != null) {
            _hashCode += getPredatorID().hashCode();
        }
        if (getPreyID() != null) {
            _hashCode += getPreyID().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Links.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Links"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LinkParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LinkParameters"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LinkParameters"));
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
        elemField.setFieldName("predatorID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "PredatorID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preyID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "PreyID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
