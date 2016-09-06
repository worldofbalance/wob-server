/**
 * ManipulationTimestepInfoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class ManipulationTimestepInfoResponse  extends cos.org.datacontract.schemas._2004._07.WCFService_Portal.Response  implements java.io.Serializable {
    private Integer curPage;

    private Boolean isNodeTimestep;

    private String manipulationId;

    private cos.org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationLinkTimestepInfo[] manipulationLinkTimestepInfos;

    private cos.org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationTimestepInfo[] manipulationTimestepInfos;

    private String message;

    private Integer nodeCount;

    private String orderBy;

    private Integer pageAvailable;

    public ManipulationTimestepInfoResponse() {
    }

    public ManipulationTimestepInfoResponse(
           String _message,
           Integer curPage,
           Boolean isNodeTimestep,
           String manipulationId,
           cos.org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationLinkTimestepInfo[] manipulationLinkTimestepInfos,
           cos.org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationTimestepInfo[] manipulationTimestepInfos,
           String message,
           Integer nodeCount,
           String orderBy,
           Integer pageAvailable) {
        super(
            _message);
        this.curPage = curPage;
        this.isNodeTimestep = isNodeTimestep;
        this.manipulationId = manipulationId;
        this.manipulationLinkTimestepInfos = manipulationLinkTimestepInfos;
        this.manipulationTimestepInfos = manipulationTimestepInfos;
        this.message = message;
        this.nodeCount = nodeCount;
        this.orderBy = orderBy;
        this.pageAvailable = pageAvailable;
    }


    /**
     * Gets the curPage value for this ManipulationTimestepInfoResponse.
     *
     * @return curPage
     */
    public Integer getCurPage() {
        return curPage;
    }


    /**
     * Sets the curPage value for this ManipulationTimestepInfoResponse.
     *
     * @param curPage
     */
    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }


    /**
     * Gets the isNodeTimestep value for this ManipulationTimestepInfoResponse.
     *
     * @return isNodeTimestep
     */
    public Boolean getIsNodeTimestep() {
        return isNodeTimestep;
    }


    /**
     * Sets the isNodeTimestep value for this ManipulationTimestepInfoResponse.
     *
     * @param isNodeTimestep
     */
    public void setIsNodeTimestep(Boolean isNodeTimestep) {
        this.isNodeTimestep = isNodeTimestep;
    }


    /**
     * Gets the manipulationId value for this ManipulationTimestepInfoResponse.
     *
     * @return manipulationId
     */
    public String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationTimestepInfoResponse.
     *
     * @param manipulationId
     */
    public void setManipulationId(String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the manipulationLinkTimestepInfos value for this ManipulationTimestepInfoResponse.
     *
     * @return manipulationLinkTimestepInfos
     */
    public cos.org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationLinkTimestepInfo[] getManipulationLinkTimestepInfos() {
        return manipulationLinkTimestepInfos;
    }


    /**
     * Sets the manipulationLinkTimestepInfos value for this ManipulationTimestepInfoResponse.
     *
     * @param manipulationLinkTimestepInfos
     */
    public void setManipulationLinkTimestepInfos(cos.org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationLinkTimestepInfo[] manipulationLinkTimestepInfos) {
        this.manipulationLinkTimestepInfos = manipulationLinkTimestepInfos;
    }


    /**
     * Gets the manipulationTimestepInfos value for this ManipulationTimestepInfoResponse.
     *
     * @return manipulationTimestepInfos
     */
    public cos.org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationTimestepInfo[] getManipulationTimestepInfos() {
        return manipulationTimestepInfos;
    }


    /**
     * Sets the manipulationTimestepInfos value for this ManipulationTimestepInfoResponse.
     *
     * @param manipulationTimestepInfos
     */
    public void setManipulationTimestepInfos(cos.org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationTimestepInfo[] manipulationTimestepInfos) {
        this.manipulationTimestepInfos = manipulationTimestepInfos;
    }


    /**
     * Gets the message value for this ManipulationTimestepInfoResponse.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this ManipulationTimestepInfoResponse.
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Gets the nodeCount value for this ManipulationTimestepInfoResponse.
     *
     * @return nodeCount
     */
    public Integer getNodeCount() {
        return nodeCount;
    }


    /**
     * Sets the nodeCount value for this ManipulationTimestepInfoResponse.
     *
     * @param nodeCount
     */
    public void setNodeCount(Integer nodeCount) {
        this.nodeCount = nodeCount;
    }


    /**
     * Gets the orderBy value for this ManipulationTimestepInfoResponse.
     *
     * @return orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }


    /**
     * Sets the orderBy value for this ManipulationTimestepInfoResponse.
     *
     * @param orderBy
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


    /**
     * Gets the pageAvailable value for this ManipulationTimestepInfoResponse.
     *
     * @return pageAvailable
     */
    public Integer getPageAvailable() {
        return pageAvailable;
    }


    /**
     * Sets the pageAvailable value for this ManipulationTimestepInfoResponse.
     *
     * @param pageAvailable
     */
    public void setPageAvailable(Integer pageAvailable) {
        this.pageAvailable = pageAvailable;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulationTimestepInfoResponse)) return false;
        ManipulationTimestepInfoResponse other = (ManipulationTimestepInfoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.curPage==null && other.getCurPage()==null) ||
             (this.curPage!=null &&
              this.curPage.equals(other.getCurPage()))) &&
            ((this.isNodeTimestep==null && other.getIsNodeTimestep()==null) ||
             (this.isNodeTimestep!=null &&
              this.isNodeTimestep.equals(other.getIsNodeTimestep()))) &&
            ((this.manipulationId==null && other.getManipulationId()==null) ||
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            ((this.manipulationLinkTimestepInfos==null && other.getManipulationLinkTimestepInfos()==null) ||
             (this.manipulationLinkTimestepInfos!=null &&
              java.util.Arrays.equals(this.manipulationLinkTimestepInfos, other.getManipulationLinkTimestepInfos()))) &&
            ((this.manipulationTimestepInfos==null && other.getManipulationTimestepInfos()==null) ||
             (this.manipulationTimestepInfos!=null &&
              java.util.Arrays.equals(this.manipulationTimestepInfos, other.getManipulationTimestepInfos()))) &&
            ((this.message==null && other.getMessage()==null) ||
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.nodeCount==null && other.getNodeCount()==null) ||
             (this.nodeCount!=null &&
              this.nodeCount.equals(other.getNodeCount()))) &&
            ((this.orderBy==null && other.getOrderBy()==null) ||
             (this.orderBy!=null &&
              this.orderBy.equals(other.getOrderBy()))) &&
            ((this.pageAvailable==null && other.getPageAvailable()==null) ||
             (this.pageAvailable!=null &&
              this.pageAvailable.equals(other.getPageAvailable())));
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
        if (getCurPage() != null) {
            _hashCode += getCurPage().hashCode();
        }
        if (getIsNodeTimestep() != null) {
            _hashCode += getIsNodeTimestep().hashCode();
        }
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        if (getManipulationLinkTimestepInfos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationLinkTimestepInfos());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getManipulationLinkTimestepInfos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getManipulationTimestepInfos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationTimestepInfos());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getManipulationTimestepInfos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getNodeCount() != null) {
            _hashCode += getNodeCount().hashCode();
        }
        if (getOrderBy() != null) {
            _hashCode += getOrderBy().hashCode();
        }
        if (getPageAvailable() != null) {
            _hashCode += getPageAvailable().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationTimestepInfoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationTimestepInfoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curPage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "CurPage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isNodeTimestep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "IsNodeTimestep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationLinkTimestepInfos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationLinkTimestepInfos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationLinkTimestepInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationLinkTimestepInfo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationTimestepInfos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationTimestepInfos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationTimestepInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationTimestepInfo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "OrderBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageAvailable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "PageAvailable"));
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
