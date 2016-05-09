/**
 * EntityObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses;

public class EntityObject  extends cos.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.StructuralObject  implements java.io.Serializable {
    private cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey;

    public EntityObject() {
    }

    public EntityObject(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey) {
        super(
            id,
            ref);
        this.entityKey = entityKey;
    }


    /**
     * Gets the entityKey value for this EntityObject.
     *
     * @return entityKey
     */
    public cos.org.datacontract.schemas._2004._07.System_Data.EntityKey getEntityKey() {
        return entityKey;
    }


    /**
     * Sets the entityKey value for this EntityObject.
     *
     * @param entityKey
     */
    public void setEntityKey(cos.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey) {
        this.entityKey = entityKey;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof EntityObject)) return false;
        EntityObject other = (EntityObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.entityKey==null && other.getEntityKey()==null) ||
             (this.entityKey!=null &&
              this.entityKey.equals(other.getEntityKey())));
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
        if (getEntityKey() != null) {
            _hashCode += getEntityKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntityObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "EntityKey"));
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
