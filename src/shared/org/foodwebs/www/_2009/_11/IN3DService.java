/**
 * IN3DService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package shared.org.foodwebs.www._2009._11;

public interface IN3DService extends java.rmi.Remote {
    public shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeRequest(shared.org.datacontract.schemas._2004._07.WCFService_Portal.Request request) throws java.rmi.RemoteException;
    public shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeManipulationRequest(shared.org.datacontract.schemas._2004._07.WCFService_Portal.SimpleManipulationRequest request) throws java.rmi.RemoteException;
    public shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeNetworkCreationRequest(shared.org.datacontract.schemas._2004._07.WCFService_Portal.NetworkCreationRequest request) throws java.rmi.RemoteException;
}
