/**
 * User.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.LINQ2Entities;

public class User  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private Integer authoriziedMode;

    private String firstname;

    private String lastname;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationRequest[] manipulationRequest;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting[] network3DViewCtrlSetting;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkACL[] networkACL;

    private String password;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.UserLoginInfo[] userLoginInfo;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.UserVote[] userVote;

    private String username;

    private String email;

    public User() {
    }

    public User(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           Integer authoriziedMode,
           String firstname,
           String lastname,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationRequest[] manipulationRequest,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting[] network3DViewCtrlSetting,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkACL[] networkACL,
           String password,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.UserLoginInfo[] userLoginInfo,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.UserVote[] userVote,
           String username,
           String email) {
        super(
            id,
            ref,
            entityKey);
        this.authoriziedMode = authoriziedMode;
        this.firstname = firstname;
        this.lastname = lastname;
        this.manipulationRequest = manipulationRequest;
        this.network3DViewCtrlSetting = network3DViewCtrlSetting;
        this.networkACL = networkACL;
        this.password = password;
        this.userLoginInfo = userLoginInfo;
        this.userVote = userVote;
        this.username = username;
        this.email = email;
    }


    /**
     * Gets the authoriziedMode value for this User.
     *
     * @return authoriziedMode
     */
    public Integer getAuthoriziedMode() {
        return authoriziedMode;
    }


    /**
     * Sets the authoriziedMode value for this User.
     *
     * @param authoriziedMode
     */
    public void setAuthoriziedMode(Integer authoriziedMode) {
        this.authoriziedMode = authoriziedMode;
    }


    /**
     * Gets the firstname value for this User.
     *
     * @return firstname
     */
    public String getFirstname() {
        return firstname;
    }


    /**
     * Sets the firstname value for this User.
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    /**
     * Gets the lastname value for this User.
     *
     * @return lastname
     */
    public String getLastname() {
        return lastname;
    }


    /**
     * Sets the lastname value for this User.
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    /**
     * Gets the manipulationRequest value for this User.
     *
     * @return manipulationRequest
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationRequest[] getManipulationRequest() {
        return manipulationRequest;
    }


    /**
     * Sets the manipulationRequest value for this User.
     *
     * @param manipulationRequest
     */
    public void setManipulationRequest(cos.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationRequest[] manipulationRequest) {
        this.manipulationRequest = manipulationRequest;
    }


    /**
     * Gets the network3DViewCtrlSetting value for this User.
     *
     * @return network3DViewCtrlSetting
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting[] getNetwork3DViewCtrlSetting() {
        return network3DViewCtrlSetting;
    }


    /**
     * Sets the network3DViewCtrlSetting value for this User.
     *
     * @param network3DViewCtrlSetting
     */
    public void setNetwork3DViewCtrlSetting(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Network3DViewCtrlSetting[] network3DViewCtrlSetting) {
        this.network3DViewCtrlSetting = network3DViewCtrlSetting;
    }


    /**
     * Gets the networkACL value for this User.
     *
     * @return networkACL
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkACL[] getNetworkACL() {
        return networkACL;
    }


    /**
     * Sets the networkACL value for this User.
     *
     * @param networkACL
     */
    public void setNetworkACL(cos.org.datacontract.schemas._2004._07.LINQ2Entities.NetworkACL[] networkACL) {
        this.networkACL = networkACL;
    }


    /**
     * Gets the password value for this User.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this User.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Gets the userLoginInfo value for this User.
     *
     * @return userLoginInfo
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.UserLoginInfo[] getUserLoginInfo() {
        return userLoginInfo;
    }


    /**
     * Sets the userLoginInfo value for this User.
     *
     * @param userLoginInfo
     */
    public void setUserLoginInfo(cos.org.datacontract.schemas._2004._07.LINQ2Entities.UserLoginInfo[] userLoginInfo) {
        this.userLoginInfo = userLoginInfo;
    }


    /**
     * Gets the userVote value for this User.
     *
     * @return userVote
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.UserVote[] getUserVote() {
        return userVote;
    }


    /**
     * Sets the userVote value for this User.
     *
     * @param userVote
     */
    public void setUserVote(cos.org.datacontract.schemas._2004._07.LINQ2Entities.UserVote[] userVote) {
        this.userVote = userVote;
    }


    /**
     * Gets the username value for this User.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this User.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Gets the email value for this User.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this User.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.authoriziedMode==null && other.getAuthoriziedMode()==null) ||
             (this.authoriziedMode!=null &&
              this.authoriziedMode.equals(other.getAuthoriziedMode()))) &&
            ((this.firstname==null && other.getFirstname()==null) ||
             (this.firstname!=null &&
              this.firstname.equals(other.getFirstname()))) &&
            ((this.lastname==null && other.getLastname()==null) ||
             (this.lastname!=null &&
              this.lastname.equals(other.getLastname()))) &&
            ((this.manipulationRequest==null && other.getManipulationRequest()==null) ||
             (this.manipulationRequest!=null &&
              java.util.Arrays.equals(this.manipulationRequest, other.getManipulationRequest()))) &&
            ((this.network3DViewCtrlSetting==null && other.getNetwork3DViewCtrlSetting()==null) ||
             (this.network3DViewCtrlSetting!=null &&
              java.util.Arrays.equals(this.network3DViewCtrlSetting, other.getNetwork3DViewCtrlSetting()))) &&
            ((this.networkACL==null && other.getNetworkACL()==null) ||
             (this.networkACL!=null &&
              java.util.Arrays.equals(this.networkACL, other.getNetworkACL()))) &&
            ((this.password==null && other.getPassword()==null) ||
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.userLoginInfo==null && other.getUserLoginInfo()==null) ||
             (this.userLoginInfo!=null &&
              java.util.Arrays.equals(this.userLoginInfo, other.getUserLoginInfo()))) &&
            ((this.userVote==null && other.getUserVote()==null) ||
             (this.userVote!=null &&
              java.util.Arrays.equals(this.userVote, other.getUserVote()))) &&
            ((this.username==null && other.getUsername()==null) ||
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.email==null && other.getEmail()==null) ||
             (this.email!=null &&
              this.email.equals(other.getEmail())));
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
        if (getAuthoriziedMode() != null) {
            _hashCode += getAuthoriziedMode().hashCode();
        }
        if (getFirstname() != null) {
            _hashCode += getFirstname().hashCode();
        }
        if (getLastname() != null) {
            _hashCode += getLastname().hashCode();
        }
        if (getManipulationRequest() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationRequest());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getManipulationRequest(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNetwork3DViewCtrlSetting() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNetwork3DViewCtrlSetting());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getNetwork3DViewCtrlSetting(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNetworkACL() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNetworkACL());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getNetworkACL(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getUserLoginInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUserLoginInfo());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getUserLoginInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUserVote() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUserVote());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getUserVote(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(User.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authoriziedMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "AuthoriziedMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Firstname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Lastname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationRequest"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationRequest"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("network3DViewCtrlSetting");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewCtrlSetting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewCtrlSetting"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Network3DViewCtrlSetting"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkACL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworkACL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworkACL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworkACL"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userLoginInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserLoginInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserLoginInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserLoginInfo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userVote");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserVote"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserVote"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserVote"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
