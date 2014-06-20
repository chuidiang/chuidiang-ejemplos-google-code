package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.data.BufferedImageRaster;
import gov.nasa.worldwind.data.DataRaster;
import gov.nasa.worldwind.data.DataRasterReader;
import gov.nasa.worldwind.data.DataRasterReaderFactory;
import gov.nasa.worldwind.data.GDALDataRaster;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.SurfaceImageLayer;
import gov.nasa.worldwind.render.SurfaceImage;
import gov.nasa.worldwindx.examples.util.ExampleUtil;

import java.awt.image.BufferedImage;

public class FicheroTiff {
	public static Layer leeFichero(String sourceFile) {
		try {
//			AVListImpl params = new AVListImpl();
//			DataRasterReaderFactory readerFactory = (DataRasterReaderFactory) WorldWind
//					.createConfigurationComponent(AVKey.DATA_RASTER_READER_FACTORY_CLASS_NAME);
//			DataRasterReader reader = readerFactory.findReaderFor(sourceFile,
//					params);
//
//			// Before reading the raster, verify that the file contains imagery.
//			AVList metadata = reader.readMetadata(sourceFile, params);
//			if (metadata == null
//					|| !AVKey.IMAGE.equals(metadata
//							.getStringValue(AVKey.PIXEL_FORMAT)))
//				throw new Exception("Not an image file.");
//
//			// Read the file into the raster. read() returns potentially several
//			// rasters if there are multiple
//			// files, but in this case there is only one so just use the first
//			// element of the returned array.
//			DataRaster[] rasters = reader.read(sourceFile, null);
//			if (rasters == null || rasters.length == 0)
//				throw new Exception("Can't read the image file.");
//
//			DataRaster raster = rasters[0];
//
//			if (raster.getSector() == null && params.hasKey(AVKey.SECTOR))
//            {
//                Object o = params.getValue(AVKey.SECTOR);
//                if (null != o && o instanceof Sector)
//                {
//                    Sector sector = (Sector) o;
//
//                    if (raster instanceof GDALDataRaster)
//                        ((GDALDataRaster) raster).setSector(sector);
//                    else
//                        raster.setValue(AVKey.SECTOR, sector);
//                }
//            }
//			final Sector sector = (Sector) raster.getValue(AVKey.SECTOR);
//			if (sector == null)
//				throw new Exception("No location specified with image.");

			final SurfaceImage si1 = new SurfaceImage();

			// On the event-dispatch thread, add the imported data as an
			// SurfaceImageLayer.
			// Add the SurfaceImage to a layer.
			SurfaceImageLayer layer = new SurfaceImageLayer();
			layer.setName("Ficheros Tiff");
			layer.addImage(sourceFile);
			
			return layer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
