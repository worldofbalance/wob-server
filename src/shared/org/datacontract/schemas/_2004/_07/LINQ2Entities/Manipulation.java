/**
 * Manipulation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.datacontract.schemas._2004._07.LINQ2Entities;

public class Manipulation  extends shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.Integer beginigTimestep;

    private java.lang.String biomassUnit;

    private java.lang.Boolean derived;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.EnergyFlowLink[] energyFlowLink;

    private java.lang.String id;

    private java.lang.Boolean initialBiomass;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode[] manipulationBiomassNode;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationModel[] manipulationModel;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationNetwork[] manipulationNetwork;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationParameter[] manipulationParameter;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationRequest manipulationRequest;

    private java.lang.String manipulationRequestId;

    private shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationRequesthovVakal manipulationRequestReference;

    private shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] manipulationTimesteps;

    private java.lang.Integer modelType;

    private java.lang.String networkId;

    private java.lang.String originalManipulationId;

    private java.lang.Boolean saveLink;

    private java.lang.Integer totalTimesteps;

    private java.lang.Integer type;

    private java.lang.Integer curTimestep;

    private java.lang.String description;

    public Manipulation() {
    }

    public Manipulation(
           org.apache.axis.types.Id _id,
           org.apache.axis.types.IDRef ref,
           shared.org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.Integer beginigTimestep,
           java.lang.String biomassUnit,
           java.lang.Boolean derived,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.EnergyFlowLink[] energyFlowLink,
           java.lang.String id,
           java.lang.Boolean initialBiomass,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode[] manipulationBiomassNode,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationModel[] manipulationModel,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationNetwork[] manipulationNetwork,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationParameter[] manipulationParameter,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationRequest manipulationRequest,
           java.lang.String manipulationRequestId,
           shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationRequesthovVakal manipulationRequestReference,
           shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] manipulationTimesteps,
           java.lang.Integer modelType,
           java.lang.String networkId,
           java.lang.String originalManipulationId,
           java.lang.Boolean saveLink,
           java.lang.Integer totalTimesteps,
           java.lang.Integer type,
           java.lang.Integer curTimestep,
           java.lang.String description) {
        super(
            _id,
            ref,
            entityKey);
        this.beginigTimestep = beginigTimestep;
        this.biomassUnit = biomassUnit;
        this.derived = derived;
        this.energyFlowLink = energyFlowLink;
        this.id = id;
        this.initialBiomass = initialBiomass;
        this.manipulationBiomassNode = manipulationBiomassNode;
        this.manipulationModel = manipulationModel;
        this.manipulationNetwork = manipulationNetwork;
        this.manipulationParameter = manipulationParameter;
        this.manipulationRequest = manipulationRequest;
        this.manipulationRequestId = manipulationRequestId;
        this.manipulationRequestReference = manipulationRequestReference;
        this.manipulationTimesteps = manipulationTimesteps;
        this.modelType = modelType;
        this.networkId = networkId;
        this.originalManipulationId = originalManipulationId;
        this.saveLink = saveLink;
        this.totalTimesteps = totalTimesteps;
        this.type = type;
        this.curTimestep = curTimestep;
        this.description = description;
    }


    /**
     * Gets the beginigTimestep value for this Manipulation.
     * 
     * @return beginigTimestep
     */
    public java.lang.Integer getBeginigTimestep() {
        return beginigTimestep;
    }


    /**
     * Sets the beginigTimestep value for this Manipulation.
     * 
     * @param beginigTimestep
     */
    public void setBeginigTimestep(java.lang.Integer beginigTimestep) {
        this.beginigTimestep = beginigTimestep;
    }


    /**
     * Gets the biomassUnit value for this Manipulation.
     * 
     * @return biomassUnit
     */
    public java.lang.String getBiomassUnit() {
        return biomassUnit;
    }


    /**
     * Sets the biomassUnit value for this Manipulation.
     * 
     * @param biomassUnit
     */
    public void setBiomassUnit(java.lang.String biomassUnit) {
        this.biomassUnit = biomassUnit;
    }


    /**
     * Gets the derived value for this Manipulation.
     * 
     * @return derived
     */
    public java.lang.Boolean getDerived() {
        return derived;
    }


    /**
     * Sets the derived value for this Manipulation.
     * 
     * @param derived
     */
    public void setDerived(java.lang.Boolean derived) {
        this.derived = derived;
    }


    /**
     * Gets the energyFlowLink value for this Manipulation.
     * 
     * @return energyFlowLink
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.EnergyFlowLink[] getEnergyFlowLink() {
        return energyFlowLink;
    }


    /**
     * Sets the energyFlowLink value for this Manipulation.
     * 
     * @param energyFlowLink
     */
    public void setEnergyFlowLink(shared.org.datacontract.schemas._2004._07.LINQ2Entities.EnergyFlowLink[] energyFlowLink) {
        this.energyFlowLink = energyFlowLink;
    }


    /**
     * Gets the id value for this Manipulation.
     * 
     * @return id
     */
