/**
 * EntityKey.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.System_Data;

public class EntityKey  implements java.io.Serializable {
    private java.lang.String entityContainerName;

    private shared.org.datacontract.schemas._2004._07.System_Data.EntityKeyMember[] entityKeyValues;

    private java.lang.String entitySetName;

    private org.apache.axis.types.Id id;  // attribute

    private org.apache.axis.types.IDRef ref;  // attribute

    public EntityKey() {
    }

    public EntityKey(
           java.lang.String entityContainerName,
           shared.org.datacontract.schemas._2004._07.System_Data.EntityKeyMember[] entityKeyValues,
           java.lang.String entitySetName,
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref) {
           this.entityContainerName = entityContainerName;
           this.entityKeyValues = entityKeyValues;
           this.entitySetName = entitySetName;
           this.id = id;
           this.ref = ref;
    }


    /**
     * Gets the entityContainerName value for this EntityKey.
     * 
     * @return entityContainerName
     */
    public java.lang.String getEntityContainerName() {
        return entityContainerName;
    }


    /**
     * Sets the entityContainerName value for this EntityKey.
     * 
     * @param entityContainerName
     */
    public void setEntityContainerName(java.lang.String entityContainerName) {
        this.entityContainerName = entityContainerName;
    }


    /**
     * Gets the entityKeyValues value for this EntityKey.
     * 
     * @return entityKeyValues
     */
    public shared.org.datacontract.schemas._2004._07.System_Data.EntityKeyMember[] getEntityKeyValues() {
        return entityKeyValues;
    }


    /**
     * Sets the entityKeyValues value for this EntityKey.
     * 
     * @param entityKeyValues
     */
    public void setEntityKeyValues(shared.org.datacontract.schemas._2004._07.System_Data.EntityKeyMember[] entityKeyValues) {
        this.entityKeyValues = entityKeyValues;
    }


    /**
     * Gets the entitySetName value for this EntityKey.
     * 
     * @return entitySetName
     */
    public java.lang.String getEntitySetName() {
        return entitySetName;
    }


    /**
     * Sets the entitySetName value for this EntityKey.
     * 
     * @param entitySetName
     */
    public void setEntitySetName(java.lang.String entitySetName) {
        this.entitySetName = entitySetName;
    }


    /**
     * Gets the id value for this EntityKey.
     * 
     * @return id
     */
    public org.apache.axis.types.Id getId() {
        return id;
    }


    /**
     * Sets the id value for this EntityKey.
     * 
     * @param id
     */
    public void setId(org.apache.axis.types.Id id) {
        this.id = id;
    }


    /**
     * Gets the ref value for this EntityKey.
     * 
     * @return ref
     */
    public org.apache.axis.types.IDRef getRef() {
        return ref;
    }


    /**
     * Sets the ref value for this EntityKey.
     * 
     * @param ref
     */
    public void setRef(org.apache.axis.types.IDRef ref) {
        this.ref = ref;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntityKey)) return false;
        EntityKey other = (EntityKey) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entityContainerName==null && other.getEntityContainerName()==null) || 
             (this.entityContainerName!=null &&
              this.entityContainerName.equals(other.getEntityContainerName()))) &&
            ((this.entityKeyValues==null && other.getEntityKeyValues()==null) || 
             (this.entityKeyValues!=null &&
              java.util.Arrays.equals(this.entityKeyValues, other.getEntityKeyValues()))) &&
            ((this.entitySetName==null && other.getEntitySetName()==null) || 
             (this.entitySetName!=null &&
              this.entitySetName.equals(other.getEntitySetName()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.ref==null && other.getRef()==null) || 
             (this.ref!=null &&
              this.ref.equals(other.getRef())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getEntityContainerName() != null) {
            _hashCode += getEntityContainerName().hashCode();
        }
        if (getEntityKeyValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEntityKeyValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEntityKeyValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEntitySetName() != null) {
            _hashCode += getEntitySetName().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getRef() != null) {
            _hashCode += getRef().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntityKey.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "EntityKey"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/", "Id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "ID"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("ref");
        attrField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/", "Ref"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "IDREF"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityContainerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "EntityContainerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityKeyValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "EntityKeyValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "EntityKeyMember"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "EntityKeyMember"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entitySetName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "EntitySetName"));
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
