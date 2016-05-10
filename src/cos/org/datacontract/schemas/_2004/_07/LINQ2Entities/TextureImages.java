/**
 * TextureImages.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class TextureImages  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Critters critters;

    private cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCrittershovVakal crittersReference;

    private String critterId;

    private String id;

    private String imageName;

    private byte[] imageObject;

    private Integer size;

    public TextureImages() {
    }

    public TextureImages(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Critters critters,
           cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCrittershovVakal crittersReference,
           String critterId,
           String id,
           String imageName,
           byte[] imageObject,
           Integer size) {
        super(
            _id,
            ref,
            entityKey);
        this.critters = critters;
        this.crittersReference = crittersReference;
        this.critterId = critterId;
        this.id = id;
        this.imageName = imageName;
        this.imageObject = imageObject;
        this.size = size;
    }


    /**
     * Gets the critters value for this TextureImages.
     *
     * @return critters
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Critters getCritters() {
        return critters;
    }


    /**
     * Sets the critters value for this TextureImages.
     *
     * @param critters
     */
    public void setCritters(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Critters critters) {
        this.critters = critters;
    }


    /**
     * Gets the crittersReference value for this TextureImages.
     *
     * @return crittersReference
     */
    public cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCrittershovVakal getCrittersReference() {
        return crittersReference;
    }


    /**
     * Sets the crittersReference value for this TextureImages.
     *
     * @param crittersReference
     */
    public void setCrittersReference(cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCrittershovVakal crittersReference) {
        this.crittersReference = crittersReference;
    }


    /**
     * Gets the critterId value for this TextureImages.
     *
     * @return critterId
     */
    public String getCritterId() {
        return critterId;
    }


    /**
     * Sets the critterId value for this TextureImages.
     *
     * @param critterId
     */
    public void setCritterId(String critterId) {
        this.critterId = critterId;
    }


    /**
     * Gets the id value for this TextureImages.
     *
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this TextureImages.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets the imageName value for this TextureImages.
     *
     * @return imageName
     */
    public String getImageName() {
        return imageName;
    }


    /**
     * Sets the imageName value for this TextureImages.
     *
     * @param imageName
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    /**
     * Gets the imageObject value for this TextureImages.
     *
     * @return imageObject
     */
    public byte[] getImageObject() {
        return imageObject;
    }


    /**
     * Sets the imageObject value for this TextureImages.
     *
     * @param imageObject
     */
    public void setImageObject(byte[] imageObject) {
        this.imageObject = imageObject;
    }


    /**
     * Gets the size value for this TextureImages.
     *
     * @return size
     */
    public Integer getSize() {
        return size;
    }


    /**
     * Sets the size value for this TextureImages.
     *
     * @param size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TextureImages)) return false;
        TextureImages other = (TextureImages) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.critters==null && other.getCritters()==null) ||
             (this.critters!=null &&
              this.critters.equals(other.getCritters()))) &&
            ((this.crittersReference==null && other.getCrittersReference()==null) ||
             (this.crittersReference!=null &&
              this.crittersReference.equals(other.getCrittersReference()))) &&
            ((this.critterId==null && other.getCritterId()==null) ||
             (this.critterId!=null &&
              this.critterId.equals(other.getCritterId()))) &&
            ((this.id==null && other.getId()==null) ||
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.imageName==null && other.getImageName()==null) ||
             (this.imageName!=null &&
              this.imageName.equals(other.getImageName()))) &&
            ((this.imageObject==null && other.getImageObject()==null) ||
             (this.imageObject!=null &&
              java.util.Arrays.equals(this.imageObject, other.getImageObject()))) &&
            ((this.size==null && other.getSize()==null) ||
             (this.size!=null &&
              this.size.equals(other.getSize())));
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
        if (getCritters() != null) {
            _hashCode += getCritters().hashCode();
        }
        if (getCrittersReference() != null) {
            _hashCode += getCrittersReference().hashCode();
        }
        if (getCritterId() != null) {
            _hashCode += getCritterId().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getImageName() != null) {
            _hashCode += getImageName().hashCode();
        }
        if (getImageObject() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getImageObject());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getImageObject(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSize() != null) {
            _hashCode += getSize().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TextureImages.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "TextureImages"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("critters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Critters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Critters"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crittersReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "CrittersReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfCrittershovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("critterId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "critterId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "imageName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageObject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "imageObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
