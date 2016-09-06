/**
 * Network3DViewSizeInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Network3DViewSizeInfo  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private Double linkDia;

    private String linkProperty;

    private Double linkScale;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference;

    private Double nodeDia;

    private String nodeProperty;

    private Double nodeScale;

    private String settingId;

    public Network3DViewSizeInfo() {
    }

    public Network3DViewSizeInfo(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           Double linkDia,
           String linkProperty,
           Double linkScale,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference,
           Double nodeDia,
           String nodeProperty,
           Double nodeScale,
           String settingId) {
        super(
            id,
            ref,
            entityKey);
        this.linkDia = linkDia;
        this.linkProperty = linkProperty;
        this.linkScale = linkScale;
        this.network3DViewCtrlSetting = network3DViewCtrlSetting;
        this.network3DViewCtrlSettingReference = network3DViewCtrlSettingReference;
        this.nodeDia = nodeDia;
        this.nodeProperty = nodeProperty;
        this.nodeScale = nodeScale;
        this.settingId = settingId;
    }


    /**
     * Gets the linkDia value for this Network3DViewSizeInfo.
     *
     * @return linkDia
     */
    public Double getLinkDia() {
        return linkDia;
    }


    /**
     * Sets the linkDia value for this Network3DViewSizeInfo.
     *
     * @param linkDia
     */
    public void setLinkDia(Double linkDia) {
        this.linkDia = linkDia;
    }


    /**
     * Gets the linkProperty value for this Network3DViewSizeInfo.
     *
     * @return linkProperty
     */
    public String getLinkProperty() {
        return linkProperty;
    }


    /**
     * Sets the linkProperty value for this Network3DViewSizeInfo.
     *
     * @param linkProperty
     */
    public void setLinkProperty(String linkProperty) {
        this.linkProperty = linkProperty;
    }


    /**
     * Gets the linkScale value for this Network3DViewSizeInfo.
     *
     * @return linkScale
     */
    public Double getLinkScale() {
        return linkScale;
    }


    /**
     * Sets the linkScale value for this Network3DViewSizeInfo.
     *
     * @param linkScale
     */
    public void setLinkScale(Double linkScale) {
        this.linkScale = linkScale;
    }


    /**
     * Gets the network3DViewCtrlSetting value for this Network3DViewSizeInfo.
     *
     * @return network3DViewCtrlSetting
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting getNetwork3DViewCtrlSetting() {
        return network3DViewCtrlSetting;
    }


    /**
     * Sets the network3DViewCtrlSetting value for this Network3DViewSizeInfo.
     *
     * @param network3DViewCtrlSetting
     */
    public void setNetwork3DViewCtrlSetting(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting) {
        this.network3DViewCtrlSetting = network3DViewCtrlSetting;
    }


    /**
     * Gets the network3DViewCtrlSettingReference value for this Network3DViewSizeInfo.
     *
     * @return network3DViewCtrlSettingReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal getNetwork3DViewCtrlSettingReference() {
        return network3DViewCtrlSettingReference;
    }


    /**
     * Sets the network3DViewCtrlSettingReference value for this Network3DViewSizeInfo.
     *
     * @param network3DViewCtrlSettingReference
     */
    public void setNetwork3DViewCtrlSettingReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference) {
        this.network3DViewCtrlSettingReference = network3DViewCtrlSettingReference;
    }


    /**
     * Gets the nodeDia value for this Network3DViewSizeInfo.
     *
     * @return nodeDia
     */
    public Double getNodeDia() {
        return nodeDia;
    }


    /**
     * Sets the nodeDia value for this Network3DViewSizeInfo.
     *
     * @param nodeDia
     */
    public void setNodeDia(Double nodeDia) {
        this.nodeDia = nodeDia;
    }


    /**
     * Gets the nodeProperty value for this Network3DViewSizeInfo.
     *
     * @return nodeProperty
     */
    public String getNodeProperty() {
        return nodeProperty;
    }


    /**
     * Sets the nodeProperty value for this Network3DViewSizeInfo.
     *
     * @param nodeProperty
     */
    public void setNodeProperty(String nodeProperty) {
        this.nodeProperty = nodeProperty;
    }


    /**
     * Gets the nodeScale value for this Network3DViewSizeInfo.
     *
     * @return nodeScale
     */
    public Double getNodeScale() {
        return nodeScale;
    }


    /**
     * Sets the nodeScale value for this Network3DViewSizeInfo.
     *
     * @param nodeScale
     */
    public void setNodeScale(Double nodeScale) {
        this.nodeScale = nodeScale;
    }


    /**
     * Gets the settingId value for this Network3DViewSizeInfo.
     *
     * @return settingId
     */
    public String getSettingId() {
        return settingId;
    }


    /**
     * Sets the settingId value for this Network3DViewSizeInfo.
     *
     * @param settingId
     */
    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Network3DViewSizeInfo)) return false;
        Network3DViewSizeInfo other = (Network3DViewSizeInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.linkDia==null && other.getLinkDia()==null) ||
             (this.linkDia!=null &&
              this.linkDia.equals(other.getLinkDia()))) &&
            ((this.linkProperty==null && other.getLinkProperty()==null) ||
             (this.linkProperty!=null &&
              this.linkProperty.equals(other.getLinkProperty()))) &&
            ((this.linkScale==null && other.getLinkScale()==null) ||
             (this.linkScale!=null &&
              this.linkScale.equals(other.getLinkScale()))) &&
            ((this.network3DViewCtrlSetting==null && other.getNetwork3DViewCtrlSetting()==null) ||
             (this.network3DViewCtrlSetting!=null &&
              this.network3DViewCtrlSetting.equals(other.getNetwork3DViewCtrlSetting()))) &&
            ((this.network3DViewCtrlSettingReference==null && other.getNetwork3DViewCtrlSettingReference()==null) ||
             (this.network3DViewCtrlSettingReference!=null &&
              this.network3DViewCtrlSettingReference.equals(other.getNetwork3DViewCtrlSettingReference()))) &&
            ((this.nodeDia==null && other.getNodeDia()==null) ||
             (this.nodeDia!=null &&
              this.nodeDia.equals(other.getNodeDia()))) &&
            ((this.nodeProperty==null && other.getNodeProperty()==null) ||
             (this.nodeProperty!=null &&
              this.nodeProperty.equals(other.getNodeProperty()))) &&
            ((this.nodeScale==null && other.getNodeScale()==null) ||
             (this.nodeScale!=null &&
              this.nodeScale.equals(other.getNodeScale()))) &&
            ((this.settingId==null && other.getSettingId()==null) ||
             (this.settingId!=null &&
              this.settingId.equals(other.getSettingId())));
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
        if (getLinkDia() != null) {
            _hashCode += getLinkDia().hashCode();
        }
        if (getLinkProperty() != null) {
            _hashCode += getLinkProperty().hashCode();
        }
        if (getLinkScale() != null) {
            _hashCode += getLinkScale().hashCode();
        }
        if (getNetwork3DViewCtrlSetting() != null) {
            _hashCode += getNetwork3DViewCtrlSetting().hashCode();
        }
        if (getNetwork3DViewCtrlSettingReference() != null) {
            _hashCode += getNetwork3DViewCtrlSettingReference().hashCode();
        }
        if (getNodeDia() != null) {
            _hashCode += getNodeDia().hashCode();
        }
        if (getNodeProperty() != null) {
            _hashCode += getNodeProperty().hashCode();
        }
        if (getNodeScale() != null) {
            _hashCode += getNodeScale().hashCode();
        }
        if (getSettingId() != null) {
            _hashCode += getSettingId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Network3DViewSizeInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewSizeInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkDia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LinkDia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LinkProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkScale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LinkScale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("network3DViewCtrlSetting");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewCtrlSetting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewCtrlSetting"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("network3DViewCtrlSettingReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewCtrlSettingReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfNetwork3DViewCtrlSettinghovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeDia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeDia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeScale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeScale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settingId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "settingId"));
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
