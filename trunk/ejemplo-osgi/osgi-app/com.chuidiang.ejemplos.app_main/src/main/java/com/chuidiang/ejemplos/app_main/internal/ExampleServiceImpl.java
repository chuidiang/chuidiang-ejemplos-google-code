package com.chuidiang.ejemplos.app_main.internal;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import com.chuidiang.ejemplos.app_main.ApplicationMain;
import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

/**
 * Internal implementation of our example OSGi service
 */
public final class ExampleServiceImpl implements ApplicationMain, PluginInstallation {

	private List<BundleAndMap> plugins = new LinkedList<>();
	private MainWindow mainWindow = null;
	private BundleContext bc;

	public void start(BundleContext bc, Map properties) {
		this.bc = bc;
		System.out.println("app-main starting "+properties);
		mainWindow = new MainWindow(this);
		synchronized (plugins) {

			for (BundleAndMap plugin : plugins) {
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
	public void addPlugin(PluginInterface plugin, Map properties) {
		System.out.println("Plugin added");
		if (null == mainWindow) {
			synchronized (plugins) {
				System.out.println("plugin added to list");
				plugins.add(new BundleAndMap(plugin, properties));
			}

		} else {
			System.out.println("pluggin added to mainwindow");
			mainWindow.addPlugin(new BundleAndMap(plugin, properties));
		}

	}

	@Override
	public void removePlugin(PluginInterface plugin, Map properties) {
		System.out.println("Plugin removed");
		if (null == mainWindow) {
			synchronized (plugins) {
				plugins.add(new BundleAndMap(plugin, properties));
			}
		} else {
			mainWindow.removePlugin(plugin);
		}

	}

	@Override
	public long installPlugin(String path) {
		try {
			Bundle bundle = bc.installBundle(path);
			if (null!=bundle){
			   bundle.start();
			   return bundle.getBundleId();
			   
			}
		} catch (Exception e){
			System.err.println(e);
		}
		return -1;
	}

   @Override
   public void uninstall(long bundleId) {
      try {
//         bc.getBundle(bundleId).stop();
         bc.getBundle(bundleId).uninstall();
      } catch (BundleException e) {
         System.err.println("Bundle can't be uninstalled "+e.getMessage());
      }
      
   }

}
