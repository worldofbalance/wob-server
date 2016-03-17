/**
 * ManipulationRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.LINQ2Entities;

public class ManipulationRequest  extends shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.Float calculationTime;

    private java.util.Calendar createdDate;

    private java.lang.String errDescription;

    private java.lang.String id;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation[] manipulation;

    private java.lang.Integer recordedNumber;

    private java.lang.Integer runningStatus;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    private shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfUserhovVakal userReference;

    private java.lang.String username;

    public ManipulationRequest() {
    }

    public ManipulationRequest(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           shared.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.Float calculationTime,
           java.util.Calendar createdDate,
           java.lang.String errDescription,
           java.lang.String id,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation[] manipulation,
           java.lang.Integer recordedNumber,
           java.lang.Integer runningStatus,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user,
           shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfUserhovVakal userReference,
           java.lang.String username) {
        super(
            _id,
            ref,
            entityKey);
        this.calculationTime = calculationTime;
        this.createdDate = createdDate;
        this.errDescription = errDescription;
        this.id = id;
        this.manipulation = manipulation;
        this.recordedNumber = recordedNumber;
        this.runningStatus = runningStatus;
        this.user = user;
        this.userReference = userReference;
        this.username = username;
    }


    /**
     * Gets the calculationTime value for this ManipulationRequest.
     * 
     * @return calculationTime
     */
    public java.lang.Float getCalculationTime() {
        return calculationTime;
    }


    /**
     * Sets the calculationTime value for this ManipulationRequest.
     * 
     * @param calculationTime
     */
    public void setCalculationTime(java.lang.Float calculationTime) {
        this.calculationTime = calculationTime;
    }


    /**
     * Gets the createdDate value for this ManipulationRequest.
     * 
     * @return createdDate
     */
    public java.util.Calendar getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this ManipulationRequest.
     * 
     * @param createdDate
     */
    public void setCreatedDate(java.util.Calendar createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the errDescription value for this ManipulationRequest.
     * 
     * @return errDescription
     */
    public java.lang.String getErrDescription() {
        return errDescription;
    }


    /**
     * Sets the errDescription value for this ManipulationRequest.
     * 
     * @param errDescription
     */
    public void setErrDescription(java.lang.String errDescription) {
        this.errDescription = errDescription;
    }


    /**
     * Gets the id value for this ManipulationRequest.
     * 
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this ManipulationRequest.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the manipulation value for this ManipulationRequest.
     * 
     * @return manipulation
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation[] getManipulation() {
        return manipulation;
    }


    /**
     * Sets the manipulation value for this ManipulationRequest.
     * 
     * @param manipulation
     */
    public void setManipulation(shared.org.datacontract.schemas._2004._07.LINQ2Entities.Manipulation[] manipulation) {
        this.manipulation = manipulation;
    }


    /**
     * Gets the recordedNumber value for this ManipulationRequest.
     * 
     * @return recordedNumber
     */
    public java.lang.Integer getRecordedNumber() {
        return recordedNumber;
    }


    /**
     * Sets the recordedNumber value for this ManipulationRequest.
     * 
     * @param recordedNumber
     */
    public void setRecordedNumber(java.lang.Integer recordedNumber) {
        this.recordedNumber = recordedNumber;
    }


    /**
     * Gets the runningStatus value for this ManipulationRequest.
     * 
     * @return runningStatus
     */
    public java.lang.Integer getRunningStatus() {
        return runningStatus;
    }


    /**
     * Sets the runningStatus value for this ManipulationRequest.
     * 
     * @param runningStatus
     */
    public void setRunningStatus(java.lang.Integer runningStatus) {
        this.runningStatus = runningStatus;
    }


    /**
     * Gets the user value for this ManipulationRequest.
     * 
     * @return user
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this ManipulationRequest.
     * 
     * @param user
     */
    public void setUser(shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }


    /**
     * Gets the userReference value for this ManipulationRequest.
     * 
     * @return userReference
     */
    public shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfUserhovVakal getUserReference() {
        return userReference;
    }


    /**
     * Sets the userReference value for this ManipulationRequest.
     * 
     * @param userReference
     */
    public void setUserReference(shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfUserhovVakal userReference) {
        this.userReference = userReference;
    }


    /**
     * Gets the username value for this ManipulationRequest.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this ManipulationRequest.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ManipulationRequest)) return false;
        ManipulationRequest other = (ManipulationRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.calculationTime==null && other.getCalculationTime()==null) || 
             (this.calculationTime!=null &&
              this.calculationTime.equals(other.getCalculationTime()))) &&
            ((this.createdDate==null && other.getCreatedDate()==null) || 
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.errDescription==null && other.getErrDescription()==null) || 
             (this.errDescription!=null &&
              this.errDescription.equals(other.getErrDescription()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.manipulation==null && other.getManipulation()==null) || 
             (this.manipulation!=null &&
              java.util.Arrays.equals(this.manipulation, other.getManipulation()))) &&
            ((this.recordedNumber==null && other.getRecordedNumber()==null) || 
             (this.recordedNumber!=null &&
              this.recordedNumber.equals(other.getRecordedNumber()))) &&
            ((this.runningStatus==null && other.getRunningStatus()==null) || 
             (this.runningStatus!=null &&
              this.runningStatus.equals(other.getRunningStatus()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.userReference==null && other.getUserReference()==null) || 
             (this.userReference!=null &&
              this.userReference.equals(other.getUserReference()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername())));
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
        if (getCalculationTime() != null) {
            _hashCode += getCalculationTime().hashCode();
        }
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getErrDescription() != null) {
            _hashCode += getErrDescription().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getManipulation() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulation());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManipulation(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRecordedNumber() != null) {
            _hashCode += getRecordedNumber().hashCode();
        }
        if (getRunningStatus() != null) {
            _hashCode += getRunningStatus().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getUserReference() != null) {
            _hashCode += getUserReference().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calculationTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "CalculationTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "CreatedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ErrDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Manipulation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Manipulation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Manipulation"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordedNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "RecordedNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("runningStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "RunningStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "UserReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfUserhovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Username"));
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
