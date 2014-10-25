package com.chuidiang.ejemplos.app_main.internal;


import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

import com.chuidiang.ejemplos.app_main.ApplicationMain;

/**
 * Internal implementation of our example OSGi service
 */
public final class ExampleServiceImpl implements ApplicationMain {

   public void start() {
      System.out.println("app-main starting");
   }

   public void stop() {
      System.out.println("app-main stoping");
   }

   @Override
   public void addPlugin(PluginInterface plugin) {
      System.out.println("Plugin added");

   }

   @Override
   public void removePlugin(PluginInterface plugin) {
      System.out.println("Plugin removed");

   }

}
