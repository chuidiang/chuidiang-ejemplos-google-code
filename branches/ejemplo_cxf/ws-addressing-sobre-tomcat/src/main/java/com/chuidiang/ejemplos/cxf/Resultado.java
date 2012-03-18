package com.chuidiang.ejemplos.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class Resultado {

   public void sumaResponse(@WebParam(name = "return") double sumaResponse,
         @WebParam(name = "sum1") double arg1) {
      System.out.println(sumaResponse);
      System.out.println(arg1);
   }
}
