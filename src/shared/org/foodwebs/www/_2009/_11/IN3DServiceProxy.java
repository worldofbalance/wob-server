package shared.org.foodwebs.www._2009._11;

public class IN3DServiceProxy implements shared.org.foodwebs.www._2009._11.IN3DService {
  private String _endpoint = null;
  private shared.org.foodwebs.www._2009._11.IN3DService iN3DService = null;
  
  public IN3DServiceProxy() {
    _initIN3DServiceProxy();
  }
  
  public IN3DServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIN3DServiceProxy();
  }
  
  private void _initIN3DServiceProxy() {
    try {
      iN3DService = (new shared.org.tempuri.N3DWebServiceLocator()).getBasicHttpBinding_IN3DService();
      if (iN3DService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iN3DService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iN3DService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iN3DService != null)
      ((javax.xml.rpc.Stub)iN3DService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public shared.org.foodwebs.www._2009._11.IN3DService getIN3DService() {
    if (iN3DService == null)
      _initIN3DServiceProxy();
    return iN3DService;
  }
  
  public shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeRequest(shared.org.datacontract.schemas._2004._07.WCFService_Portal.Request request) throws java.rmi.RemoteException{
    if (iN3DService == null)
      _initIN3DServiceProxy();
    return iN3DService.executeRequest(request);
  }
  
  public shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeManipulationRequest(shared.org.datacontract.schemas._2004._07.WCFService_Portal.SimpleManipulationRequest request) throws java.rmi.RemoteException{
    if (iN3DService == null)
      _initIN3DServiceProxy();
    return iN3DService.executeManipulationRequest(request);
  }
  
  public shared.org.datacontract.schemas._2004._07.WCFService_Portal.Response executeNetworkCreationRequest(shared.org.datacontract.schemas._2004._07.WCFService_Portal.NetworkCreationRequest request) throws java.rmi.RemoteException{
    if (iN3DService == null)
      _initIN3DServiceProxy();
    return iN3DService.executeNetworkCreationRequest(request);
  }
  
  
}