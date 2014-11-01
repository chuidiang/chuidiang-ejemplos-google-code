package com.chuidiang.ejemplos.app_main;

import java.util.Map;

import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

/**
 * Public API representing an example OSGi service
 */
public interface ApplicationMain
{
   void addPlugin(PluginInterface plugin, Map properties);
   void removePlugin(PluginInterface plugin);
}

