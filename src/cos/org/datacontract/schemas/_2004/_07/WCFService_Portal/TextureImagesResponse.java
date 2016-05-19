/**
 * TextureImagesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class TextureImagesResponse  extends cos.org.datacontract.schemas._2004._07.WCFService_Portal.Response  implements java.io.Serializable {
    private Integer curPage;

    private String message;

    private String orderBy;

    private Integer pageAvailable;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.TextureImages[] textureImages;

    public TextureImagesResponse() {
    }

    public TextureImagesResponse(
           String _message,
           Integer curPage,
           String message,
           String orderBy,
           Integer pageAvailable,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.TextureImages[] textureImages) {
        super(
            _message);
        this.curPage = curPage;
        this.message = message;
        this.orderBy = orderBy;
        this.pageAvailable = pageAvailable;
        this.textureImages = textureImages;
    }


    /**
     * Gets the curPage value for this TextureImagesResponse.
     *
     * @return curPage
     */
    public Integer getCurPage() {
        return curPage;
    }


    /**
     * Sets the curPage value for this TextureImagesResponse.
     *
     * @param curPage
     */
    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }


    /**
     * Gets the message value for this TextureImagesResponse.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this TextureImagesResponse.
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Gets the orderBy value for this TextureImagesResponse.
     *
     * @return orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }


    /**
     * Sets the orderBy value for this TextureImagesResponse.
     *
     * @param orderBy
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


    /**
     * Gets the pageAvailable value for this TextureImagesResponse.
     *
     * @return pageAvailable
     */
    public Integer getPageAvailable() {
        return pageAvailable;
    }


    /**
     * Sets the pageAvailable value for this TextureImagesResponse.
     *
     * @param pageAvailable
     */
    public void setPageAvailable(Integer pageAvailable) {
        this.pageAvailable = pageAvailable;
    }


    /**
     * Gets the textureImages value for this TextureImagesResponse.
     *
     * @return textureImages
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.TextureImages[] getTextureImages() {
        return textureImages;
    }


    /**
     * Sets the textureImages value for this TextureImagesResponse.
     *
     * @param textureImages
     */
    public void setTextureImages(cos.org.datacontract.schemas._2004._07.LINQ2Entities.TextureImages[] textureImages) {
        this.textureImages = textureImages;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TextureImagesResponse)) return false;
        TextureImagesResponse other = (TextureImagesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.curPage==null && other.getCurPage()==null) ||
             (this.curPage!=null &&
              this.curPage.equals(other.getCurPage()))) &&
            ((this.message==null && other.getMessage()==null) ||
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.orderBy==null && other.getOrderBy()==null) ||
             (this.orderBy!=null &&
              this.orderBy.equals(other.getOrderBy()))) &&
            ((this.pageAvailable==null && other.getPageAvailable()==null) ||
             (this.pageAvailable!=null &&
              this.pageAvailable.equals(other.getPageAvailable()))) &&
            ((this.textureImages==null && other.getTextureImages()==null) ||
             (this.textureImages!=null &&
              java.util.Arrays.equals(this.textureImages, other.getTextureImages())));
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
        if (getCurPage() != null) {
            _hashCode += getCurPage().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getOrderBy() != null) {
            _hashCode += getOrderBy().hashCode();
        }
        if (getPageAvailable() != null) {
            _hashCode += getPageAvailable().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TextureImagesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TextureImagesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curPage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "CurPage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "OrderBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageAvailable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "PageAvailable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textureImages");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TextureImages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "TextureImages"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "TextureImages"));
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
