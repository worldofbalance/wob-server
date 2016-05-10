/**
 * Network3DViewPositions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Network3DViewPositions  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private Double aspectRatio;

    private Integer coordinates;

    private Double damping;

    private String depth;

    private String LRProperty;

    private String layoutType;

    private Integer leftRight;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference;

    private Double repulsion;

    private Double springConst;

    private Integer springType;

    private String stdHeight;

    private Integer XNHeight;

    private String settingId;

    public Network3DViewPositions() {
    }

    public Network3DViewPositions(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           Double aspectRatio,
           Integer coordinates,
           Double damping,
           String depth,
           String LRProperty,
           String layoutType,
           Integer leftRight,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference,
           Double repulsion,
           Double springConst,
           Integer springType,
           String stdHeight,
           Integer XNHeight,
           String settingId) {
        super(
            id,
            ref,
            entityKey);
        this.aspectRatio = aspectRatio;
        this.coordinates = coordinates;
        this.damping = damping;
        this.depth = depth;
        this.LRProperty = LRProperty;
        this.layoutType = layoutType;
        this.leftRight = leftRight;
        this.network3DViewCtrlSetting = network3DViewCtrlSetting;
        this.network3DViewCtrlSettingReference = network3DViewCtrlSettingReference;
        this.repulsion = repulsion;
        this.springConst = springConst;
        this.springType = springType;
        this.stdHeight = stdHeight;
        this.XNHeight = XNHeight;
        this.settingId = settingId;
    }


    /**
     * Gets the aspectRatio value for this Network3DViewPositions.
     *
     * @return aspectRatio
     */
    public Double getAspectRatio() {
        return aspectRatio;
    }


    /**
     * Sets the aspectRatio value for this Network3DViewPositions.
     *
     * @param aspectRatio
     */
    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }


    /**
     * Gets the coordinates value for this Network3DViewPositions.
     *
     * @return coordinates
     */
    public Integer getCoordinates() {
        return coordinates;
    }


    /**
     * Sets the coordinates value for this Network3DViewPositions.
     *
     * @param coordinates
     */
    public void setCoordinates(Integer coordinates) {
        this.coordinates = coordinates;
    }


    /**
     * Gets the damping value for this Network3DViewPositions.
     *
     * @return damping
     */
    public Double getDamping() {
        return damping;
    }


    /**
     * Sets the damping value for this Network3DViewPositions.
     *
     * @param damping
     */
    public void setDamping(Double damping) {
        this.damping = damping;
    }


    /**
     * Gets the depth value for this Network3DViewPositions.
     *
     * @return depth
     */
    public String getDepth() {
        return depth;
    }


    /**
     * Sets the depth value for this Network3DViewPositions.
     *
     * @param depth
     */
    public void setDepth(String depth) {
        this.depth = depth;
    }


    /**
     * Gets the LRProperty value for this Network3DViewPositions.
     *
     * @return LRProperty
     */
    public String getLRProperty() {
        return LRProperty;
    }


    /**
     * Sets the LRProperty value for this Network3DViewPositions.
     *
     * @param LRProperty
     */
    public void setLRProperty(String LRProperty) {
        this.LRProperty = LRProperty;
    }


    /**
     * Gets the layoutType value for this Network3DViewPositions.
     *
     * @return layoutType
     */
    public String getLayoutType() {
        return layoutType;
    }


    /**
     * Sets the layoutType value for this Network3DViewPositions.
     *
     * @param layoutType
     */
    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }


    /**
     * Gets the leftRight value for this Network3DViewPositions.
     *
     * @return leftRight
     */
    public Integer getLeftRight() {
        return leftRight;
    }


    /**
     * Sets the leftRight value for this Network3DViewPositions.
     *
     * @param leftRight
     */
    public void setLeftRight(Integer leftRight) {
        this.leftRight = leftRight;
    }


    /**
     * Gets the network3DViewCtrlSetting value for this Network3DViewPositions.
     *
     * @return network3DViewCtrlSetting
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting getNetwork3DViewCtrlSetting() {
        return network3DViewCtrlSetting;
    }


    /**
     * Sets the network3DViewCtrlSetting value for this Network3DViewPositions.
     *
     * @param network3DViewCtrlSetting
     */
    public void setNetwork3DViewCtrlSetting(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting network3DViewCtrlSetting) {
        this.network3DViewCtrlSetting = network3DViewCtrlSetting;
    }


    /**
     * Gets the network3DViewCtrlSettingReference value for this Network3DViewPositions.
     *
     * @return network3DViewCtrlSettingReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal getNetwork3DViewCtrlSettingReference() {
        return network3DViewCtrlSettingReference;
    }


    /**
     * Sets the network3DViewCtrlSettingReference value for this Network3DViewPositions.
     *
     * @param network3DViewCtrlSettingReference
     */
    public void setNetwork3DViewCtrlSettingReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewCtrlSettinghovVakal network3DViewCtrlSettingReference) {
        this.network3DViewCtrlSettingReference = network3DViewCtrlSettingReference;
    }


    /**
     * Gets the repulsion value for this Network3DViewPositions.
     *
     * @return repulsion
     */
    public Double getRepulsion() {
        return repulsion;
    }


    /**
     * Sets the repulsion value for this Network3DViewPositions.
     *
     * @param repulsion
     */
    public void setRepulsion(Double repulsion) {
        this.repulsion = repulsion;
    }


    /**
     * Gets the springConst value for this Network3DViewPositions.
     *
     * @return springConst
     */
    public Double getSpringConst() {
        return springConst;
    }


    /**
     * Sets the springConst value for this Network3DViewPositions.
     *
     * @param springConst
     */
    public void setSpringConst(Double springConst) {
        this.springConst = springConst;
    }


    /**
     * Gets the springType value for this Network3DViewPositions.
     *
     * @return springType
     */
    public Integer getSpringType() {
        return springType;
    }


    /**
     * Sets the springType value for this Network3DViewPositions.
     *
     * @param springType
     */
    public void setSpringType(Integer springType) {
        this.springType = springType;
    }


    /**
     * Gets the stdHeight value for this Network3DViewPositions.
     *
     * @return stdHeight
     */
    public String getStdHeight() {
        return stdHeight;
    }


    /**
     * Sets the stdHeight value for this Network3DViewPositions.
     *
     * @param stdHeight
     */
    public void setStdHeight(String stdHeight) {
        this.stdHeight = stdHeight;
    }


    /**
     * Gets the XNHeight value for this Network3DViewPositions.
     *
     * @return XNHeight
     */
    public Integer getXNHeight() {
        return XNHeight;
    }


    /**
     * Sets the XNHeight value for this Network3DViewPositions.
     *
     * @param XNHeight
     */
    public void setXNHeight(Integer XNHeight) {
        this.XNHeight = XNHeight;
    }


    /**
     * Gets the settingId value for this Network3DViewPositions.
     *
     * @return settingId
     */
    public String getSettingId() {
        return settingId;
    }


    /**
     * Sets the settingId value for this Network3DViewPositions.
     *
     * @param settingId
     */
    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Network3DViewPositions)) return false;
        Network3DViewPositions other = (Network3DViewPositions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.aspectRatio==null && other.getAspectRatio()==null) ||
             (this.aspectRatio!=null &&
              this.aspectRatio.equals(other.getAspectRatio()))) &&
            ((this.coordinates==null && other.getCoordinates()==null) ||
             (this.coordinates!=null &&
              this.coordinates.equals(other.getCoordinates()))) &&
            ((this.damping==null && other.getDamping()==null) ||
             (this.damping!=null &&
              this.damping.equals(other.getDamping()))) &&
            ((this.depth==null && other.getDepth()==null) ||
             (this.depth!=null &&
              this.depth.equals(other.getDepth()))) &&
            ((this.LRProperty==null && other.getLRProperty()==null) ||
             (this.LRProperty!=null &&
              this.LRProperty.equals(other.getLRProperty()))) &&
            ((this.layoutType==null && other.getLayoutType()==null) ||
             (this.layoutType!=null &&
              this.layoutType.equals(other.getLayoutType()))) &&
            ((this.leftRight==null && other.getLeftRight()==null) ||
             (this.leftRight!=null &&
              this.leftRight.equals(other.getLeftRight()))) &&
            ((this.network3DViewCtrlSetting==null && other.getNetwork3DViewCtrlSetting()==null) ||
             (this.network3DViewCtrlSetting!=null &&
              this.network3DViewCtrlSetting.equals(other.getNetwork3DViewCtrlSetting()))) &&
            ((this.network3DViewCtrlSettingReference==null && other.getNetwork3DViewCtrlSettingReference()==null) ||
             (this.network3DViewCtrlSettingReference!=null &&
              this.network3DViewCtrlSettingReference.equals(other.getNetwork3DViewCtrlSettingReference()))) &&
            ((this.repulsion==null && other.getRepulsion()==null) ||
             (this.repulsion!=null &&
              this.repulsion.equals(other.getRepulsion()))) &&
            ((this.springConst==null && other.getSpringConst()==null) ||
             (this.springConst!=null &&
              this.springConst.equals(other.getSpringConst()))) &&
            ((this.springType==null && other.getSpringType()==null) ||
             (this.springType!=null &&
              this.springType.equals(other.getSpringType()))) &&
            ((this.stdHeight==null && other.getStdHeight()==null) ||
             (this.stdHeight!=null &&
              this.stdHeight.equals(other.getStdHeight()))) &&
            ((this.XNHeight==null && other.getXNHeight()==null) ||
             (this.XNHeight!=null &&
              this.XNHeight.equals(other.getXNHeight()))) &&
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
        if (getAspectRatio() != null) {
            _hashCode += getAspectRatio().hashCode();
        }
        if (getCoordinates() != null) {
            _hashCode += getCoordinates().hashCode();
        }
        if (getDamping() != null) {
            _hashCode += getDamping().hashCode();
        }
        if (getDepth() != null) {
            _hashCode += getDepth().hashCode();
        }
        if (getLRProperty() != null) {
            _hashCode += getLRProperty().hashCode();
        }
        if (getLayoutType() != null) {
            _hashCode += getLayoutType().hashCode();
        }
        if (getLeftRight() != null) {
            _hashCode += getLeftRight().hashCode();
        }
        if (getNetwork3DViewCtrlSetting() != null) {
            _hashCode += getNetwork3DViewCtrlSetting().hashCode();
        }
        if (getNetwork3DViewCtrlSettingReference() != null) {
            _hashCode += getNetwork3DViewCtrlSettingReference().hashCode();
        }
        if (getRepulsion() != null) {
            _hashCode += getRepulsion().hashCode();
        }
        if (getSpringConst() != null) {
            _hashCode += getSpringConst().hashCode();
        }
        if (getSpringType() != null) {
            _hashCode += getSpringType().hashCode();
        }
        if (getStdHeight() != null) {
            _hashCode += getStdHeight().hashCode();
        }
        if (getXNHeight() != null) {
            _hashCode += getXNHeight().hashCode();
        }
        if (getSettingId() != null) {
            _hashCode += getSettingId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Network3DViewPositions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewPositions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aspectRatio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "AspectRatio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coordinates");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Coordinates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("damping");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Damping"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("depth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Depth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LRProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LRProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("layoutType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LayoutType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("leftRight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "LeftRight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("repulsion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Repulsion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("springConst");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "SpringConst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("springType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "SpringType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stdHeight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "StdHeight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XNHeight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "XNHeight"));
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
