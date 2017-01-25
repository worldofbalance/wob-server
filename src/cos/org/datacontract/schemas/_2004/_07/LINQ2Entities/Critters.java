/**
 * Critters.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Critters  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes[] nodes;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.TextureImages[] textureImages;

    private java.util.Calendar creationDate;

    private String critterName;

    private byte[] critterObject;

    private Integer download;

    private Integer foodwebType;

    private String id;

    private String nickName;

    private Integer size;

    private Integer trophicLevelCode;

    public Critters() {
    }

    public Critters(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes[] nodes,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.TextureImages[] textureImages,
           java.util.Calendar creationDate,
           String critterName,
           byte[] critterObject,
           Integer download,
           Integer foodwebType,
           String id,
           String nickName,
           Integer size,
           Integer trophicLevelCode) {
        super(
            _id,
            ref,
            entityKey);
        this.nodes = nodes;
        this.textureImages = textureImages;
        this.creationDate = creationDate;
        this.critterName = critterName;
        this.critterObject = critterObject;
        this.download = download;
        this.foodwebType = foodwebType;
        this.id = id;
        this.nickName = nickName;
        this.size = size;
        this.trophicLevelCode = trophicLevelCode;
    }


    /**
     * Gets the nodes value for this Critters.
     *
     * @return nodes
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes[] getNodes() {
        return nodes;
    }


    /**
     * Sets the nodes value for this Critters.
     *
     * @param nodes
     */
    public void setNodes(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes[] nodes) {
        this.nodes = nodes;
    }


    /**
     * Gets the textureImages value for this Critters.
     *
     * @return textureImages
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.TextureImages[] getTextureImages() {
        return textureImages;
    }


    /**
     * Sets the textureImages value for this Critters.
     *
     * @param textureImages
     */
    public void setTextureImages(cos.org.datacontract.schemas._2004._07.LINQ2Entities.TextureImages[] textureImages) {
        this.textureImages = textureImages;
    }


    /**
     * Gets the creationDate value for this Critters.
     *
     * @return creationDate
     */
    public java.util.Calendar getCreationDate() {
        return creationDate;
    }


    /**
     * Sets the creationDate value for this Critters.
     *
     * @param creationDate
     */
    public void setCreationDate(java.util.Calendar creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Gets the critterName value for this Critters.
     *
     * @return critterName
     */
    public String getCritterName() {
        return critterName;
    }


    /**
     * Sets the critterName value for this Critters.
     *
     * @param critterName
     */
    public void setCritterName(String critterName) {
        this.critterName = critterName;
    }


    /**
     * Gets the critterObject value for this Critters.
     *
     * @return critterObject
     */
    public byte[] getCritterObject() {
        return critterObject;
    }


    /**
     * Sets the critterObject value for this Critters.
     *
     * @param critterObject
     */
    public void setCritterObject(byte[] critterObject) {
        this.critterObject = critterObject;
    }


    /**
     * Gets the download value for this Critters.
     *
     * @return download
     */
    public Integer getDownload() {
        return download;
    }


    /**
     * Sets the download value for this Critters.
     *
     * @param download
     */
    public void setDownload(Integer download) {
        this.download = download;
    }


    /**
     * Gets the foodwebType value for this Critters.
     *
     * @return foodwebType
     */
    public Integer getFoodwebType() {
        return foodwebType;
    }


    /**
     * Sets the foodwebType value for this Critters.
     *
     * @param foodwebType
     */
    public void setFoodwebType(Integer foodwebType) {
        this.foodwebType = foodwebType;
    }


    /**
     * Gets the id value for this Critters.
     *
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this Critters.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets the nickName value for this Critters.
     *
     * @return nickName
     */
    public String getNickName() {
        return nickName;
    }


    /**
     * Sets the nickName value for this Critters.
     *
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    /**
     * Gets the size value for this Critters.
     *
     * @return size
     */
    public Integer getSize() {
        return size;
    }


    /**
     * Sets the size value for this Critters.
     *
     * @param size
     */
    public void setSize(Integer size) {
        this.size = size;
    }


    /**
     * Gets the trophicLevelCode value for this Critters.
     *
     * @return trophicLevelCode
     */
    public Integer getTrophicLevelCode() {
        return trophicLevelCode;
    }


    /**
     * Sets the trophicLevelCode value for this Critters.
     *
     * @param trophicLevelCode
     */
    public void setTrophicLevelCode(Integer trophicLevelCode) {
        this.trophicLevelCode = trophicLevelCode;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Critters)) return false;
        Critters other = (Critters) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.nodes==null && other.getNodes()==null) ||
             (this.nodes!=null &&
              java.util.Arrays.equals(this.nodes, other.getNodes()))) &&
            ((this.textureImages==null && other.getTextureImages()==null) ||
             (this.textureImages!=null &&
              java.util.Arrays.equals(this.textureImages, other.getTextureImages()))) &&
            ((this.creationDate==null && other.getCreationDate()==null) ||
             (this.creationDate!=null &&
              this.creationDate.equals(other.getCreationDate()))) &&
            ((this.critterName==null && other.getCritterName()==null) ||
             (this.critterName!=null &&
              this.critterName.equals(other.getCritterName()))) &&
            ((this.critterObject==null && other.getCritterObject()==null) ||
             (this.critterObject!=null &&
              java.util.Arrays.equals(this.critterObject, other.getCritterObject()))) &&
            ((this.download==null && other.getDownload()==null) ||
             (this.download!=null &&
              this.download.equals(other.getDownload()))) &&
            ((this.foodwebType==null && other.getFoodwebType()==null) ||
             (this.foodwebType!=null &&
              this.foodwebType.equals(other.getFoodwebType()))) &&
            ((this.id==null && other.getId()==null) ||
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.nickName==null && other.getNickName()==null) ||
             (this.nickName!=null &&
              this.nickName.equals(other.getNickName()))) &&
            ((this.size==null && other.getSize()==null) ||
             (this.size!=null &&
              this.size.equals(other.getSize()))) &&
            ((this.trophicLevelCode==null && other.getTrophicLevelCode()==null) ||
             (this.trophicLevelCode!=null &&
              this.trophicLevelCode.equals(other.getTrophicLevelCode())));
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
        if (getNodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNodes());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getNodes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTextureImages() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTextureImages());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getTextureImages(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCreationDate() != null) {
            _hashCode += getCreationDate().hashCode();
        }
        if (getCritterName() != null) {
            _hashCode += getCritterName().hashCode();
        }
        if (getCritterObject() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCritterObject());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCritterObject(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDownload() != null) {
            _hashCode += getDownload().hashCode();
        }
        if (getFoodwebType() != null) {
            _hashCode += getFoodwebType().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNickName() != null) {
            _hashCode += getNickName().hashCode();
        }
        if (getSize() != null) {
            _hashCode += getSize().hashCode();
        }
        if (getTrophicLevelCode() != null) {
            _hashCode += getTrophicLevelCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Critters.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Critters"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Nodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Nodes"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Nodes"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textureImages");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "TextureImages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "TextureImages"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "TextureImages"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "creationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("critterName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "critterName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("critterObject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "critterObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("download");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "download"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foodwebType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "foodwebType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nickName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "nickName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("size");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "size"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trophicLevelCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "trophicLevelCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
