/**
 * Network3DViewGLViewInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Network3DViewGLViewInfo  extends shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.Double currentPtX;

    private java.lang.Double currentPtY;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting;

    private shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference;

    private java.lang.Double zoomRectCenterX;

    private java.lang.Double zoomRectCenterY;

    private java.lang.Double zoomRectHeight;

    private java.lang.Double zoomRectWidth;

    private java.lang.Double zoomRectX;

    private java.lang.Double zoomRectY;

    private java.lang.Double rotX;

    private java.lang.Double rotY;

    private java.lang.String settingId;

    private java.lang.Double zModel;

    public Network3DViewGLViewInfo() {
    }

    public Network3DViewGLViewInfo(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           shared.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.Double currentPtX,
           java.lang.Double currentPtY,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting,
           shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference,
           java.lang.Double zoomRectCenterX,
           java.lang.Double zoomRectCenterY,
           java.lang.Double zoomRectHeight,
           java.lang.Double zoomRectWidth,
           java.lang.Double zoomRectX,
           java.lang.Double zoomRectY,
           java.lang.Double rotX,
           java.lang.Double rotY,
           java.lang.String settingId,
           java.lang.Double zModel) {
        super(
            id,
            ref,
            entityKey);
        this.currentPtX = currentPtX;
        this.currentPtY = currentPtY;
        this.network3DViewCtrlSetting = network3DViewCtrlSetting;
        this.network3DViewCtrlSettingReference = network3DViewCtrlSettingReference;
        this.zoomRectCenterX = zoomRectCenterX;
        this.zoomRectCenterY = zoomRectCenterY;
        this.zoomRectHeight = zoomRectHeight;
        this.zoomRectWidth = zoomRectWidth;
        this.zoomRectX = zoomRectX;
        this.zoomRectY = zoomRectY;
        this.rotX = rotX;
        this.rotY = rotY;
        this.settingId = settingId;
        this.zModel = zModel;
    }


    /**
     * Gets the currentPtX value for this Network3DViewGLViewInfo.
     * 
     * @return currentPtX
     */
    public java.lang.Double getCurrentPtX() {
        return currentPtX;
    }


    /**
     * Sets the currentPtX value for this Network3DViewGLViewInfo.
     * 
     * @param currentPtX
     */
    public void setCurrentPtX(java.lang.Double currentPtX) {
        this.currentPtX = currentPtX;
    }


    /**
     * Gets the currentPtY value for this Network3DViewGLViewInfo.
     * 
     * @return currentPtY
     */
    public java.lang.Double getCurrentPtY() {
        return currentPtY;
    }


    /**
     * Sets the currentPtY value for this Network3DViewGLViewInfo.
     * 
     * @param currentPtY
     */
    public void setCurrentPtY(java.lang.Double currentPtY) {
        this.currentPtY = currentPtY;
    }


    /**
     * Gets the network3DViewCtrlSetting value for this Network3DViewGLViewInfo.
     * 
     * @return network3DViewCtrlSetting
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting getNetwork3DViewCtrlSetting() {
        return network3DViewCtrlSetting;
    }


    /**
     * Sets the network3DViewCtrlSetting value for this Network3DViewGLViewInfo.
     * 
     * @param network3DViewCtrlSetting
     */
    public void setNetwork3DViewCtrlSetting(shared.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting) {
        this.network3DViewCtrlSetting = network3DViewCtrlSetting;
    }


    /**
     * Gets the network3DViewCtrlSettingReference value for this Network3DViewGLViewInfo.
     * 
     * @return network3DViewCtrlSettingReference
     */
    public shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal getNetwork3DViewCtrlSettingReference() {
        return network3DViewCtrlSettingReference;
    }


    /**
     * Sets the network3DViewCtrlSettingReference value for this Network3DViewGLViewInfo.
     * 
     * @param network3DViewCtrlSettingReference
     */
    public void setNetwork3DViewCtrlSettingReference(shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference) {
        this.network3DViewCtrlSettingReference = network3DViewCtrlSettingReference;
    }


    /**
     * Gets the zoomRectCenterX value for this Network3DViewGLViewInfo.
     * 
     * @return zoomRectCenterX
     */
    public java.lang.Double getZoomRectCenterX() {
        return zoomRectCenterX;
    }


    /**
     * Sets the zoomRectCenterX value for this Network3DViewGLViewInfo.
     * 
     * @param zoomRectCenterX
     */
    public void setZoomRectCenterX(java.lang.Double zoomRectCenterX) {
        this.zoomRectCenterX = zoomRectCenterX;
    }


    /**
     * Gets the zoomRectCenterY value for this Network3DViewGLViewInfo.
     * 
     * @return zoomRectCenterY
     */
    public java.lang.Double getZoomRectCenterY() {
        return zoomRectCenterY;
    }


    /**
     * Sets the zoomRectCenterY value for this Network3DViewGLViewInfo.
     * 
     * @param zoomRectCenterY
     */
    public void setZoomRectCenterY(java.lang.Double zoomRectCenterY) {
        this.zoomRectCenterY = zoomRectCenterY;
    }


    /**
     * Gets the zoomRectHeight value for this Network3DViewGLViewInfo.
     * 
     * @return zoomRectHeight
     */
    public java.lang.Double getZoomRectHeight() {
        return zoomRectHeight;
    }


    /**
     * Sets the zoomRectHeight value for this Network3DViewGLViewInfo.
     * 
     * @param zoomRectHeight
     */
    public void setZoomRectHeight(java.lang.Double zoomRectHeight) {
        this.zoomRectHeight = zoomRectHeight;
    }


    /**
     * Gets the zoomRectWidth value for this Network3DViewGLViewInfo.
     * 
     * @return zoomRectWidth
     */
    public java.lang.Double getZoomRectWidth() {
        return zoomRectWidth;
    }


    /**
     * Sets the zoomRectWidth value for this Network3DViewGLViewInfo.
     * 
     * @param zoomRectWidth
     */
    public void setZoomRectWidth(java.lang.Double zoomRectWidth) {
        this.zoomRectWidth = zoomRectWidth;
    }


    /**
     * Gets the zoomRectX value for this Network3DViewGLViewInfo.
     * 
     * @return zoomRectX
     */
    public java.lang.Double getZoomRectX() {
        return zoomRectX;
    }


    /**
     * Sets the zoomRectX value for this Network3DViewGLViewInfo.
     * 
     * @param zoomRectX
     */
    public void setZoomRectX(java.lang.Double zoomRectX) {
        this.zoomRectX = zoomRectX;
    }


    /**
     * Gets the zoomRectY value for this Network3DViewGLViewInfo.
     * 
     * @return zoomRectY
     */
    public java.lang.Double getZoomRectY() {
        return zoomRectY;
    }


    /**
     * Sets the zoomRectY value for this Network3DViewGLViewInfo.
     * 
     * @param zoomRectY
     */
    public void setZoomRectY(java.lang.Double zoomRectY) {
        this.zoomRectY = zoomRectY;
    }


    /**
     * Gets the rotX value for this Network3DViewGLViewInfo.
     * 
     * @return rotX
     */
    public java.lang.Double getRotX() {
        return rotX;
    }


    /**
     * Sets the rotX value for this Network3DViewGLViewInfo.
     * 
     * @param rotX
     */
    public void setRotX(java.lang.Double rotX) {
        this.rotX = rotX;
    }


    /**
     * Gets the rotY value for this Network3DViewGLViewInfo.
     * 
     * @return rotY
     */
    public java.lang.Double getRotY() {
        return rotY;
    }


    /**
     * Sets the rotY value for this Network3DViewGLViewInfo.
     * 
     * @param rotY
     */
    public void setRotY(java.lang.Double rotY) {
        this.rotY = rotY;
    }


    /**
     * Gets the settingId value for this Network3DViewGLViewInfo.
     * 
     * @return settingId
     */
    public java.lang.String getSettingId() {
        return settingId;
    }


    /**
     * Sets the settingId value for this Network3DViewGLViewInfo.
     * 
     * @param settingId
     */
    public void setSettingId(java.lang.String settingId) {
        this.settingId = settingId;
    }


    /**
     * Gets the zModel value for this Network3DViewGLViewInfo.
     * 
     * @return zModel
     */
    public java.lang.Double getZModel() {
        return zModel;
    }


    /**
     * Sets the zModel value for this Network3DViewGLViewInfo.
     * 
     * @param zModel
     */
    public void setZModel(java.lang.Double zModel) {
        this.zModel = zModel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Network3DViewGLViewInfo)) return false;
        Network3DViewGLViewInfo other = (Network3DViewGLViewInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.currentPtX==null && other.getCurrentPtX()==null) || 
             (this.currentPtX!=null &&
              this.currentPtX.equals(other.getCurrentPtX()))) &&
            ((this.currentPtY==null && other.getCurrentPtY()==null) || 
             (this.currentPtY!=null &&
              this.currentPtY.equals(other.getCurrentPtY()))) &&
            ((this.network3DViewCtrlSetting==null && other.getNetwork3DViewCtrlSetting()==null) || 
             (this.network3DViewCtrlSetting!=null &&
              this.network3DViewCtrlSetting.equals(other.getNetwork3DViewCtrlSetting()))) &&
            ((this.network3DViewCtrlSettingReference==null && other.getNetwork3DViewCtrlSettingReference()==null) || 
             (this.network3DViewCtrlSettingReference!=null &&
              this.network3DViewCtrlSettingReference.equals(other.getNetwork3DViewCtrlSettingReference()))) &&
            ((this.zoomRectCenterX==null && other.getZoomRectCenterX()==null) || 
             (this.zoomRectCenterX!=null &&
              this.zoomRectCenterX.equals(other.getZoomRectCenterX()))) &&
            ((this.zoomRectCenterY==null && other.getZoomRectCenterY()==null) || 
             (this.zoomRectCenterY!=null &&
              this.zoomRectCenterY.equals(other.getZoomRectCenterY()))) &&
            ((this.zoomRectHeight==null && other.getZoomRectHeight()==null) || 
             (this.zoomRectHeight!=null &&
              this.zoomRectHeight.equals(other.getZoomRectHeight()))) &&
            ((this.zoomRectWidth==null && other.getZoomRectWidth()==null) || 
             (this.zoomRectWidth!=null &&
              this.zoomRectWidth.equals(other.getZoomRectWidth()))) &&
            ((this.zoomRectX==null && other.getZoomRectX()==null) || 
             (this.zoomRectX!=null &&
              this.zoomRectX.equals(other.getZoomRectX()))) &&
            ((this.zoomRectY==null && other.getZoomRectY()==null) || 
             (this.zoomRectY!=null &&
              this.zoomRectY.equals(other.getZoomRectY()))) &&
            ((this.rotX==null && other.getRotX()==null) || 
             (this.rotX!=null &&
              this.rotX.equals(other.getRotX()))) &&
            ((this.rotY==null && other.getRotY()==null) || 
             (this.rotY!=null &&
              this.rotY.equals(other.getRotY()))) &&
            ((this.settingId==null && other.getSettingId()==null) || 
             (this.settingId!=null &&
              this.settingId.equals(other.getSettingId()))) &&
            ((this.zModel==null && other.getZModel()==null) || 
             (this.zModel!=null &&
              this.zModel.equals(other.getZModel())));
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
        if (getCurrentPtX() != null) {
            _hashCode += getCurrentPtX().hashCode();
        }
        if (getCurrentPtY() != null) {
            _hashCode += getCurrentPtY().hashCode();
        }
        if (getNetwork3DViewCtrlSetting() != null) {
            _hashCode += getNetwork3DViewCtrlSetting().hashCode();
        }
        if (getNetwork3DViewCtrlSettingReference() != null) {
            _hashCode += getNetwork3DViewCtrlSettingReference().hashCode();
        }
        if (getZoomRectCenterX() != null) {
            _hashCode += getZoomRectCenterX().hashCode();
        }
        if (getZoomRectCenterY() != null) {
            _hashCode += getZoomRectCenterY().hashCode();
        }
        if (getZoomRectHeight() != null) {
            _hashCode += getZoomRectHeight().hashCode();
        }
        if (getZoomRectWidth() != null) {
            _hashCode += getZoomRectWidth().hashCode();
        }
        if (getZoomRectX() != null) {
            _hashCode += getZoomRectX().hashCode();
        }
        if (getZoomRectY() != null) {
            _hashCode += getZoomRectY().hashCode();
        }
        if (getRotX() != null) {
            _hashCode += getRotX().hashCode();
        }
        if (getRotY() != null) {
            _hashCode += getRotY().hashCode();
        }
        if (getSettingId() != null) {
            _hashCode += getSettingId().hashCode();
        }
        if (getZModel() != null) {
            _hashCode += getZModel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Network3DViewGLViewInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewGLViewInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentPtX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "CurrentPtX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentPtY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "CurrentPtY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
        elemField.setFieldName("zoomRectCenterX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ZoomRectCenterX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zoomRectCenterY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ZoomRectCenterY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zoomRectHeight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ZoomRectHeight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zoomRectWidth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ZoomRectWidth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zoomRectX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ZoomRectX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zoomRectY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ZoomRectY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rotX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "rotX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rotY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "rotY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settingId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "settingId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZModel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "zModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
