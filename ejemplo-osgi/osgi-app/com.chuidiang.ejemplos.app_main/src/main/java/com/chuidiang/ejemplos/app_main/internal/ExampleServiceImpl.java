package com.chuidiang.ejemplos.app_main.internal;

import java.util.LinkedList;
import java.util.List;

import com.chuidiang.ejemplos.app_main.ApplicationMain;
import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

/**
 * Internal implementation of our example OSGi service
 */
public final class ExampleServiceImpl implements ApplicationMain {

   private List<PluginInterface> plugins = new LinkedList<>();
   private MainWindow mainWindow = null;

   public void start() {
      System.out.println("app-main starting");
      mainWindow = new MainWindow();
      synchronized (plugins) {

         for (PluginInterface plugin : plugins) {
            mainWindow.addPlugin(plugin);
         }
         plugins.clear();
      }
   }

   public void stop() {
      System.out.println("app-main stoping");
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
      if (null==mainWindow){
         synchronized (plugins) {
            plugins.add(plugin);
         } 
      } else {
         mainWindow.removePlugin(plugin);
      }

   }

}
