package com.chuidiang.ejemplos.cxf;

import javax.jws.WebService;

@WebService
public interface Calculadora {

   double suma(double a, double b);

}