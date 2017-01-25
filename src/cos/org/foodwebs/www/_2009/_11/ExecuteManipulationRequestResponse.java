/**
 * ExecuteManipulationRequestResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.foodwebs.www._2009._11;

public class ExecuteManipulationRequestResponse  implements java.io.Serializable {
    private cos.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeManipulationRequestResult;

    public ExecuteManipulationRequestResponse() {
    }

    public ExecuteManipulationRequestResponse(
      cos.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeManipulationRequestResult) {
           this.executeManipulationRequestResult = executeManipulationRequestResult;
    }


    /**
     * Gets the executeManipulationRequestResult value for this ExecuteManipulationRequestResponse.
     * 
     * @return executeManipulationRequestResult
     */
    public cos.org.datacontract.schemas._2004._07.WCFService_Portal.Response getExecuteManipulationRequestResult() {
        return executeManipulationRequestResult;
    }


    /**
     * Sets the executeManipulationRequestResult value for this ExecuteManipulationRequestResponse.
     * 
     * @param executeManipulationRequestResult
     */
    public void setExecuteManipulationRequestResult(cos.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeManipulationRequestResult) {
        this.executeManipulationRequestResult = executeManipulationRequestResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ExecuteManipulationRequestResponse)) return false;
        ExecuteManipulationRequestResponse other = (ExecuteManipulationRequestResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.executeManipulationRequestResult==null && other.getExecuteManipulationRequestResult()==null) ||
             (this.executeManipulationRequestResult!=null &&
              this.executeManipulationRequestResult.equals(other.getExecuteManipulationRequestResult())));
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
        if (getExecuteManipulationRequestResult() != null) {
            _hashCode += getExecuteManipulationRequestResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExecuteManipulationRequestResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.foodwebs.org/2009/11", ">ExecuteManipulationRequestResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executeManipulationRequestResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.foodwebs.org/2009/11", "ExecuteManipulationRequestResult"));
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
