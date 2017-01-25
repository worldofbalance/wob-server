/**
 * NetworkMatrixResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.WCFService_Portal;

public class NetworkMatrixResponse  extends shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response  implements java.io.Serializable {
    private java.lang.String message;

    private java.lang.String networkId;

    private double[][] predSimMatrix;

    private double[][] preySimMatrix;

    private double[][] simMatrix;

    private java.lang.Integer size;

    public NetworkMatrixResponse() {
    }

    public NetworkMatrixResponse(
           java.lang.String _message,
           java.lang.String message,
           java.lang.String networkId,
           double[][] predSimMatrix,
           double[][] preySimMatrix,
           double[][] simMatrix,
           java.lang.Integer size) {
        super(
            _message);
        this.message = message;
        this.networkId = networkId;
        this.predSimMatrix = predSimMatrix;
        this.preySimMatrix = preySimMatrix;
        this.simMatrix = simMatrix;
        this.size = size;
    }


    /**
     * Gets the message value for this NetworkMatrixResponse.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this NetworkMatrixResponse.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the networkId value for this NetworkMatrixResponse.
     * 
     * @return networkId
     */
    public java.lang.String getNetworkId() {
        return networkId;
    }


    /**
     * Sets the networkId value for this NetworkMatrixResponse.
     * 
     * @param networkId
     */
    public void setNetworkId(java.lang.String networkId) {
        this.networkId = networkId;
    }


    /**
     * Gets the predSimMatrix value for this NetworkMatrixResponse.
     * 
     * @return predSimMatrix
     */
    public double[][] getPredSimMatrix() {
        return predSimMatrix;
    }


    /**
     * Sets the predSimMatrix value for this NetworkMatrixResponse.
     * 
     * @param predSimMatrix
     */
    public void setPredSimMatrix(double[][] predSimMatrix) {
        this.predSimMatrix = predSimMatrix;
    }


    /**
     * Gets the preySimMatrix value for this NetworkMatrixResponse.
     * 
     * @return preySimMatrix
     */
    public double[][] getPreySimMatrix() {
        return preySimMatrix;
    }


    /**
     * Sets the preySimMatrix value for this NetworkMatrixResponse.
     * 
     * @param preySimMatrix
     */
    public void setPreySimMatrix(double[][] preySimMatrix) {
        this.preySimMatrix = preySimMatrix;
    }


    /**
     * Gets the simMatrix value for this NetworkMatrixResponse.
     * 
     * @return simMatrix
     */
    public double[][] getSimMatrix() {
        return simMatrix;
    }


    /**
     * Sets the simMatrix value for this NetworkMatrixResponse.
     * 
     * @param simMatrix
     */
    public void setSimMatrix(double[][] simMatrix) {
        this.simMatrix = simMatrix;
    }


    /**
     * Gets the size value for this NetworkMatrixResponse.
     * 
     * @return size
     */
    public java.lang.Integer getSize() {
        return size;
    }


    /**
     * Sets the size value for this NetworkMatrixResponse.
     * 
     * @param size
     */
    public void setSize(java.lang.Integer size) {
        this.size = size;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NetworkMatrixResponse)) return false;
        NetworkMatrixResponse other = (NetworkMatrixResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.networkId==null && other.getNetworkId()==null) || 
             (this.networkId!=null &&
              this.networkId.equals(other.getNetworkId()))) &&
            ((this.predSimMatrix==null && other.getPredSimMatrix()==null) || 
             (this.predSimMatrix!=null &&
              java.util.Arrays.equals(this.predSimMatrix, other.getPredSimMatrix()))) &&
            ((this.preySimMatrix==null && other.getPreySimMatrix()==null) || 
             (this.preySimMatrix!=null &&
              java.util.Arrays.equals(this.preySimMatrix, other.getPreySimMatrix()))) &&
            ((this.simMatrix==null && other.getSimMatrix()==null) || 
             (this.simMatrix!=null &&
              java.util.Arrays.equals(this.simMatrix, other.getSimMatrix()))) &&
            ((this.size==null && other.getSize()==null) || 
             (this.size!=null &&
              this.size.equals(other.getSize())));
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
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getNetworkId() != null) {
            _hashCode += getNetworkId().hashCode();
        }
        if (getPredSimMatrix() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPredSimMatrix());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPredSimMatrix(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPreySimMatrix() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPreySimMatrix());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPreySimMatrix(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSimMatrix() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSimMatrix());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSimMatrix(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSize() != null) {
            _hashCode += getSize().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NetworkMatrixResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkMatrixResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("predSimMatrix");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "PredSimMatrix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfdouble"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfdouble"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preySimMatrix");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "PreySimMatrix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfdouble"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfdouble"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("simMatrix");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "SimMatrix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfdouble"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfdouble"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("size");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Size"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
