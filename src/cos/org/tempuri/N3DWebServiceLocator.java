/**
 * N3DWebServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.tempuri;

public class N3DWebServiceLocator extends org.apache.axis.client.Service implements cos.org.tempuri.N3DWebService {

    public N3DWebServiceLocator() {
    }


    public N3DWebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public N3DWebServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IN3DService
    private String BasicHttpBinding_IN3DService_address = "http://localhost:8000/N3DWebService/service?wsdl";

    public String getBasicHttpBinding_IN3DServiceAddress() {
        return BasicHttpBinding_IN3DService_address;
    }

    // The WSDD service name defaults to the port name.
    private String BasicHttpBinding_IN3DServiceWSDDServiceName = "BasicHttpBinding_IN3DService";

    public String getBasicHttpBinding_IN3DServiceWSDDServiceName() {
        return BasicHttpBinding_IN3DServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IN3DServiceWSDDServiceName(String name) {
        BasicHttpBinding_IN3DServiceWSDDServiceName = name;
    }

    public cos.org.foodwebs.www._2009._11.IN3DService getBasicHttpBinding_IN3DService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IN3DService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IN3DService(endpoint);
    }

    public cos.org.foodwebs.www._2009._11.IN3DService getBasicHttpBinding_IN3DService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cos.org.tempuri.BasicHttpBinding_IN3DServiceStub _stub = new cos.org.tempuri.BasicHttpBinding_IN3DServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IN3DServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IN3DServiceEndpointAddress(String address) {
        BasicHttpBinding_IN3DService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cos.org.foodwebs.www._2009._11.IN3DService.class.isAssignableFrom(serviceEndpointInterface)) {
                cos.org.tempuri.BasicHttpBinding_IN3DServiceStub _stub = new cos.org.tempuri.BasicHttpBinding_IN3DServiceStub(new java.net.URL(BasicHttpBinding_IN3DService_address), this);
                _stub.setPortName(getBasicHttpBinding_IN3DServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("BasicHttpBinding_IN3DService".equals(inputPortName)) {
            return getBasicHttpBinding_IN3DService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "N3DWebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IN3DService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("BasicHttpBinding_IN3DService".equals(portName)) {
            setBasicHttpBinding_IN3DServiceEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
