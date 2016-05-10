/**
 * NetworkCreationRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class NetworkCreationRequest  extends cos.org.datacontract.schemas._2004._07.WCFService_Portal.Request  implements java.io.Serializable {
    private Integer creationType;

    private java.util.Calendar date;

    private cos.org.datacontract.schemas._2004._07.ManipulationParameter.ModelParam[] modelParams;

    private int modelType;

    private String networkName;

    private int[] nodeList;

    private String originFoodweb;

    private Boolean overwrite;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    public NetworkCreationRequest() {
    }

    public NetworkCreationRequest(
           Integer creationType,
           java.util.Calendar date,
           cos.org.datacontract.schemas._2004._07.ManipulationParameter.ModelParam[] modelParams,
           int modelType,
           String networkName,
           int[] nodeList,
           String originFoodweb,
           Boolean overwrite,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.creationType = creationType;
        this.date = date;
        this.modelParams = modelParams;
        this.modelType = modelType;
        this.networkName = networkName;
        this.nodeList = nodeList;
        this.originFoodweb = originFoodweb;
        this.overwrite = overwrite;
        this.user = user;
    }


    /**
     * Gets the creationType value for this NetworkCreationRequest.
     *
     * @return creationType
     */
    public Integer getCreationType() {
        return creationType;
    }


    /**
     * Sets the creationType value for this NetworkCreationRequest.
     *
     * @param creationType
     */
    public void setCreationType(Integer creationType) {
        this.creationType = creationType;
    }


    /**
     * Gets the date value for this NetworkCreationRequest.
     *
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this NetworkCreationRequest.
     *
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the modelParams value for this NetworkCreationRequest.
     *
     * @return modelParams
     */
    public cos.org.datacontract.schemas._2004._07.ManipulationParameter.ModelParam[] getModelParams() {
        return modelParams;
    }


    /**
     * Sets the modelParams value for this NetworkCreationRequest.
     *
     * @param modelParams
     */
    public void setModelParams(cos.org.datacontract.schemas._2004._07.ManipulationParameter.ModelParam[] modelParams) {
        this.modelParams = modelParams;
    }


    /**
     * Gets the modelType value for this NetworkCreationRequest.
     *
     * @return modelType
     */
    public int getModelType() {
        return modelType;
    }


    /**
     * Sets the modelType value for this NetworkCreationRequest.
     *
     * @param modelType
     */
    public void setModelType(int modelType) {
        this.modelType = modelType;
    }


    /**
     * Gets the networkName value for this NetworkCreationRequest.
     *
     * @return networkName
     */
    public String getNetworkName() {
        return networkName;
    }


    /**
     * Sets the networkName value for this NetworkCreationRequest.
     *
     * @param networkName
     */
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }


    /**
     * Gets the nodeList value for this NetworkCreationRequest.
     *
     * @return nodeList
     */
    public int[] getNodeList() {
        return nodeList;
    }


    /**
     * Sets the nodeList value for this NetworkCreationRequest.
     *
     * @param nodeList
     */
    public void setNodeList(int[] nodeList) {
        this.nodeList = nodeList;
    }


    /**
     * Gets the originFoodweb value for this NetworkCreationRequest.
     *
     * @return originFoodweb
     */
    public String getOriginFoodweb() {
        return originFoodweb;
    }


    /**
     * Sets the originFoodweb value for this NetworkCreationRequest.
     *
     * @param originFoodweb
     */
    public void setOriginFoodweb(String originFoodweb) {
        this.originFoodweb = originFoodweb;
    }


    /**
     * Gets the overwrite value for this NetworkCreationRequest.
     *
     * @return overwrite
     */
    public Boolean getOverwrite() {
        return overwrite;
    }


    /**
     * Sets the overwrite value for this NetworkCreationRequest.
     *
     * @param overwrite
     */
    public void setOverwrite(Boolean overwrite) {
        this.overwrite = overwrite;
    }


    /**
     * Gets the user value for this NetworkCreationRequest.
     *
     * @return user
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this NetworkCreationRequest.
     *
     * @param user
     */
    public void setUser(cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof NetworkCreationRequest)) return false;
        NetworkCreationRequest other = (NetworkCreationRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.creationType==null && other.getCreationType()==null) ||
             (this.creationType!=null &&
              this.creationType.equals(other.getCreationType()))) &&
            ((this.date==null && other.getDate()==null) ||
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.modelParams==null && other.getModelParams()==null) ||
             (this.modelParams!=null &&
              java.util.Arrays.equals(this.modelParams, other.getModelParams()))) &&
            this.modelType == other.getModelType() &&
            ((this.networkName==null && other.getNetworkName()==null) ||
             (this.networkName!=null &&
              this.networkName.equals(other.getNetworkName()))) &&
            ((this.nodeList==null && other.getNodeList()==null) ||
             (this.nodeList!=null &&
              java.util.Arrays.equals(this.nodeList, other.getNodeList()))) &&
            ((this.originFoodweb==null && other.getOriginFoodweb()==null) ||
             (this.originFoodweb!=null &&
              this.originFoodweb.equals(other.getOriginFoodweb()))) &&
            ((this.overwrite==null && other.getOverwrite()==null) ||
             (this.overwrite!=null &&
              this.overwrite.equals(other.getOverwrite()))) &&
            ((this.user==null && other.getUser()==null) ||
             (this.user!=null &&
              this.user.equals(other.getUser())));
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
        if (getCreationType() != null) {
            _hashCode += getCreationType().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getModelParams() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getModelParams());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getModelParams(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getModelType();
        if (getNetworkName() != null) {
            _hashCode += getNetworkName().hashCode();
        }
        if (getNodeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNodeList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getNodeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOriginFoodweb() != null) {
            _hashCode += getOriginFoodweb().hashCode();
        }
        if (getOverwrite() != null) {
            _hashCode += getOverwrite().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NetworkCreationRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkCreationRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "CreationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelParams");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ModelParams"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ModelParam"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ModelParam"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ModelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originFoodweb");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "OriginFoodweb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("overwrite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Overwrite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
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
