/**
 * ManipulatingNode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.ManipulationParameter;

public class ManipulatingNode  implements java.io.Serializable {
    private double beginingBiomass;

    private boolean gameMode;

    private boolean hasLinks;

    private int manipulationActionType;

    private int nodeIdx;

    private String nodeName;

    private String originFoodwebId;

    private float timestep;

    private int timestepIdx;

    private int modelType;

    public ManipulatingNode() {
    }

    public ManipulatingNode(
           double beginingBiomass,
           boolean gameMode,
           boolean hasLinks,
           int manipulationActionType,
           int nodeIdx,
           String nodeName,
           String originFoodwebId,
           float timestep,
           int timestepIdx,
           int modelType) {
           this.beginingBiomass = beginingBiomass;
           this.gameMode = gameMode;
           this.hasLinks = hasLinks;
           this.manipulationActionType = manipulationActionType;
           this.nodeIdx = nodeIdx;
           this.nodeName = nodeName;
           this.originFoodwebId = originFoodwebId;
           this.timestep = timestep;
           this.timestepIdx = timestepIdx;
           this.modelType = modelType;
    }


    /**
     * Gets the beginingBiomass value for this ManipulatingNode.
     *
     * @return beginingBiomass
     */
    public double getBeginingBiomass() {
        return beginingBiomass;
    }


    /**
     * Sets the beginingBiomass value for this ManipulatingNode.
     *
     * @param beginingBiomass
     */
    public void setBeginingBiomass(double beginingBiomass) {
        this.beginingBiomass = beginingBiomass;
    }


    /**
     * Gets the gameMode value for this ManipulatingNode.
     *
     * @return gameMode
     */
    public boolean isGameMode() {
        return gameMode;
    }


    /**
     * Sets the gameMode value for this ManipulatingNode.
     *
     * @param gameMode
     */
    public void setGameMode(boolean gameMode) {
        this.gameMode = gameMode;
    }


    /**
     * Gets the hasLinks value for this ManipulatingNode.
     *
     * @return hasLinks
     */
    public boolean isHasLinks() {
        return hasLinks;
    }


    /**
     * Sets the hasLinks value for this ManipulatingNode.
     *
     * @param hasLinks
     */
    public void setHasLinks(boolean hasLinks) {
        this.hasLinks = hasLinks;
    }


    /**
     * Gets the manipulationActionType value for this ManipulatingNode.
     *
     * @return manipulationActionType
     */
    public int getManipulationActionType() {
        return manipulationActionType;
    }


    /**
     * Sets the manipulationActionType value for this ManipulatingNode.
     *
     * @param manipulationActionType
     */
    public void setManipulationActionType(int manipulationActionType) {
        this.manipulationActionType = manipulationActionType;
    }


    /**
     * Gets the nodeIdx value for this ManipulatingNode.
     *
     * @return nodeIdx
     */
    public int getNodeIdx() {
        return nodeIdx;
    }


    /**
     * Sets the nodeIdx value for this ManipulatingNode.
     *
     * @param nodeIdx
     */
    public void setNodeIdx(int nodeIdx) {
        this.nodeIdx = nodeIdx;
    }


    /**
     * Gets the nodeName value for this ManipulatingNode.
     *
     * @return nodeName
     */
    public String getNodeName() {
        return nodeName;
    }


    /**
     * Sets the nodeName value for this ManipulatingNode.
     *
     * @param nodeName
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }


    /**
     * Gets the originFoodwebId value for this ManipulatingNode.
     *
     * @return originFoodwebId
     */
    public String getOriginFoodwebId() {
        return originFoodwebId;
    }


    /**
     * Sets the originFoodwebId value for this ManipulatingNode.
     *
     * @param originFoodwebId
     */
    public void setOriginFoodwebId(String originFoodwebId) {
        this.originFoodwebId = originFoodwebId;
    }


    /**
     * Gets the timestep value for this ManipulatingNode.
     *
     * @return timestep
     */
    public float getTimestep() {
        return timestep;
    }


    /**
     * Sets the timestep value for this ManipulatingNode.
     *
     * @param timestep
     */
    public void setTimestep(float timestep) {
        this.timestep = timestep;
    }


    /**
     * Gets the timestepIdx value for this ManipulatingNode.
     *
     * @return timestepIdx
     */
    public int getTimestepIdx() {
        return timestepIdx;
    }


    /**
     * Sets the timestepIdx value for this ManipulatingNode.
     *
     * @param timestepIdx
     */
    public void setTimestepIdx(int timestepIdx) {
        this.timestepIdx = timestepIdx;
    }


    /**
     * Gets the modelType value for this ManipulatingNode.
     *
     * @return modelType
     */
    public int getModelType() {
        return modelType;
    }


    /**
     * Sets the modelType value for this ManipulatingNode.
     *
     * @param modelType
     */
    public void setModelType(int modelType) {
        this.modelType = modelType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ManipulatingNode)) return false;
        ManipulatingNode other = (ManipulatingNode) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.beginingBiomass == other.getBeginingBiomass() &&
            this.gameMode == other.isGameMode() &&
            this.hasLinks == other.isHasLinks() &&
            this.manipulationActionType == other.getManipulationActionType() &&
            this.nodeIdx == other.getNodeIdx() &&
            ((this.nodeName==null && other.getNodeName()==null) ||
             (this.nodeName!=null &&
              this.nodeName.equals(other.getNodeName()))) &&
            ((this.originFoodwebId==null && other.getOriginFoodwebId()==null) ||
             (this.originFoodwebId!=null &&
              this.originFoodwebId.equals(other.getOriginFoodwebId()))) &&
            this.timestep == other.getTimestep() &&
            this.timestepIdx == other.getTimestepIdx() &&
            this.modelType == other.getModelType();
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
        _hashCode += new Double(getBeginingBiomass()).hashCode();
        _hashCode += (isGameMode() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isHasLinks() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getManipulationActionType();
        _hashCode += getNodeIdx();
        if (getNodeName() != null) {
            _hashCode += getNodeName().hashCode();
        }
        if (getOriginFoodwebId() != null) {
            _hashCode += getOriginFoodwebId().hashCode();
        }
        _hashCode += new Float(getTimestep()).hashCode();
        _hashCode += getTimestepIdx();
        _hashCode += getModelType();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManipulatingNode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ManipulatingNode"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginingBiomass");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "BeginingBiomass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gameMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "GameMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasLinks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "HasLinks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationActionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "ManipulationActionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "NodeIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "NodeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originFoodwebId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "OriginFoodwebId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "Timestep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestepIdx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "TimestepIdx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/ManipulationParameter", "modelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
