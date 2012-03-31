package com.chuidiang.ejemplos.cxf;

import javax.jws.WebService;

@WebService(serviceName = "HolaMundo")
public class HolaMundoImpl implements HolaMundo {
   @Override
   public String diHola(String nombre) {
      return "Hola " + nombre;
   }
}
