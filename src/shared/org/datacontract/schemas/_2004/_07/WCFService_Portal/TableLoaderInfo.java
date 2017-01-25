/**
 * TableLoaderInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.WCFService_Portal;

public class TableLoaderInfo  implements java.io.Serializable {
    private java.lang.String[] conceptSelected;

    private java.lang.Integer delim;

    private java.lang.String fileContent;

    private java.lang.Boolean firstColHeader;

    private java.lang.Boolean header;

    private java.lang.Boolean isSingle;

    private java.lang.Boolean isWebFile;

    private java.lang.Integer lineRead;

    private java.lang.Integer linkFormat;

    private java.lang.Integer NDefault;

    private java.lang.Integer NRows;

    private java.lang.Boolean overrideSingularityCheck;

    private java.lang.Integer skip;

    private java.lang.String type;

    public TableLoaderInfo() {
    }

    public TableLoaderInfo(
           java.lang.String[] conceptSelected,
           java.lang.Integer delim,
           java.lang.String fileContent,
           java.lang.Boolean firstColHeader,
           java.lang.Boolean header,
           java.lang.Boolean isSingle,
           java.lang.Boolean isWebFile,
           java.lang.Integer lineRead,
           java.lang.Integer linkFormat,
           java.lang.Integer NDefault,
           java.lang.Integer NRows,
           java.lang.Boolean overrideSingularityCheck,
           java.lang.Integer skip,
           java.lang.String type) {
           this.conceptSelected = conceptSelected;
           this.delim = delim;
           this.fileContent = fileContent;
           this.firstColHeader = firstColHeader;
           this.header = header;
           this.isSingle = isSingle;
           this.isWebFile = isWebFile;
           this.lineRead = lineRead;
           this.linkFormat = linkFormat;
           this.NDefault = NDefault;
           this.NRows = NRows;
           this.overrideSingularityCheck = overrideSingularityCheck;
           this.skip = skip;
           this.type = type;
    }


    /**
     * Gets the conceptSelected value for this TableLoaderInfo.
     * 
     * @return conceptSelected
     */
    public java.lang.String[] getConceptSelected() {
        return conceptSelected;
    }


    /**
     * Sets the conceptSelected value for this TableLoaderInfo.
     * 
     * @param conceptSelected
     */
    public void setConceptSelected(java.lang.String[] conceptSelected) {
        this.conceptSelected = conceptSelected;
    }


    /**
     * Gets the delim value for this TableLoaderInfo.
     * 
     * @return delim
     */
    public java.lang.Integer getDelim() {
        return delim;
    }


    /**
     * Sets the delim value for this TableLoaderInfo.
     * 
     * @param delim
     */
    public void setDelim(java.lang.Integer delim) {
        this.delim = delim;
    }


    /**
     * Gets the fileContent value for this TableLoaderInfo.
     * 
     * @return fileContent
     */
    public java.lang.String getFileContent() {
        return fileContent;
    }


    /**
     * Sets the fileContent value for this TableLoaderInfo.
     * 
     * @param fileContent
     */
    public void setFileContent(java.lang.String fileContent) {
        this.fileContent = fileContent;
    }


    /**
     * Gets the firstColHeader value for this TableLoaderInfo.
     * 
     * @return firstColHeader
     */
    public java.lang.Boolean getFirstColHeader() {
        return firstColHeader;
    }


    /**
     * Sets the firstColHeader value for this TableLoaderInfo.
     * 
     * @param firstColHeader
     */
    public void setFirstColHeader(java.lang.Boolean firstColHeader) {
        this.firstColHeader = firstColHeader;
    }


    /**
     * Gets the header value for this TableLoaderInfo.
     * 
     * @return header
     */
    public java.lang.Boolean getHeader() {
        return header;
    }


    /**
     * Sets the header value for this TableLoaderInfo.
     * 
     * @param header
     */
    public void setHeader(java.lang.Boolean header) {
        this.header = header;
    }


    /**
     * Gets the isSingle value for this TableLoaderInfo.
     * 
     * @return isSingle
     */
    public java.lang.Boolean getIsSingle() {
        return isSingle;
    }


    /**
     * Sets the isSingle value for this TableLoaderInfo.
     * 
     * @param isSingle
     */
    public void setIsSingle(java.lang.Boolean isSingle) {
        this.isSingle = isSingle;
    }


    /**
     * Gets the isWebFile value for this TableLoaderInfo.
     * 
     * @return isWebFile
     */
    public java.lang.Boolean getIsWebFile() {
        return isWebFile;
    }


    /**
     * Sets the isWebFile value for this TableLoaderInfo.
     * 
     * @param isWebFile
     */
    public void setIsWebFile(java.lang.Boolean isWebFile) {
        this.isWebFile = isWebFile;
    }


    /**
     * Gets the lineRead value for this TableLoaderInfo.
     * 
     * @return lineRead
     */
    public java.lang.Integer getLineRead() {
        return lineRead;
    }


    /**
     * Sets the lineRead value for this TableLoaderInfo.
     * 
     * @param lineRead
     */
    public void setLineRead(java.lang.Integer lineRead) {
        this.lineRead = lineRead;
    }


    /**
     * Gets the linkFormat value for this TableLoaderInfo.
     * 
     * @return linkFormat
     */
    public java.lang.Integer getLinkFormat() {
        return linkFormat;
    }


    /**
     * Sets the linkFormat value for this TableLoaderInfo.
     * 
     * @param linkFormat
     */
    public void setLinkFormat(java.lang.Integer linkFormat) {
        this.linkFormat = linkFormat;
    }


    /**
     * Gets the NDefault value for this TableLoaderInfo.
     * 
     * @return NDefault
     */
    public java.lang.Integer getNDefault() {
        return NDefault;
    }


    /**
     * Sets the NDefault value for this TableLoaderInfo.
     * 
     * @param NDefault
     */
    public void setNDefault(java.lang.Integer NDefault) {
        this.NDefault = NDefault;
    }


    /**
     * Gets the NRows value for this TableLoaderInfo.
     * 
     * @return NRows
     */
    public java.lang.Integer getNRows() {
        return NRows;
    }


    /**
     * Sets the NRows value for this TableLoaderInfo.
     * 
     * @param NRows
     */
    public void setNRows(java.lang.Integer NRows) {
        this.NRows = NRows;
    }


    /**
     * Gets the overrideSingularityCheck value for this TableLoaderInfo.
     * 
     * @return overrideSingularityCheck
     */
    public java.lang.Boolean getOverrideSingularityCheck() {
        return overrideSingularityCheck;
    }


    /**
     * Sets the overrideSingularityCheck value for this TableLoaderInfo.
     * 
     * @param overrideSingularityCheck
     */
    public void setOverrideSingularityCheck(java.lang.Boolean overrideSingularityCheck) {
        this.overrideSingularityCheck = overrideSingularityCheck;
    }


    /**
     * Gets the skip value for this TableLoaderInfo.
     * 
     * @return skip
     */
    public java.lang.Integer getSkip() {
        return skip;
    }


    /**
     * Sets the skip value for this TableLoaderInfo.
     * 
     * @param skip
     */
    public void setSkip(java.lang.Integer skip) {
        this.skip = skip;
    }


    /**
     * Gets the type value for this TableLoaderInfo.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this TableLoaderInfo.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TableLoaderInfo)) return false;
        TableLoaderInfo other = (TableLoaderInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.conceptSelected==null && other.getConceptSelected()==null) || 
             (this.conceptSelected!=null &&
              java.util.Arrays.equals(this.conceptSelected, other.getConceptSelected()))) &&
            ((this.delim==null && other.getDelim()==null) || 
             (this.delim!=null &&
              this.delim.equals(other.getDelim()))) &&
            ((this.fileContent==null && other.getFileContent()==null) || 
             (this.fileContent!=null &&
              this.fileContent.equals(other.getFileContent()))) &&
            ((this.firstColHeader==null && other.getFirstColHeader()==null) || 
             (this.firstColHeader!=null &&
              this.firstColHeader.equals(other.getFirstColHeader()))) &&
            ((this.header==null && other.getHeader()==null) || 
             (this.header!=null &&
              this.header.equals(other.getHeader()))) &&
            ((this.isSingle==null && other.getIsSingle()==null) || 
             (this.isSingle!=null &&
              this.isSingle.equals(other.getIsSingle()))) &&
            ((this.isWebFile==null && other.getIsWebFile()==null) || 
             (this.isWebFile!=null &&
              this.isWebFile.equals(other.getIsWebFile()))) &&
            ((this.lineRead==null && other.getLineRead()==null) || 
             (this.lineRead!=null &&
              this.lineRead.equals(other.getLineRead()))) &&
            ((this.linkFormat==null && other.getLinkFormat()==null) || 
             (this.linkFormat!=null &&
              this.linkFormat.equals(other.getLinkFormat()))) &&
            ((this.NDefault==null && other.getNDefault()==null) || 
             (this.NDefault!=null &&
              this.NDefault.equals(other.getNDefault()))) &&
            ((this.NRows==null && other.getNRows()==null) || 
             (this.NRows!=null &&
              this.NRows.equals(other.getNRows()))) &&
            ((this.overrideSingularityCheck==null && other.getOverrideSingularityCheck()==null) || 
             (this.overrideSingularityCheck!=null &&
              this.overrideSingularityCheck.equals(other.getOverrideSingularityCheck()))) &&
            ((this.skip==null && other.getSkip()==null) || 
             (this.skip!=null &&
              this.skip.equals(other.getSkip()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType())));
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
        if (getConceptSelected() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConceptSelected());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConceptSelected(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDelim() != null) {
            _hashCode += getDelim().hashCode();
        }
        if (getFileContent() != null) {
            _hashCode += getFileContent().hashCode();
        }
        if (getFirstColHeader() != null) {
            _hashCode += getFirstColHeader().hashCode();
        }
        if (getHeader() != null) {
            _hashCode += getHeader().hashCode();
        }
        if (getIsSingle() != null) {
            _hashCode += getIsSingle().hashCode();
        }
        if (getIsWebFile() != null) {
            _hashCode += getIsWebFile().hashCode();
        }
        if (getLineRead() != null) {
            _hashCode += getLineRead().hashCode();
        }
        if (getLinkFormat() != null) {
            _hashCode += getLinkFormat().hashCode();
        }
        if (getNDefault() != null) {
            _hashCode += getNDefault().hashCode();
        }
        if (getNRows() != null) {
            _hashCode += getNRows().hashCode();
        }
        if (getOverrideSingularityCheck() != null) {
            _hashCode += getOverrideSingularityCheck().hashCode();
        }
        if (getSkip() != null) {
            _hashCode += getSkip().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TableLoaderInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TableLoaderInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conceptSelected");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ConceptSelected"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delim");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Delim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "FileContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstColHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "FirstColHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSingle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "IsSingle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isWebFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "IsWebFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineRead");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LineRead"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LinkFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NDefault");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NDefault"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRows");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NRows"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("overrideSingularityCheck");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "OverrideSingularityCheck"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("skip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Skip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Type"));
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
