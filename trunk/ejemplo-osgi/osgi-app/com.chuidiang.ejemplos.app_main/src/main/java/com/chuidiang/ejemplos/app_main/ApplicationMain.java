package com.chuidiang.ejemplos.app_main;

import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

/**
 * Public API representing an example OSGi service
 */
public interface ApplicationMain
{
   void addPlugin(PluginInterface plugin);
   void removePlugin(PluginInterface plugin);
}

