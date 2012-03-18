package com.chuidiang.ejemplos.cxf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.ws.addressing.AddressingBuilder;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.apache.cxf.ws.addressing.MAPAggregator;
import org.apache.cxf.ws.addressing.ObjectFactory;
import org.apache.cxf.ws.addressing.WSAddressingFeature;
import org.apache.cxf.ws.addressing.soap.MAPCodec;

public class ClienteCalculadora {

   public static void main(String[] args) throws MalformedURLException {

      Calculadora calculadora = creaClienteWebService();

      Client client = ClientProxy.getClient(calculadora);
      Endpoint endPoint = client.getEndpoint();
      endPoint.getActiveFeatures().add(new WSAddressingFeature());

      ponWSAddresing(calculadora);

      ponSOAPenLog(calculadora);

      try {
         Map<String, Object> requestContext = ((BindingProvider) calculadora)
               .getRequestContext();
         requestContext.put(JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES,
               getParametricaWSAddressing());

         Holder<Double> holder = new Holder<Double>();
         holder.value = 11.2;
         calculadora.suma(holder, 33.4);
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

   private static void ponWSAddresing(Calculadora calculadora) {
      Client client = ClientProxy.getClient(calculadora);
      client.getBus().getFeatures().add(new WSAddressingFeature());
      client.getOutInterceptors().add(new MAPAggregator());
      client.getOutInterceptors().add(new MAPCodec());
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

   private static AddressingProperties getParametricaWSAddressing() {
      // get Message Addressing Properties instance
      AddressingBuilder builder = AddressingBuilder.getAddressingBuilder();
      AddressingProperties maps = builder.newAddressingProperties();

      // set MessageID property
      AttributedURIType messageID = WSA_OBJECT_FACTORY
            .createAttributedURIType();
      messageID.setValue("urn:uuid:" + System.currentTimeMillis());
      maps.setMessageID(messageID);

      AttributedURIType replyTo = WSA_OBJECT_FACTORY.createAttributedURIType();
      replyTo.setValue("http://localhost:8080/ejemplo_cxf/Resultado");
      EndpointReferenceType eprt = WSA_OBJECT_FACTORY
            .createEndpointReferenceType();
      eprt.setAddress(replyTo);
      maps.setReplyTo(eprt);

      return maps;
   }

   private static final ObjectFactory WSA_OBJECT_FACTORY = new ObjectFactory();

}
