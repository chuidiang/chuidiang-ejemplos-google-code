package org.chuidiang.chuwiki.worldwind;

import java.net.URI;
import java.util.List;
import java.util.Set;

import gov.nasa.worldwind.Factory;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.ogc.wms.WMSCapabilities;
import gov.nasa.worldwind.ogc.wms.WMSLayerCapabilities;
import gov.nasa.worldwind.ogc.wms.WMSLayerStyle;
import gov.nasa.worldwind.wms.WMSTiledImageLayer;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import gov.nasa.worldwindx.examples.LayerPanel;

public class CapaWMS {
   private WMSTiledImageLayer capa;

   public CapaWMS() {

   }

   public void anadeUrl(String url, WorldWindowGLCanvas wwd,
         LayerPanel panelCapas) {
      try {
         WMSCapabilities capabilities = WMSCapabilities.retrieve(new URI(url));
         capabilities.parse();
         List<WMSLayerCapabilities> listaCapacidadesCapa = capabilities
               .getNamedLayers();
         for (WMSLayerCapabilities capacidadesCapa : listaCapacidadesCapa) {

            AVListImpl params = new AVListImpl();
            params.setValue(AVKey.LAYER_NAMES, capacidadesCapa.getName());
            Set<WMSLayerStyle> estilos = capacidadesCapa.getStyles();

            // Codigo si queremos aplicar un estilo concreto en vez de el estilo
            // por defecto.
            // if (null != estilos){
            // if (style != null)
            // linfo.params.setValue(AVKey.STYLE_NAMES, style.getName());
            // }

            // Some wms servers are slow, so increase the timeouts and limits
            // used by world wind's retrievers.
            params.setValue(AVKey.URL_CONNECT_TIMEOUT, 30000);
            params.setValue(AVKey.URL_READ_TIMEOUT, 30000);
            params.setValue(AVKey.RETRIEVAL_QUEUE_STALE_REQUEST_LIMIT, 60000);

            String factoryKey = getFactoryKeyForCapabilities(capabilities);
            Factory factory = (Factory) WorldWind
                  .createConfigurationComponent(factoryKey);
            Object laCapa = factory
                  .createFromConfigSource(capabilities, params);
            
            if (laCapa instanceof Layer) {
               ((Layer) laCapa).setEnabled(false);
               ApplicationTemplate.insertBeforeCompass(wwd, (Layer) laCapa);
               panelCapas.update(wwd);
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      wwd.redraw();
   }

   protected String getFactoryKeyForCapabilities(WMSCapabilities caps) {
      boolean hasApplicationBilFormat = false;

      Set<String> formats = caps.getImageFormats();
      for (String s : formats) {
         if (s.contains("application/bil")) {
            hasApplicationBilFormat = true;
            break;
         }
      }

      return hasApplicationBilFormat ? AVKey.ELEVATION_MODEL_FACTORY
            : AVKey.LAYER_FACTORY;
   }

}
