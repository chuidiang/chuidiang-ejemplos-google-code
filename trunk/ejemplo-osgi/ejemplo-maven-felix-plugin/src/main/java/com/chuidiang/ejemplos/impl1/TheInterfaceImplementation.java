package com.chuidiang.ejemplos.impl1;

import org.apache.felix.scr.annotations.Component;

import com.chuidiang.ejemplos.api.ThePublicInterface;

@Component
public class TheInterfaceImplementation implements ThePublicInterface {

	@Override
	public void someMethod() {
		System.out.println("Doing some method");
		
	}

}
