package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.data.BufferedImageRaster;
import gov.nasa.worldwind.data.DataRaster;
import gov.nasa.worldwind.data.DataRasterReader;
import gov.nasa.worldwind.data.DataRasterReaderFactory;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.SurfaceImageLayer;
import gov.nasa.worldwind.render.SurfaceImage;
import gov.nasa.worldwindx.examples.util.ExampleUtil;

import java.awt.image.BufferedImage;

public class FicheroTiff {
	public static Layer leeFichero(String sourceFile) {
		try {
			DataRasterReaderFactory readerFactory = (DataRasterReaderFactory) WorldWind
					.createConfigurationComponent(AVKey.DATA_RASTER_READER_FACTORY_CLASS_NAME);
			DataRasterReader reader = readerFactory.findReaderFor(sourceFile,
					null);

			// Before reading the raster, verify that the file contains imagery.
			AVList metadata = reader.readMetadata(sourceFile, null);
			if (metadata == null
					|| !AVKey.IMAGE.equals(metadata
							.getStringValue(AVKey.PIXEL_FORMAT)))
				throw new Exception("Not an image file.");

			// Read the file into the raster. read() returns potentially several
			// rasters if there are multiple
			// files, but in this case there is only one so just use the first
			// element of the returned array.
			DataRaster[] rasters = reader.read(sourceFile, null);
			if (rasters == null || rasters.length == 0)
				throw new Exception("Can't read the image file.");

			DataRaster raster = rasters[0];

			// Determine the sector covered by the image. This information is in
			// the GeoTIFF file or auxiliary
			// files associated with the image file.
			final Sector sector = (Sector) raster.getValue(AVKey.SECTOR);
			if (sector == null)
				throw new Exception("No location specified with image.");

			// Request a sub-raster that contains the whole image. This step is
			// necessary because only sub-rasters
			// are reprojected (if necessary); primary rasters are not.
			int width = raster.getWidth();
			int height = raster.getHeight();

			// getSubRaster() returns a sub-raster of the size specified by
			// width and height for the area indicated
			// by a sector. The width, height and sector need not be the full
			// width, height and sector of the data,
			// but we use the full values of those here because we know the full
			// size isn't huge. If it were huge
			// it would be best to get only sub-regions as needed or install it
			// as a tiled image layer rather than
			// merely import it.
			DataRaster subRaster = raster.getSubRaster(width, height, sector,
					null);

			// Tne primary raster can be disposed now that we have a sub-raster.
			// Disposal won't affect the
			// sub-raster.
			raster.dispose();

			// Verify that the sub-raster can create a BufferedImage, then
			// create one.
			if (!(subRaster instanceof BufferedImageRaster))
				throw new Exception("Cannot get BufferedImage.");
			BufferedImage image = ((BufferedImageRaster) subRaster)
					.getBufferedImage();

			// The sub-raster can now be disposed. Disposal won't affect the
			// BufferedImage.
			subRaster.dispose();

			// Create a SurfaceImage to display the image over the specified
			// sector.
			final SurfaceImage si1 = new SurfaceImage(image, sector);

			// On the event-dispatch thread, add the imported data as an
			// SurfaceImageLayer.
			// Add the SurfaceImage to a layer.
			SurfaceImageLayer layer = new SurfaceImageLayer();
			layer.setName("Imported Surface Image");
			layer.setPickEnabled(false);
			layer.addRenderable(si1);
			
			return layer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
