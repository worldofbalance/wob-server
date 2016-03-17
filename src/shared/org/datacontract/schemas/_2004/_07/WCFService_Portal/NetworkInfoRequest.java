/**
 * NetworkInfoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.WCFService_Portal;

public class NetworkInfoRequest  extends shared.org.datacontract.schemas._2004._07.WCFService_Portal.Request  implements java.io.Serializable {
    private int[] ACLs;

    private java.lang.Boolean desc;

    private java.lang.Integer modelType;

    private java.lang.String orderBy;

    private java.lang.Integer page;

    private java.lang.Integer pageSize;

    private java.lang.Object[] searchParameters;

    private java.lang.Boolean showPrivatesOnly;

    private java.lang.Boolean textSearch;

    private java.lang.Integer textSearchMode;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    private java.lang.String whereClause;

    public NetworkInfoRequest() {
    }

    public NetworkInfoRequest(
           int[] ACLs,
           java.lang.Boolean desc,
           java.lang.Integer modelType,
           java.lang.String orderBy,
           java.lang.Integer page,
           java.lang.Integer pageSize,
           java.lang.Object[] searchParameters,
           java.lang.Boolean showPrivatesOnly,
           java.lang.Boolean textSearch,
           java.lang.Integer textSearchMode,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user,
           java.lang.String whereClause) {
        this.ACLs = ACLs;
        this.desc = desc;
        this.modelType = modelType;
        this.orderBy = orderBy;
        this.page = page;
        this.pageSize = pageSize;
        this.searchParameters = searchParameters;
        this.showPrivatesOnly = showPrivatesOnly;
        this.textSearch = textSearch;
        this.textSearchMode = textSearchMode;
        this.user = user;
        this.whereClause = whereClause;
    }


    /**
     * Gets the ACLs value for this NetworkInfoRequest.
     * 
     * @return ACLs
     */
    public int[] getACLs() {
        return ACLs;
    }


    /**
     * Sets the ACLs value for this NetworkInfoRequest.
     * 
     * @param ACLs
     */
    public void setACLs(int[] ACLs) {
        this.ACLs = ACLs;
    }


    /**
     * Gets the desc value for this NetworkInfoRequest.
     * 
     * @return desc
     */
    public java.lang.Boolean getDesc() {
        return desc;
    }


    /**
     * Sets the desc value for this NetworkInfoRequest.
     * 
     * @param desc
     */
    public void setDesc(java.lang.Boolean desc) {
        this.desc = desc;
    }


    /**
     * Gets the modelType value for this NetworkInfoRequest.
     * 
     * @return modelType
     */
    public java.lang.Integer getModelType() {
        return modelType;
    }


    /**
     * Sets the modelType value for this NetworkInfoRequest.
     * 
     * @param modelType
     */
    public void setModelType(java.lang.Integer modelType) {
        this.modelType = modelType;
    }


    /**
     * Gets the orderBy value for this NetworkInfoRequest.
     * 
     * @return orderBy
     */
    public java.lang.String getOrderBy() {
        return orderBy;
    }


    /**
     * Sets the orderBy value for this NetworkInfoRequest.
     * 
     * @param orderBy
     */
    public void setOrderBy(java.lang.String orderBy) {
        this.orderBy = orderBy;
    }


    /**
     * Gets the page value for this NetworkInfoRequest.
     * 
     * @return page
     */
    public java.lang.Integer getPage() {
        return page;
    }


    /**
     * Sets the page value for this NetworkInfoRequest.
     * 
     * @param page
     */
    public void setPage(java.lang.Integer page) {
        this.page = page;
    }


    /**
     * Gets the pageSize value for this NetworkInfoRequest.
     * 
     * @return pageSize
     */
    public java.lang.Integer getPageSize() {
        return pageSize;
    }


    /**
     * Sets the pageSize value for this NetworkInfoRequest.
     * 
     * @param pageSize
     */
    public void setPageSize(java.lang.Integer pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * Gets the searchParameters value for this NetworkInfoRequest.
     * 
     * @return searchParameters
     */
    public java.lang.Object[] getSearchParameters() {
        return searchParameters;
    }


    /**
     * Sets the searchParameters value for this NetworkInfoRequest.
     * 
     * @param searchParameters
     */
    public void setSearchParameters(java.lang.Object[] searchParameters) {
        this.searchParameters = searchParameters;
    }


    /**
     * Gets the showPrivatesOnly value for this NetworkInfoRequest.
     * 
     * @return showPrivatesOnly
     */
    public java.lang.Boolean getShowPrivatesOnly() {
        return showPrivatesOnly;
    }


    /**
     * Sets the showPrivatesOnly value for this NetworkInfoRequest.
     * 
     * @param showPrivatesOnly
     */
    public void setShowPrivatesOnly(java.lang.Boolean showPrivatesOnly) {
        this.showPrivatesOnly = showPrivatesOnly;
    }


    /**
     * Gets the textSearch value for this NetworkInfoRequest.
     * 
     * @return textSearch
     */
    public java.lang.Boolean getTextSearch() {
        return textSearch;
    }


    /**
     * Sets the textSearch value for this NetworkInfoRequest.
     * 
     * @param textSearch
     */
    public void setTextSearch(java.lang.Boolean textSearch) {
        this.textSearch = textSearch;
    }


    /**
     * Gets the textSearchMode value for this NetworkInfoRequest.
     * 
     * @return textSearchMode
     */
    public java.lang.Integer getTextSearchMode() {
        return textSearchMode;
    }


    /**
     * Sets the textSearchMode value for this NetworkInfoRequest.
     * 
     * @param textSearchMode
     */
    public void setTextSearchMode(java.lang.Integer textSearchMode) {
        this.textSearchMode = textSearchMode;
    }


    /**
     * Gets the user value for this NetworkInfoRequest.
     * 
     * @return user
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this NetworkInfoRequest.
     * 
     * @param user
     */
    public void setUser(shared.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }


    /**
     * Gets the whereClause value for this NetworkInfoRequest.
     * 
     * @return whereClause
     */
    public java.lang.String getWhereClause() {
        return whereClause;
    }


    /**
     * Sets the whereClause value for this NetworkInfoRequest.
     * 
     * @param whereClause
     */
    public void setWhereClause(java.lang.String whereClause) {
        this.whereClause = whereClause;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NetworkInfoRequest)) return false;
        NetworkInfoRequest other = (NetworkInfoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ACLs==null && other.getACLs()==null) || 
             (this.ACLs!=null &&
              java.util.Arrays.equals(this.ACLs, other.getACLs()))) &&
            ((this.desc==null && other.getDesc()==null) || 
             (this.desc!=null &&
              this.desc.equals(other.getDesc()))) &&
            ((this.modelType==null && other.getModelType()==null) || 
             (this.modelType!=null &&
              this.modelType.equals(other.getModelType()))) &&
            ((this.orderBy==null && other.getOrderBy()==null) || 
             (this.orderBy!=null &&
              this.orderBy.equals(other.getOrderBy()))) &&
            ((this.page==null && other.getPage()==null) || 
             (this.page!=null &&
              this.page.equals(other.getPage()))) &&
            ((this.pageSize==null && other.getPageSize()==null) || 
             (this.pageSize!=null &&
              this.pageSize.equals(other.getPageSize()))) &&
            ((this.searchParameters==null && other.getSearchParameters()==null) || 
             (this.searchParameters!=null &&
              java.util.Arrays.equals(this.searchParameters, other.getSearchParameters()))) &&
            ((this.showPrivatesOnly==null && other.getShowPrivatesOnly()==null) || 
             (this.showPrivatesOnly!=null &&
              this.showPrivatesOnly.equals(other.getShowPrivatesOnly()))) &&
            ((this.textSearch==null && other.getTextSearch()==null) || 
             (this.textSearch!=null &&
              this.textSearch.equals(other.getTextSearch()))) &&
            ((this.textSearchMode==null && other.getTextSearchMode()==null) || 
             (this.textSearchMode!=null &&
              this.textSearchMode.equals(other.getTextSearchMode()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.whereClause==null && other.getWhereClause()==null) || 
             (this.whereClause!=null &&
              this.whereClause.equals(other.getWhereClause())));
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
        if (getACLs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getACLs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getACLs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDesc() != null) {
            _hashCode += getDesc().hashCode();
        }
        if (getModelType() != null) {
            _hashCode += getModelType().hashCode();
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
        if (getSearchParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSearchParameters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSearchParameters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getShowPrivatesOnly() != null) {
            _hashCode += getShowPrivatesOnly().hashCode();
        }
        if (getTextSearch() != null) {
            _hashCode += getTextSearch().hashCode();
        }
        if (getTextSearchMode() != null) {
            _hashCode += getTextSearchMode().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getWhereClause() != null) {
            _hashCode += getWhereClause().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NetworkInfoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkInfoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACLs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ACLs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("desc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Desc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ModelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
        elemField.setFieldName("searchParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "SearchParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "anyType"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("showPrivatesOnly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ShowPrivatesOnly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textSearch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TextSearch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textSearchMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TextSearchMode"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("whereClause");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "WhereClause"));
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
