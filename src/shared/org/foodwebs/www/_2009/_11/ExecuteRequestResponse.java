/**
 * ExecuteRequestResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.foodwebs.www._2009._11;

public class ExecuteRequestResponse  implements java.io.Serializable {
    private shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeRequestResult;

    public ExecuteRequestResponse() {
    }

    public ExecuteRequestResponse(
           shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeRequestResult) {
           this.executeRequestResult = executeRequestResult;
    }


    /**
     * Gets the executeRequestResult value for this ExecuteRequestResponse.
     * 
     * @return executeRequestResult
     */
    public shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response getExecuteRequestResult() {
        return executeRequestResult;
    }


    /**
     * Sets the executeRequestResult value for this ExecuteRequestResponse.
     * 
     * @param executeRequestResult
     */
    public void setExecuteRequestResult(shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeRequestResult) {
        this.executeRequestResult = executeRequestResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExecuteRequestResponse)) return false;
        ExecuteRequestResponse other = (ExecuteRequestResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.executeRequestResult==null && other.getExecuteRequestResult()==null) || 
             (this.executeRequestResult!=null &&
              this.executeRequestResult.equals(other.getExecuteRequestResult())));
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
        if (getExecuteRequestResult() != null) {
            _hashCode += getExecuteRequestResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExecuteRequestResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.foodwebs.org/2009/11", ">ExecuteRequestResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executeRequestResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.foodwebs.org/2009/11", "ExecuteRequestResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Response"));
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
