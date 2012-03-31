package com.chuidiang.ejemplos.cxf;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.EndpointImpl;

public class Main {
   public static void main(String[] args) {
      Endpoint endpointCalculadora = EndpointImpl.create(new CalculadoraImpl());
      endpointCalculadora.publish("http://localhost:8080/Calculadora");

      Endpoint endpointHolaMundo = EndpointImpl.create(new HolaMundoImpl());
      endpointHolaMundo.publish("http://localhost:8080/HolaMundo");
   }
}
