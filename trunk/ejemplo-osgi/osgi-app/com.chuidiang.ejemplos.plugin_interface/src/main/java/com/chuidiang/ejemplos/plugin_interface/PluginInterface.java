package com.chuidiang.ejemplos.plugin_interface;

import java.awt.Component;

/**
 * Public API representing an example OSGi service
 */
public interface PluginInterface
{
   String getName();

   Component getComponent();
}

