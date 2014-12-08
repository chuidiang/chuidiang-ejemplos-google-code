package com.chuidiang.ejemplos.impl1;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator {
	@Override
	public void start(BundleContext bc){
		System.out.println("Activator Starting ...");
	}
	
	@Override
	public void stop(BundleContext bc) {
		System.out.println("Activator Stopping ...");
	}
}
