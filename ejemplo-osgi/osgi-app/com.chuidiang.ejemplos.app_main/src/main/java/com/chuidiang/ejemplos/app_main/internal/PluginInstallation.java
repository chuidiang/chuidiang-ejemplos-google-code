package com.chuidiang.ejemplos.app_main.internal;

public interface PluginInstallation {
   long installPlugin(String path);

   void uninstall(long bundleId);
}
