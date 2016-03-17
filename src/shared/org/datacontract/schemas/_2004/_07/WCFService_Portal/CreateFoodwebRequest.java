/**
 * CreateFoodwebRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.WCFService_Portal;

public class CreateFoodwebRequest  extends shared.org.datacontract.schemas._2004._07.WCFService_Portal.Request  implements java.io.Serializable {
    private java.util.Calendar date;

    private shared.org.datacontract.schemas._2004._07.WCFService_Portal.TableLoaderInfo linkTableLoaderInfo;

    private shared.org.datacontract.schemas._2004._07.ManipulationParameter.ModelParam[] modelParams;

    private int modelType;

    private java.lang.String networkName;

    private shared.org.datacontract.schemas._2004._07.WCFService_Portal.TableLoaderInfo nodeTableLoaderInfo;

    private java.lang.Boolean overwrite;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    private boolean isModelWeb;

    public CreateFoodwebRequest() {
    }

    public CreateFoodwebRequest(
           java.util.Calendar date,
           shared.org.datacontract.schemas._2004._07.WCFService_Portal.TableLoaderInfo linkTableLoaderInfo,
           shared.org.datacontract.schemas._2004._07.ManipulationParameter.ModelParam[] modelParams,
           int modelType,
           java.lang.String networkName,
           shared.org.datacontract.schemas._2004._07.WCFService_Portal.TableLoaderInfo nodeTableLoaderInfo,
           java.lang.Boolean overwrite,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user,
           boolean isModelWeb) {
        this.date = date;
        this.linkTableLoaderInfo = linkTableLoaderInfo;
        this.modelParams = modelParams;
        this.modelType = modelType;
        this.networkName = networkName;
        this.nodeTableLoaderInfo = nodeTableLoaderInfo;
        this.overwrite = overwrite;
        this.user = user;
        this.isModelWeb = isModelWeb;
    }


    /**
     * Gets the date value for this CreateFoodwebRequest.
     * 
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this CreateFoodwebRequest.
     * 
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the linkTableLoaderInfo value for this CreateFoodwebRequest.
     * 
     * @return linkTableLoaderInfo
     */
    public shared.org.datacontract.schemas._2004._07.WCFService_Portal.TableLoaderInfo getLinkTableLoaderInfo() {
        return linkTableLoaderInfo;
    }


    /**
     * Sets the linkTableLoaderInfo value for this CreateFoodwebRequest.
     * 
     * @param linkTableLoaderInfo
     */
    public void setLinkTableLoaderInfo(shared.org.datacontract.schemas._2004._07.WCFService_Portal.TableLoaderInfo linkTableLoaderInfo) {
        this.linkTableLoaderInfo = linkTableLoaderInfo;
    }


    /**
     * Gets the modelParams value for this CreateFoodwebRequest.
     * 
     * @return modelParams
     */
    public shared.org.datacontract.schemas._2004._07.ManipulationParameter.ModelParam[] getModelParams() {
        return modelParams;
    }


    /**
     * Sets the modelParams value for this CreateFoodwebRequest.
     * 
     * @param modelParams
     */
    public void setModelParams(shared.org.datacontract.schemas._2004._07.ManipulationParameter.ModelParam[] modelParams) {
        this.modelParams = modelParams;
    }


    /**
     * Gets the modelType value for this CreateFoodwebRequest.
     * 
     * @return modelType
     */
    public int getModelType() {
        return modelType;
    }


    /**
     * Sets the modelType value for this CreateFoodwebRequest.
     * 
     * @param modelType
     */
    public void setModelType(int modelType) {
        this.modelType = modelType;
    }


    /**
     * Gets the networkName value for this CreateFoodwebRequest.
     * 
     * @return networkName
     */
    public java.lang.String getNetworkName() {
        return networkName;
    }


    /**
     * Sets the networkName value for this CreateFoodwebRequest.
     * 
     * @param networkName
     */
    public void setNetworkName(java.lang.String networkName) {
        this.networkName = networkName;
    }


    /**
     * Gets the nodeTableLoaderInfo value for this CreateFoodwebRequest.
     * 
     * @return nodeTableLoaderInfo
     */
    public shared.org.datacontract.schemas._2004._07.WCFService_Portal.TableLoaderInfo getNodeTableLoaderInfo() {
        return nodeTableLoaderInfo;
    }


    /**
     * Sets the nodeTableLoaderInfo value for this CreateFoodwebRequest.
     * 
     * @param nodeTableLoaderInfo
     */
    public void setNodeTableLoaderInfo(shared.org.datacontract.schemas._2004._07.WCFService_Portal.TableLoaderInfo nodeTableLoaderInfo) {
        this.nodeTableLoaderInfo = nodeTableLoaderInfo;
    }


    /**
     * Gets the overwrite value for this CreateFoodwebRequest.
     * 
     * @return overwrite
     */
    public java.lang.Boolean getOverwrite() {
        return overwrite;
    }


    /**
     * Sets the overwrite value for this CreateFoodwebRequest.
     * 
     * @param overwrite
     */
    public void setOverwrite(java.lang.Boolean overwrite) {
        this.overwrite = overwrite;
    }


    /**
     * Gets the user value for this CreateFoodwebRequest.
     * 
     * @return user
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this CreateFoodwebRequest.
     * 
     * @param user
     */
    public void setUser(shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }


    /**
     * Gets the isModelWeb value for this CreateFoodwebRequest.
     * 
     * @return isModelWeb
     */
    public boolean isIsModelWeb() {
        return isModelWeb;
    }


    /**
     * Sets the isModelWeb value for this CreateFoodwebRequest.
     * 
     * @param isModelWeb
     */
    public void setIsModelWeb(boolean isModelWeb) {
        this.isModelWeb = isModelWeb;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateFoodwebRequest)) return false;
        CreateFoodwebRequest other = (CreateFoodwebRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.linkTableLoaderInfo==null && other.getLinkTableLoaderInfo()==null) || 
             (this.linkTableLoaderInfo!=null &&
              this.linkTableLoaderInfo.equals(other.getLinkTableLoaderInfo()))) &&
            ((this.modelParams==null && other.getModelParams()==null) || 
             (this.modelParams!=null &&
              java.util.Arrays.equals(this.modelParams, other.getModelParams()))) &&
            this.modelType == other.getModelType() &&
            ((this.networkName==null && other.getNetworkName()==null) || 
             (this.networkName!=null &&
              this.networkName.equals(other.getNetworkName()))) &&
            ((this.nodeTableLoaderInfo==null && other.getNodeTableLoaderInfo()==null) || 
             (this.nodeTableLoaderInfo!=null &&
              this.nodeTableLoaderInfo.equals(other.getNodeTableLoaderInfo()))) &&
            ((this.overwrite==null && other.getOverwrite()==null) || 
             (this.overwrite!=null &&
              this.overwrite.equals(other.getOverwrite()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            this.isModelWeb == other.isIsModelWeb();
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
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getLinkTableLoaderInfo() != null) {
            _hashCode += getLinkTableLoaderInfo().hashCode();
        }
        if (getModelParams() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getModelParams());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getModelParams(), i);
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
        if (getNodeTableLoaderInfo() != null) {
            _hashCode += getNodeTableLoaderInfo().hashCode();
        }
        if (getOverwrite() != null) {
            _hashCode += getOverwrite().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        _hashCode += (isIsModelWeb() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateFoodwebRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "CreateFoodwebRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkTableLoaderInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LinkTableLoaderInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TableLoaderInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
        elemField.setFieldName("nodeTableLoaderInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeTableLoaderInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TableLoaderInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isModelWeb");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "isModelWeb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
