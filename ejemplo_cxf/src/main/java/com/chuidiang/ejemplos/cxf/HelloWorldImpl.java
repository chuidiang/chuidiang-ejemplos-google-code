
package com.chuidiang.ejemplos.cxf;

import javax.jws.WebService;

@WebService(endpointInterface = "com.chuidiang.ejemplos.cxf.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

