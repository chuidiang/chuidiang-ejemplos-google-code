package com.chuidiang.ejemplos.impl1;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.chuidiang.ejemplos.api.ThePublicInterface;

public class TheInterfaceImplementation implements ThePublicInterface{

	@Override
	public void someMethod() {
		System.out.println("Doing some method");
		Logger.getLogger(TheInterfaceImplementation.class).info("Reported by log4j");
	}

	public void start() {
	   BasicConfigurator.configure();
	   someMethod();
	}
	
	public void stop() {
	   System.out.println("stopping...");
	}
}
