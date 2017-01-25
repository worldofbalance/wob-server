/**
 * Network3DViewCtrlSettingResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.WCFService_Portal;

public class Network3DViewCtrlSettingResponse  extends shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response  implements java.io.Serializable {
    private java.lang.Integer curPage;

    private java.lang.Boolean DESC;

    private java.lang.String message;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting[] network3DViewCtrlSettings;

    private java.lang.String orderBy;

    private java.lang.Integer pageAvailable;

    public Network3DViewCtrlSettingResponse() {
    }

    public Network3DViewCtrlSettingResponse(
           java.lang.String _message,
           java.lang.Integer curPage,
           java.lang.Boolean DESC,
           java.lang.String message,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting[] network3DViewCtrlSettings,
           java.lang.String orderBy,
           java.lang.Integer pageAvailable) {
        super(
            _message);
        this.curPage = curPage;
        this.DESC = DESC;
        this.message = message;
        this.network3DViewCtrlSettings = network3DViewCtrlSettings;
        this.orderBy = orderBy;
        this.pageAvailable = pageAvailable;
    }


    /**
     * Gets the curPage value for this Network3DViewCtrlSettingResponse.
     * 
     * @return curPage
     */
    public java.lang.Integer getCurPage() {
        return curPage;
    }


    /**
     * Sets the curPage value for this Network3DViewCtrlSettingResponse.
     * 
     * @param curPage
     */
    public void setCurPage(java.lang.Integer curPage) {
        this.curPage = curPage;
    }


    /**
     * Gets the DESC value for this Network3DViewCtrlSettingResponse.
     * 
     * @return DESC
     */
    public java.lang.Boolean getDESC() {
        return DESC;
    }


    /**
     * Sets the DESC value for this Network3DViewCtrlSettingResponse.
     * 
     * @param DESC
     */
    public void setDESC(java.lang.Boolean DESC) {
        this.DESC = DESC;
    }


    /**
     * Gets the message value for this Network3DViewCtrlSettingResponse.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this Network3DViewCtrlSettingResponse.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the network3DViewCtrlSettings value for this Network3DViewCtrlSettingResponse.
     * 
     * @return network3DViewCtrlSettings
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting[] getNetwork3DViewCtrlSettings() {
        return network3DViewCtrlSettings;
    }


    /**
     * Sets the network3DViewCtrlSettings value for this Network3DViewCtrlSettingResponse.
     * 
     * @param network3DViewCtrlSettings
     */
    public void setNetwork3DViewCtrlSettings(shared.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting[] network3DViewCtrlSettings) {
        this.network3DViewCtrlSettings = network3DViewCtrlSettings;
    }


    /**
     * Gets the orderBy value for this Network3DViewCtrlSettingResponse.
     * 
     * @return orderBy
     */
    public java.lang.String getOrderBy() {
        return orderBy;
    }


    /**
     * Sets the orderBy value for this Network3DViewCtrlSettingResponse.
     * 
     * @param orderBy
     */
    public void setOrderBy(java.lang.String orderBy) {
        this.orderBy = orderBy;
    }


    /**
     * Gets the pageAvailable value for this Network3DViewCtrlSettingResponse.
     * 
     * @return pageAvailable
     */
    public java.lang.Integer getPageAvailable() {
        return pageAvailable;
    }


    /**
     * Sets the pageAvailable value for this Network3DViewCtrlSettingResponse.
     * 
     * @param pageAvailable
     */
    public void setPageAvailable(java.lang.Integer pageAvailable) {
        this.pageAvailable = pageAvailable;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Network3DViewCtrlSettingResponse)) return false;
        Network3DViewCtrlSettingResponse other = (Network3DViewCtrlSettingResponse) obj;
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
            ((this.DESC==null && other.getDESC()==null) || 
             (this.DESC!=null &&
              this.DESC.equals(other.getDESC()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.network3DViewCtrlSettings==null && other.getNetwork3DViewCtrlSettings()==null) || 
             (this.network3DViewCtrlSettings!=null &&
              java.util.Arrays.equals(this.network3DViewCtrlSettings, other.getNetwork3DViewCtrlSettings()))) &&
            ((this.orderBy==null && other.getOrderBy()==null) || 
             (this.orderBy!=null &&
              this.orderBy.equals(other.getOrderBy()))) &&
            ((this.pageAvailable==null && other.getPageAvailable()==null) || 
             (this.pageAvailable!=null &&
              this.pageAvailable.equals(other.getPageAvailable())));
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
        if (getDESC() != null) {
            _hashCode += getDESC().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getNetwork3DViewCtrlSettings() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNetwork3DViewCtrlSettings());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNetwork3DViewCtrlSettings(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOrderBy() != null) {
            _hashCode += getOrderBy().hashCode();
        }
        if (getPageAvailable() != null) {
            _hashCode += getPageAvailable().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Network3DViewCtrlSettingResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Network3DViewCtrlSettingResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curPage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "CurPage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "DESC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("network3DViewCtrlSettings");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Network3DViewCtrlSettings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewCtrlSetting"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewCtrlSetting"));
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
