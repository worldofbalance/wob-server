/**
 * Network3DViewGLViewInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Network3DViewGLViewInfo  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private Double currentPtX;

    private Double currentPtY;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference;

    private Double zoomRectCenterX;

    private Double zoomRectCenterY;

    private Double zoomRectHeight;

    private Double zoomRectWidth;

    private Double zoomRectX;

    private Double zoomRectY;

    private Double rotX;

    private Double rotY;

    private String settingId;

    private Double zModel;

    public Network3DViewGLViewInfo() {
    }

    public Network3DViewGLViewInfo(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           Double currentPtX,
           Double currentPtY,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference,
           Double zoomRectCenterX,
           Double zoomRectCenterY,
           Double zoomRectHeight,
           Double zoomRectWidth,
           Double zoomRectX,
           Double zoomRectY,
           Double rotX,
           Double rotY,
           String settingId,
           Double zModel) {
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
    public Double getCurrentPtX() {
        return currentPtX;
    }


    /**
     * Sets the currentPtX value for this Network3DViewGLViewInfo.
     *
     * @param currentPtX
     */
    public void setCurrentPtX(Double currentPtX) {
        this.currentPtX = currentPtX;
    }


    /**
     * Gets the currentPtY value for this Network3DViewGLViewInfo.
     *
     * @return currentPtY
     */
    public Double getCurrentPtY() {
        return currentPtY;
    }


    /**
     * Sets the currentPtY value for this Network3DViewGLViewInfo.
     *
     * @param currentPtY
     */
    public void setCurrentPtY(Double currentPtY) {
        this.currentPtY = currentPtY;
    }


    /**
     * Gets the network3DViewCtrlSetting value for this Network3DViewGLViewInfo.
     *
     * @return network3DViewCtrlSetting
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting getNetwork3DViewCtrlSetting() {
        return network3DViewCtrlSetting;
    }


    /**
     * Sets the network3DViewCtrlSetting value for this Network3DViewGLViewInfo.
     *
     * @param network3DViewCtrlSetting
     */
    public void setNetwork3DViewCtrlSetting(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting) {
        this.network3DViewCtrlSetting = network3DViewCtrlSetting;
    }


    /**
     * Gets the network3DViewCtrlSettingReference value for this Network3DViewGLViewInfo.
     *
     * @return network3DViewCtrlSettingReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal getNetwork3DViewCtrlSettingReference() {
        return network3DViewCtrlSettingReference;
    }


    /**
     * Sets the network3DViewCtrlSettingReference value for this Network3DViewGLViewInfo.
     *
     * @param network3DViewCtrlSettingReference
     */
    public void setNetwork3DViewCtrlSettingReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference) {
        this.network3DViewCtrlSettingReference = network3DViewCtrlSettingReference;
    }


    /**
     * Gets the zoomRectCenterX value for this Network3DViewGLViewInfo.
     *
     * @return zoomRectCenterX
     */
    public Double getZoomRectCenterX() {
        return zoomRectCenterX;
    }


    /**
     * Sets the zoomRectCenterX value for this Network3DViewGLViewInfo.
     *
     * @param zoomRectCenterX
     */
    public void setZoomRectCenterX(Double zoomRectCenterX) {
        this.zoomRectCenterX = zoomRectCenterX;
    }


    /**
     * Gets the zoomRectCenterY value for this Network3DViewGLViewInfo.
     *
     * @return zoomRectCenterY
     */
    public Double getZoomRectCenterY() {
        return zoomRectCenterY;
    }


    /**
     * Sets the zoomRectCenterY value for this Network3DViewGLViewInfo.
     *
     * @param zoomRectCenterY
     */
    public void setZoomRectCenterY(Double zoomRectCenterY) {
        this.zoomRectCenterY = zoomRectCenterY;
    }


    /**
     * Gets the zoomRectHeight value for this Network3DViewGLViewInfo.
     *
     * @return zoomRectHeight
     */
    public Double getZoomRectHeight() {
        return zoomRectHeight;
    }


    /**
     * Sets the zoomRectHeight value for this Network3DViewGLViewInfo.
     *
     * @param zoomRectHeight
     */
    public void setZoomRectHeight(Double zoomRectHeight) {
        this.zoomRectHeight = zoomRectHeight;
    }


    /**
     * Gets the zoomRectWidth value for this Network3DViewGLViewInfo.
     *
     * @return zoomRectWidth
     */
    public Double getZoomRectWidth() {
        return zoomRectWidth;
    }


    /**
     * Sets the zoomRectWidth value for this Network3DViewGLViewInfo.
     *
     * @param zoomRectWidth
     */
    public void setZoomRectWidth(Double zoomRectWidth) {
        this.zoomRectWidth = zoomRectWidth;
    }


    /**
     * Gets the zoomRectX value for this Network3DViewGLViewInfo.
     *
     * @return zoomRectX
     */
    public Double getZoomRectX() {
        return zoomRectX;
    }


    /**
     * Sets the zoomRectX value for this Network3DViewGLViewInfo.
     *
     * @param zoomRectX
     */
    public void setZoomRectX(Double zoomRectX) {
        this.zoomRectX = zoomRectX;
    }


    /**
     * Gets the zoomRectY value for this Network3DViewGLViewInfo.
     *
     * @return zoomRectY
     */
    public Double getZoomRectY() {
        return zoomRectY;
    }


    /**
     * Sets the zoomRectY value for this Network3DViewGLViewInfo.
     *
     * @param zoomRectY
     */
    public void setZoomRectY(Double zoomRectY) {
        this.zoomRectY = zoomRectY;
    }


    /**
     * Gets the rotX value for this Network3DViewGLViewInfo.
     *
     * @return rotX
     */
    public Double getRotX() {
        return rotX;
    }


    /**
     * Sets the rotX value for this Network3DViewGLViewInfo.
     *
     * @param rotX
     */
    public void setRotX(Double rotX) {
        this.rotX = rotX;
    }


    /**
     * Gets the rotY value for this Network3DViewGLViewInfo.
     *
     * @return rotY
     */
    public Double getRotY() {
        return rotY;
    }


    /**
     * Sets the rotY value for this Network3DViewGLViewInfo.
     *
     * @param rotY
     */
    public void setRotY(Double rotY) {
        this.rotY = rotY;
    }


    /**
     * Gets the settingId value for this Network3DViewGLViewInfo.
     *
     * @return settingId
     */
    public String getSettingId() {
        return settingId;
    }


    /**
     * Sets the settingId value for this Network3DViewGLViewInfo.
     *
     * @param settingId
     */
    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }


    /**
     * Gets the zModel value for this Network3DViewGLViewInfo.
     *
     * @return zModel
     */
    public Double getZModel() {
        return zModel;
    }


    /**
     * Sets the zModel value for this Network3DViewGLViewInfo.
     *
     * @param zModel
     */
    public void setZModel(Double zModel) {
        this.zModel = zModel;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
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
