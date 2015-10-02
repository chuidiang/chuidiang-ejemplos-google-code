package com.chuidiang.ejemplos.jasper;

public class TheData {
   public TheData() {
   }
   public TheData(int aNumber, String aString) {
      this.aNumber = aNumber;
      this.aString = aString;
   }
   private int aNumber;
   public int getaNumber() {
      return aNumber;
   }
   public void setaNumber(int aNumber) {
      this.aNumber = aNumber;
   }
   public String getaString() {
      return aString;
   }
   public void setaString(String aString) {
      this.aString = aString;
   }
   private String aString;
}
