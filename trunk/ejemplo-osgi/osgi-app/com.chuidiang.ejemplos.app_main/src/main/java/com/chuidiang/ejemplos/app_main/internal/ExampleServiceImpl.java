package com.chuidiang.ejemplos.app_main.internal;

import java.util.LinkedList;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import com.chuidiang.ejemplos.app_main.ApplicationMain;
import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

/**
 * Internal implementation of our example OSGi service
 */
public final class ExampleServiceImpl implements ApplicationMain, PluginInstallation {

	private List<PluginInterface> plugins = new LinkedList<>();
	private MainWindow mainWindow = null;
	private BundleContext bc;

	public void start(BundleContext bc) {
		this.bc = bc;
		System.out.println("app-main starting");
		mainWindow = new MainWindow(this);
		synchronized (plugins) {

			for (PluginInterface plugin : plugins) {
				mainWindow.addPlugin(plugin);
			}
			plugins.clear();
		}
	}

	public void exit(){
	   try {
         bc.getBundle(0).stop();
      } catch (BundleException e) {
         System.err.println("Framework can't be stopped"+e);
         
      }
	}
	public void stop() {
		System.out.println("app-main stoping");
		mainWindow.exit();
	}

	@Override
	public void addPlugin(PluginInterface plugin) {
		System.out.println("Plugin added");
		if (null == mainWindow) {
			synchronized (plugins) {
				System.out.println("plugin added to list");
				plugins.add(plugin);
			}

		} else {
			System.out.println("pluggin added to mainwindow");
			mainWindow.addPlugin(plugin);
		}

	}

	@Override
	public void removePlugin(PluginInterface plugin) {
		System.out.println("Plugin removed");
		if (null == mainWindow) {
			synchronized (plugins) {
				plugins.add(plugin);
			}
		} else {
			mainWindow.removePlugin(plugin);
		}

	}

	@Override
	public boolean installPlugin(String path) {
		try {
			Bundle bundle = bc.installBundle(path);
			if (null!=bundle){
			   bundle.start();
			   return true;
			}
		} catch (Exception e){
			System.err.println(e);
		}
		return false;
	}

}
