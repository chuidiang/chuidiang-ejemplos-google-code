package com.chuidiang.ejemplos.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Holder;

@WebService(endpointInterface = "com.chuidiang.ejemplos.cxf.Calculadora")
public class CalculadoraImpl implements Calculadora {

   public double suma(@WebParam(name = "sum1") Holder<Double> a,
         @WebParam(name = "sum2") double b) {
      a.value = a.value + 1;
      return a.value + b - 1;
   }

   public double resta(double a, double b) {
      return a - b;
   }

}