/*    public java.lang.String getId() {
        return id;
    }
*/

    /**
     * Sets the id value for this Manipulation.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the initialBiomass value for this Manipulation.
     * 
     * @return initialBiomass
     */
    public java.lang.Boolean getInitialBiomass() {
        return initialBiomass;
    }


    /**
     * Sets the initialBiomass value for this Manipulation.
     * 
     * @param initialBiomass
     */
    public void setInitialBiomass(java.lang.Boolean initialBiomass) {
        this.initialBiomass = initialBiomass;
    }


    /**
     * Gets the manipulationBiomassNode value for this Manipulation.
     * 
     * @return manipulationBiomassNode
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode[] getManipulationBiomassNode() {
        return manipulationBiomassNode;
    }


    /**
     * Sets the manipulationBiomassNode value for this Manipulation.
     * 
     * @param manipulationBiomassNode
     */
    public void setManipulationBiomassNode(shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationBiomassNode[] manipulationBiomassNode) {
        this.manipulationBiomassNode = manipulationBiomassNode;
    }


    /**
     * Gets the manipulationModel value for this Manipulation.
     * 
     * @return manipulationModel
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationModel[] getManipulationModel() {
        return manipulationModel;
    }


    /**
     * Sets the manipulationModel value for this Manipulation.
     * 
     * @param manipulationModel
     */
    public void setManipulationModel(shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationModel[] manipulationModel) {
        this.manipulationModel = manipulationModel;
    }


    /**
     * Gets the manipulationNetwork value for this Manipulation.
     * 
     * @return manipulationNetwork
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationNetwork[] getManipulationNetwork() {
        return manipulationNetwork;
    }


    /**
     * Sets the manipulationNetwork value for this Manipulation.
     * 
     * @param manipulationNetwork
     */
    public void setManipulationNetwork(shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationNetwork[] manipulationNetwork) {
        this.manipulationNetwork = manipulationNetwork;
    }


    /**
     * Gets the manipulationParameter value for this Manipulation.
     * 
     * @return manipulationParameter
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationParameter[] getManipulationParameter() {
        return manipulationParameter;
    }


    /**
     * Sets the manipulationParameter value for this Manipulation.
     * 
     * @param manipulationParameter
     */
    public void setManipulationParameter(shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationParameter[] manipulationParameter) {
        this.manipulationParameter = manipulationParameter;
    }


    /**
     * Gets the manipulationRequest value for this Manipulation.
     * 
     * @return manipulationRequest
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationRequest getManipulationRequest() {
        return manipulationRequest;
    }


    /**
     * Sets the manipulationRequest value for this Manipulation.
     * 
     * @param manipulationRequest
     */
    public void setManipulationRequest(shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationRequest manipulationRequest) {
        this.manipulationRequest = manipulationRequest;
    }


    /**
     * Gets the manipulationRequestId value for this Manipulation.
     * 
     * @return manipulationRequestId
     */
    public java.lang.String getManipulationRequestId() {
        return manipulationRequestId;
    }


    /**
     * Sets the manipulationRequestId value for this Manipulation.
     * 
     * @param manipulationRequestId
     */
    public void setManipulationRequestId(java.lang.String manipulationRequestId) {
        this.manipulationRequestId = manipulationRequestId;
    }


    /**
     * Gets the manipulationRequestReference value for this Manipulation.
     * 
     * @return manipulationRequestReference
     */
    public shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationRequesthovVakal getManipulationRequestReference() {
        return manipulationRequestReference;
    }


    /**
     * Sets the manipulationRequestReference value for this Manipulation.
     * 
     * @param manipulationRequestReference
     */
    public void setManipulationRequestReference(shared.org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfManipulationRequesthovVakal manipulationRequestReference) {
        this.manipulationRequestReference = manipulationRequestReference;
    }


    /**
     * Gets the manipulationTimesteps value for this Manipulation.
     * 
     * @return manipulationTimesteps
     */
    public shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] getManipulationTimesteps() {
        return manipulationTimesteps;
    }


    /**
     * Sets the manipulationTimesteps value for this Manipulation.
     * 
     * @param manipulationTimesteps
     */
    public void setManipulationTimesteps(shared.org.datacontract.schemas._2004._07.LINQ2Entities.ManipulationTimesteps[] manipulationTimesteps) {
        this.manipulationTimesteps = manipulationTimesteps;
    }


    /**
     * Gets the modelType value for this Manipulation.
     * 
     * @return modelType
     */
    public java.lang.Integer getModelType() {
        return modelType;
    }


    /**
     * Sets the modelType value for this Manipulation.
     * 
     * @param modelType
     */
    public void setModelType(java.lang.Integer modelType) {
        this.modelType = modelType;
    }


    /**
     * Gets the networkId value for this Manipulation.
     * 
     * @return networkId
     */
    public java.lang.String getNetworkId() {
        return networkId;
    }


    /**
     * Sets the networkId value for this Manipulation.
     * 
     * @param networkId
     */
    public void setNetworkId(java.lang.String networkId) {
        this.networkId = networkId;
    }


    /**
     * Gets the originalManipulationId value for this Manipulation.
     * 
     * @return originalManipulationId
     */
    public java.lang.String getOriginalManipulationId() {
        return originalManipulationId;
    }


    /**
     * Sets the originalManipulationId value for this Manipulation.
     * 
     * @param originalManipulationId
     */
    public void setOriginalManipulationId(java.lang.String originalManipulationId) {
        this.originalManipulationId = originalManipulationId;
    }


    /**
     * Gets the saveLink value for this Manipulation.
     * 
     * @return saveLink
     */
    public java.lang.Boolean getSaveLink() {
        return saveLink;
    }


    /**
     * Sets the saveLink value for this Manipulation.
     * 
     * @param saveLink
     */
    public void setSaveLink(java.lang.Boolean saveLink) {
        this.saveLink = saveLink;
    }


    /**
     * Gets the totalTimesteps value for this Manipulation.
     * 
     * @return totalTimesteps
     */
    public java.lang.Integer getTotalTimesteps() {
        return totalTimesteps;
    }


    /**
     * Sets the totalTimesteps value for this Manipulation.
     * 
     * @param totalTimesteps
     */
    public void setTotalTimesteps(java.lang.Integer totalTimesteps) {
        this.totalTimesteps = totalTimesteps;
    }


    /**
     * Gets the type value for this Manipulation.
     * 
     * @return type
     */
    public java.lang.Integer getType() {
        return type;
    }


    /**
     * Sets the type value for this Manipulation.
     * 
     * @param type
     */
    public void setType(java.lang.Integer type) {
        this.type = type;
    }


    /**
     * Gets the curTimestep value for this Manipulation.
     * 
     * @return curTimestep
     */
    public java.lang.Integer getCurTimestep() {
        return curTimestep;
    }


    /**
     * Sets the curTimestep value for this Manipulation.
     * 
     * @param curTimestep
     */
    public void setCurTimestep(java.lang.Integer curTimestep) {
        this.curTimestep = curTimestep;
    }


    /**
     * Gets the description value for this Manipulation.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Manipulation.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Manipulation)) return false;
        Manipulation other = (Manipulation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.beginigTimestep==null && other.getBeginigTimestep()==null) || 
             (this.beginigTimestep!=null &&
              this.beginigTimestep.equals(other.getBeginigTimestep()))) &&
            ((this.biomassUnit==null && other.getBiomassUnit()==null) || 
             (this.biomassUnit!=null &&
              this.biomassUnit.equals(other.getBiomassUnit()))) &&
            ((this.derived==null && other.getDerived()==null) || 
             (this.derived!=null &&
              this.derived.equals(other.getDerived()))) &&
            ((this.energyFlowLink==null && other.getEnergyFlowLink()==null) || 
             (this.energyFlowLink!=null &&
              java.util.Arrays.equals(this.energyFlowLink, other.getEnergyFlowLink()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.initialBiomass==null && other.getInitialBiomass()==null) || 
             (this.initialBiomass!=null &&
              this.initialBiomass.equals(other.getInitialBiomass()))) &&
            ((this.manipulationBiomassNode==null && other.getManipulationBiomassNode()==null) || 
             (this.manipulationBiomassNode!=null &&
              java.util.Arrays.equals(this.manipulationBiomassNode, other.getManipulationBiomassNode()))) &&
            ((this.manipulationModel==null && other.getManipulationModel()==null) || 
             (this.manipulationModel!=null &&
              java.util.Arrays.equals(this.manipulationModel, other.getManipulationModel()))) &&
            ((this.manipulationNetwork==null && other.getManipulationNetwork()==null) || 
             (this.manipulationNetwork!=null &&
              java.util.Arrays.equals(this.manipulationNetwork, other.getManipulationNetwork()))) &&
            ((this.manipulationParameter==null && other.getManipulationParameter()==null) || 
             (this.manipulationParameter!=null &&
              java.util.Arrays.equals(this.manipulationParameter, other.getManipulationParameter()))) &&
            ((this.manipulationRequest==null && other.getManipulationRequest()==null) || 
             (this.manipulationRequest!=null &&
              this.manipulationRequest.equals(other.getManipulationRequest()))) &&
            ((this.manipulationRequestId==null && other.getManipulationRequestId()==null) || 
             (this.manipulationRequestId!=null &&
              this.manipulationRequestId.equals(other.getManipulationRequestId()))) &&
            ((this.manipulationRequestReference==null && other.getManipulationRequestReference()==null) || 
             (this.manipulationRequestReference!=null &&
              this.manipulationRequestReference.equals(other.getManipulationRequestReference()))) &&
            ((this.manipulationTimesteps==null && other.getManipulationTimesteps()==null) || 
             (this.manipulationTimesteps!=null &&
              java.util.Arrays.equals(this.manipulationTimesteps, other.getManipulationTimesteps()))) &&
            ((this.modelType==null && other.getModelType()==null) || 
             (this.modelType!=null &&
              this.modelType.equals(other.getModelType()))) &&
            ((this.networkId==null && other.getNetworkId()==null) || 
             (this.networkId!=null &&
              this.networkId.equals(other.getNetworkId()))) &&
            ((this.originalManipulationId==null && other.getOriginalManipulationId()==null) || 
             (this.originalManipulationId!=null &&
              this.originalManipulationId.equals(other.getOriginalManipulationId()))) &&
            ((this.saveLink==null && other.getSaveLink()==null) || 
             (this.saveLink!=null &&
              this.saveLink.equals(other.getSaveLink()))) &&
            ((this.totalTimesteps==null && other.getTotalTimesteps()==null) || 
             (this.totalTimesteps!=null &&
              this.totalTimesteps.equals(other.getTotalTimesteps()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.curTimestep==null && other.getCurTimestep()==null) || 
             (this.curTimestep!=null &&
              this.curTimestep.equals(other.getCurTimestep()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription())));
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
        if (getBeginigTimestep() != null) {
            _hashCode += getBeginigTimestep().hashCode();
        }
        if (getBiomassUnit() != null) {
            _hashCode += getBiomassUnit().hashCode();
        }
        if (getDerived() != null) {
            _hashCode += getDerived().hashCode();
        }
        if (getEnergyFlowLink() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEnergyFlowLink());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEnergyFlowLink(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getInitialBiomass() != null) {
            _hashCode += getInitialBiomass().hashCode();
        }
        if (getManipulationBiomassNode() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationBiomassNode());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManipulationBiomassNode(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getManipulationModel() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationModel());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManipulationModel(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getManipulationNetwork() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationNetwork());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManipulationNetwork(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getManipulationParameter() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationParameter());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManipulationParameter(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getManipulationRequest() != null) {
            _hashCode += getManipulationRequest().hashCode();
        }
        if (getManipulationRequestId() != null) {
            _hashCode += getManipulationRequestId().hashCode();
        }
        if (getManipulationRequestReference() != null) {
            _hashCode += getManipulationRequestReference().hashCode();
        }
        if (getManipulationTimesteps() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManipulationTimesteps());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManipulationTimesteps(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getModelType() != null) {
            _hashCode += getModelType().hashCode();
        }
        if (getNetworkId() != null) {
            _hashCode += getNetworkId().hashCode();
        }
        if (getOriginalManipulationId() != null) {
            _hashCode += getOriginalManipulationId().hashCode();
        }
        if (getSaveLink() != null) {
            _hashCode += getSaveLink().hashCode();
        }
        if (getTotalTimesteps() != null) {
            _hashCode += getTotalTimesteps().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getCurTimestep() != null) {
            _hashCode += getCurTimestep().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Manipulation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Manipulation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginigTimestep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "BeginigTimestep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("biomassUnit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "BiomassUnit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("derived");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Derived"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("energyFlowLink");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "EnergyFlowLink"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "EnergyFlowLink"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "EnergyFlowLink"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("initialBiomass");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "InitialBiomass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationBiomassNode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationBiomassNode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationBiomassNode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationBiomassNode"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationModel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationModel"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationModel"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationNetwork");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationNetwork"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationNetwork"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationNetwork"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationParameter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationParameter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationParameter"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationParameter"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationRequest"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationRequestId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationRequestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationRequestReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationRequestReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfManipulationRequesthovVakal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manipulationTimesteps");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationTimesteps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationTimesteps"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ManipulationTimesteps"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "ModelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "NetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalManipulationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "OriginalManipulationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saveLink");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "SaveLink"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalTimesteps");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "TotalTimesteps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curTimestep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "curTimestep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "description"));
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
