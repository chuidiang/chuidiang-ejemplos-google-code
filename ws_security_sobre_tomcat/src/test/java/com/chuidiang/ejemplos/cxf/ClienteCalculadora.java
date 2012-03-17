package com.chuidiang.ejemplos.cxf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;

public class ClienteCalculadora {
   public static void main(String[] args) throws MalformedURLException {
      Calculadora calculadora = creaClienteWebService();

      ponWSSecurity(calculadora);
      ponSOAPenLog(calculadora);

      try {
         System.out.println(calculadora.suma(11.2, 33.4));
         System.out.println(calculadora.resta(11.2, 33.4));
      } catch (SOAPFaultException e) {
         System.out.println("la has cagao " + e);
      }
   }

   private static Calculadora creaClienteWebService()
         throws MalformedURLException {
      URL wsdlURL = new URL(
            "http://localhost:8080/ejemplo_cxf/Calculadora?wsdl");
      QName SERVICE_NAME = new QName("http://cxf.ejemplos.chuidiang.com/",
            "CalculadoraImplService");
      Service service = Service.create(wsdlURL, SERVICE_NAME);
      Calculadora calculadora = service.getPort(Calculadora.class);
      return calculadora;
   }

   private static void ponSOAPenLog(Calculadora calculadora) {
      Client client = ClientProxy.getClient(calculadora);
      LoggingInInterceptor logIn = new LoggingInInterceptor();
      logIn.setPrettyLogging(true);
      client.getInInterceptors().add(logIn);
      LoggingOutInterceptor logOut = new LoggingOutInterceptor();
      logOut.setPrettyLogging(true);
      client.getOutInterceptors().add(logOut);
   }

   private static void ponWSSecurity(Calculadora calculadora) {
      Map<String, Object> outProps = new HashMap<String, Object>();
      outProps
            .put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
      // Specify our username
      outProps.put(WSHandlerConstants.USER, "joe");
      // Password type : plain text
      outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
      // for hashed password use:
      // properties.put(WSHandlerConstants.PASSWORD_TYPE,
      // WSConstants.PW_DIGEST);
      // Callback used to retrieve password for given user.
      outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
            ClientPasswordHandler.class.getName());

      Client client = ClientProxy.getClient(calculadora);
      Endpoint endPoint = client.getEndpoint();
      WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
      endPoint.getOutInterceptors().add(wssOut);
      endPoint.getOutInterceptors().add(new SAAJOutInterceptor());
   }
}
