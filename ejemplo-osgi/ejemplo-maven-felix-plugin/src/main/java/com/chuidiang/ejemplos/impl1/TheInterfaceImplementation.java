package com.chuidiang.ejemplos.impl1;

import com.chuidiang.ejemplos.api.ThePublicInterface;

public class TheInterfaceImplementation implements ThePublicInterface{

	@Override
	public void someMethod() {
		System.out.println("Doing some method");
	}

	public void start() {
	   someMethod();
	}
	
	public void stop() {
	   System.out.println("stopping...");
	}
}
