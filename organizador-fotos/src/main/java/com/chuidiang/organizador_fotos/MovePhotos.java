package com.chuidiang.organizador_fotos;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.drew.imaging.FileTypeDetector;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.file.FileMetadataDescriptor;
import com.drew.metadata.file.FileMetadataDirectory;
import com.drew.metadata.file.FileMetadataReader;

public class MovePhotos {
   static String targetDir = "D:/JAVIER/Google Drive/FOTOS_FECHA";

   public static void main(String[] args) {
      String sourceDir = "D:/JAVIER/Google Drive/FOTOS/2009";
//      "D:/JAVIER/Google Drive/FOTOS_FECHA/2/11/30";
      String[] extensions = { "jpg", "jpeg" };

      MovePhotos photoOrganizer = new MovePhotos();
      photoOrganizer.analyze(sourceDir, extensions);
   }

   private void analyze(String sourceDir, String[] extensions) {
      File source = new File(sourceDir);
      if (!source.exists()) {
         System.err.println(sourceDir + " can't be read");
         return;
      }

      if (source.isDirectory()) {
         String[] files = source.list();
         for (String file : files) {
            analyze(sourceDir + "/" + file, extensions);
         }
      }

      if (Arrays.asList(extensions).indexOf(
            FilenameUtils.getExtension(sourceDir).toLowerCase()) > -1) {
         try {
            // printMetadata(source);
            printDate(source);
         } catch (Exception e) {
            System.err.println(sourceDir + " exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
         }
      }
   }

   private void printMetadata(File source) throws ImageProcessingException,
         IOException {
      Metadata metadata = ImageMetadataReader.readMetadata(source);
      for (Directory directory : metadata.getDirectories()) {
         for (Tag tag : directory.getTags()) {
            System.out.println(tag);
         }
      }
   }

   private void printDate(File source) {

      Date date = null;

      try {
         date = getPhotoDate(source);
      } catch (Exception e) {
         // e.printStackTrace();
      }

//      if (null == date) {
//         try {
//            date = getFileDate(source);
//         } catch (Exception e) {
//            // e.printStackTrace();
//         }
//      }

      if (null != date) {
         copyPhoto(source, date);
      } else {
         System.err.println("No date for " + source);
      }
   }

   private Date getFileDate(File source) throws ImageProcessingException,
         IOException {
      Metadata metadata = ImageMetadataReader.readMetadata(source);
      Collection<FileMetadataDirectory> fileDirectories = metadata
            .getDirectoriesOfType(FileMetadataDirectory.class);
      if (null != fileDirectories) {
         for (Directory directory : fileDirectories) {
            Date date = directory
                  .getDate(FileMetadataDirectory.TAG_FILE_MODIFIED_DATE);
            if (null != date) {
               if (date.getTime() == 0L) {
                  date = null;
                  continue;
               }
               return date;
            }
         }
      }
      return null;
   }

   private Date getPhotoDate(File source) throws ImageProcessingException,
         IOException {
      Metadata metadata = ImageMetadataReader.readMetadata(source);
      Collection<ExifSubIFDDirectory> directories = metadata
            .getDirectoriesOfType(ExifSubIFDDirectory.class);
      
      Date date;
      if (null != directories) {
         for (Directory directory : directories) {
            date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
            if (null != date) {
               if (date.getTime() < 10L*365L*24L*60L*60L*1000L) {
                  date = null;
                  continue;
               }
               return date;
            }
         }
      }
      return null;
   }

   private void copyPhoto(File source, Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);

      String year = Integer.toString(calendar.get(Calendar.YEAR));
      String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
      if (month.length()==1){
         month = "0"+month;
      }
      String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
      if (day.length()==1){
         day = "0"+day;
      }

      File path = new File(targetDir + "/" + year + "/" + month + "/" + day
            + "/");

      if (source.getParentFile().equals(path)) {
         System.out.println("skipping " + source);
         return;
      }

      try {
         System.out.println("moving " + source + " to " + path);
         FileUtils.moveFileToDirectory(source, path, true);
      } catch (IOException e) {
         System.err.println("Error moving file " + source + " : "
               + e.getMessage());
         System.err.println(source.getParentFile());
         System.err.println(path);
      }

   }
}
