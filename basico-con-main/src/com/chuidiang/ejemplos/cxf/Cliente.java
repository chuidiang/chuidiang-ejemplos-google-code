package com.chuidiang.ejemplos.cxf;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Cliente {
   public static void main(String[] args) throws MalformedURLException {
      creaYUtilizaClienteCalculadora();
      creaYUtilizaClienteHolaMundo();
   }

   private static void creaYUtilizaClienteCalculadora()
         throws MalformedURLException {
      URL urlWsdlCalculadora = new URL("http://localhost:8080/Calculadora?wsdl");
      QName nombreServicioCalculadora = new QName(
            "http://cxf.ejemplos.chuidiang.com/", "CalculadoraImplService");
      Service servicioCalculadora = Service.create(urlWsdlCalculadora,
            nombreServicioCalculadora);
      Calculadora calculadora = servicioCalculadora.getPort(Calculadora.class);
      System.out.println(calculadora.suma(11.0, 12.0));
   }

   private static void creaYUtilizaClienteHolaMundo()
         throws MalformedURLException {
      URL urlWsdlHolaMundo = new URL("http://localhost:8080/HolaMundo?wsdl");
      QName nombreServicioHolaMundo = new QName(
            "http://cxf.ejemplos.chuidiang.com/", "HolaMundo");
      Service servicioHolaMundo = Service.create(urlWsdlHolaMundo,
            nombreServicioHolaMundo);
      HolaMundo holaMundo = servicioHolaMundo.getPort(HolaMundo.class);
      System.out.println(holaMundo.diHola("chuidiang"));
   }

}
