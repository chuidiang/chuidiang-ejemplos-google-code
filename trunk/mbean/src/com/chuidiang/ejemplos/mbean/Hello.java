package com.chuidiang.ejemplos.mbean;

/**
 * A mbean implementation. It must implement a mbean interface 
 * ( A interface which name ends with "MBean" ). The class must 
 * have the same name as the interface, but without the trailing
 * "MBean"
 * 
 * Something implements SomethingMBean
 * 
 * @author chuidiang
 *
 */
public class Hello implements HelloMBean {
	private int counter=0;
	@Override
	public int getCounter() {
		return counter;
	}

	@Override
	public void reset() {
		counter=0;
	}

	public void increment() {
		counter++;
	}
}
