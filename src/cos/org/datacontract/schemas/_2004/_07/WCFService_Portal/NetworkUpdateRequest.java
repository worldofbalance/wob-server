/**
 * NetworkUpdateRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class NetworkUpdateRequest  extends cos.org.datacontract.schemas._2004._07.WCFService_Portal.Request  implements java.io.Serializable {
    private java.util.Calendar date;

    private String networkId;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    private String[] fieldNames;

    private Object[] values;

    public NetworkUpdateRequest() {
    }

    public NetworkUpdateRequest(
           java.util.Calendar date,
           String networkId,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user,
           String[] fieldNames,
           Object[] values) {
        this.date = date;
        this.networkId = networkId;
        this.networks = networks;
        this.user = user;
        this.fieldNames = fieldNames;
        this.values = values;
    }


    /**
     * Gets the date value for this NetworkUpdateRequest.
     *
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this NetworkUpdateRequest.
     *
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the networkId value for this NetworkUpdateRequest.
     *
     * @return networkId
     */
    public String getNetworkId() {
        return networkId;
    }


    /**
     * Sets the networkId value for this NetworkUpdateRequest.
     *
     * @param networkId
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }


    /**
     * Gets the networks value for this NetworkUpdateRequest.
     *
     * @return networks
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks getNetworks() {
        return networks;
    }


    /**
     * Sets the networks value for this NetworkUpdateRequest.
     *
     * @param networks
     */
    public void setNetworks(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Networks networks) {
        this.networks = networks;
    }


    /**
     * Gets the user value for this NetworkUpdateRequest.
     *
     * @return user
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this NetworkUpdateRequest.
     *
     * @param user
     */
    public void setUser(cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }


    /**
     * Gets the fieldNames value for this NetworkUpdateRequest.
     *
     * @return fieldNames
     */
    public String[] getFieldNames() {
        return fieldNames;
    }


    /**
     * Sets the fieldNames value for this NetworkUpdateRequest.
     *
     * @param fieldNames
     */
    public void setFieldNames(String[] fieldNames) {
        this.fieldNames = fieldNames;
    }


    /**
     * Gets the values value for this NetworkUpdateRequest.
     *
     * @return values
     */
    public Object[] getValues() {
        return values;
    }


    /**
     * Sets the values value for this NetworkUpdateRequest.
     *
     * @param values
     */
    public void setValues(Object[] values) {
        this.values = values;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof NetworkUpdateRequest)) return false;
        NetworkUpdateRequest other = (NetworkUpdateRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.date==null && other.getDate()==null) ||
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.networkId==null && other.getNetworkId()==null) ||
             (this.networkId!=null &&
              this.networkId.equals(other.getNetworkId()))) &&
            ((this.networks==null && other.getNetworks()==null) ||
             (this.networks!=null &&
              this.networks.equals(other.getNetworks()))) &&
            ((this.user==null && other.getUser()==null) ||
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.fieldNames==null && other.getFieldNames()==null) ||
             (this.fieldNames!=null &&
              java.util.Arrays.equals(this.fieldNames, other.getFieldNames()))) &&
            ((this.values==null && other.getValues()==null) ||
             (this.values!=null &&
              java.util.Arrays.equals(this.values, other.getValues())));
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
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getNetworkId() != null) {
            _hashCode += getNetworkId().hashCode();
        }
        if (getNetworks() != null) {
            _hashCode += getNetworks().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getFieldNames() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFieldNames());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFieldNames(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValues());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getValues(), i);
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
        new org.apache.axis.description.TypeDesc(NetworkUpdateRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkUpdateRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Networks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Networks"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldNames");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "fieldNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("values");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "values"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "anyType"));
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
