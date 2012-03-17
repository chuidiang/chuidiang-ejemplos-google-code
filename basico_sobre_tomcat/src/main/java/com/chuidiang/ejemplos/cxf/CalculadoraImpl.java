package com.chuidiang.ejemplos.cxf;

import javax.jws.WebService;

@WebService(endpointInterface = "com.chuidiang.ejemplos.cxf.Calculadora")
public class CalculadoraImpl implements Calculadora {

   public double suma(double a, double b) {
      return a + b;
   }

   public double resta(double a, double b) {
      return a - b;
   }
}
