package com.chuidiang.ejemplos.cxf;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class ClienteCalculadora {
   public static void main(String[] args) throws MalformedURLException {
      URL wsdlURL = new URL(
            "http://localhost:8080/ejemplo_cxf/Calculadora?wsdl");
      QName SERVICE_NAME = new QName("http://cxf.ejemplos.chuidiang.com/",
            "CalculadoraImplService");
      Service service = Service.create(wsdlURL, SERVICE_NAME);
      Calculadora client = service.getPort(Calculadora.class);
      System.out.println(client.suma(11.2, 33.4));
      System.out.println(client.resta(11.2, 33.4));
   }
}
