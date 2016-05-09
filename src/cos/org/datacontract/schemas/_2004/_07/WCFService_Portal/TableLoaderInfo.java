/**
 * TableLoaderInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class TableLoaderInfo  implements java.io.Serializable {
    private String[] conceptSelected;

    private Integer delim;

    private String fileContent;

    private Boolean firstColHeader;

    private Boolean header;

    private Boolean isSingle;

    private Boolean isWebFile;

    private Integer lineRead;

    private Integer linkFormat;

    private Integer NDefault;

    private Integer NRows;

    private Boolean overrideSingularityCheck;

    private Integer skip;

    private String type;

    public TableLoaderInfo() {
    }

    public TableLoaderInfo(
           String[] conceptSelected,
           Integer delim,
           String fileContent,
           Boolean firstColHeader,
           Boolean header,
           Boolean isSingle,
           Boolean isWebFile,
           Integer lineRead,
           Integer linkFormat,
           Integer NDefault,
           Integer NRows,
           Boolean overrideSingularityCheck,
           Integer skip,
           String type) {
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
    public String[] getConceptSelected() {
        return conceptSelected;
    }


    /**
     * Sets the conceptSelected value for this TableLoaderInfo.
     *
     * @param conceptSelected
     */
    public void setConceptSelected(String[] conceptSelected) {
        this.conceptSelected = conceptSelected;
    }


    /**
     * Gets the delim value for this TableLoaderInfo.
     *
     * @return delim
     */
    public Integer getDelim() {
        return delim;
    }


    /**
     * Sets the delim value for this TableLoaderInfo.
     *
     * @param delim
     */
    public void setDelim(Integer delim) {
        this.delim = delim;
    }


    /**
     * Gets the fileContent value for this TableLoaderInfo.
     *
     * @return fileContent
     */
    public String getFileContent() {
        return fileContent;
    }


    /**
     * Sets the fileContent value for this TableLoaderInfo.
     *
     * @param fileContent
     */
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }


    /**
     * Gets the firstColHeader value for this TableLoaderInfo.
     *
     * @return firstColHeader
     */
    public Boolean getFirstColHeader() {
        return firstColHeader;
    }


    /**
     * Sets the firstColHeader value for this TableLoaderInfo.
     *
     * @param firstColHeader
     */
    public void setFirstColHeader(Boolean firstColHeader) {
        this.firstColHeader = firstColHeader;
    }


    /**
     * Gets the header value for this TableLoaderInfo.
     *
     * @return header
     */
    public Boolean getHeader() {
        return header;
    }


    /**
     * Sets the header value for this TableLoaderInfo.
     *
     * @param header
     */
    public void setHeader(Boolean header) {
        this.header = header;
    }


    /**
     * Gets the isSingle value for this TableLoaderInfo.
     *
     * @return isSingle
     */
    public Boolean getIsSingle() {
        return isSingle;
    }


    /**
     * Sets the isSingle value for this TableLoaderInfo.
     *
     * @param isSingle
     */
    public void setIsSingle(Boolean isSingle) {
        this.isSingle = isSingle;
    }


    /**
     * Gets the isWebFile value for this TableLoaderInfo.
     *
     * @return isWebFile
     */
    public Boolean getIsWebFile() {
        return isWebFile;
    }


    /**
     * Sets the isWebFile value for this TableLoaderInfo.
     *
     * @param isWebFile
     */
    public void setIsWebFile(Boolean isWebFile) {
        this.isWebFile = isWebFile;
    }


    /**
     * Gets the lineRead value for this TableLoaderInfo.
     *
     * @return lineRead
     */
    public Integer getLineRead() {
        return lineRead;
    }


    /**
     * Sets the lineRead value for this TableLoaderInfo.
     *
     * @param lineRead
     */
    public void setLineRead(Integer lineRead) {
        this.lineRead = lineRead;
    }


    /**
     * Gets the linkFormat value for this TableLoaderInfo.
     *
     * @return linkFormat
     */
    public Integer getLinkFormat() {
        return linkFormat;
    }


    /**
     * Sets the linkFormat value for this TableLoaderInfo.
     *
     * @param linkFormat
     */
    public void setLinkFormat(Integer linkFormat) {
        this.linkFormat = linkFormat;
    }


    /**
     * Gets the NDefault value for this TableLoaderInfo.
     *
     * @return NDefault
     */
    public Integer getNDefault() {
        return NDefault;
    }


    /**
     * Sets the NDefault value for this TableLoaderInfo.
     *
     * @param NDefault
     */
    public void setNDefault(Integer NDefault) {
        this.NDefault = NDefault;
    }


    /**
     * Gets the NRows value for this TableLoaderInfo.
     *
     * @return NRows
     */
    public Integer getNRows() {
        return NRows;
    }


    /**
     * Sets the NRows value for this TableLoaderInfo.
     *
     * @param NRows
     */
    public void setNRows(Integer NRows) {
        this.NRows = NRows;
    }


    /**
     * Gets the overrideSingularityCheck value for this TableLoaderInfo.
     *
     * @return overrideSingularityCheck
     */
    public Boolean getOverrideSingularityCheck() {
        return overrideSingularityCheck;
    }


    /**
     * Sets the overrideSingularityCheck value for this TableLoaderInfo.
     *
     * @param overrideSingularityCheck
     */
    public void setOverrideSingularityCheck(Boolean overrideSingularityCheck) {
        this.overrideSingularityCheck = overrideSingularityCheck;
    }


    /**
     * Gets the skip value for this TableLoaderInfo.
     *
     * @return skip
     */
    public Integer getSkip() {
        return skip;
    }


    /**
     * Sets the skip value for this TableLoaderInfo.
     *
     * @param skip
     */
    public void setSkip(Integer skip) {
        this.skip = skip;
    }


    /**
     * Gets the type value for this TableLoaderInfo.
     *
     * @return type
     */
    public String getType() {
        return type;
    }


    /**
     * Sets the type value for this TableLoaderInfo.
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
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
                Object obj = java.lang.reflect.Array.get(getConceptSelected(), i);
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
