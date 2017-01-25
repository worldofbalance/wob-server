/**
 * LoginResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class LoginResponse  extends cos.org.datacontract.schemas._2004._07.WCFService_Portal.Response  implements java.io.Serializable {
    private java.util.Calendar lastLoginDateTime;

    private String loginMessage;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    public LoginResponse() {
    }

    public LoginResponse(
           String message,
           java.util.Calendar lastLoginDateTime,
           String loginMessage,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        super(
            message);
        this.lastLoginDateTime = lastLoginDateTime;
        this.loginMessage = loginMessage;
        this.user = user;
    }


    /**
     * Gets the lastLoginDateTime value for this LoginResponse.
     *
     * @return lastLoginDateTime
     */
    public java.util.Calendar getLastLoginDateTime() {
        return lastLoginDateTime;
    }


    /**
     * Sets the lastLoginDateTime value for this LoginResponse.
     *
     * @param lastLoginDateTime
     */
    public void setLastLoginDateTime(java.util.Calendar lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
    }


    /**
     * Gets the loginMessage value for this LoginResponse.
     *
     * @return loginMessage
     */
    public String getLoginMessage() {
        return loginMessage;
    }


    /**
     * Sets the loginMessage value for this LoginResponse.
     *
     * @param loginMessage
     */
    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }


    /**
     * Gets the user value for this LoginResponse.
     *
     * @return user
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this LoginResponse.
     *
     * @param user
     */
    public void setUser(cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LoginResponse)) return false;
        LoginResponse other = (LoginResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.lastLoginDateTime==null && other.getLastLoginDateTime()==null) ||
             (this.lastLoginDateTime!=null &&
              this.lastLoginDateTime.equals(other.getLastLoginDateTime()))) &&
            ((this.loginMessage==null && other.getLoginMessage()==null) ||
             (this.loginMessage!=null &&
              this.loginMessage.equals(other.getLoginMessage()))) &&
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
        if (getLastLoginDateTime() != null) {
            _hashCode += getLastLoginDateTime().hashCode();
        }
        if (getLoginMessage() != null) {
            _hashCode += getLoginMessage().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LoginResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LoginResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastLoginDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LastLoginDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LoginMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
