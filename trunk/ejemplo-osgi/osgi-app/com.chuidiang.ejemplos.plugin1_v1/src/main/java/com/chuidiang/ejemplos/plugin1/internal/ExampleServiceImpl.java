package com.chuidiang.ejemplos.plugin1.internal;

import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

/**
 * Internal implementation of our example OSGi service
 */
public final class ExampleServiceImpl implements PluginInterface {

   public void start() {
      System.out.println("plugin1 starting");
   }

   public void stop() {
      System.out.println("plugin1 stoping");
   }

   @Override
   public String getName() {
      // TODO Auto-generated method stub
      return "I'm plugin1, version 1";
   }
}
