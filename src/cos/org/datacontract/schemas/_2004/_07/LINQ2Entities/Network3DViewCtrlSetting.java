/**
 * Network3DViewCtrlSetting.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Network3DViewCtrlSetting  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColors network3DViewColors;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewColorshovVakal network3DViewColorsReference;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewGLViewInfo network3DViewGLViewInfo;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewGLViewInfohovVakal network3DViewGLViewInfoReference;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewPositions network3DViewPositions;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewPositionshovVakal network3DViewPositionsReference;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewSizeInfo network3DViewSizeInfo;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewSizeInfohovVakal network3DViewSizeInfoReference;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfUserhovVakal userReference;

    private java.util.Calendar createdDate;

    private String settingId;

    private String settingName;

    public Network3DViewCtrlSetting() {
    }

    public Network3DViewCtrlSetting(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColors network3DViewColors,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewColorshovVakal network3DViewColorsReference,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewGLViewInfo network3DViewGLViewInfo,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewGLViewInfohovVakal network3DViewGLViewInfoReference,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewPositions network3DViewPositions,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewPositionshovVakal network3DViewPositionsReference,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewSizeInfo network3DViewSizeInfo,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewSizeInfohovVakal network3DViewSizeInfoReference,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfUserhovVakal userReference,
           java.util.Calendar createdDate,
           String settingId,
           String settingName) {
        super(
            id,
            ref,
            entityKey);
        this.network3DViewColors = network3DViewColors;
        this.network3DViewColorsReference = network3DViewColorsReference;
        this.network3DViewGLViewInfo = network3DViewGLViewInfo;
        this.network3DViewGLViewInfoReference = network3DViewGLViewInfoReference;
        this.network3DViewPositions = network3DViewPositions;
        this.network3DViewPositionsReference = network3DViewPositionsReference;
        this.network3DViewSizeInfo = network3DViewSizeInfo;
        this.network3DViewSizeInfoReference = network3DViewSizeInfoReference;
        this.user = user;
        this.userReference = userReference;
        this.createdDate = createdDate;
        this.settingId = settingId;
        this.settingName = settingName;
    }


    /**
     * Gets the network3DViewColors value for this Network3DViewCtrlSetting.
     *
     * @return network3DViewColors
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColors getNetwork3DViewColors() {
        return network3DViewColors;
    }


    /**
     * Sets the network3DViewColors value for this Network3DViewCtrlSetting.
     *
     * @param network3DViewColors
     */
    public void setNetwork3DViewColors(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColors network3DViewColors) {
        this.network3DViewColors = network3DViewColors;
    }


    /**
     * Gets the network3DViewColorsReference value for this Network3DViewCtrlSetting.
     *
     * @return network3DViewColorsReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewColorshovVakal getNetwork3DViewColorsReference() {
        return network3DViewColorsReference;
    }


    /**
     * Sets the network3DViewColorsReference value for this Network3DViewCtrlSetting.
     *
     * @param network3DViewColorsReference
     */
    public void setNetwork3DViewColorsReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewColorshovVakal network3DViewColorsReference) {
        this.network3DViewColorsReference = network3DViewColorsReference;
    }


    /**
     * Gets the network3DViewGLViewInfo value for this Network3DViewCtrlSetting.
     *
     * @return network3DViewGLViewInfo
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewGLViewInfo getNetwork3DViewGLViewInfo() {
        return network3DViewGLViewInfo;
    }


    /**
     * Sets the network3DViewGLViewInfo value for this Network3DViewCtrlSetting.
     *
     * @param network3DViewGLViewInfo
     */
    public void setNetwork3DViewGLViewInfo(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewGLViewInfo network3DViewGLViewInfo) {
        this.network3DViewGLViewInfo = network3DViewGLViewInfo;
    }


    /**
     * Gets the network3DViewGLViewInfoReference value for this Network3DViewCtrlSetting.
     *
     * @return network3DViewGLViewInfoReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewGLViewInfohovVakal getNetwork3DViewGLViewInfoReference() {
        return network3DViewGLViewInfoReference;
    }


    /**
     * Sets the network3DViewGLViewInfoReference value for this Network3DViewCtrlSetting.
     *
     * @param network3DViewGLViewInfoReference
     */
    public void setNetwork3DViewGLViewInfoReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewGLViewInfohovVakal network3DViewGLViewInfoReference) {
        this.network3DViewGLViewInfoReference = network3DViewGLViewInfoReference;
    }


    /**
     * Gets the network3DViewPositions value for this Network3DViewCtrlSetting.
     *
     * @return network3DViewPositions
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewPositions getNetwork3DViewPositions() {
        return network3DViewPositions;
    }


    /**
     * Sets the network3DViewPositions value for this Network3DViewCtrlSetting.
     *
     * @param network3DViewPositions
     */
    public void setNetwork3DViewPositions(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewPositions network3DViewPositions) {
        this.network3DViewPositions = network3DViewPositions;
    }


    /**
     * Gets the network3DViewPositionsReference value for this Network3DViewCtrlSetting.
     *
     * @return network3DViewPositionsReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewPositionshovVakal getNetwork3DViewPositionsReference() {
        return network3DViewPositionsReference;
    }


    /**
     * Sets the network3DViewPositionsReference value for this Network3DViewCtrlSetting.
     *
     * @param network3DViewPositionsReference
     */
    public void setNetwork3DViewPositionsReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewPositionshovVakal network3DViewPositionsReference) {
        this.network3DViewPositionsReference = network3DViewPositionsReference;
    }


    /**
     * Gets the network3DViewSizeInfo value for this Network3DViewCtrlSetting.
     *
     * @return network3DViewSizeInfo
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewSizeInfo getNetwork3DViewSizeInfo() {
        return network3DViewSizeInfo;
    }


    /**
     * Sets the network3DViewSizeInfo value for this Network3DViewCtrlSetting.
     *
     * @param network3DViewSizeInfo
     */
    public void setNetwork3DViewSizeInfo(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewSizeInfo network3DViewSizeInfo) {
        this.network3DViewSizeInfo = network3DViewSizeInfo;
    }


    /**
     * Gets the network3DViewSizeInfoReference value for this Network3DViewCtrlSetting.
     *
     * @return network3DViewSizeInfoReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewSizeInfohovVakal getNetwork3DViewSizeInfoReference() {
        return network3DViewSizeInfoReference;
    }


    /**
     * Sets the network3DViewSizeInfoReference value for this Network3DViewCtrlSetting.
     *
     * @param network3DViewSizeInfoReference
     */
    public void setNetwork3DViewSizeInfoReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewSizeInfohovVakal network3DViewSizeInfoReference) {
        this.network3DViewSizeInfoReference = network3DViewSizeInfoReference;
    }


    /**
     * Gets the user value for this Network3DViewCtrlSetting.
     *
     * @return user
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this Network3DViewCtrlSetting.
     *
     * @param user
     */
    public void setUser(cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }


    /**
     * Gets the userReference value for this Network3DViewCtrlSetting.
     *
     * @return userReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfUserhovVakal getUserReference() {
        return userReference;
    }


    /**
     * Sets the userReference value for this Network3DViewCtrlSetting.
     *
     * @param userReference
     */
    public void setUserReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfUserhovVakal userReference) {
        this.userReference = userReference;
    }


    /**
     * Gets the createdDate value for this Network3DViewCtrlSetting.
     *
     * @return createdDate
     */
    public java.util.Calendar getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this Network3DViewCtrlSetting.
     *
     * @param createdDate
     */
    public void setCreatedDate(java.util.Calendar createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the settingId value for this Network3DViewCtrlSetting.
     *
     * @return settingId
     */
    public String getSettingId() {
        return settingId;
    }


    /**
     * Sets the settingId value for this Network3DViewCtrlSetting.
     *
     * @param settingId
     */
    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }


    /**
     * Gets the settingName value for this Network3DViewCtrlSetting.
     *
     * @return settingName
     */
    public String getSettingName() {
        return settingName;
    }


    /**
     * Sets the settingName value for this Network3DViewCtrlSetting.
     *
     * @param settingName
     */
    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Network3DViewCtrlSetting)) return false;
        Network3DViewCtrlSetting other = (Network3DViewCtrlSetting) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.network3DViewColors==null && other.getNetwork3DViewColors()==null) ||
             (this.network3DViewColors!=null &&
              this.network3DViewColors.equals(other.getNetwork3DViewColors()))) &&
            ((this.network3DViewColorsReference==null && other.getNetwork3DViewColorsReference()==null) ||
             (this.network3DViewColorsReference!=null &&
              this.network3DViewColorsReference.equals(other.getNetwork3DViewColorsReference()))) &&
            ((this.network3DViewGLViewInfo==null && other.getNetwork3DViewGLViewInfo()==null) ||
             (this.network3DViewGLViewInfo!=null &&
              this.network3DViewGLViewInfo.equals(other.getNetwork3DViewGLViewInfo()))) &&
            ((this.network3DViewGLViewInfoReference==null && other.getNetwork3DViewGLViewInfoReference()==null) ||
             (this.network3DViewGLViewInfoReference!=null &&
              this.network3DViewGLViewInfoReference.equals(other.getNetwork3DViewGLViewInfoReference()))) &&
            ((this.network3DViewPositions==null && other.getNetwork3DViewPositions()==null) ||
             (this.network3DViewPositions!=null &&
              this.network3DViewPositions.equals(other.getNetwork3DViewPositions()))) &&
            ((this.network3DViewPositionsReference==null && other.getNetwork3DViewPositionsReference()==null) ||
             (this.network3DViewPositionsReference!=null &&
              this.network3DViewPositionsReference.equals(other.getNetwork3DViewPositionsReference()))) &&
            ((this.network3DViewSizeInfo==null && other.getNetwork3DViewSizeInfo()==null) ||
             (this.network3DViewSizeInfo!=null &&
              this.network3DViewSizeInfo.equals(other.getNetwork3DViewSizeInfo()))) &&
            ((this.network3DViewSizeInfoReference==null && other.getNetwork3DViewSizeInfoReference()==null) ||
             (this.network3DViewSizeInfoReference!=null &&
              this.network3DViewSizeInfoReference.equals(other.getNetwork3DViewSizeInfoReference()))) &&
            ((this.user==null && other.getUser()==null) ||
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.userReference==null && other.getUserReference()==null) ||
             (this.userReference!=null &&
              this.userReference.equals(other.getUserReference()))) &&
            ((this.createdDate==null && other.getCreatedDate()==null) ||
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.settingId==null && other.getSettingId()==null) ||
             (this.settingId!=null &&
              this.settingId.equals(other.getSettingId()))) &&
            ((this.settingName==null && other.getSettingName()==null) ||
             (this.settingName!=null &&
              this.settingName.equals(other.getSettingName())));
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
        if (getNetwork3DViewColors() != null) {
            _hashCode += getNetwork3DViewColors().hashCode();
        }
        if (getNetwork3DViewColorsReference() != null) {
            _hashCode += getNetwork3DViewColorsReference().hashCode();
        }
        if (getNetwork3DViewGLViewInfo() != null) {
            _hashCode += getNetwork3DViewGLViewInfo().hashCode();
        }
        if (getNetwork3DViewGLViewInfoReference() != null) {
            _hashCode += getNetwork3DViewGLViewInfoReference().hashCode();
        }
        if (getNetwork3DViewPositions() != null) {
            _hashCode += getNetwork3DViewPositions().hashCode();
        }
        if (getNetwork3DViewPositionsReference() != null) {
            _hashCode += getNetwork3DViewPositionsReference().hashCode();
        }
        if (getNetwork3DViewSizeInfo() != null) {
            _hashCode += getNetwork3DViewSizeInfo().hashCode();
        }
        if (getNetwork3DViewSizeInfoReference() != null) {
            _hashCode += getNetwork3DViewSizeInfoReference().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getUserReference() != null) {
            _hashCode += getUserReference().hashCode();
        }
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getSettingId() != null) {
            _hashCode += getSettingId().hashCode();
        }
        if (getSettingName() != null) {
            _hashCode += getSettingName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Network3DViewCtrlSetting.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewCtrlSetting"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("network3DViewColors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewColors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewColors"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("network3DViewColorsReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewColorsReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfNetwork3DViewColorshovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("network3DViewGLViewInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewGLViewInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewGLViewInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("network3DViewGLViewInfoReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewGLViewInfoReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfNetwork3DViewGLViewInfohovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("network3DViewPositions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewPositions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewPositions"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("network3DViewPositionsReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewPositionsReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfNetwork3DViewPositionshovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("network3DViewSizeInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewSizeInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewSizeInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("network3DViewSizeInfoReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewSizeInfoReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfNetwork3DViewSizeInfohovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfUserhovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "createdDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settingName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "settingName"));
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
