/**
 * ManipulationTimestepInfoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class ManipulationTimestepInfoRequest  extends cos.org.datacontract.schemas._2004._07.WCFService_Portal.Request  implements java.io.Serializable {
    private Boolean DESC;

    private Boolean isNodeTimestep;

    private String manipulationId;

    private Integer nodeIdx;

    private String orderBy;

    private Integer page;

    private Integer pageSize;

    private Integer predIdx;

    private Integer preyIdx;

    private Integer timestep;

    public ManipulationTimestepInfoRequest() {
    }

    public ManipulationTimestepInfoRequest(
           Boolean DESC,
           Boolean isNodeTimestep,
           String manipulationId,
           Integer nodeIdx,
           String orderBy,
           Integer page,
           Integer pageSize,
           Integer predIdx,
           Integer preyIdx,
           Integer timestep) {
        this.DESC = DESC;
        this.isNodeTimestep = isNodeTimestep;
        this.manipulationId = manipulationId;
        this.nodeIdx = nodeIdx;
        this.orderBy = orderBy;
        this.page = page;
        this.pageSize = pageSize;
        this.predIdx = predIdx;
        this.preyIdx = preyIdx;
        this.timestep = timestep;
    }


    /**
     * Gets the DESC value for this ManipulationTimestepInfoRequest.
     *
     * @return DESC
     */
    public Boolean getDESC() {
        return DESC;
    }


    /**
     * Sets the DESC value for this ManipulationTimestepInfoRequest.
     *
     * @param DESC
     */
    public void setDESC(Boolean DESC) {
        this.DESC = DESC;
    }


    /**
     * Gets the isNodeTimestep value for this ManipulationTimestepInfoRequest.
     *
     * @return isNodeTimestep
     */
    public Boolean getIsNodeTimestep() {
        return isNodeTimestep;
    }


    /**
     * Sets the isNodeTimestep value for this ManipulationTimestepInfoRequest.
     *
     * @param isNodeTimestep
     */
    public void setIsNodeTimestep(Boolean isNodeTimestep) {
        this.isNodeTimestep = isNodeTimestep;
    }


    /**
     * Gets the manipulationId value for this ManipulationTimestepInfoRequest.
     *
     * @return manipulationId
     */
    public String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationTimestepInfoRequest.
     *
     * @param manipulationId
     */
    public void setManipulationId(String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the nodeIdx value for this ManipulationTimestepInfoRequest.
     *
     * @return nodeIdx
     */
    public Integer getNodeIdx() {
        return nodeIdx;
    }


    /**
     * Sets the nodeIdx value for this ManipulationTimestepInfoRequest.
     *
     * @param nodeIdx
     */
    public void setNodeIdx(Integer nodeIdx) {
        this.nodeIdx = nodeIdx;
    }


    /**
     * Gets the orderBy value for this ManipulationTimestepInfoRequest.
     *
     * @return orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }


    /**
     * Sets the orderBy value for this ManipulationTimestepInfoRequest.
     *
     * @param orderBy
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


    /**
     * Gets the page value for this ManipulationTimestepInfoRequest.
     *
     * @return page
     */
    public Integer getPage() {
        return page;
    }


    /**
     * Sets the page value for this ManipulationTimestepInfoRequest.
     *
     * @param page
     */
    public void setPage(Integer page) {
        this.page = page;
    }


    /**
     * Gets the pageSize value for this ManipulationTimestepInfoRequest.
     *
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }


    /**
     * Sets the pageSize value for this ManipulationTimestepInfoRequest.
     *
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * Gets the predIdx value for this ManipulationTimestepInfoRequest.
     *
     * @return predIdx
     */
    public Integer getPredIdx() {
        return predIdx;
    }


    /**
     * Sets the predIdx value for this ManipulationTimestepInfoRequest.
     *
     * @param predIdx
     */
    public void setPredIdx(Integer predIdx) {
        this.predIdx = predIdx;
    }


    /**
     * Gets the preyIdx value for this ManipulationTimestepInfoRequest.
     *
     * @return preyIdx
     */
    public Integer getPreyIdx() {
        return preyIdx;
    }


    /**
     * Sets the preyIdx value for this ManipulationTimestepInfoRequest.
     *
     * @param preyIdx
     */
    public void setPreyIdx(Integer preyIdx) {
        this.preyIdx = preyIdx;
    }


    /**
     * Gets the timestep value for this ManipulationTimestepInfoRequest.
     *
     * @return timestep
     */
    public Integer getTimestep() {
        return timestep;
    }


    /**
     * Sets the timestep value for this ManipulationTimestepInfoRequest.
     *
     * @param timestep
     */
    public void setTimestep(Integer timestep) {
        this.timestep = timestep;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulationTimestepInfoRequest)) return false;
        ManipulationTimestepInfoRequest other = (ManipulationTimestepInfoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.DESC==null && other.getDESC()==null) ||
             (this.DESC!=null &&
              this.DESC.equals(other.getDESC()))) &&
            ((this.isNodeTimestep==null && other.getIsNodeTimestep()==null) ||
             (this.isNodeTimestep!=null &&
              this.isNodeTimestep.equals(other.getIsNodeTimestep()))) &&
            ((this.manipulationId==null && other.getManipulationId()==null) ||
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            ((this.nodeIdx==null && other.getNodeIdx()==null) ||
             (this.nodeIdx!=null &&
              this.nodeIdx.equals(other.getNodeIdx()))) &&
            ((this.orderBy==null && other.getOrderBy()==null) ||
             (this.orderBy!=null &&
              this.orderBy.equals(other.getOrderBy()))) &&
            ((this.page==null && other.getPage()==null) ||
             (this.page!=null &&
              this.page.equals(other.getPage()))) &&
            ((this.pageSize==null && other.getPageSize()==null) ||
             (this.pageSize!=null &&
              this.pageSize.equals(other.getPageSize()))) &&
            ((this.predIdx==null && other.getPredIdx()==null) ||
             (this.predIdx!=null &&
              this.predIdx.equals(other.getPredIdx()))) &&
            ((this.preyIdx==null && other.getPreyIdx()==null) ||
             (this.preyIdx!=null &&
              this.preyIdx.equals(other.getPreyIdx()))) &&
            ((this.timestep==null && other.getTimestep()==null) ||
             (this.timestep!=null &&
              this.timestep.equals(other.getTimestep())));
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
        if (getDESC() != null) {
            _hashCode += getDESC().hashCode();
        }
        if (getIsNodeTimestep() != null) {
            _hashCode += getIsNodeTimestep().hashCode();
        }
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        if (getNodeIdx() != null) {
            _hashCode += getNodeIdx().hashCode();
        }
        if (getOrderBy() != null) {
            _hashCode += getOrderBy().hashCode();
        }
        if (getPage() != null) {
            _hashCode += getPage().hashCode();
        }
        if (getPageSize() != null) {
            _hashCode += getPageSize().hashCode();
        }
        if (getPredIdx() != null) {
            _hashCode += getPredIdx().hashCode();
        }
        if (getPreyIdx() != null) {
            _hashCode += getPreyIdx().hashCode();
        }
        if (getTimestep() != null) {
            _hashCode += getTimestep().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationTimestepInfoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationTimestepInfoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "DESC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("nodeIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeIdx"));
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
        elemField.setFieldName("page");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Page"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "PageSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("predIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "PredIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preyIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "PreyIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Timestep"));
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
