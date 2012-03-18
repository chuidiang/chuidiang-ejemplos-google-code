package com.chuidiang.ejemplos.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Holder;

@WebService
public interface Calculadora {
   double suma(@WebParam(name = "sum1") Holder<Double> a,
         @WebParam(name = "sum2") double b);

   double resta(double a, double b);

}
