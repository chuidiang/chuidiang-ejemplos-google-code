package com.chuidiang.ejemplos.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "HolaMundo")
public interface HolaMundo {
   @WebMethod(operationName = "saluda")
   public abstract String diHola(@WebParam(name = "tu") String nombre);
}