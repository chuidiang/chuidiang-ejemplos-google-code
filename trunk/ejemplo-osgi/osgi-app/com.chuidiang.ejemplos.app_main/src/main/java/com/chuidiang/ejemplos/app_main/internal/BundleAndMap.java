package com.chuidiang.ejemplos.app_main.internal;

import java.util.Map;

import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

public class BundleAndMap {
   public BundleAndMap(PluginInterface pluginInterface, Map pluginProperties){
      this.plugin = pluginInterface;
      this.pluginProperties = pluginProperties;
   }
   public PluginInterface plugin;
   public Map pluginProperties;
}
