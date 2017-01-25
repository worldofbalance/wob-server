/**
 * LoginRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.WCFService_Portal;

public class LoginRequest  extends shared.org.datacontract.schemas._2004._07.WCFService_Portal.Request  implements java.io.Serializable {
    private java.util.Calendar loginDateTime;

    private int loginMode;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    public LoginRequest() {
    }

    public LoginRequest(
           java.util.Calendar loginDateTime,
           int loginMode,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.loginDateTime = loginDateTime;
        this.loginMode = loginMode;
        this.user = user;
    }


    /**
     * Gets the loginDateTime value for this LoginRequest.
     * 
     * @return loginDateTime
     */
    public java.util.Calendar getLoginDateTime() {
        return loginDateTime;
    }


    /**
     * Sets the loginDateTime value for this LoginRequest.
     * 
     * @param loginDateTime
     */
    public void setLoginDateTime(java.util.Calendar loginDateTime) {
        this.loginDateTime = loginDateTime;
    }


    /**
     * Gets the loginMode value for this LoginRequest.
     * 
     * @return loginMode
     */
    public int getLoginMode() {
        return loginMode;
    }


    /**
     * Sets the loginMode value for this LoginRequest.
     * 
     * @param loginMode
     */
    public void setLoginMode(int loginMode) {
        this.loginMode = loginMode;
    }


    /**
     * Gets the user value for this LoginRequest.
     * 
     * @return user
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this LoginRequest.
     * 
     * @param user
     */
    public void setUser(shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LoginRequest)) return false;
        LoginRequest other = (LoginRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.loginDateTime==null && other.getLoginDateTime()==null) || 
             (this.loginDateTime!=null &&
              this.loginDateTime.equals(other.getLoginDateTime()))) &&
            this.loginMode == other.getLoginMode() &&
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
        if (getLoginDateTime() != null) {
            _hashCode += getLoginDateTime().hashCode();
        }
        _hashCode += getLoginMode();
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LoginRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LoginRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LoginDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LoginMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
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
