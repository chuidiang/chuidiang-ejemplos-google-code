package com.chuidiang.organizador_fotos;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

public class RenamePhotos {

   public static void main(String[] args) {
      RenamePhotos rename = new RenamePhotos();
      String path = "D:/JAVIER/Google Drive/FOTOS_FECHA/2003/10/31";
      String text = "Maria y Nuria 2003";
      rename.rename(path, text);

   }

   private void rename(String path, String text) {
      File directory = new File(path);
      if (!directory.canRead()){
         System.err.println("Can't read "+path);
         return;
      }
      
      if (directory.isDirectory()){
         File [] files = directory.listFiles();
         for (File file : files){
            renameFile(file.getAbsolutePath(), text);
         }
      } else {
         renameFile(path,text);
      }
      
   }

   private void renameFile(String file, String text) {
      File photo = new File(file);
      if (!photo.isFile()){
         return;
      }
      if (!photo.canRead()){
         return;
      }
      
      String name = photo.getName();
      String [] tokens = name.split("\\.");
      String newName = tokens[0]+"-"+text+"."+tokens[1];
      
      
      try {
//         FileUtils.moveFile(photo, new File(photo.getParentFile(),newName));
         System.out.println(photo+ " -> "+ new File(photo.getParentFile(),newName));
         FileUtils.moveFile(photo, new File(photo.getParentFile(),newName));
      } catch (IOException e) {
         System.err.println("Error renaming file "+e.getMessage());
      }
   }

}
