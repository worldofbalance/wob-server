/**
 * ExecuteNetworkCreationRequestResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.foodwebs.www._2009._11;

public class ExecuteNetworkCreationRequestResponse  implements java.io.Serializable {
    private shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeNetworkCreationRequestResult;

    public ExecuteNetworkCreationRequestResponse() {
    }

    public ExecuteNetworkCreationRequestResponse(
           shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeNetworkCreationRequestResult) {
           this.executeNetworkCreationRequestResult = executeNetworkCreationRequestResult;
    }


    /**
     * Gets the executeNetworkCreationRequestResult value for this ExecuteNetworkCreationRequestResponse.
     * 
     * @return executeNetworkCreationRequestResult
     */
    public shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response getExecuteNetworkCreationRequestResult() {
        return executeNetworkCreationRequestResult;
    }


    /**
     * Sets the executeNetworkCreationRequestResult value for this ExecuteNetworkCreationRequestResponse.
     * 
     * @param executeNetworkCreationRequestResult
     */
    public void setExecuteNetworkCreationRequestResult(shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeNetworkCreationRequestResult) {
        this.executeNetworkCreationRequestResult = executeNetworkCreationRequestResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExecuteNetworkCreationRequestResponse)) return false;
        ExecuteNetworkCreationRequestResponse other = (ExecuteNetworkCreationRequestResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.executeNetworkCreationRequestResult==null && other.getExecuteNetworkCreationRequestResult()==null) || 
             (this.executeNetworkCreationRequestResult!=null &&
              this.executeNetworkCreationRequestResult.equals(other.getExecuteNetworkCreationRequestResult())));
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
        if (getExecuteNetworkCreationRequestResult() != null) {
            _hashCode += getExecuteNetworkCreationRequestResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExecuteNetworkCreationRequestResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.foodwebs.org/2009/11", ">ExecuteNetworkCreationRequestResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executeNetworkCreationRequestResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.foodwebs.org/2009/11", "ExecuteNetworkCreationRequestResult"));
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
