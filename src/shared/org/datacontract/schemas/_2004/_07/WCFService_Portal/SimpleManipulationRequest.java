/**
 * SimpleManipulationRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.WCFService_Portal;

public class SimpleManipulationRequest  implements java.io.Serializable {
    private java.lang.Integer beginingTimestepIdx;

    private java.util.Calendar date;

    private java.lang.String description;

    private java.lang.String manipulationId;

    private shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNode[] manipulationModelNodes;

    private java.lang.String networkId;

    private shared.org.datacontract.schemas._2004._07.ManipulationParameter.NodeBiomass[] nodeBiomasses;

    private shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNodeProperty[] nodeProperties;

    private java.lang.Boolean saveLastTimestepOnly;

    private java.lang.Boolean saveLink;

    private shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingParameter[] sysParams;

    private java.lang.Integer timestepsToRun;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    public SimpleManipulationRequest() {
    }

    public SimpleManipulationRequest(
           java.lang.Integer beginingTimestepIdx,
           java.util.Calendar date,
           java.lang.String description,
           java.lang.String manipulationId,
           shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNode[] manipulationModelNodes,
           java.lang.String networkId,
           shared.org.datacontract.schemas._2004._07.ManipulationParameter.NodeBiomass[] nodeBiomasses,
           shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNodeProperty[] nodeProperties,
           java.lang.Boolean saveLastTimestepOnly,
           java.lang.Boolean saveLink,
           shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingParameter[] sysParams,
           java.lang.Integer timestepsToRun,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
           this.beginingTimestepIdx = beginingTimestepIdx;
           this.date = date;
           this.description = description;
           this.manipulationId = manipulationId;
           this.manipulationModelNodes = manipulationModelNodes;
           this.networkId = networkId;
           this.nodeBiomasses = nodeBiomasses;
           this.nodeProperties = nodeProperties;
           this.saveLastTimestepOnly = saveLastTimestepOnly;
           this.saveLink = saveLink;
           this.sysParams = sysParams;
           this.timestepsToRun = timestepsToRun;
           this.user = user;
    }


    /**
     * Gets the beginingTimestepIdx value for this SimpleManipulationRequest.
     * 
     * @return beginingTimestepIdx
     */
    public java.lang.Integer getBeginingTimestepIdx() {
        return beginingTimestepIdx;
    }


    /**
     * Sets the beginingTimestepIdx value for this SimpleManipulationRequest.
     * 
     * @param beginingTimestepIdx
     */
    public void setBeginingTimestepIdx(java.lang.Integer beginingTimestepIdx) {
        this.beginingTimestepIdx = beginingTimestepIdx;
    }


    /**
     * Gets the date value for this SimpleManipulationRequest.
     * 
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this SimpleManipulationRequest.
     * 
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the description value for this SimpleManipulationRequest.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this SimpleManipulationRequest.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the manipulationId value for this SimpleManipulationRequest.
     * 
     * @return manipulationId
     */
    public java.lang.String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this SimpleManipulationRequest.
     * 
     * @param manipulationId
     */
    public void setManipulationId(java.lang.String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the manipulationModelNodes value for this SimpleManipulationRequest.
     * 
     * @return manipulationModelNodes
     */
    public shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNode[] getManipulationModelNodes() {
        return manipulationModelNodes;
    }


    /**
     * Sets the manipulationModelNodes value for this SimpleManipulationRequest.
     * 
     * @param manipulationModelNodes
     */
    public void setManipulationModelNodes(shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNode[] manipulationModelNodes) {
        this.manipulationModelNodes = manipulationModelNodes;
    }


    /**
     * Gets the networkId value for this SimpleManipulationRequest.
     * 
     * @return networkId
     */
    public java.lang.String getNetworkId() {
        return networkId;
    }


    /**
     * Sets the networkId value for this SimpleManipulationRequest.
     * 
     * @param networkId
     */
    public void setNetworkId(java.lang.String networkId) {
        this.networkId = networkId;
    }


    /**
     * Gets the nodeBiomasses value for this SimpleManipulationRequest.
     * 
     * @return nodeBiomasses
     */
    public shared.org.datacontract.schemas._2004._07.ManipulationParameter.NodeBiomass[] getNodeBiomasses() {
        return nodeBiomasses;
    }


    /**
     * Sets the nodeBiomasses value for this SimpleManipulationRequest.
     * 
     * @param nodeBiomasses
     */
    public void setNodeBiomasses(shared.org.datacontract.schemas._2004._07.ManipulationParameter.NodeBiomass[] nodeBiomasses) {
        this.nodeBiomasses = nodeBiomasses;
    }


    /**
     * Gets the nodeProperties value for this SimpleManipulationRequest.
     * 
     * @return nodeProperties
     */
    public shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNodeProperty[] getNodeProperties() {
        return nodeProperties;
    }


    /**
     * Sets the nodeProperties value for this SimpleManipulationRequest.
     * 
     * @param nodeProperties
     */
    public void setNodeProperties(shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNodeProperty[] nodeProperties) {
        this.nodeProperties = nodeProperties;
    }


    /**
     * Gets the saveLastTimestepOnly value for this SimpleManipulationRequest.
     * 
     * @return saveLastTimestepOnly
     */
    public java.lang.Boolean getSaveLastTimestepOnly() {
        return saveLastTimestepOnly;
    }


    /**
     * Sets the saveLastTimestepOnly value for this SimpleManipulationRequest.
     * 
     * @param saveLastTimestepOnly
     */
    public void setSaveLastTimestepOnly(java.lang.Boolean saveLastTimestepOnly) {
        this.saveLastTimestepOnly = saveLastTimestepOnly;
    }


    /**
     * Gets the saveLink value for this SimpleManipulationRequest.
     * 
     * @return saveLink
     */
    public java.lang.Boolean getSaveLink() {
        return saveLink;
    }


    /**
     * Sets the saveLink value for this SimpleManipulationRequest.
     * 
     * @param saveLink
     */
    public void setSaveLink(java.lang.Boolean saveLink) {
        this.saveLink = saveLink;
    }


    /**
     * Gets the sysParams value for this SimpleManipulationRequest.
     * 
     * @return sysParams
     */
    public shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingParameter[] getSysParams() {
        return sysParams;
    }


    /**
     * Sets the sysParams value for this SimpleManipulationRequest.
     * 
     * @param sysParams
     */
    public void setSysParams(shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingParameter[] sysParams) {
        this.sysParams = sysParams;
    }


    /**
     * Gets the timestepsToRun value for this SimpleManipulationRequest.
     * 
     * @return timestepsToRun
     */
    public java.lang.Integer getTimestepsToRun() {
        return timestepsToRun;
    }


    /**
     * Sets the timestepsToRun value for this SimpleManipulationRequest.
     * 
     * @param timestepsToRun
     */
    public void setTimestepsToRun(java.lang.Integer timestepsToRun) {
        this.timestepsToRun = timestepsToRun;
    }


    /**
     * Gets the user value for this SimpleManipulationRequest.
     * 
     * @return user
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this SimpleManipulationRequest.
     * 
     * @param user
     */
    public void setUser(shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SimpleManipulationRequest)) return false;
        SimpleManipulationRequest other = (SimpleManipulationRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.beginingTimestepIdx==null && other.getBeginingTimestepIdx()==null) || 
             (this.beginingTimestepIdx!=null &&
              this.beginingTimestepIdx.equals(other.getBeginingTimestepIdx()))) &&
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.manipulationId==null && other.getManipulationId()==null) || 
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            ((this.manipulationModelNodes==null && other.getManipulationModelNodes()==null) || 
             (this.manipulationModelNodes!=null &&
              java.util.Arrays.equals(this.manipulationModelNodes, other.getManipulationModelNodes()))) &&
            ((this.networkId==null && other.getNetworkId()==null) || 
             (this.networkId!=null &&
              this.networkId.equals(other.getNetworkId()))) &&
            ((this.nodeBiomasses==null && other.getNodeBiomasses()==null) || 
             (this.nodeBiomasses!=null &&
              java.util.Arrays.equals(this.nodeBiomasses, other.getNodeBiomasses()))) &&
            ((this.nodeProperties==null && other.getNodeProperties()==null) || 
             (this.nodeProperties!=null &&
              java.util.Arrays.equals(this.nodeProperties, other.getNodeProperties()))) &&
            ((this.saveLastTimestepOnly==null && other.getSaveLastTimestepOnly()==null) || 
             (this.saveLastTimestepOnly!=null &&
              this.saveLastTimestepOnly.equals(other.getSaveLastTimestepOnly()))) &&
            ((this.saveLink==null && other.getSaveLink()==null) || 
             (this.saveLink!=null &&
              this.saveLink.equals(other.getSaveLink()))) &&
            ((this.sysParams==null && other.getSysParams()==null) || 
             (this.sysParams!=null &&
              java.util.Arrays.equals(this.sysParams, other.getSysParams()))) &&
            ((this.timestepsToRun==null && other.getTimestepsToRun()==null) || 
             (this.timestepsToRun!=null &&
              this.timestepsToRun.equals(other.getTimestepsToRun()))) &&
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
        int _hashCode = 1;
        if (getBeginingTimestepIdx() != null) {
            _hashCode += getBeginingTimestepIdx().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        if (getManipulationModelNodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationModelNodes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManipulationModelNodes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNetworkId() != null) {
            _hashCode += getNetworkId().hashCode();
        }
        if (getNodeBiomasses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNodeBiomasses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNodeBiomasses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNodeProperties() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNodeProperties());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNodeProperties(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSaveLastTimestepOnly() != null) {
            _hashCode += getSaveLastTimestepOnly().hashCode();
        }
        if (getSaveLink() != null) {
            _hashCode += getSaveLink().hashCode();
        }
        if (getSysParams() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSysParams());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSysParams(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTimestepsToRun() != null) {
            _hashCode += getTimestepsToRun().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SimpleManipulationRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "SimpleManipulationRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginingTimestepIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "BeginingTimestepIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationModelNodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationModelNodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ManipulatingNode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ManipulatingNode"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeBiomasses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeBiomasses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "NodeBiomass"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "NodeBiomass"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeProperties");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeProperties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ManipulatingNodeProperty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ManipulatingNodeProperty"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saveLastTimestepOnly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "SaveLastTimestepOnly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saveLink");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "SaveLink"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sysParams");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "SysParams"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ManipulatingParameter"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ManipulatingParameter"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestepsToRun");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TimestepsToRun"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
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
