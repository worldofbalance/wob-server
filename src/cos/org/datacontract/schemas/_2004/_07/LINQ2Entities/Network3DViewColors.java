/**
 * Network3DViewColors.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Network3DViewColors  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private String colorBlend;

    private String linkProperty;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColorItems[] network3DViewColorItems;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference;

    private String nodeHighlight;

    private Boolean nodeHighlightEnabled;

    private String nodeProperty;

    private String nodeSelection;

    private String maxTL;

    private Integer maxTLVal;

    private String settingId;

    public Network3DViewColors() {
    }

    public Network3DViewColors(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           String colorBlend,
           String linkProperty,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColorItems[] network3DViewColorItems,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference,
           String nodeHighlight,
           Boolean nodeHighlightEnabled,
           String nodeProperty,
           String nodeSelection,
           String maxTL,
           Integer maxTLVal,
           String settingId) {
        super(
            id,
            ref,
            entityKey);
        this.colorBlend = colorBlend;
        this.linkProperty = linkProperty;
        this.network3DViewColorItems = network3DViewColorItems;
        this.network3DViewCtrlSetting = network3DViewCtrlSetting;
        this.network3DViewCtrlSettingReference = network3DViewCtrlSettingReference;
        this.nodeHighlight = nodeHighlight;
        this.nodeHighlightEnabled = nodeHighlightEnabled;
        this.nodeProperty = nodeProperty;
        this.nodeSelection = nodeSelection;
        this.maxTL = maxTL;
        this.maxTLVal = maxTLVal;
        this.settingId = settingId;
    }


    /**
     * Gets the colorBlend value for this Network3DViewColors.
     *
     * @return colorBlend
     */
    public String getColorBlend() {
        return colorBlend;
    }


    /**
     * Sets the colorBlend value for this Network3DViewColors.
     *
     * @param colorBlend
     */
    public void setColorBlend(String colorBlend) {
        this.colorBlend = colorBlend;
    }


    /**
     * Gets the linkProperty value for this Network3DViewColors.
     *
     * @return linkProperty
     */
    public String getLinkProperty() {
        return linkProperty;
    }


    /**
     * Sets the linkProperty value for this Network3DViewColors.
     *
     * @param linkProperty
     */
    public void setLinkProperty(String linkProperty) {
        this.linkProperty = linkProperty;
    }


    /**
     * Gets the network3DViewColorItems value for this Network3DViewColors.
     *
     * @return network3DViewColorItems
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColorItems[] getNetwork3DViewColorItems() {
        return network3DViewColorItems;
    }


    /**
     * Sets the network3DViewColorItems value for this Network3DViewColors.
     *
     * @param network3DViewColorItems
     */
    public void setNetwork3DViewColorItems(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColorItems[] network3DViewColorItems) {
        this.network3DViewColorItems = network3DViewColorItems;
    }


    /**
     * Gets the network3DViewCtrlSetting value for this Network3DViewColors.
     *
     * @return network3DViewCtrlSetting
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting getNetwork3DViewCtrlSetting() {
        return network3DViewCtrlSetting;
    }


    /**
     * Sets the network3DViewCtrlSetting value for this Network3DViewColors.
     *
     * @param network3DViewCtrlSetting
     */
    public void setNetwork3DViewCtrlSetting(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting) {
        this.network3DViewCtrlSetting = network3DViewCtrlSetting;
    }


    /**
     * Gets the network3DViewCtrlSettingReference value for this Network3DViewColors.
     *
     * @return network3DViewCtrlSettingReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal getNetwork3DViewCtrlSettingReference() {
        return network3DViewCtrlSettingReference;
    }


    /**
     * Sets the network3DViewCtrlSettingReference value for this Network3DViewColors.
     *
     * @param network3DViewCtrlSettingReference
     */
    public void setNetwork3DViewCtrlSettingReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference) {
        this.network3DViewCtrlSettingReference = network3DViewCtrlSettingReference;
    }


    /**
     * Gets the nodeHighlight value for this Network3DViewColors.
     *
     * @return nodeHighlight
     */
    public String getNodeHighlight() {
        return nodeHighlight;
    }


    /**
     * Sets the nodeHighlight value for this Network3DViewColors.
     *
     * @param nodeHighlight
     */
    public void setNodeHighlight(String nodeHighlight) {
        this.nodeHighlight = nodeHighlight;
    }


    /**
     * Gets the nodeHighlightEnabled value for this Network3DViewColors.
     *
     * @return nodeHighlightEnabled
     */
    public Boolean getNodeHighlightEnabled() {
        return nodeHighlightEnabled;
    }


    /**
     * Sets the nodeHighlightEnabled value for this Network3DViewColors.
     *
     * @param nodeHighlightEnabled
     */
    public void setNodeHighlightEnabled(Boolean nodeHighlightEnabled) {
        this.nodeHighlightEnabled = nodeHighlightEnabled;
    }


    /**
     * Gets the nodeProperty value for this Network3DViewColors.
     *
     * @return nodeProperty
     */
    public String getNodeProperty() {
        return nodeProperty;
    }


    /**
     * Sets the nodeProperty value for this Network3DViewColors.
     *
     * @param nodeProperty
     */
    public void setNodeProperty(String nodeProperty) {
        this.nodeProperty = nodeProperty;
    }


    /**
     * Gets the nodeSelection value for this Network3DViewColors.
     *
     * @return nodeSelection
     */
    public String getNodeSelection() {
        return nodeSelection;
    }


    /**
     * Sets the nodeSelection value for this Network3DViewColors.
     *
     * @param nodeSelection
     */
    public void setNodeSelection(String nodeSelection) {
        this.nodeSelection = nodeSelection;
    }


    /**
     * Gets the maxTL value for this Network3DViewColors.
     *
     * @return maxTL
     */
    public String getMaxTL() {
        return maxTL;
    }


    /**
     * Sets the maxTL value for this Network3DViewColors.
     *
     * @param maxTL
     */
    public void setMaxTL(String maxTL) {
        this.maxTL = maxTL;
    }


    /**
     * Gets the maxTLVal value for this Network3DViewColors.
     *
     * @return maxTLVal
     */
    public Integer getMaxTLVal() {
        return maxTLVal;
    }


    /**
     * Sets the maxTLVal value for this Network3DViewColors.
     *
     * @param maxTLVal
     */
    public void setMaxTLVal(Integer maxTLVal) {
        this.maxTLVal = maxTLVal;
    }


    /**
     * Gets the settingId value for this Network3DViewColors.
     *
     * @return settingId
     */
    public String getSettingId() {
        return settingId;
    }


    /**
     * Sets the settingId value for this Network3DViewColors.
     *
     * @param settingId
     */
    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Network3DViewColors)) return false;
        Network3DViewColors other = (Network3DViewColors) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.colorBlend==null && other.getColorBlend()==null) ||
             (this.colorBlend!=null &&
              this.colorBlend.equals(other.getColorBlend()))) &&
            ((this.linkProperty==null && other.getLinkProperty()==null) ||
             (this.linkProperty!=null &&
              this.linkProperty.equals(other.getLinkProperty()))) &&
            ((this.network3DViewColorItems==null && other.getNetwork3DViewColorItems()==null) ||
             (this.network3DViewColorItems!=null &&
              java.util.Arrays.equals(this.network3DViewColorItems, other.getNetwork3DViewColorItems()))) &&
            ((this.network3DViewCtrlSetting==null && other.getNetwork3DViewCtrlSetting()==null) ||
             (this.network3DViewCtrlSetting!=null &&
              this.network3DViewCtrlSetting.equals(other.getNetwork3DViewCtrlSetting()))) &&
            ((this.network3DViewCtrlSettingReference==null && other.getNetwork3DViewCtrlSettingReference()==null) ||
             (this.network3DViewCtrlSettingReference!=null &&
              this.network3DViewCtrlSettingReference.equals(other.getNetwork3DViewCtrlSettingReference()))) &&
            ((this.nodeHighlight==null && other.getNodeHighlight()==null) ||
             (this.nodeHighlight!=null &&
              this.nodeHighlight.equals(other.getNodeHighlight()))) &&
            ((this.nodeHighlightEnabled==null && other.getNodeHighlightEnabled()==null) ||
             (this.nodeHighlightEnabled!=null &&
              this.nodeHighlightEnabled.equals(other.getNodeHighlightEnabled()))) &&
            ((this.nodeProperty==null && other.getNodeProperty()==null) ||
             (this.nodeProperty!=null &&
              this.nodeProperty.equals(other.getNodeProperty()))) &&
            ((this.nodeSelection==null && other.getNodeSelection()==null) ||
             (this.nodeSelection!=null &&
              this.nodeSelection.equals(other.getNodeSelection()))) &&
            ((this.maxTL==null && other.getMaxTL()==null) ||
             (this.maxTL!=null &&
              this.maxTL.equals(other.getMaxTL()))) &&
            ((this.maxTLVal==null && other.getMaxTLVal()==null) ||
             (this.maxTLVal!=null &&
              this.maxTLVal.equals(other.getMaxTLVal()))) &&
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
        if (getColorBlend() != null) {
            _hashCode += getColorBlend().hashCode();
        }
        if (getLinkProperty() != null) {
            _hashCode += getLinkProperty().hashCode();
        }
        if (getNetwork3DViewColorItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNetwork3DViewColorItems());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getNetwork3DViewColorItems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNetwork3DViewCtrlSetting() != null) {
            _hashCode += getNetwork3DViewCtrlSetting().hashCode();
        }
        if (getNetwork3DViewCtrlSettingReference() != null) {
            _hashCode += getNetwork3DViewCtrlSettingReference().hashCode();
        }
        if (getNodeHighlight() != null) {
            _hashCode += getNodeHighlight().hashCode();
        }
        if (getNodeHighlightEnabled() != null) {
            _hashCode += getNodeHighlightEnabled().hashCode();
        }
        if (getNodeProperty() != null) {
            _hashCode += getNodeProperty().hashCode();
        }
        if (getNodeSelection() != null) {
            _hashCode += getNodeSelection().hashCode();
        }
        if (getMaxTL() != null) {
            _hashCode += getMaxTL().hashCode();
        }
        if (getMaxTLVal() != null) {
            _hashCode += getMaxTLVal().hashCode();
        }
        if (getSettingId() != null) {
            _hashCode += getSettingId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Network3DViewColors.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewColors"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colorBlend");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ColorBlend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("network3DViewColorItems");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewColorItems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewColorItems"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewColorItems"));
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
        elemField.setFieldName("nodeHighlight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeHighlight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeHighlightEnabled");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeHighlightEnabled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeSelection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NodeSelection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxTL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "maxTL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxTLVal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "maxTLVal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
