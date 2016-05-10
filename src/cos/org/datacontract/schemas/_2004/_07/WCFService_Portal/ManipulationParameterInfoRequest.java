/**
 * ManipulationParameterInfoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class ManipulationParameterInfoRequest  extends cos.org.datacontract.schemas._2004._07.WCFService_Portal.Request  implements java.io.Serializable {
    private String manipulationId;

    private int mode;

    private Integer page;

    private Integer pageSize;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    public ManipulationParameterInfoRequest() {
    }

    public ManipulationParameterInfoRequest(
           String manipulationId,
           int mode,
           Integer page,
           Integer pageSize,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.manipulationId = manipulationId;
        this.mode = mode;
        this.page = page;
        this.pageSize = pageSize;
        this.user = user;
    }


    /**
     * Gets the manipulationId value for this ManipulationParameterInfoRequest.
     *
     * @return manipulationId
     */
    public String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationParameterInfoRequest.
     *
     * @param manipulationId
     */
    public void setManipulationId(String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the mode value for this ManipulationParameterInfoRequest.
     *
     * @return mode
     */
    public int getMode() {
        return mode;
    }


    /**
     * Sets the mode value for this ManipulationParameterInfoRequest.
     *
     * @param mode
     */
    public void setMode(int mode) {
        this.mode = mode;
    }


    /**
     * Gets the page value for this ManipulationParameterInfoRequest.
     *
     * @return page
     */
    public Integer getPage() {
        return page;
    }


    /**
     * Sets the page value for this ManipulationParameterInfoRequest.
     *
     * @param page
     */
    public void setPage(Integer page) {
        this.page = page;
    }


    /**
     * Gets the pageSize value for this ManipulationParameterInfoRequest.
     *
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }


    /**
     * Sets the pageSize value for this ManipulationParameterInfoRequest.
     *
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * Gets the user value for this ManipulationParameterInfoRequest.
     *
     * @return user
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this ManipulationParameterInfoRequest.
     *
     * @param user
     */
    public void setUser(cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulationParameterInfoRequest)) return false;
        ManipulationParameterInfoRequest other = (ManipulationParameterInfoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.manipulationId==null && other.getManipulationId()==null) ||
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            this.mode == other.getMode() &&
            ((this.page==null && other.getPage()==null) ||
             (this.page!=null &&
              this.page.equals(other.getPage()))) &&
            ((this.pageSize==null && other.getPageSize()==null) ||
             (this.pageSize!=null &&
              this.pageSize.equals(other.getPageSize()))) &&
            ((this.user==null && other.getUser()==null) ||
             (this.user!=null &&
              this.user.equals(other.getUser())));
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
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        _hashCode += getMode();
        if (getPage() != null) {
            _hashCode += getPage().hashCode();
        }
        if (getPageSize() != null) {
            _hashCode += getPageSize().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationParameterInfoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationParameterInfoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Mode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("page");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Page"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "PageSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
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
