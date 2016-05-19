/**
 * ManipulationInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class ManipulationInfo  implements java.io.Serializable {
    private String baseNetworkId;

    private String caculationTime;

    private java.util.Calendar createdDate;

    private String curProgress;

    private String derivedNetworkId;

    private String description;

    private String errDescription;

    private int[] invaders;

    private int linkCount;

    private Boolean linkSaved;

    private String manipulationId;

    private int modelType;

    private String networkName;

    private int nodeCount;

    private boolean original;

    private Integer recordedNumber;

    private int[] removedList;

    private Integer runningStatus;

    private int totalTimesteps;

    private int type;

    private String username;

    public ManipulationInfo() {
    }

    public ManipulationInfo(
           String baseNetworkId,
           String caculationTime,
           java.util.Calendar createdDate,
           String curProgress,
           String derivedNetworkId,
           String description,
           String errDescription,
           int[] invaders,
           int linkCount,
           Boolean linkSaved,
           String manipulationId,
           int modelType,
           String networkName,
           int nodeCount,
           boolean original,
           Integer recordedNumber,
           int[] removedList,
           Integer runningStatus,
           int totalTimesteps,
           int type,
           String username) {
           this.baseNetworkId = baseNetworkId;
           this.caculationTime = caculationTime;
           this.createdDate = createdDate;
           this.curProgress = curProgress;
           this.derivedNetworkId = derivedNetworkId;
           this.description = description;
           this.errDescription = errDescription;
           this.invaders = invaders;
           this.linkCount = linkCount;
           this.linkSaved = linkSaved;
           this.manipulationId = manipulationId;
           this.modelType = modelType;
           this.networkName = networkName;
           this.nodeCount = nodeCount;
           this.original = original;
           this.recordedNumber = recordedNumber;
           this.removedList = removedList;
           this.runningStatus = runningStatus;
           this.totalTimesteps = totalTimesteps;
           this.type = type;
           this.username = username;
    }


    /**
     * Gets the baseNetworkId value for this ManipulationInfo.
     *
     * @return baseNetworkId
     */
    public String getBaseNetworkId() {
        return baseNetworkId;
    }


    /**
     * Sets the baseNetworkId value for this ManipulationInfo.
     *
     * @param baseNetworkId
     */
    public void setBaseNetworkId(String baseNetworkId) {
        this.baseNetworkId = baseNetworkId;
    }


    /**
     * Gets the caculationTime value for this ManipulationInfo.
     *
     * @return caculationTime
     */
    public String getCaculationTime() {
        return caculationTime;
    }


    /**
     * Sets the caculationTime value for this ManipulationInfo.
     *
     * @param caculationTime
     */
    public void setCaculationTime(String caculationTime) {
        this.caculationTime = caculationTime;
    }


    /**
     * Gets the createdDate value for this ManipulationInfo.
     *
     * @return createdDate
     */
    public java.util.Calendar getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this ManipulationInfo.
     *
     * @param createdDate
     */
    public void setCreatedDate(java.util.Calendar createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the curProgress value for this ManipulationInfo.
     *
     * @return curProgress
     */
    public String getCurProgress() {
        return curProgress;
    }


    /**
     * Sets the curProgress value for this ManipulationInfo.
     *
     * @param curProgress
     */
    public void setCurProgress(String curProgress) {
        this.curProgress = curProgress;
    }


    /**
     * Gets the derivedNetworkId value for this ManipulationInfo.
     *
     * @return derivedNetworkId
     */
    public String getDerivedNetworkId() {
        return derivedNetworkId;
    }


    /**
     * Sets the derivedNetworkId value for this ManipulationInfo.
     *
     * @param derivedNetworkId
     */
    public void setDerivedNetworkId(String derivedNetworkId) {
        this.derivedNetworkId = derivedNetworkId;
    }


    /**
     * Gets the description value for this ManipulationInfo.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ManipulationInfo.
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the errDescription value for this ManipulationInfo.
     *
     * @return errDescription
     */
    public String getErrDescription() {
        return errDescription;
    }


    /**
     * Sets the errDescription value for this ManipulationInfo.
     *
     * @param errDescription
     */
    public void setErrDescription(String errDescription) {
        this.errDescription = errDescription;
    }


    /**
     * Gets the invaders value for this ManipulationInfo.
     *
     * @return invaders
     */
    public int[] getInvaders() {
        return invaders;
    }


    /**
     * Sets the invaders value for this ManipulationInfo.
     *
     * @param invaders
     */
    public void setInvaders(int[] invaders) {
        this.invaders = invaders;
    }


    /**
     * Gets the linkCount value for this ManipulationInfo.
     *
     * @return linkCount
     */
    public int getLinkCount() {
        return linkCount;
    }


    /**
     * Sets the linkCount value for this ManipulationInfo.
     *
     * @param linkCount
     */
    public void setLinkCount(int linkCount) {
        this.linkCount = linkCount;
    }


    /**
     * Gets the linkSaved value for this ManipulationInfo.
     *
     * @return linkSaved
     */
    public Boolean getLinkSaved() {
        return linkSaved;
    }


    /**
     * Sets the linkSaved value for this ManipulationInfo.
     *
     * @param linkSaved
     */
    public void setLinkSaved(Boolean linkSaved) {
        this.linkSaved = linkSaved;
    }


    /**
     * Gets the manipulationId value for this ManipulationInfo.
     *
     * @return manipulationId
     */
    public String getManipulationId() {
        return manipulationId;
    }


    /**
     * Sets the manipulationId value for this ManipulationInfo.
     *
     * @param manipulationId
     */
    public void setManipulationId(String manipulationId) {
        this.manipulationId = manipulationId;
    }


    /**
     * Gets the modelType value for this ManipulationInfo.
     *
     * @return modelType
     */
    public int getModelType() {
        return modelType;
    }


    /**
     * Sets the modelType value for this ManipulationInfo.
     *
     * @param modelType
     */
    public void setModelType(int modelType) {
        this.modelType = modelType;
    }


    /**
     * Gets the networkName value for this ManipulationInfo.
     *
     * @return networkName
     */
    public String getNetworkName() {
        return networkName;
    }


    /**
     * Sets the networkName value for this ManipulationInfo.
     *
     * @param networkName
     */
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }


    /**
     * Gets the nodeCount value for this ManipulationInfo.
     *
     * @return nodeCount
     */
    public int getNodeCount() {
        return nodeCount;
    }


    /**
     * Sets the nodeCount value for this ManipulationInfo.
     *
     * @param nodeCount
     */
    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }


    /**
     * Gets the original value for this ManipulationInfo.
     *
     * @return original
     */
    public boolean isOriginal() {
        return original;
    }


    /**
     * Sets the original value for this ManipulationInfo.
     *
     * @param original
     */
    public void setOriginal(boolean original) {
        this.original = original;
    }


    /**
     * Gets the recordedNumber value for this ManipulationInfo.
     *
     * @return recordedNumber
     */
    public Integer getRecordedNumber() {
        return recordedNumber;
    }


    /**
     * Sets the recordedNumber value for this ManipulationInfo.
     *
     * @param recordedNumber
     */
    public void setRecordedNumber(Integer recordedNumber) {
        this.recordedNumber = recordedNumber;
    }


    /**
     * Gets the removedList value for this ManipulationInfo.
     *
     * @return removedList
     */
    public int[] getRemovedList() {
        return removedList;
    }


    /**
     * Sets the removedList value for this ManipulationInfo.
     *
     * @param removedList
     */
    public void setRemovedList(int[] removedList) {
        this.removedList = removedList;
    }


    /**
     * Gets the runningStatus value for this ManipulationInfo.
     *
     * @return runningStatus
     */
    public Integer getRunningStatus() {
        return runningStatus;
    }


    /**
     * Sets the runningStatus value for this ManipulationInfo.
     *
     * @param runningStatus
     */
    public void setRunningStatus(Integer runningStatus) {
        this.runningStatus = runningStatus;
    }


    /**
     * Gets the totalTimesteps value for this ManipulationInfo.
     *
     * @return totalTimesteps
     */
    public int getTotalTimesteps() {
        return totalTimesteps;
    }


    /**
     * Sets the totalTimesteps value for this ManipulationInfo.
     *
     * @param totalTimesteps
     */
    public void setTotalTimesteps(int totalTimesteps) {
        this.totalTimesteps = totalTimesteps;
    }


    /**
     * Gets the type value for this ManipulationInfo.
     *
     * @return type
     */
    public int getType() {
        return type;
    }


    /**
     * Sets the type value for this ManipulationInfo.
     *
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }


    /**
     * Gets the username value for this ManipulationInfo.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this ManipulationInfo.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulationInfo)) return false;
        ManipulationInfo other = (ManipulationInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.baseNetworkId==null && other.getBaseNetworkId()==null) ||
             (this.baseNetworkId!=null &&
              this.baseNetworkId.equals(other.getBaseNetworkId()))) &&
            ((this.caculationTime==null && other.getCaculationTime()==null) ||
             (this.caculationTime!=null &&
              this.caculationTime.equals(other.getCaculationTime()))) &&
            ((this.createdDate==null && other.getCreatedDate()==null) ||
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.curProgress==null && other.getCurProgress()==null) ||
             (this.curProgress!=null &&
              this.curProgress.equals(other.getCurProgress()))) &&
            ((this.derivedNetworkId==null && other.getDerivedNetworkId()==null) ||
             (this.derivedNetworkId!=null &&
              this.derivedNetworkId.equals(other.getDerivedNetworkId()))) &&
            ((this.description==null && other.getDescription()==null) ||
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.errDescription==null && other.getErrDescription()==null) ||
             (this.errDescription!=null &&
              this.errDescription.equals(other.getErrDescription()))) &&
            ((this.invaders==null && other.getInvaders()==null) ||
             (this.invaders!=null &&
              java.util.Arrays.equals(this.invaders, other.getInvaders()))) &&
            this.linkCount == other.getLinkCount() &&
            ((this.linkSaved==null && other.getLinkSaved()==null) ||
             (this.linkSaved!=null &&
              this.linkSaved.equals(other.getLinkSaved()))) &&
            ((this.manipulationId==null && other.getManipulationId()==null) ||
             (this.manipulationId!=null &&
              this.manipulationId.equals(other.getManipulationId()))) &&
            this.modelType == other.getModelType() &&
            ((this.networkName==null && other.getNetworkName()==null) ||
             (this.networkName!=null &&
              this.networkName.equals(other.getNetworkName()))) &&
            this.nodeCount == other.getNodeCount() &&
            this.original == other.isOriginal() &&
            ((this.recordedNumber==null && other.getRecordedNumber()==null) ||
             (this.recordedNumber!=null &&
              this.recordedNumber.equals(other.getRecordedNumber()))) &&
            ((this.removedList==null && other.getRemovedList()==null) ||
             (this.removedList!=null &&
              java.util.Arrays.equals(this.removedList, other.getRemovedList()))) &&
            ((this.runningStatus==null && other.getRunningStatus()==null) ||
             (this.runningStatus!=null &&
              this.runningStatus.equals(other.getRunningStatus()))) &&
            this.totalTimesteps == other.getTotalTimesteps() &&
            this.type == other.getType() &&
            ((this.username==null && other.getUsername()==null) ||
             (this.username!=null &&
              this.username.equals(other.getUsername())));
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
        if (getBaseNetworkId() != null) {
            _hashCode += getBaseNetworkId().hashCode();
        }
        if (getCaculationTime() != null) {
            _hashCode += getCaculationTime().hashCode();
        }
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getCurProgress() != null) {
            _hashCode += getCurProgress().hashCode();
        }
        if (getDerivedNetworkId() != null) {
            _hashCode += getDerivedNetworkId().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getErrDescription() != null) {
            _hashCode += getErrDescription().hashCode();
        }
        if (getInvaders() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInvaders());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getInvaders(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getLinkCount();
        if (getLinkSaved() != null) {
            _hashCode += getLinkSaved().hashCode();
        }
        if (getManipulationId() != null) {
            _hashCode += getManipulationId().hashCode();
        }
        _hashCode += getModelType();
        if (getNetworkName() != null) {
            _hashCode += getNetworkName().hashCode();
        }
        _hashCode += getNodeCount();
        _hashCode += (isOriginal() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getRecordedNumber() != null) {
            _hashCode += getRecordedNumber().hashCode();
        }
        if (getRemovedList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRemovedList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getRemovedList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRunningStatus() != null) {
            _hashCode += getRunningStatus().hashCode();
        }
        _hashCode += getTotalTimesteps();
        _hashCode += getType();
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulationInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baseNetworkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "BaseNetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caculationTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "CaculationTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "CreatedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curProgress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "CurProgress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("derivedNetworkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "DerivedNetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("errDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ErrDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invaders");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Invaders"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LinkCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkSaved");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LinkSaved"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ManipulationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ModelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("original");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Original"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordedNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "RecordedNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("removedList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "RemovedList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("runningStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "RunningStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalTimesteps");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TotalTimesteps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
