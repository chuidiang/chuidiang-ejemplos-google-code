package com.chuidiang.ejemplos.cxf;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    String sayHi(String text);
}

