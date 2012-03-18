package com.chuidiang.ejemplos.cxf;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.addressing.WSAddressingFeature;

public class ServerCalculadora {

   /**
    * @param args
    */
   public static void main(String[] args) {

      EndpointImpl endpoint = (EndpointImpl) EndpointImpl.create(

      new CalculadoraImpl());

      endpoint.getFeatures().add(new WSAddressingFeature());
      endpoint.publish("http://localhost:8080/ejemplo_cxf/Calculadora");

      LoggingInInterceptor logIn = new LoggingInInterceptor();
      logIn.setPrettyLogging(true);
      endpoint.getInInterceptors().add(logIn);
      LoggingOutInterceptor logOut = new LoggingOutInterceptor();
      logOut.setPrettyLogging(true);
      endpoint.getOutInterceptors().add(logOut);
   }

}