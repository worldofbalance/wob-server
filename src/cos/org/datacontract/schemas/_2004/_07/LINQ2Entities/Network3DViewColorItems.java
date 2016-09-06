/**
 * Network3DViewColorItems.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Network3DViewColorItems  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private String itemName;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColors network3DViewColors;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewColorshovVakal network3DViewColorsReference;

    private Integer colorB;

    private Integer colorG;

    private Integer colorR;

    private String settingId;

    public Network3DViewColorItems() {
    }

    public Network3DViewColorItems(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           String itemName,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColors network3DViewColors,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewColorshovVakal network3DViewColorsReference,
           Integer colorB,
           Integer colorG,
           Integer colorR,
           String settingId) {
        super(
            id,
            ref,
            entityKey);
        this.itemName = itemName;
        this.network3DViewColors = network3DViewColors;
        this.network3DViewColorsReference = network3DViewColorsReference;
        this.colorB = colorB;
        this.colorG = colorG;
        this.colorR = colorR;
        this.settingId = settingId;
    }


    /**
     * Gets the itemName value for this Network3DViewColorItems.
     *
     * @return itemName
     */
    public String getItemName() {
        return itemName;
    }


    /**
     * Sets the itemName value for this Network3DViewColorItems.
     *
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    /**
     * Gets the network3DViewColors value for this Network3DViewColorItems.
     *
     * @return network3DViewColors
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColors getNetwork3DViewColors() {
        return network3DViewColors;
    }


    /**
     * Sets the network3DViewColors value for this Network3DViewColorItems.
     *
     * @param network3DViewColors
     */
    public void setNetwork3DViewColors(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewColors network3DViewColors) {
        this.network3DViewColors = network3DViewColors;
    }


    /**
     * Gets the network3DViewColorsReference value for this Network3DViewColorItems.
     *
     * @return network3DViewColorsReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewColorshovVakal getNetwork3DViewColorsReference() {
        return network3DViewColorsReference;
    }


    /**
     * Sets the network3DViewColorsReference value for this Network3DViewColorItems.
     *
     * @param network3DViewColorsReference
     */
    public void setNetwork3DViewColorsReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfNetwork3DViewColorshovVakal network3DViewColorsReference) {
        this.network3DViewColorsReference = network3DViewColorsReference;
    }


    /**
     * Gets the colorB value for this Network3DViewColorItems.
     *
     * @return colorB
     */
    public Integer getColorB() {
        return colorB;
    }


    /**
     * Sets the colorB value for this Network3DViewColorItems.
     *
     * @param colorB
     */
    public void setColorB(Integer colorB) {
        this.colorB = colorB;
    }


    /**
     * Gets the colorG value for this Network3DViewColorItems.
     *
     * @return colorG
     */
    public Integer getColorG() {
        return colorG;
    }


    /**
     * Sets the colorG value for this Network3DViewColorItems.
     *
     * @param colorG
     */
    public void setColorG(Integer colorG) {
        this.colorG = colorG;
    }


    /**
     * Gets the colorR value for this Network3DViewColorItems.
     *
     * @return colorR
     */
    public Integer getColorR() {
        return colorR;
    }


    /**
     * Sets the colorR value for this Network3DViewColorItems.
     *
     * @param colorR
     */
    public void setColorR(Integer colorR) {
        this.colorR = colorR;
    }


    /**
     * Gets the settingId value for this Network3DViewColorItems.
     *
     * @return settingId
     */
    public String getSettingId() {
        return settingId;
    }


    /**
     * Sets the settingId value for this Network3DViewColorItems.
     *
     * @param settingId
     */
    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Network3DViewColorItems)) return false;
        Network3DViewColorItems other = (Network3DViewColorItems) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.itemName==null && other.getItemName()==null) ||
             (this.itemName!=null &&
              this.itemName.equals(other.getItemName()))) &&
            ((this.network3DViewColors==null && other.getNetwork3DViewColors()==null) ||
             (this.network3DViewColors!=null &&
              this.network3DViewColors.equals(other.getNetwork3DViewColors()))) &&
            ((this.network3DViewColorsReference==null && other.getNetwork3DViewColorsReference()==null) ||
             (this.network3DViewColorsReference!=null &&
              this.network3DViewColorsReference.equals(other.getNetwork3DViewColorsReference()))) &&
            ((this.colorB==null && other.getColorB()==null) ||
             (this.colorB!=null &&
              this.colorB.equals(other.getColorB()))) &&
            ((this.colorG==null && other.getColorG()==null) ||
             (this.colorG!=null &&
              this.colorG.equals(other.getColorG()))) &&
            ((this.colorR==null && other.getColorR()==null) ||
             (this.colorR!=null &&
              this.colorR.equals(other.getColorR()))) &&
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
        if (getItemName() != null) {
            _hashCode += getItemName().hashCode();
        }
        if (getNetwork3DViewColors() != null) {
            _hashCode += getNetwork3DViewColors().hashCode();
        }
        if (getNetwork3DViewColorsReference() != null) {
            _hashCode += getNetwork3DViewColorsReference().hashCode();
        }
        if (getColorB() != null) {
            _hashCode += getColorB().hashCode();
        }
        if (getColorG() != null) {
            _hashCode += getColorG().hashCode();
        }
        if (getColorR() != null) {
            _hashCode += getColorR().hashCode();
        }
        if (getSettingId() != null) {
            _hashCode += getSettingId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Network3DViewColorItems.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewColorItems"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ItemName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("colorB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "colorB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colorG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "colorG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colorR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "colorR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
